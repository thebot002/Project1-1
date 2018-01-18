package knapsack.filling;

import javafx.geometry.Point3D;
import knapsack.components.Parcel;
import knapsack.components.ParcelList;
import knapsack.components.Truck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class FillTruck {
    private static Truck filled;
    private static final double FILLED_PERCENTAGE = 1.0;

    private static ArrayList<ArrayList<Parcel>> searchedTruck;

    public static Truck getFilled(){
        return filled;
    }

    static {
        searchedTruck = new ArrayList<>();

        ParcelList parcelList = new ParcelList();

        parcelList.add(new Parcel("A"),14);
        parcelList.add(new Parcel("B"),14);
        parcelList.add(new Parcel("C"),14);


        Truck t = new Truck();
        fill(t,parcelList,0,0);

    }

    private static boolean fill(Truck t, ParcelList list, int rotation, int index){

        //recursion stop signal (list empty or filled at N%
        if((list.size() == 0) || ((t.getVolume() * FILLED_PERCENTAGE) < (t.getVolume() - t.getGapAmount()))){
            filled = t;
            t.printTruck();
            return true;
        }

        //gets the next position to add
        Point3D nextPos = t.isPossible(list.get(index));

        //checks if it creates gaps bigger than the limit percentage volume filled requested
        if(nextPos != null && t.getGapAmount(new Point3D(nextPos.getX(),t.getHeight(),t.getLength())) > t.getVolume()*(1.0-FILLED_PERCENTAGE)) return false;

        //adds a parcel to a new truck and calls recursively.
        if(nextPos != null){
            ParcelList newList = list.copy();
            newList.removeParcel(index);
            Truck newT = t.copy();
            newT.addParcel(list.get(index).copy(),nextPos);
            ArrayList<Parcel> currentTruck = newT.getTruck();
            if(searchedTruck.contains(currentTruck)){
                return false;
            }
            searchedTruck.add(currentTruck);
            if(fill(newT, newList, 0, 0)) return true; //true: one solution
        }

        //tries the next rotation if the previous call was unsuccessful (=fill call returned false)
        if((rotation<6 && list.get(index).getID().equals("B")) || (rotation<3 && list.get(index).getID().equals("A")) || (rotation<0 && list.get(index).getID().equals("C"))){
            if(rotation%3 == 0) list.get(index).rotateAroundX();
            else if(rotation%3 == 1) list.get(index).rotateAroundZ();
            else list.get(index).rotateAroundY();
            rotation++;
        }
        //if no more rotation tries next parcel
        else{
            index++;
            rotation = 0;
        }
        //if no more parcels in the list return false;
        /*else{
            return false;
        }*/

        if(index == list.size()) return false;

        //returns the result of another recursive call with one more rotation/index
        return fill(t,list,rotation,index);
    }
}
