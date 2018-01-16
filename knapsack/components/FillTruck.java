package knapsack.components;

import java.util.ArrayList;

public class FillTruck {
    public static boolean fill(Truck t, ArrayList<Parcel> list, int rotation, int index){

        if(index>=list.size())return false;
        if(t.getVolume()*0.7 < t.getVolume() - t.getGapAmount() || list.isEmpty()){
            t.printTruck();
            return true;
        }
        else{
            if(t.addParcel(list.get(index))){
                ArrayList<Parcel> newList = new ArrayList<>();
                for(Parcel p: list){
                    newList.add(p);
                }
                newList.remove(index);
                if(fill(t.copyTruck(), newList, rotation, index)) return true;
            }
            else {
                if(rotation<6){
                    if(rotation%3 == 0) list.get(index).rotateAroundX();
                    else if(rotation%3 == 1) list.get(index).rotateAroundZ();
                    else list.get(index).rotateAroundY();
                    rotation++;
                }
                else if(index+1 < list.size()){
                    index ++;
                    rotation = 0;
                }
                else return false;
            }
        }
        return fill(t,list,rotation,index);
    }
}
