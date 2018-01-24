package knapsack.filling;

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
	private static Truck truck;
	private static ArrayList<PentominoParcel> parcelList;

	/* Calculates the average total value of all the solutions the back tracking algorithm had found */
	public static int avTotVal() {
		int averageTotVal=0;
		for(int i=0; i<truckValueList.size(); i++) {
			averageTotVal+=truckValueList.get(i);
		}
		return averageTotVal/truckValueList.size();
	}
	/**
	 * Used to bulk create a Parcel array containing all variations of the A parcel with a certain (user inputted) value
	 * @returns Truck with the highest sum of values of packages it contains
	 */
	public static Truck getBestTruck() {
		int[] bestValue = getBestValue();
		return truckList.get(bestValue[1]);
	}

	/**
	 * Creates an array that contains information about the index of the best truck in truckList and the value of it
	 * @returns 1D integer array
	 */
	public static int[] getBestValue() {
		int bestValue=0;
		int index=0;
		int[] valueIndex = new int[2];
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
	 * @param parcelArr The parcel array with the three kind of parcels and all their rotations
	 * @param parcelList Every parcel that is placed in the truck, it is also added to this list
	 * @param index Keeps truck of the parcel and its state that will be used next
	 * @param numL numP numT The numbers of the remaining parcel of each kind
	 *  */
	public static boolean backTracking(Truck truck, PentominoParcel[] parcelArr, ArrayList<PentominoParcel> parcelList, int index, int numL, int numP, int numT, int recursionCount) {

		int totVol = 0;
		int totVal = 0;
		int[] position = new int[3];

		for(int i=0; i<parcelList.size(); i++) {
			totVol+=parcelList.get(i).getVolume();
		}

		if(parcelArr.length==0) {
			truck.printTruck();
			System.out.println(numL+" "+numP+" "+numT);
			System.out.println(parcelList.size());
			return true;
		}
		else if(totVol==truck.getVolume()){
			solutionCount++;
			if(solutionCount%1000==0)
				System.out.println(solutionCount);
			truckList.add(truck);
			int vol=0;
			for(int i=0; i<parcelList.size(); i++) {
				totVal+=parcelList.get(i).getValue();
			}

			truckValueList.add(totVal);
			return true;

		} else {
			position=truck.positionToAdd();
			Point3D p = new Point3D(position[0],position[1],position[2]);

			if(truck.isPossible(parcelArr[index], parcelArr[index].getArray(), p	)) {
				p = new Point3D(position[0],position[1],position[2]);
				Truck newTruck = truck.copy();
				newTruck.addParcel(parcelArr[index], parcelArr[index].getArray(), p);

				int l=numL;
				int nump=numP;
				int t=numT;
				PentominoParcel[] newParcelAr = parcelArr.clone();
				if(parcelArr[index].getID().equals("L")) {
					l--;
					if(l==0) {
						newParcelAr = reducedParcelAr("L",parcelArr);
					}
				}
				else if(parcelArr[index].getID().equals("P")) {
					nump--;
					if(nump==0) {
						newParcelAr = reducedParcelAr("P",parcelArr);
					}
				}
				else if(parcelArr[index].getID().equals("T")) {
					t--;
					if(t==0) {
						newParcelAr = reducedParcelAr("T",parcelArr);
					}
				}
				ArrayList<PentominoParcel> newParcelList = new ArrayList<>(parcelList);
				newParcelList.add(parcelArr[index]);
				if (backTracking(newTruck, newParcelAr, newParcelList, 0, l, nump, t, recursionCount) && solutionCount<1000 )
					return false;
				else if(solutionCount==1000)
					return true;
			}
					index++;
					if(index==parcelArr.length) {
						return false;
					}
					return backTracking(truck, parcelArr,parcelList, index,numL,numP,numT, recursionCount);
		}
	}

	/* The same algorithm as before in the case we have infinite amount of each kind of parcel */

	public static boolean backTracking(Truck truck, PentominoParcel[] parcelArr, ArrayList<PentominoParcel> parcelList, int index,  int recursionCount) {

		int totVol = 0;
		int totVal = 0;
		int[] position = new int[3];



		for(int i=0; i<parcelList.size(); i++) {
			totVol+=parcelList.get(i).getVolume();
		}


		if(totVol==truck.getVolume()){


			solutionCount++;
			if(solutionCount%1000==0)
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

			if(truck.isPossible(parcelArr[index], parcelArr[index].getArray(), p	)) {
				p = new Point3D(position[0],position[1],position[2]);
				Truck newTruck = truck.copy();
				newTruck.addParcel(parcelArr[index], parcelArr[index].getArray(), p);


				ArrayList<PentominoParcel> newParcelList = new ArrayList<>(parcelList);
				newParcelList.add(parcelArr[index]);
				if (backTracking(newTruck, parcelArr, newParcelList, 0, recursionCount) && solutionCount<1000 )
					return false;
				else if(solutionCount==1000)
					return true;



			}
					index++;
					if(index==parcelArr.length) {
						return false;
					}


					return backTracking(truck, parcelArr,parcelList, index, recursionCount);
		}
	}

	/** The method to reduce the parcel array in case there is no more one of the parcel types
	 * @param ID The id of the parcel that can't be used any more.
	 * @param parcelArr The parcel array*/
	public static PentominoParcel[] reducedParcelAr(String ID, Parcel[] parcelArr){
		int index=0;

		if(ID.equals("L") || ID.equals("P")) {
			PentominoParcel[] reducedAr = new PentominoParcel[parcelArr.length-24];
			for(int i=0; i<parcelArr.length; i++) {
				if(!parcelArr[i].getID().equals(ID)) {
					reducedAr[index]=(PentominoParcel) parcelArr[i];
					index++;
				}
			}
			return reducedAr;
		}else {
			PentominoParcel[] reducedAr = new PentominoParcel[parcelArr.length-12];
			for(int i=0; i<parcelArr.length; i++) {
				if(!parcelArr[i].getID().equals(ID)) {
					reducedAr[index]=(PentominoParcel) parcelArr[i];
					index++;
				}
			}
			return reducedAr;
		}

	}
}
