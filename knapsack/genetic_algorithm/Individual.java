package knapsack.genetic_algorithm;

import knapsack.components.Parcel;
import knapsack.components.Truck;

import java.util.ArrayList;

public class Individual {
    private ArrayList<Parcel> list;
    private String genome = "";
    private int fitness;

    public Individual(ArrayList<Parcel> list){
        this.list = list;
        for(int i=0; i<list.size(); i++){
            if(Math.random()<0.5) genome += "0";
            else genome += "1";
        }
    }

    public Individual(String genome){
        this.genome = genome;
    }

    public void evalFitness(){
        ArrayList<Parcel> parcelList = new ArrayList<>();
        for(int i=0; i<genome.length(); i++){
            if(genome.charAt(i) == '1')parcelList.add(list.get(i));
        }
        Truck t = new Truck();
        t.fill(parcelList);
        fitness = t.getValue();
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
