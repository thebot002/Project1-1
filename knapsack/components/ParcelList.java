package knapsack.components;

import java.util.ArrayList;

/**
 * Class used to store parcel objects to
 */
public class ParcelList extends ArrayList<Parcel>{
    private ArrayList<Integer> amounts;

    public ParcelList(){
        amounts = new ArrayList<>();
    }

    public ParcelList copy(){
        ParcelList newList = new ParcelList();
        for(int i=0; i<size(); i++){
            newList.add(get(i).copy(),amounts.get(i));
        }
        return newList;
    }

    public void add(Parcel type, int amount){
        this.amounts.add(amount);
        add(type);
    }

    public void removeParcel(int index){
        if(amounts.get(index) == 1){
            remove(index);
            amounts.remove(index);
        }
        else{
            amounts.set(index, amounts.get(index)-1);
        }
    }

    public int getTotalSize(){
        int sum = 0;
        for(int i: amounts){
            sum += i;
        }
        return sum;
    }

    public ArrayList<Parcel> getFullArray(){
        ArrayList<Parcel> list = new ArrayList<>();
        for (int i=0; i<size();i++){
            for (int j=0; j<amounts.get(i);j++){
                list.add(get(i).copy());
            }
        }
        return list;
    }
}
