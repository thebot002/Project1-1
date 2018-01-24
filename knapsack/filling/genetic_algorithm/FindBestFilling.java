package knapsack.filling.genetic_algorithm;

import knapsack.components.Parcel;
import knapsack.components.ParcelList;
import knapsack.components.Truck;

import java.util.ArrayList;

/**
 * Class used to find the best filling of a truck with the combination of a genetic algorithm and another algorithm such as backtracking, simulated annealing, or greedy.
 */
public class FindBestFilling{
    private final int MAX_VALUE_AMOUNT = 10;

    private int generationThreshold = 30;
    private ArrayList<Parcel> parcelList;
    private Population elite;

    private boolean value = false; //false = maximize volume; true = maximize value

    /*
To be worked on!!!! Adaptative mechanism!!!!!
 */
    /**
     * Constructor of the an object FindBestFilling which also makes the genetic algorithm loop start.
     * @param parcelList The list of items to fill a truck with.
     */
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

    /*
    Maybe add another stop condition: if best appears X amount of times => stop.
     */
    /**
     * Method to create a new population and creates new generation until the generation treshold.
     * @return
     */
    private Individual findBestIndividual(){
        Population pop = new Population(parcelList);
        do{
            pop.nextGen();
        }while(pop.getCurrentGen() < generationThreshold);
        pop.sort();
        return pop.get(0);
    }

    /**
     * Method to get the best individual in the elite set. Usable only after the genetic algorithm has run.
     * @return The best overall individual.
     */
    public Individual getBest(){
        return elite.get(0);
    }
}
