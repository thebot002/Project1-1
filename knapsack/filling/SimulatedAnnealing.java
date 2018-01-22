package knapsack.filling;

import knapsack.components.Parcel;
import knapsack.components.ParcelList;
import knapsack.components.Truck;

import java.util.ArrayList;

/*

List length problem: other get method to get actual index of array? Something else?

 */

public class SimulatedAnnealing {
    private static double temperature;
    private static double beta = 0.2; //cooling parameter
    private static double alpha = 0.002; //heating parameter

    private static Truck bestTruck;
    private static ArrayList<Parcel> list;
    private static int[][] sequence;
    private static int bestVolume;

    private static final double INITIAL_TEMPERATURE = 0.2;
    private static final int TOTAL_ROTATIONS = 6; //to be adapted if pentominoes

    private static long startTime;
    private static long timeToRun = 1200000; //60sec, to be adapted...

    public static Truck getTruck(){
        ParcelList pList = new ParcelList();

        pList.add(new Parcel("A"),20);
        pList.add(new Parcel("B"),20);
        pList.add(new Parcel("C"),20);

        list = pList.getFullArray();

        sequence = new int[4][pList.getTotalSize()];

        simulate();

        return bestTruck;
    }

    private static void simulate(){
        //variable initialization
        startTime = System.currentTimeMillis();
        temperature = INITIAL_TEMPERATURE;
        //initialize the sequence
        generate();

        //construct the truck
        bestTruck = new Truck();
        bestTruck = fill(sequence);

        //gets the volume to initialize bestVolume variable
        bestVolume = bestTruck.getParcelVolume();

        //annealing loop (separate method?)
        while(System.currentTimeMillis()-startTime < timeToRun){

            //creates neighbourhood
            ArrayList<int[][]> neighbourhood = generate(sequence); //place list here

            //tries to fill truck with random neighbour
            Truck newT = fill(neighbourhood.get((int)(Math.random()*neighbourhood.size())));
            int volume = newT.getParcelVolume();
            boolean better = false;

            //check if filling is better in new neighbour
            if(volume > bestVolume) better = true;
            else{
                temperature = temperature/(1+(beta*temperature));
                double delta = (bestVolume- volume)/bestVolume;
                double i = Math.random();

                if(i < Math.exp(-delta / temperature)) better = true; //the cooler it gets the smaller chance there is for this to be activated
                else{ //reheats the temperature
                    temperature = temperature/(1-(alpha*temperature));
                }
            }

            //saves configuration
            if(better){
                bestTruck = newT.copy();
                bestVolume = volume;
            }
        }
        System.out.println(bestVolume);
    }

    //generates a random sequence to fill the truck
    private static void generate(){
        //A, B & C sequence definition
        for(int i=0; i<sequence.length-1;i++){
            for(int j=0; j<sequence[0].length;j++){
                int parcel;
                boolean contains = false;
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

    private static ArrayList<int[][]> generate(int[][] s){
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

    private static int[][] switchBox(int[][] s, int index){
        int a = (int)(Math.random()*s[0].length);
        int b;
        do{
            b= (int)(Math.random()*s[0].length);
        }while(b==a);
        int[][] newS = new int[s.length][s[0].length];
        for(int i=0; i<s.length; i++){
            for(int j=0; j<s[0].length; j++){
                newS[i][j] = s[i][j];
            }
        }
        int temp = newS[index][a];
        newS[index][a] = newS[index][b];
        newS[index][b] = temp;
        return newS;
    }

    public static void setBeta(double beta) {
        SimulatedAnnealing.beta = beta;
    }

    public static void setAlpha(double alpha) {

        SimulatedAnnealing.alpha = alpha;
    }

    private static Truck fill(int[][] s){
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
