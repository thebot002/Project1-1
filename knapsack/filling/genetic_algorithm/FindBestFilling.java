package knapsack.filling.genetic_algorithm;

import knapsack.components.Parcel;

import java.util.ArrayList;

public class FindBestFilling {
    private final int MAX_VALUE_AMOUNT = 10;

    private int generationThreshold = 30;
    private ArrayList<Parcel> parcelList;
    private Population elite;

    public FindBestFilling(ArrayList<Parcel> parcelList){
        this.parcelList = parcelList;
        elite = new Population();
        do{
            elite.add(findBestIndividual());
            elite.sort();
        }while(elite.lastIndexOf(elite.get(0)) > MAX_VALUE_AMOUNT);
    }
    /*public FindBestFilling(ArrayList<Parcel> parcelList, ArrayList<Integer> amounts){

    }*/

    private Individual findBestIndividual(){
        Population pop = new Population(parcelList);
        do{
            pop.nextGen();
        }while(pop.getCurrentGen() < generationThreshold);
        pop.sort();
        return pop.get(0);
    }

    public Individual getBest(){
        return elite.get(0);
    }
}
