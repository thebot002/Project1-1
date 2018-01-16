package knapsack.greedy;

import java.util.ArrayList;

import knapsack.filling.FillTruck;
import knapsack.components.Parcel;
import knapsack.components.Truck;

public class Greedy {
    public static void main(String[] args) {
        ArrayList<Parcel> list = new ArrayList<>();
        list.add(new Parcel("A"));
        list.add(new Parcel("B"));
        list.add(new Parcel("C"));

        ArrayList<Integer> amounts = new ArrayList<>();

        amounts.add(10);
        amounts.add(10);
        amounts.add(10);

        ArrayList<Parcel> pList = new ArrayList<>();
        for(int i=0; i<list.size(); i++){
            for(int j=0; j<amounts.get(i); j++){
                pList.add(new Parcel(list.get(i).getID()));
            }
        }

        Truck t = new Truck();
        //FillTruck.fill(t,pList,0,0);
    }
}
