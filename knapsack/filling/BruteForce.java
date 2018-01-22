package knapsack.filling;

import javafx.geometry.Point3D;
import knapsack.components.Parcel;
import knapsack.components.Truck;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BruteForce {
    private static Parcel[] parcelArr;
    private static int[][] userInput;

    public static void main(String[] args){
        Truck truck = new Truck();
        fill(truck);
        System.out.print(truck);
    }

    static{
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
        parcelArr = new Parcel[10];

        // Get the rotations of the parcels
        Parcel xRotParcelA = new Parcel("A",3);
        xRotParcelA.xRotate();
        Parcel yRotParcelA = new Parcel("A",3);
        yRotParcelA.yRotate();
        Parcel zRotParcelA = new Parcel("A",3);
        zRotParcelA.zRotate();
        Parcel xyRotParcelA = new Parcel("A",3);
        xyRotParcelA.xRotate();
        xyRotParcelA.yRotate();
        Parcel xzRotParcelA = new Parcel("A",3);
        xzRotParcelA.xRotate();
        xzRotParcelA.zRotate();
        Parcel yRotParcelB = new Parcel("B",3);
        yRotParcelB.yRotate();
        Parcel zRotParcelB = new Parcel("B",3);
        zRotParcelB.zRotate();

        // Place everything into the parcel array
        parcelArr[9]=parcelA;
        parcelArr[1]=xRotParcelA;
        parcelArr[2]=yRotParcelA;
        parcelArr[3]=zRotParcelA;
        parcelArr[4]=xyRotParcelA;
        parcelArr[5]=xzRotParcelA;
        parcelArr[6]=parcelB;
        parcelArr[7]=yRotParcelB;
        parcelArr[8]=zRotParcelB;
        parcelArr[0]=parcelC;
    }
    /**
     * The method fills the truck with brute force.
     * @param truck The truck we fill with parcels
     * */
    public static void fill(Truck truck) {
        int totVal=0;
        int index =0;

        //might be used as an output?
        ArrayList<Parcel> parcelList = new ArrayList<>();

        /* We use the nested for loops in order to check one by one all the positions of the truck
         * and try to each one of them to place a parcel. Whenever a parcel doesn't fit, either we try another
         * rotation of this parcel or another parcel. When a parcel is placed, we also add it to the parcelList */
        for(int i=0; i<truck.getWidth(); i++) {
            for(int j=0; j<truck.getHeight(); j++) {
                for(int k=0; k<truck.getLength(); k++) {
                    while(!truck.isPossible(parcelArr[index], new Point3D(i, j, k)) && index<9) {
                        index++;
                    }
                    if(truck.addParcel(parcelArr[index].copy())){
                        parcelList.add(parcelArr[index].copy());
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
    public static void setParcelArrray(Parcel[] customParcelArr) {
    	parcelArr = customParcelArr;
    }
    public static Parcel[] getParcelArrray() {
    	return parcelArr;
    }
    public static void setUserInput(int[][] customUserInput) {
    	userInput = customUserInput;
    }
    public static int[][] getUserInput() {
    	return userInput;
    }
    public Parcel[] updateParcelRectangleAvailability(ArrayList<Parcel> placedParcels, int[][] parcelsArr, Parcel[] inputParcelsArr ) {
    	Parcel[] parcelsA = Parcel.createParcelsArrA(parcelsArr[0][1]);
    	Parcel[] parcelsB = Parcel.createParcelsArrB(parcelsArr[1][1]);
        Parcel[] parcelsC = Parcel.createParcelsArrC(parcelsArr[2][1]);
    	int counterA = 0;
    	int counterB = 0;
    	int counterC = 0;
    	for(int i = 0; i < parcelsA.length; i++) {
    		for(int j = 0; j < placedParcels.size(); j++) {
    			if(parcelsA[i].equals(placedParcels.get(j))) counterA++;
    		}
    	}
    	for(int i = 0; i < parcelsB.length; i++) {
    		for(int j = 0; j < placedParcels.size(); j++) {
    			if(parcelsB[i].equals(placedParcels.get(j))) counterB++;
    		}
    	}
    	for(int i = 0; i < parcelsC.length; i++) {
    		for(int j = 0; j < placedParcels.size(); j++) {
    			if(parcelsC[i].equals(placedParcels.get(j))) counterC++;
    		}
    	}
    	if(counterA >= parcelsArr[0][0]) {
    		for(int i = 0; i < parcelsA.length; i++) {
        		for(int j = 0; j < inputParcelsArr.length; j++) {
        			if(parcelsA[i].equals(inputParcelsArr[j])) {
        				inputParcelsArr[j] = null;
        			}
        		}
        	}
    	}
    	if(counterB >= parcelsArr[1][0]) {
    		for(int i = 0; i < parcelsB.length; i++) {
        		for(int j = 0; j < inputParcelsArr.length; j++) {
        			if(parcelsB[i].equals(inputParcelsArr[j])) {
        				inputParcelsArr[j] = null;
        			}
        		}
        	}
    	}
    	if(counterC >= parcelsArr[2][0]) {
    		for(int i = 0; i < parcelsC.length; i++) {
        		for(int j = 0; j < inputParcelsArr.length; j++) {
        			if(parcelsC[i].equals(inputParcelsArr[j])) {
        				inputParcelsArr[j] = null;
        			}
        		}
        	}
    	}
    	inputParcelsArr = cleanNull(inputParcelsArr);
    	return inputParcelsArr;
    }
    public static Parcel[] cleanNull(Parcel[] v) {
        List<Parcel> parcels = new ArrayList<Parcel>(Arrays.asList(v));
        parcels.removeAll(Collections.singleton(null));
        return parcels.toArray(new Parcel[parcels.size()]);
    }
}
