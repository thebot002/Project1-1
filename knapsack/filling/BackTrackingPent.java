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

	public static void main(String[] args) {
		System.out.println("START");

		Truck truck = new Truck();
		ArrayList<PentominoParcel> parcelList = new ArrayList<PentominoParcel>();

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
		zRotXMirrorP.mirrorX();
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
		xzRotParcelT.rotX();
		xzRotParcelT.rotZ();

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





//    // Place everything into the parcel array
		parcelAr[48]=parcelL;
		parcelAr[49]=xMirrorL;
		parcelAr[50]=zMirrorL;
		parcelAr[51]=xzMirrorL;
		parcelAr[52]=xRotParcelL;
		parcelAr[53]=yRotParcelL;
		parcelAr[54]=zRotParcelL;
		parcelAr[55]=xyRotParcelL;
		parcelAr[56]=xzRotParcelL;
		parcelAr[57]=xRotXMirrorL;
		parcelAr[58]=yRotXMirrorL;
		parcelAr[59]=zRotXMirrorL;
		parcelAr[36]=xyRotXmirrorL;
		parcelAr[37]=xzRotXmirrorL;
		parcelAr[38]=xRotZMirrorL;
		parcelAr[39]=yRotZMirrorL;
		parcelAr[40]=zRotZMirrorL;
		parcelAr[41]=xyRotZmirrorL;
		parcelAr[42]=xzRotZmirrorL;
	    parcelAr[43]=xRotXZMirrorL;
	    parcelAr[44]=yRotXZMirrorL;
	    parcelAr[45]=zRotXZMirrorL;
	    parcelAr[46]=xyRotXZmirrorL;
	    parcelAr[47]=xzRotXZmirrorL;
	    parcelAr[12]=parcelP;
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
		parcelAr[24]=xyRotXmirrorP;
		parcelAr[25]=xzRotXmirrorP;
		parcelAr[26]=xRotZMirrorP;
		parcelAr[27]=yRotZMirrorP;
		parcelAr[28]=zRotZMirrorP;
		parcelAr[29]=xyRotZmirrorP;
		parcelAr[30]=xzRotZmirrorP;
	    parcelAr[31]=xRotXZMirrorP;
	    parcelAr[32]=yRotXZMirrorP;
	    parcelAr[33]=zRotXZMirrorP;
	    parcelAr[34]=xyRotXZmirrorP;
	    parcelAr[35]=xzRotXZmirrorP;
	    parcelAr[0]=parcelT;
		parcelAr[1]=xRotParcelT;
		parcelAr[2]=yRotParcelT;
		parcelAr[3]=zRotParcelT;
		parcelAr[4]=xyRotParcelT;
		parcelAr[5]=xzRotParcelT;
		parcelAr[6]=xMirrorT;
	    parcelAr[7]=xRotXMirrorT;
	    parcelAr[8]=yRotXMirrorT;
	    parcelAr[9]=zRotXMirrorT;
	    parcelAr[10]=xyRotXmirrorT;
	    parcelAr[11]=xzRotXmirrorT;



			backTracking(truck, parcelAr, parcelList,  0,  0);
			truckList.get(getBestValue()[1]).printTruck();
			System.out.println(getBestValue()[0]);
			System.out.println(avTotVal());

	}


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

		}else {
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
