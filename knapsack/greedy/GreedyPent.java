package knapsack.greedy;

import java.util.ArrayList;

import javafx.geometry.Point3D;
import knapsack.components.PentominoParcel;
import knapsack.components.Simulations;
import knapsack.components.Truck;

public class GreedyPent {

	private static ArrayList<Truck> truckList = new ArrayList<>();
	private static ArrayList<Integer> truckValueList = new ArrayList<>();

	public static void main(String[] args) {

	    // Build the truck.
			Truck truck = new Truck();

	    // Build the three parcels
			PentominoParcel parcelL = new PentominoParcel("L",3);
			PentominoParcel parcelP = new PentominoParcel("P",4);
			PentominoParcel parcelT = new PentominoParcel("T",5);




	    /* Create an array with the three parcels and
	       all their rotations. */
			PentominoParcel[] parcelAr = new PentominoParcel[60];


		// Get the mirror image of the parcels

			PentominoParcel xMirrorL = new PentominoParcel("L",3);
			xMirrorL.mirrorX();
			PentominoParcel zMirrorL = new PentominoParcel("L",3);
			zMirrorL.mirrorZ();
			PentominoParcel xzMirrorL = new PentominoParcel("L",3);
			xzMirrorL.mirrorX();
			xzMirrorL.mirrorZ();
			PentominoParcel xMirrorP = new PentominoParcel("P",4);
			xMirrorP.mirrorX();
			PentominoParcel zMirrorP = new PentominoParcel("P",4);
			zMirrorP.mirrorZ();
			PentominoParcel xzMirrorP = new PentominoParcel("P",4);
			xzMirrorP.mirrorX();
			xzMirrorP.mirrorZ();
			PentominoParcel xMirrorT = new PentominoParcel("T",5);
			xMirrorT.mirrorX();



	    // Get the rotations of the parcels

			PentominoParcel xRotParcelP = new PentominoParcel("P",4);
			xRotParcelP.rotX();
			PentominoParcel yRotParcelP = new PentominoParcel("P",4);
			yRotParcelP.rotY();
			PentominoParcel zRotParcelP = new PentominoParcel("P",4);
			zRotParcelP.rotZ();
			PentominoParcel xyRotParcelP = new PentominoParcel("P",4);
			xyRotParcelP.rotX();
			xyRotParcelP.rotY();
			PentominoParcel xzRotParcelP = new PentominoParcel("P",4);
			xzRotParcelP.xRotate();
			xzRotParcelP.zRotate();

			PentominoParcel xRotXMirrorP = new PentominoParcel("P",4);
			xRotXMirrorP.mirrorX();
			xRotXMirrorP.rotX();
			PentominoParcel yRotXMirrorP = new PentominoParcel("P",4);
			yRotXMirrorP.mirrorX();
			yRotXMirrorP.rotY();
			PentominoParcel zRotXMirrorP = new PentominoParcel("P",4);
			zRotXMirrorP.rotZ();
			PentominoParcel xyRotXmirrorP = new PentominoParcel("P",4);
			xyRotXmirrorP.mirrorX();
			xyRotXmirrorP.rotX();
			xyRotXmirrorP.rotY();
			PentominoParcel xzRotXmirrorP = new PentominoParcel("P",4);
			xzRotXmirrorP.mirrorX();
			xzRotXmirrorP.rotX();
			xzRotXmirrorP.rotZ();

			PentominoParcel xRotZMirrorP = new PentominoParcel("P",4);
			xRotZMirrorP.mirrorZ();
			xRotZMirrorP.rotX();
			PentominoParcel yRotZMirrorP = new PentominoParcel("P",4);
			yRotZMirrorP.mirrorZ();
			yRotZMirrorP.rotY();
			PentominoParcel zRotZMirrorP = new PentominoParcel("P",4);
			zRotZMirrorP.mirrorZ();
			zRotZMirrorP.rotZ();
			PentominoParcel xyRotZmirrorP = new PentominoParcel("P",4);
			xyRotZmirrorP.mirrorZ();
			xyRotZmirrorP.rotX();
			xyRotZmirrorP.rotY();
			PentominoParcel xzRotZmirrorP = new PentominoParcel("P",4);
			xzRotZmirrorP.mirrorZ();
			xzRotZmirrorP.rotX();
			xzRotZmirrorP.rotZ();

			PentominoParcel xRotXZMirrorP = new PentominoParcel("P",4);
			xRotXZMirrorP.mirrorX();
			xRotXZMirrorP.mirrorZ();
			xRotXZMirrorP.rotX();
			PentominoParcel yRotXZMirrorP = new PentominoParcel("P",4);
			yRotXZMirrorP.mirrorX();
			yRotXZMirrorP.mirrorZ();
			yRotXZMirrorP.rotY();
			PentominoParcel zRotXZMirrorP = new PentominoParcel("P",4);
			zRotXZMirrorP.mirrorX();
			zRotXZMirrorP.mirrorZ();
			zRotXZMirrorP.rotZ();
			PentominoParcel xyRotXZmirrorP = new PentominoParcel("P",4);
			xyRotXZmirrorP.mirrorX();
			xyRotXZmirrorP.mirrorZ();
			xyRotXZmirrorP.rotX();
			xyRotXZmirrorP.rotY();
			PentominoParcel xzRotXZmirrorP = new PentominoParcel("P",4);
			xzRotXZmirrorP.mirrorX();
			xzRotXZmirrorP.mirrorZ();
			xzRotXZmirrorP.rotX();
			xzRotXZmirrorP.rotZ();

			PentominoParcel xRotParcelL = new PentominoParcel("L",3);
			xRotParcelL.rotX();
			PentominoParcel yRotParcelL = new PentominoParcel("L",3);
			yRotParcelL.rotY();
			PentominoParcel zRotParcelL = new PentominoParcel("L",3);
			zRotParcelL.rotZ();
			PentominoParcel xyRotParcelL = new PentominoParcel("L",3);
			xyRotParcelL.rotX();
			xyRotParcelL.rotY();
			PentominoParcel xzRotParcelL = new PentominoParcel("L",3);
			xzRotParcelL.xRotate();
			xzRotParcelL.zRotate();

			PentominoParcel xRotXMirrorL = new PentominoParcel("L",3);
			xRotXMirrorL.mirrorX();
			xRotXMirrorL.rotX();
			PentominoParcel yRotXMirrorL = new PentominoParcel("L",3);
			yRotXMirrorL.mirrorX();
			yRotXMirrorL.rotY();
			PentominoParcel zRotXMirrorL = new PentominoParcel("L",3);
			zRotXMirrorL.mirrorX();
			zRotXMirrorL.rotZ();
			PentominoParcel xyRotXmirrorL = new PentominoParcel("L",3);
			xyRotXmirrorL.mirrorX();
			xyRotXmirrorL.rotX();
			xyRotXmirrorL.rotY();
			PentominoParcel xzRotXmirrorL = new PentominoParcel("L",3);
			xzRotXmirrorL.mirrorX();
			xzRotXmirrorL.rotX();
			xzRotXmirrorL.rotZ();

			PentominoParcel xRotZMirrorL = new PentominoParcel("L",3);
			xRotZMirrorL.mirrorZ();
			xRotZMirrorL.rotX();
			PentominoParcel yRotZMirrorL = new PentominoParcel("L",3);
			yRotZMirrorL.mirrorZ();
			yRotZMirrorL.rotY();
			PentominoParcel zRotZMirrorL = new PentominoParcel("L",3);
			zRotZMirrorL.mirrorZ();
			zRotZMirrorL.rotZ();
			PentominoParcel xyRotZmirrorL = new PentominoParcel("L",3);
			xyRotZmirrorL.mirrorZ();
			xyRotZmirrorL.rotX();
			xyRotZmirrorL.rotY();
			PentominoParcel xzRotZmirrorL = new PentominoParcel("L",3);
			xzRotZmirrorL.mirrorZ();
			xzRotZmirrorL.rotX();
			xzRotZmirrorL.rotZ();

			PentominoParcel xRotXZMirrorL = new PentominoParcel("L",3);
			xRotXZMirrorL.mirrorX();
			xRotXZMirrorL.mirrorZ();
			xRotXZMirrorL.rotX();
			PentominoParcel yRotXZMirrorL = new PentominoParcel("L",3);
			yRotXZMirrorL.mirrorX();
			yRotXZMirrorL.mirrorZ();
			yRotXZMirrorL.rotY();
			PentominoParcel zRotXZMirrorL = new PentominoParcel("L",3);
			zRotXZMirrorL.mirrorX();
			zRotXZMirrorL.mirrorZ();
			zRotXZMirrorL.rotZ();
			PentominoParcel xyRotXZmirrorL = new PentominoParcel("L",3);
			xyRotXZmirrorL.mirrorX();
			xyRotXZmirrorL.mirrorZ();
			xyRotXZmirrorL.rotX();
			xyRotXZmirrorL.rotY();
			PentominoParcel xzRotXZmirrorL = new PentominoParcel("L",3);
			xzRotXZmirrorL.mirrorX();
			xzRotXZmirrorL.mirrorZ();
			xzRotXZmirrorL.rotX();
			xzRotXZmirrorL.rotZ();


			PentominoParcel xRotParcelT = new PentominoParcel("T",5);
			xRotParcelT.rotX();
			PentominoParcel yRotParcelT = new PentominoParcel("T",5);
			yRotParcelT.rotY();
			PentominoParcel zRotParcelT = new PentominoParcel("T",5);
			zRotParcelT.rotZ();
			PentominoParcel xyRotParcelT = new PentominoParcel("T",5);
			xyRotParcelT.rotX();
			xyRotParcelT.rotY();
			PentominoParcel xzRotParcelT = new PentominoParcel("T",5);
			xzRotParcelT.xRotate();
			xzRotParcelT.zRotate();

			PentominoParcel xRotXMirrorT = new PentominoParcel("T",5);
			xRotXMirrorT.mirrorX();
			xRotXMirrorT.rotX();
			PentominoParcel yRotXMirrorT = new PentominoParcel("T",5);
			yRotXMirrorT.mirrorX();
			yRotXMirrorT.rotY();
			PentominoParcel zRotXMirrorT = new PentominoParcel("T",5);
			zRotXMirrorT.mirrorX();
			zRotXMirrorT.rotZ();
			PentominoParcel xyRotXmirrorT = new PentominoParcel("T",5);
			xyRotXmirrorT.mirrorX();
			xyRotXmirrorT.rotX();
			xyRotXmirrorT.rotY();
			PentominoParcel xzRotXmirrorT = new PentominoParcel("T",5);
			xzRotXmirrorT.mirrorX();
			xzRotXmirrorT.rotX();
			xzRotXmirrorT.rotZ();





//	    // Place everything into the parcel array
			parcelAr[1]=parcelL;
			parcelAr[25]=xMirrorL;
			parcelAr[26]=zMirrorL;
			parcelAr[27]=xzMirrorL;
			parcelAr[28]=xRotParcelL;
			parcelAr[29]=yRotParcelL;
			parcelAr[30]=zRotParcelL;
			parcelAr[31]=xyRotParcelL;
			parcelAr[32]=xzRotParcelL;
			parcelAr[33]=xRotXMirrorL;
			parcelAr[34]=yRotXMirrorL;
			parcelAr[35]=zRotXMirrorL;
			parcelAr[48]=xyRotXmirrorL;
			parcelAr[49]=xzRotXmirrorL;
			parcelAr[50]=xRotZMirrorL;
			parcelAr[51]=yRotZMirrorL;
			parcelAr[52]=zRotZMirrorL;
			parcelAr[53]=xyRotZmirrorL;
			parcelAr[54]=xzRotZmirrorL;
		    parcelAr[55]=xRotXZMirrorL;
		    parcelAr[56]=yRotXZMirrorL;
		    parcelAr[57]=zRotXZMirrorL;
		    parcelAr[58]=xyRotXZmirrorL;
		    parcelAr[59]=xzRotXZmirrorL;
		    parcelAr[0]=parcelP;
			parcelAr[13]=xMirrorP;
			parcelAr[14]=zMirrorP;
			parcelAr[15]=xzMirrorP;
			parcelAr[16]=xRotParcelP;
			parcelAr[17]=yRotParcelP;
			parcelAr[18]=zRotParcelP;
			parcelAr[19]=xyRotParcelP;
			parcelAr[20]=xzRotParcelP;
			parcelAr[21]=xRotXMirrorP;
			parcelAr[22]=yRotXMirrorP;
			parcelAr[23]=zRotXMirrorP;
			parcelAr[36]=xyRotXmirrorP;
			parcelAr[37]=xzRotXmirrorP;
			parcelAr[38]=xRotZMirrorP;
			parcelAr[39]=yRotZMirrorP;
			parcelAr[40]=zRotZMirrorP;
			parcelAr[41]=xyRotZmirrorP;
			parcelAr[42]=xzRotZmirrorP;
		    parcelAr[43]=xRotXZMirrorP;
		    parcelAr[44]=yRotXZMirrorP;
		    parcelAr[45]=zRotXZMirrorP;
		    parcelAr[46]=xyRotXZmirrorP;
		    parcelAr[47]=xzRotXZmirrorP;
		    parcelAr[2]=parcelT;
			parcelAr[12]=xRotParcelT;
			parcelAr[24]=yRotParcelT;
			parcelAr[3]=zRotParcelT;
			parcelAr[4]=xyRotParcelT;
			parcelAr[5]=xzRotParcelT;
			parcelAr[6]=xMirrorT;
		    parcelAr[7]=xRotXMirrorT;
		    parcelAr[8]=yRotXMirrorT;
		    parcelAr[9]=zRotXMirrorT;
		    parcelAr[10]=xyRotXmirrorT;
		    parcelAr[11]=xzRotXmirrorT;

//		// Creating the object which permute the Parcel array.
//			Simulations simulation = new Simulations(parcelAr);
//
//			simulation.permute();
//

		// Creating  a list to save all the total values we get for different orders of the parcel array.
//			ArrayList<Integer> totValueList = new ArrayList<>();
//			int totVal=1000;


//		    xRotParcelL.printPentomino();
//		    xRotXMirrorL.printPentomino();
//		    truck.addParcel(xRotParcelL, xRotParcelL.getArray(), new Point3D(0,0,0));
//		    truck.printTruck();
		    System.out.println(bruteForce(parcelAr, 0));

	}






	/**
	 * The method fills the truck with brute force.
	 * @param truck The truck we fill with parcels
	 * @param parcelAr The array that contains the parcels and their rotations
	 * @param index Keeps truck of the parcel array elements
	 * @param parcelList This list contains the parcels we have added to the truck
	 * */
	public static int bruteForce(PentominoParcel[] parcelAr,int index) {
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


}
