package knapsack.filling;

import javafx.geometry.Point3D;
import knapsack.components.Parcel;
import knapsack.components.Truck;

import java.util.ArrayList;

public class BruteForce {
    private static Parcel[] parcelAr;

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
        parcelAr = new Parcel[10];

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
                    while(!truck.isPossible(parcelAr[index], new Point3D(i, j, k)) && index<9) {
                        index++;
                    }
                    if(truck.addParcel(parcelAr[index].copy())){
                        parcelList.add(parcelAr[index].copy());
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
