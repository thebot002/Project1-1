package knapsack.greedy;

import knapsack.components.Parcel;
import knapsack.components.Truck;

import java.util.ArrayList;

public class Greedy {
    public static void findBestFit(Truck t, ArrayList<Parcel> set){
        //stops recursion
        if(set.size() != 0) return;

        // finds the parcel with the best value.
        Parcel pVal = set.get(0);
        for (Parcel p: set ) {
            if(p.getValue() > pVal.getValue()) pVal = p;
        }
        t.addParcel(pVal);
    }
}
