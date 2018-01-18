package knapsack.components;

import java.util.ArrayList;

public class Simulations {

	private Parcel[] arr;
	private ArrayList<Parcel[]> parcelList = new ArrayList<Parcel[]>();

	public Simulations(Parcel[] parcelAr) {
		arr=parcelAr;
	}
	public void permute(){
	    permuteHelper(0);
	}

	public ArrayList<Parcel[]> getList(){
		return parcelList;
	}

	public void permuteHelper(int index){
	    if(index >= arr.length - 1){ //If we are at the last element - nothing left to permute

	    	Parcel[] newAr = arr.clone();
	    	parcelList.add(newAr);
	       return;
	    }

	    for(int i = index; i < arr.length; i++){ //For each index in the sub array arr[index...end]

	        //Swap the elements at indices index and i
	        Parcel t = arr[index];
	        arr[index] = arr[i];
	        arr[i] = t;

	        //Recurse on the sub array arr[index+1...end]
	        permuteHelper(index+1);

	        //Swap the elements back
	        t = arr[index];
	        arr[index] = arr[i];
	        arr[i] = t;
	    }


	}
}
