package knapsack.components;

import javafx.geometry.Point3D;

import java.util.*;

/**
 * Class used to define the truck where the parcels or pentominoes are going to be placed.
 */
public class Truck {
	public ArrayList<Parcel> parcelList;
	private int length;
	private int height;
	private int width;
	private String[][][] truck;

	/**
	 *Create a parcel with a default position set to the origin and set its 8 vertices.
     *Object constructor.
     *
     * @param l The length of the Truck.
     * @param h The height of the Truck.
     * @param w The width of the Truck
	 */
	public Truck(double l, double h, double w) {
	    parcelList = new ArrayList<>();
		length = (int) (l/0.5);
		height = (int) (h/0.5);
		width = (int) (w/0.5);
		truck = new String[width][height][length];
		for(int i = 0; i<truck.length ; i++){
		    for(int j=0; j<truck[0].length; j++){
		        for(int k=0; k<truck[0][0].length; k++){
		            truck[i][j][k] = "-";
                }
            }
        }
	}

    /**
     * Default constructor for this project. Truck with size: 16.5 x 4.0 x 2.5 .
     */
	public Truck(){
	    this(2.5,4.0,16.5);
    }

    /**
     * Method to get the length of the truck. (z axis)
     * @return The length of the truck.
     */
	public int getLength() { return length; }
    /**
     * Method to get the height of the truck. (y axis)
     * @return The height of the truck.
     */

	public int getWidth()  { return width;  }
    /**
     * Method to get the width of the truck. (x axis)
     * @return The width of the truck.
     */
    public int getHeight() { return height; }

    /**
     * Method to add a parcel object to the truck.
     * @param p The parcel to be added to the truck.
     * @param pos The position where the parcel will be added.
     */
    public void addParcel(Parcel p, Point3D pos){
	    parcelList.add(p);
	    int x = (int) (pos.getX()/0.5);
        int y = (int) (pos.getY()/0.5);
        int z = (int) (pos.getZ()/0.5);
        for(int i=0; i<p.getWidth(); i++){
            for(int j=0; j<p.getHeight(); j++){
                for(int k=0; k<p.getLength();k++){
                    truck[x+i][y+j][z+k] = p.getID();
                }
            }
        }
	}

    /**
     * Method to remove a parcel from the truck.
     * @param p The parcel to be removed.
     */
	public void removeParcel(Parcel p){
	    parcelList.remove(p);
        Point3D pos = p.getPos();
        int x = (int) (pos.getX()/0.5);
        int y = (int) (pos.getY()/0.5);
        int z = (int) (pos.getZ()/0.5);
        for(int i=0; i<p.getWidth(); i++){
            for(int j=0; j<p.getHeight(); j++){
                for(int k=0; k<p.getLength();k++){
                    truck[x+i][y+j][z+k] = "-";
                }
            }
        }
    }
}