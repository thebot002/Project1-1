package knapsack.filling;

import java.util.ArrayList;

import javafx.geometry.Point3D;
import knapsack.components.Parcel;
import knapsack.components.PentominoParcel;
import knapsack.components.Truck;

public class GreedyPent {

	private static ArrayList<Truck> truckList = new ArrayList<>();
	private static ArrayList<Integer> truckValueList = new ArrayList<>();

	/**
	 * The method fills the truck with brute force.
	 * @param truck The truck we fill with parcels
	 * @param parcelAr The array that contains the parcels and their rotations
	 * @param index Keeps truck of the parcel array elements
	 * @param parcelList This list contains the parcels we have added to the truck
	 * @param numL numP numT The number of each kind of parcels to be used
	 * */

	public static Truck greedy(PentominoParcel[] parcelAr,int index, int numL, int numP, int numT) {
		int totVal=0;
		Truck truck = new Truck();

		ArrayList<PentominoParcel> parcelList = new ArrayList<PentominoParcel>();

		/* We use the nested for loops in order to check one by one all the positions of the truck
		 * and try to each one of them to place a parcel. Whenever a parcel doesn't fit, either we try another
		 * rotation of this parcel or another parcel. When a parcel is placed, we also add it to the parcelList */
		for(int i=0; i<truck.getWidth(); i++) {
			for(int j=0; j<truck.getHeight(); j++) {
				for(int k=0; k<truck.getLength(); k++) {
					while(!truck.isPossible(parcelAr[index], parcelAr[index].getArray(), new Point3D(i, j, k)) && index<parcelAr.length-1) {
						index++;
					}
					if(truck.isPossible(parcelAr[index], parcelAr[index].getArray(),new Point3D(i, j, k))){


						truck.addParcel(parcelAr[index], parcelAr[index].getArray(), new Point3D(i,j,k));
						parcelList.add(parcelAr[index]);
						if(parcelAr[index].getID().equals("L")) {
							numL--;
							if(numL==0)
								parcelAr=reducedParcelAr("L", parcelAr);
						}
						else if(parcelAr[index].getID().equals("P")) {
							numP--;
							if(numP==0)
								parcelAr=reducedParcelAr("P", parcelAr);
						}
						else if(parcelAr[index].getID().equals("T")) {
							numT--;
							if(numT==0)
								parcelAr=reducedParcelAr("T",parcelAr);
						}

					}

					index=0;
					if(parcelAr.length==0)
						break;
				}
				if(parcelAr.length==0)
					break;
			}
			if(parcelAr.length==0)
				break;
		}

		// We calculate the total value of the placed shapes, and we return this value.
		for(int i=0; i<parcelList.size(); i++) {
			totVal+=parcelList.get(i).getValue();
		}
		System.out.println(parcelList.size());
		truck.printTruck();

		return truck;
	}

	/* The same algorithm as before. It works if there is no restriction in the number of parcels*/
	public static int greedy(PentominoParcel[] parcelAr,int index) {
		int totVal=0;
		Truck truck = new Truck();
		ArrayList<PentominoParcel> parcelListL = new ArrayList<PentominoParcel>();
		ArrayList<PentominoParcel> parcelListP = new ArrayList<PentominoParcel>();
		ArrayList<PentominoParcel> parcelListT = new ArrayList<PentominoParcel>();
		ArrayList<PentominoParcel> parcelList = new ArrayList<PentominoParcel>();

		/* We use the nested for loops in order to check one by one all the positions of the truck
		 * and try to each one of them to place a parcel. Whenever a parcel doesn't fit, either we try another
		 * rotation of this parcel or another parcel. When a parcel is placed, we also add it to the parcelList */
		for(int i=0; i<truck.getWidth(); i++) {
			for(int j=0; j<truck.getHeight(); j++) {
				for(int k=0; k<truck.getLength(); k++) {
					while(!truck.isPossible(parcelAr[index], parcelAr[index].getArray(), new Point3D(i, j, k)) && index<59) {
						index++;
					}
					if(truck.isPossible(parcelAr[index], parcelAr[index].getArray(),new Point3D(i, j, k))){
						truck.addParcel(parcelAr[index], parcelAr[index].getArray(), new Point3D(i,j,k));
						if(parcelAr[index].getID().equals("L"))
							parcelListL.add(parcelAr[index]);
						else if(parcelAr[index].getID().equals("P"))
							parcelListP.add(parcelAr[index]);
						else if(parcelAr[index].getID().equals("T"))
							parcelListT.add(parcelAr[index]);
						parcelList.add(parcelAr[index]);
					}
					index=0;
				}
			}
		}


		// We calculate the total value of the placed shapes, and we return this value.
		for(int i=0; i<parcelList.size(); i++) {
			totVal+=parcelList.get(i).getValue();
		}

//		truck.printTruck();
		System.out.println(parcelListL.size());
		System.out.println(parcelListP.size());
		System.out.println(parcelListT.size());

		System.out.println(parcelList.size());
		truck.printTruck();
		return totVal;
	}

	/** The method to reduce the parcel array in case there is no more one of the parcel types
	 * @param ID The id of the parcel that can't be used any more.
	 * @param parcelAr The parcel array
	 */
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
			index = 0;
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
