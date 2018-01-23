package knapsack.backtracking;

import java.util.ArrayList;

import javafx.geometry.Point3D;
import knapsack.components.Parcel;
import knapsack.components.PentominoParcel;
import knapsack.components.Truck;

public class BackTrackingPent {

	private static ArrayList<Truck> truckList = new ArrayList<>();
	private static ArrayList<Integer> truckValueList = new ArrayList<>();
	private static int solutionCount=0;
	private static Truck bestTruck;

	/* Calculates the average total value of all the solutions the back tracking algorithm had found */
	public static int avTotVal() {
		int averageTotVal=0;
		for(int i=0; i<truckValueList.size(); i++) {
			averageTotVal+=truckValueList.get(i);
		}
		return averageTotVal/truckValueList.size();
	}

	/* Gives the best solution the back tracking algorithm had found*/
	public static int[] getBestValue() {
		int bestValue=0; // Will save the best value of the already checked elements of the list
		int index=0; // Keeps truck of the best value at each moment.
		int[] valueIndex = new int[2]; // In order to return the best value and its place in the list, they are both saved in this array

		for(int i=0; i<truckValueList.size(); i++) {
			if(truckValueList.get(i)>bestValue)
				bestValue=truckValueList.get(i);
		}
		for(int i=0; i<truckValueList.size(); i++) {
			if(truckValueList.get(i)==bestValue)
				index=i;
		}

		valueIndex[0]=bestValue;
		valueIndex[1]=index;
		return valueIndex;
	}

	/** The backTracking algorith
	 * @param truck The truck that need to be filled
	 * @param parcelAr The parcel array with the three kind of parcels and all their rotations
	 * @param parcelList Every parcel that is placed in the truck, it is also added to this list
	 * @param index Keeps truck of the parcel and its state that will be used next
	 * @param numL numP numT The numbers of the remaining parcel of each kind
	 *  */
	public static boolean backTracking(Truck truck, PentominoParcel[] parcelAr, ArrayList<PentominoParcel> parcelList, int index, int numL, int numP, int numT, int recursionCount) {

		int totVol = 0; // Is used to store the total volume of the already placed parcels
		int totVal = 0; // Is used to store the total value of the truck once it is fulled
		int[] position = new int[3]; // Stores the coordinates of the next empty spot in the truck

		for(int i=0; i<parcelList.size(); i++) {
			totVol+=parcelList.get(i).getVolume();
		}

	 /* If statement to stop the recursion if all the given parcels has already been used*/
		if(parcelAr.length==0) {
			truck.printTruck();
			System.out.println(numL+" "+numP+" "+numT);
			System.out.println(parcelList.size());
			return true;
		}
		/* Stops the recursion when the truck is full */
		else if(totVol==truck.getVolume()){

			solutionCount++;
			if(solutionCount%1000==0)
				System.out.println(solutionCount);
			truckList.add(truck);

		/* Calculates the total value of the filled truck. */
			for(int i=0; i<parcelList.size(); i++) {
				totVal+=parcelList.get(i).getValue();
			}

		/* The total value is added to a list, which is used in the end to find out the average total
		 * value as well as the best of all the solutions the algorithm has found. */
			truckValueList.add(totVal);
			return true;
		/* The main body of the recursion. */
		}else {
			position=truck.positionToAdd(); // The coordinates of the next empty spot in the truck.
			Point3D p = new Point3D(position[0],position[1],position[2]);

			/* If the present parcel fits to the empty spot. We continue from here. */
			if(truck.isPossible(parcelAr[index], parcelAr[index].getArray(), p	)) {
				p = new Point3D(position[0],position[1],position[2]);
				newTruck.addParcel(parcelArr[index], parcelArr[index].getArray(), p);
				Truck newTruck = new Truck();
				newTruck.setTruck(truck.copyTruck());

				// The parcel is added to the truck.
				newTruck.addParcel(parcelAr[index], parcelAr[index].getArray(), p);

				// These variables are used to keep truck of the remaining parcels after one the present has been placed
				int l=numL;
				int nump=numP;
				int t=numT;

				PentominoParcel[] newParcelAr = parcelAr.clone();

				// If one of the parcels is used, its number reduced by one.
				if(parcelAr[index].getID().equals("L")) {
					l--;
					if(l==0) {
						newParcelAr = reducedParcelAr("L",parcelAr);
					}
				}
				else if(parcelAr[index].getID().equals("P")) {
					nump--;
					if(nump==0) {
						newParcelAr = reducedParcelAr("P",parcelAr);
					}
				}
				else if(parcelAr[index].getID().equals("T")) {
					t--;
					if(t==0) {
						newParcelAr = reducedParcelAr("T",parcelAr);
					}
				}

				ArrayList<PentominoParcel> newParcelList = new ArrayList<>(parcelList);
				newParcelList.add(parcelAr[index]);

				// The recursive method is called again with the new truck, and new parcel array and the new parcel list.
				if (backTracking(newTruck, newParcelAr, newParcelList, 0, l, nump, t, recursionCount) && solutionCount<8000 )
					return false;
				else if(solutionCount==8000)
					return true;


			}

			/* If the present parcel at its present state does not fit, the next state of the parcel is beeing used.
			   Once all the states of a parcel has been used, the algorithm moves to the next parcel.
			*/
					index++;
					if(index==parcelAr.length) {
						return false;
					}

				/*The final recursive call with the new state of the present parcel, or with a new parcel in it. */
					return backTracking(truck, parcelAr,parcelList, index,numL,numP,numT, recursionCount);
		}
	}

	/* The same algorithm as before in the case we have infinite amount of each kind of parcel */

	public static boolean backTracking(Truck truck, PentominoParcel[] parcelAr, ArrayList<PentominoParcel> parcelList, int index,  int recursionCount) {

		int totVol = 0;
		int totVal = 0;
		int[] position = new int[3];

		for(int i=0; i<parcelList.size(); i++) {
			totVol+=parcelList.get(i).getVolume();
		}

		if(totVol==truck.getVolume()){
			solutionCount++;
			if(solutionCount%10==0)
				System.out.println(solutionCount);
			truckList.add(truck);
			int vol=0;
			for(int i=0; i<parcelList.size(); i++) {
				totVal+=parcelList.get(i).getValue();
			}
			truckValueList.add(totVal);
			return true;

		}else {
			position=truck.positionToAdd();
			Point3D p = new Point3D(position[0],position[1],position[2]);

			if(truck.isPossible(parcelAr[index], parcelAr[index].getArray(), p	)) {
				p = new Point3D(position[0],position[1],position[2]);
				Truck newTruck = new Truck();
				newTruck.setTruck(truck.copyTruck());
				newTruck.addParcel(parcelAr[index], parcelAr[index].getArray(), p);
				ArrayList<PentominoParcel> newParcelList = new ArrayList<>(parcelList);
				newParcelList.add(parcelAr[index]);
				if (backTracking(newTruck, parcelAr, newParcelList, 0, recursionCount) && solutionCount<1000 )
					return false;
				else if(solutionCount==1000)
					return true;
			}
					index++;
					if(index==parcelAr.length) {
						return false;
					}
					return backTracking(truck, parcelAr,parcelList, index, recursionCount);
		}
	}

	/** The method to reduce the parcel array in case there is no more one of the parcel types
	 * @param ID The id of the parcel that can't be used any more.
	 * @param parcelAr The parcel array*/
	public static PentominoParcel[] reducedParcelAr(String ID, Parcel[] parcelAr){
		int index=0;

		if(ID.equals("L") || ID.equals("P")) {
			PentominoParcel[] reducedAr = new PentominoParcel[parcelAr.length-24];
			for(int i=0; i<parcelAr.length; i++) {
				if(!parcelAr[i].getID().equals(ID)) {
					reducedAr[index]=(PentominoParcel) parcelAr[i];
					index++;
				}
			}
			return reducedAr;
		}else {
			PentominoParcel[] reducedAr = new PentominoParcel[parcelAr.length-12];
			for(int i=0; i<parcelAr.length; i++) {
				if(!parcelAr[i].getID().equals(ID)) {
					reducedAr[index]=(PentominoParcel) parcelAr[i];
					index++;
				}
			}
			return reducedAr;
		}

	}
}
