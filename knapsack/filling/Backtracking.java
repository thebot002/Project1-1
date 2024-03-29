package knapsack.filling;

import java.util.ArrayList;

import javafx.geometry.Point3D;
import knapsack.components.Parcel;
import knapsack.components.ParcelList;
import knapsack.components.Truck;

public class Backtracking {


	private ArrayList<Truck> truckList;
	private ArrayList<Integer> truckValueList;

    private ArrayList<Parcel> parcelList;

	private ParcelList list;

    public Backtracking(ParcelList list) {
        this.list = list;
    }

    //to be changed
    public Backtracking() {
        list = new ParcelList();
        //filling the array with the max amount of each parcel
        list.add(new Parcel("A"),82);
        list.add(new Parcel("B"),55);
        list.add(new Parcel("C"),48);
    }


    public Truck fillTruck() {
        backTracking(new Truck(),null,parcelList,0,0);
        return null;
    }

    public void setParcelList(ParcelList list) {
        this.list = list;
    }

    private void createArray(){
        Parcel parcelA = new Parcel("A");
        Parcel parcelB = new Parcel("B");
        Parcel parcelC = new Parcel("C");

        // Create a list to keep track of the parcels we have already placed
        parcelList = new ArrayList<>();

    /* Create an array with the three parcels and
       all their rotations. */
        Parcel[] parcelAr = new Parcel[10];

        // Get the rotations of the parcels
        Parcel xRotParcelA = new Parcel("A");
        xRotParcelA.xRotate();
        Parcel yRotParcelA = new Parcel("A");
        yRotParcelA.yRotate();
        Parcel zRotParcelA = new Parcel("A");
        zRotParcelA.zRotate();
        Parcel xyRotParcelA = new Parcel("A");
        xyRotParcelA.xRotate();
        xyRotParcelA.yRotate();
        Parcel xzRotParcelA = new Parcel("A");
        xzRotParcelA.xRotate();
        xzRotParcelA.zRotate();
        Parcel yRotParcelB = new Parcel("B");
        yRotParcelB.yRotate();
        Parcel zRotParcelB = new Parcel("B");
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
  * Method to fill the truck by using the backTracking algorithm.
  * @param  truck The truck that needs to be filled with parcels.
  * @param parcelAr The array that contains all the parcels and their rotations.
  * @param parcelList The list that is filled with the placed parcels.
  * @param index The index that keep trucks of the position in the parcel array.
  */
	public boolean backTracking(Truck truck, Parcel[] parcelAr, ArrayList<Parcel> parcelList, int index, int recursionCounter) {
	    int totVol = 0; // The total volume of the placed parcels.
		int totVal = 0; // The total value of the placed parcels.
		int[] position = new int[3]; // position to add the next parcel.

		// Calculates the total volume.
		for(int i=0; i<parcelList.size(); i++) {
			totVol+=parcelList.get(i).getVolume();
		}

		// Stops the recursion if the total volume gets too high.
		if(totVol>truck.getVolume()) {
						return false;

    // Stops the recursion if we have an accepted solution.
		}else if((int)(0.95*truck.getVolume())<totVol && totVol<=truck.getVolume()){

      // Adds the filled truck to the truckList.
			truckList.add(truck);

      // Calculates the total value of the given truck.
			for(int i=0; i<parcelList.size(); i++) {
				totVal+=parcelList.get(i).getValue();
			}

      // Adds the total value to a list so we can find out the best in the end
			truckValueList.add(totVal);
			return true;

		}else {

      // The main body of the recursion.

      // Find the next empty space to add a parcel
			position=truck.positionToAdd();
			Point3D p = new Point3D(position[0],position[1],position[2]);

      // If the parcel can be placed...
			if(truck.isPossible(parcelAr[index], p	)) {
				p = new Point3D(position[0],position[1],position[2]);
				Truck newTruck = truck.copy(); // Creates a copy of the truck
				newTruck.addParcel(parcelAr[index]); // Adds a new parcel to the copy

        // Creates a new parcelList
				ArrayList<Parcel> newParcelList = new ArrayList<>(parcelList);
				newParcelList.add(parcelAr[index]);


				if (backTracking(newTruck, parcelAr, newParcelList, index, recursionCounter+1))

        // If we change this to true, we will only get one solution
          return true;
			}

      /* If the parcel can't be placed we move on to the next element
         of the array.*/
					index++;
					if(index==10) {

      // If none of the array elements can be placed the recursion stops
						return false;
					}


					return backTracking(truck, parcelAr,parcelList, index, recursionCounter+1);
		}
	}
}
