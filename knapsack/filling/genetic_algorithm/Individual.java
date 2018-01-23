package knapsack.filling.genetic_algorithm;

import knapsack.components.Parcel;
import knapsack.components.Truck;

import java.util.ArrayList;

/**
 * Class defining a individual used in the population.
 * It has a genome defined by a binary string representing an parcel list and a fitness calculated by filling a truck with the parcel list.
 */
public class Individual {
    private ArrayList<Parcel> list;
    private String genome = "";
    private int fitness;

    //is this still used?
    /**
     * Constructor of an individual. Creates it on the basis of a given ArrayList.
     * @param list The list to create the genome from.
     */
    public Individual(ArrayList<Parcel> list){
        this.list = list;
        for(int i=0; i<list.size(); i++){
            if(Math.random()<0.5) genome += "0";
            else genome += "1";
        }
    }

    /**
     * Constructor creating an individual on the basis of a given genome.
     * @param genome The given genome.
     */
    public Individual(String genome){
        this.genome = genome;
    }

    public void evalFitness(){
        ArrayList<Parcel> parcelList = new ArrayList<>();
        for(int i=0; i<genome.length(); i++){
            if(genome.charAt(i) == '1')parcelList.add(list.get(i));
        }
        Truck t = new Truck();
        //fill(parcelList);
        //fitness = t.getValue();
    }
    public void setGenome(String genome){
        this.genome = genome;
    }
    public String getGenome(){
        return genome;
    }
    public int getFitness(){
        return fitness;
    }

    public String toString(){
        return genome;
    }
}
