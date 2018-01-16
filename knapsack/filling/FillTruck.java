package knapsack.filling;

import knapsack.components.Parcel;
import knapsack.components.Truck;

import java.util.ArrayList;

public class FillTruck {
    private static Truck filled;

    public static Truck getFilled(){
        return filled;
    }

    static {
        ArrayList<Parcel> list = new ArrayList<>();
        list.add(new Parcel("A"));
        list.add(new Parcel("B"));
        list.add(new Parcel("C"));

        ArrayList<Integer> amounts = new ArrayList<>();

        amounts.add(12);
        amounts.add(12);
        amounts.add(12);

        ArrayList<Parcel> pList = new ArrayList<>();
        for(int i=0; i<list.size(); i++){
            for(int j=0; j<amounts.get(i); j++){
                pList.add(new Parcel(list.get(i).getID()));
            }
        }

        Truck t = new Truck();
        fill(t,pList,0,0);

    }

    private static boolean fill(Truck t, ArrayList<Parcel> list, int rotation, int index){

        if((list.size() == 0) || ((t.getVolume() * 0.8) < (t.getVolume() - t.getGapAmount()))){
            filled = t;
            t.printTruck();
            return true;
        }
        else{
            if(t.isPossible(list.get(index))){
                ArrayList<Parcel> newList = new ArrayList<>();
                for(int i=0; i<list.size(); i++){
                    newList.add(list.get(i).copy());
                }
                newList.remove(index);
                Truck newT = t.copy();
                newT.addParcel(list.get(index));
                if(fill(newT, newList, 0, 0)) return true;
            }
            else {
                if(/*(*/rotation<6 /*&& list.get(index).getID().equals("B")) || (rotation<3 && list.get(index).getID().equals("A")) || (rotation<0 && list.get(index).getID().equals("C"))*/){
                    if(rotation%3 == 0) list.get(index).rotateAroundX();
                    else if(rotation%3 == 1) list.get(index).rotateAroundZ();
                    else list.get(index).rotateAroundY();
                    rotation++;
                }
                else if(index+1 < list.size()){
                    index++;
                    rotation = 0;
                }
                else{
                    return false;
                }
            }
        }
        return fill(t,list,rotation,index);
    }
}
