package knapsack.greedy;

import java.util.ArrayList;

import javafx.geometry.Point3D;
import knapsack.components.Parcel;
import knapsack.components.Truck;

public class Greedy {

	private static ArrayList<Truck> truckList = new ArrayList<>();
	private static ArrayList<Integer> truckValueList = new ArrayList<>();

	public static void main(String[] args) {

	    // Build the truck.
			Truck truck = new Truck();

	    // Build the three parcels
			Parcel parcelA = new Parcel("A",3);
			Parcel parcelB = new Parcel("B",4);
			Parcel parcelC = new Parcel("C",5);

	    // Crete a list to keep truck of the parcels we have already placed
			ArrayList<Parcel> parcelList = new ArrayList<Parcel>();

	    /* Create an array with the three parcels and
	       all their rotations. */
			Parcel[] parcelAr = new Parcel[10];

	    // Get the rotations of the parcels
			Parcel xRotParcelA = new Parcel("B",4);
			xRotParcelA.xRotate();
			Parcel yRotParcelA = new Parcel("B",4);
			yRotParcelA.yRotate();
			Parcel zRotParcelA = new Parcel("B",4);
			zRotParcelA.zRotate();
			Parcel xyRotParcelA = new Parcel("B",4);
			xyRotParcelA.xRotate();
			xyRotParcelA.yRotate();
			Parcel xzRotParcelA = new Parcel("B",4);
			xzRotParcelA.xRotate();
			xzRotParcelA.zRotate();
			Parcel yRotParcelB = new Parcel("A",3);
			yRotParcelB.yRotate();
			Parcel zRotParcelB = new Parcel("A",3);
			zRotParcelB.zRotate();

	    // Place everything into the parcel array
			parcelAr[9]=parcelA;
			parcelAr[1]=xRotParcelA;
			parcelAr[2]=yRotParcelA;
			parcelAr[3]=zRotParcelA;
			parcelAr[4]=xyRotParcelA;
			parcelAr[5]=xzRotParcelA;
			parcelAr[6]=parcelB;
			parcelAr[7]=yRotParcelB;
			parcelAr[8]=zRotParcelB;
			parcelAr[0]=parcelC;

			// Call the method that fills the truck with parcels.
			greedyAl(truck, parcelAr, 0, parcelList);




		}





	/**
	 * The method fills the truck with brute force.
	 * @param truck The truck we fill with parcels
	 * @param parcelAr The array that contains the parcels and their rotations
	 * @param index Keeps truck of the parcel array elements
	 * @param parcelList This list contains the parcels we have added to the truck
	 * */
	public static void greedyAl(Truck truck, Parcel[] parcelAr,int index, ArrayList<Parcel> parcelList) {
		int totVal=0;


		/* We use the nested for loops in order to check one by one all the positions of the truck
		 * and try to each one of them to place a parcel. Whenever a parcel doesn't fit, either we try another
		 * rotation of this parcel or another parcel. When a parcel is placed, we also add it to the parcelList */
		for(int i=0; i<truck.getWidth(); i++) {
			for(int j=0; j<truck.getHeight(); j++) {
				for(int k=0; k<truck.getLength(); k++) {
					while(!truck.isPossible(parcelAr[index], new Point3D(i, j, k)) && index<9) {
						index++;
					}
					if(truck.isPossible(parcelAr[index], new Point3D(i, j, k))){
						truck.addParcel(parcelAr[index], new Point3D(i,j,k));
						parcelList.add(parcelAr[index]);
					}
					index=0;
				}
			}
		}

		for(int i=0; i<parcelList.size(); i++) {
			totVal+=parcelList.get(i).getValue();
		}
		System.out.println(totVal);
	}


}
