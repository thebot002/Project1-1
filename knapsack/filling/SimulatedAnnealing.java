package knapsack.filling;

import knapsack.components.Parcel;
import knapsack.components.ParcelList;
import knapsack.components.PentominoParcel;
import knapsack.components.Truck;

import java.util.ArrayList;

/**
 * Class used to fill a truck with a given set of items using simulated annealing technique.
 */
public class SimulatedAnnealing implements TruckFilling{
    private double beta = 0.2; //cooling parameter
    private double alpha = 0.002; //heating parameter

    private Truck bestTruck;
    private ArrayList<Parcel> list;
    private int[][] sequence;

    private static final double INITIAL_TEMPERATURE = 0.2;
    private static final int TOTAL_ROTATIONS = 6; //to be adapted if pentominoes

    private static long timeToRun = 60000; //1000 = 1sec

    private static boolean value = false; //true = searches for the best value; false = searches for the best volume

    /**
     * Default constructor of the class. The list is set to the maximum amount of each parcels to be used.
     */
    public SimulatedAnnealing() {
        ParcelList pList = new ParcelList();

        //pList.add(new Parcel("A"),14);
        //pList.add(new Parcel("B"),14);
        //pList.add(new Parcel("C"),14);

        pList.add(new PentominoParcel("L"),2);
        pList.add(new PentominoParcel("P"),2);
        pList.add(new PentominoParcel("T"),2);

        list = pList.getFullArray();

        sequence = new int[4][pList.getTotalSize()];
    }

    /**
     * Constructor of this class. The list that will be used to fill a truck.
     * @param pList
     */
    public  SimulatedAnnealing(ParcelList pList){
        list = pList.getFullArray();

        sequence = new int[4][pList.getTotalSize()];
    }

    @Override
    public void setParcelList(ParcelList list) {

    }

    /**
     * Method used to fill a truck and return it filled.
     * @return The filled truck.
     */
    @Override
    public Truck fillTruck() {
        simulate();

        return bestTruck;
    }

    /**
     * The simulated annealing loop. It runs for a given time and sets the best truck to the best result it found.
     */
    private void simulate(){
        //variable initialization
        long startTime = System.currentTimeMillis();
        double temperature = INITIAL_TEMPERATURE;
        //initialize the sequence
        generate();

        //construct the truck
        bestTruck = new Truck();
        bestTruck = fill(sequence);

        //gets the unit to initialize bestUnit variable. Unit: volume or value
        int bestUnit;
        if(value) bestUnit = bestTruck.getValue();
        else bestUnit = bestTruck.getParcelVolume();

        //annealing loop
        while(System.currentTimeMillis()- startTime < timeToRun && bestTruck.getGapAmount() > 0){

            //creates neighbourhood
            ArrayList<int[][]> neighbourhood = generate(sequence); //place list here

            //tries to fill truck with random neighbour
            Truck newT = fill(neighbourhood.get((int)(Math.random()*neighbourhood.size())));
            int unit;
            if(value) unit = newT.getValue();
            else unit = newT.getParcelVolume();
            boolean better = false;

            //check if filling is better in new neighbour
            if(unit > bestUnit) better = true;
            else{
                temperature = temperature /(1+(beta* temperature));
                double delta = (bestUnit - unit)/ bestUnit;
                double i = Math.random();

                if(i < Math.exp(-delta / temperature)) better = true; //the cooler it gets the smaller chance there is for this to be activated
                else{ //reheats the temperature
                    temperature = temperature /(1-(alpha* temperature));
                }
            }

            //saves configuration
            if(better){
                bestTruck = newT.copy();
                bestUnit = unit;
            }
        }
        System.out.println(bestUnit);
    }

    /**
     * Generates a random sequence triple to fill the truck. The sequence is used to define the placing order of the items to be placed in the truck.
     */
    private void generate(){
        //A, B & C sequence definition
        for(int i=0; i<sequence.length-1;i++){
            for(int j=0; j<sequence[0].length;j++){
                int parcel;
                boolean contains;
                do{
                    contains = false;
                    parcel = (int)(Math.random()*sequence[0].length);
                    for(int k=j-1;k>0;k--) if(sequence[i][k] == parcel) contains = true;
                }while(contains);
                sequence[i][j]=parcel;
            }
        }
        //rotation vector definition
        for(int i=0; i<sequence[0].length; i++){
            sequence[3][i] = (int)(Math.random()*TOTAL_ROTATIONS);
        }
    }

    /**
     * Method the generate the sequence triple neighbourhood of a given sequence.
     * @param s The given sequence triple to generate the neighbourhood from
     * @return An ArrayList of sequence triples defining the created neighbourhood.
     */
    private ArrayList<int[][]> generate(int[][] s){
        ArrayList<int[][]> sequenceList = new ArrayList<>();

        //one change
        sequenceList.add(switchBox(s,(int)(Math.random()*s.length)));

        //2 changes
        sequenceList.add(switchBox(switchBox(s,1),0));
        sequenceList.add(switchBox(switchBox(s,2),1));
        sequenceList.add(switchBox(switchBox(s,2),0));
        sequenceList.add(switchBox(switchBox(s,3),0));
        sequenceList.add(switchBox(switchBox(s,3),1));
        sequenceList.add(switchBox(switchBox(s,3),2));

        //3 changes
        sequenceList.add(switchBox(switchBox(switchBox(s,2),1),0));
        sequenceList.add(switchBox(switchBox(switchBox(s,3),2),1));
        sequenceList.add(switchBox(switchBox(switchBox(s,3),1),0));
        sequenceList.add(switchBox(switchBox(switchBox(s,3),2),0));

        //4changes
        sequenceList.add(switchBox(switchBox(switchBox(switchBox(s,3),2),1),0));
        return sequenceList;
    }

    /**
     * Method used to switch two items inside a sequence given sequence.
     * @param s The given sequence triple.
     * @param index The index of the sequence inside which two box are going to be permuted.
     *              (0 = sequence A; 1 = sequence B; 2 = sequence C; 3 = rotation vector)
     * @return The sequence triple with the permuted items.
     */
    private int[][] switchBox(int[][] s, int index){
        int a = (int)(Math.random()*s[0].length);
        int b;
        do{
            b= (int)(Math.random()*s[0].length);
        }while(b==a);
        int[][] newS = new int[s.length][s[0].length];
        for(int i=0; i<s.length; i++){
            System.arraycopy(s[i], 0, newS[i], 0, s[0].length);
        }
        int temp = newS[index][a];
        newS[index][a] = newS[index][b];
        newS[index][b] = temp;
        return newS;
    }

    /**
     * Method used to fill a truck with a sequence of parcels. Follows the sequence B.
     * @param s The sequence with which the truck has to be filled.
     * @return A filled truck.
     */
    private Truck fill(int[][] s){
        Truck t = new Truck();
        //item to place
        for(int i=0; i<s[0].length; i++){
            //only sequence B, see report for details
            Parcel toPlace = list.get(s[1][i]).copy();
            //rotation loop
            for(int j=0; j<s[3][s[1][i]];j++){
                if(j%3 == 0) toPlace.rotateAroundX();
                else if(j%3 == 1) toPlace.rotateAroundZ();
                else toPlace.rotateAroundY();
            }

            t.addParcel(toPlace);
        }
        return t;
    }
}
