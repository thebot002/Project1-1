package knapsack.filling.genetic_algorithm;

import knapsack.components.Parcel;
import knapsack.components.Truck;

import java.util.ArrayList;

public class Population extends ArrayList<Individual>{
    private final int POP_SIZE = 100;
    private final int SURVIVOR_SIZE = 10;
    private final int CLONE_AMOUNT = 30;
    private final double POINT_CROSS_CHANCE = 0.3;
    private final double MUTATION_CHANCE = 0.4;

    private ArrayList<Parcel> parcelList;
    private ArrayList<Integer> amounts; //the amounts of each parcels from the parcelList

    private int currentGen = 0;

    /**
     * Best solution finding with a given list of parcels
     * @param parcelList
     */
    public Population(ArrayList<Parcel> parcelList){
        this.parcelList = parcelList;
        amounts = new ArrayList<>();
        int genomeLength = 0;
        for(Parcel p: parcelList){
            Truck t = new Truck();
            int maxAmount = (int)((1.0*t.getVolume())/p.getVolume());
            amounts.add(maxAmount);
            genomeLength += Integer.bitCount(maxAmount);
        }
        for(int i=0; i<POP_SIZE; i++){
            String genome = "";
            for(int j=0; j<genomeLength; j++){
                if(Math.random() < 0.5) genome += "0";
                else genome += "1";
            }

            while(!volumeCheck()){
                int point;
                do{
                    point = (int)(Math.random()*genome.length());
                }while(genome.charAt(point) != '1');
                genome = genome.substring(0,point) + "0" + genome.substring(point+1);
            }
            add(new Individual(genome));
        }
    }

    /**
     * Custom set of parcels.
     * @param parcelList
     * @param amounts
     */
    public Population(ArrayList<Parcel> parcelList, ArrayList<Integer> amounts){
        this.parcelList = parcelList;
        this.amounts = amounts;
        //for()
    }

    /**
     * Default population ArrayList constructor.
     */
    public Population(){}

    public void sort(){ //bubble sort
        for(int i=0; i<size(); i++){
            if(get(i).getFitness() < get(i+1).getFitness()){
                Individual temp = get(i+1);
                set(i+1, get(i));
                set(i,temp);
                i--;
            }
        }
    }

    public void nextGen(){
        currentGen++;
        sort();
        ArrayList<Individual> survivors = (ArrayList<Individual>)subList(0,SURVIVOR_SIZE);

        //keeps the best element
        set(0,survivors.get(0));

        //cloning to repopulate the CLONE_AMOUNT of the population
        for(int i=1; i<CLONE_AMOUNT; i++){
            set(i,survivors.get((int)(Math.random()*SURVIVOR_SIZE)));
        }

        //crossover to repopulate the rest of the population
        for(int i=CLONE_AMOUNT; i<size(); i++){
            int a = (int)(Math.random()*SURVIVOR_SIZE);
            int b;
            do {
                b = (int)(Math.random()*SURVIVOR_SIZE);
            }while(b==a);
            if(Math.random() < POINT_CROSS_CHANCE) set(i,pointCross(survivors.get(a),survivors.get(b)));
            else set(i,doubleCross(survivors.get(a),survivors.get(b)));
        }

        //mutates the resulting population
        for(Individual i:this){
            if(Math.random()<MUTATION_CHANCE) mutate(i);
        }
    }

    private Individual pointCross(Individual a, Individual b){
        String aGenome = a.getGenome();
        String bGenome = b.getGenome();
        int point = (int)(Math.random()*parcelList.size());
        String cGenome = aGenome.substring(0,point) + bGenome.substring(point);
        return new Individual(cGenome);
    }

    private Individual doubleCross(Individual a, Individual b){
        String aGenome = a.getGenome();
        String bGenome = b.getGenome();
        int point1 = (int)(Math.random()*parcelList.size());
        int point2;
        do{
            point2 = (int)(Math.random()*parcelList.size());
        } while(point2 == point1);
        String cGenome = aGenome.substring(0,point1) + bGenome.substring(point1,point2) + aGenome.substring(point2);
        return new Individual(cGenome);
    }

    private void mutate(Individual a){
        int point = (int)(Math.random()*parcelList.size());
        String genome = a.getGenome();
        if(genome.charAt(point) == '0') genome = genome.substring(0,point) + "1" + genome.substring(point+1);
        else genome = genome.substring(0,point) + "0" + genome.substring(point+1);
        a.setGenome(genome);
    }

    private boolean volumeCheck(){
        int volumeSum = 0;
        for(int i=0; i<parcelList.size(); i++){
            volumeSum += (parcelList.get(i).getVolume()*amounts.get(i));
        }
        if(volumeSum > new Truck().getVolume()) return false;
        return true;
    }

    public int getCurrentGen(){
        return currentGen;
    }
}
