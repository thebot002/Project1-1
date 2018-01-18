package knapsack.filling;

import knapsack.components.Parcel;
import knapsack.components.ParcelList;
import knapsack.components.Truck;

import java.util.ArrayList;

public class SimulatedAnnealing {
    private static double temperature;
    private static double beta = 0.9; //cooling parameter
    private static double alpha = 0.5; //heating parameter

    private static Truck truck;
    private static ParcelList list;
    private static int[][] sequence;
    private static int bestVolume;

    private static final double INITIAL_TEMPERATURE = 100.0;
    private static final int NEIGHBOURHOOD_SIZE = 20; // to be adapted...
    private static final int TOTAL_ROTATIONS = 6; //to be adapted if pentominoes

    private static long startTime;
    private static long timeToRun = 60000; //60sec, to be adapted...

    public static Truck getTruck(ParcelList list){
        list = list;
        return new Truck();
    }

    static{
        //variable initialization
        startTime = System.currentTimeMillis();
        temperature = INITIAL_TEMPERATURE;
        sequence = new int[4][list.size()];

        //to be modified
        //truck = generate();

        //annealing loop (separate method?)
        while(System.currentTimeMillis()-startTime < timeToRun){

            //creates neighbourhood
            ArrayList<int[][]> neighbourhood = new ArrayList<>();
            neighbourhood = generate(sequence); //place list here

            //tries to fill truck
            int[][] neighbour = neighbourhood.get((int)(Math.random()*neighbourhood.size()));
            int volume = 0; //neighbour.getVolume();
            boolean better = false;

            //check if filling is better in new neighbour
            if(volume > bestVolume) better = true;
            else{
                temperature = temperature/(1+(beta*temperature));
                double delta = (bestVolume- volume)/bestVolume;
                double i = Math.random();

                //logical error? check code...

                if(i < Math.exp(-delta / temperature)) better = true; //the cooler it gets the smaller chance there is for this to be activated
                else{ //reheats the temperature
                    temperature = temperature/(1-(alpha*temperature));
                }
            }

            //saves configuration
            if(better){
                //selected++; WHAT IS THE THATTTT?
                //truck = neighbour;
                //bestVolume = neighbour.getVolume();
            }
        }

    }

    public static void fill(Truck t){
    }

    //generates a random sequence to fill the truck
    private static void generate(){
        //A, B & C sequence definition
        for(int i=0; i<sequence.length-1;i++){
            for(int j=0; j<sequence[0].length;j++){
                int parcel;
                boolean contains = false;
                do{
                    parcel = (int)(Math.random()*sequence.length);
                    for(int k=0;k<sequence[i].length;k++) if(sequence[i][k] == parcel) contains = true;
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

    public static Truck getTruck(){
        return truck;
    }

    private Truck fill(int[][] s){
        Truck t = new Truck();
        for(int i=0; i<s.length; i++){

        }
        return t;
    }
}
