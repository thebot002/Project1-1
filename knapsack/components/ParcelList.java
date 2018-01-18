package knapsack.components;

import java.util.ArrayList;

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
}
