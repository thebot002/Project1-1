package knapsack.components;

import javafx.geometry.Point3D;

import java.util.*;

/**
 * Class used to define the truck where the parcels or pentominoes are going to be placed.
 */
public class Truck {
	private ArrayList<Parcel> parcelList;
	private int length;
	private int height;
	private int width;
	private int gaps;
	private String[][][] truck;
 
	private boolean debug = false;

	/**
	 *Create a parcel with a default position set to the origin and set its 8 vertices.
     *Object constructor.
     *
     * @param l The length of the Truck.
     * @param h The height of the Truck.
     * @param w The width of the Truck
	 */
	public Truck(double w, double h, double l) {
	    parcelList = new ArrayList<>();
		length = (int) (l);
		height = (int) (h);
		width = (int) (w);
		truck = new String[width][height][length];
		for(int i = 0; i<width ; i++){
		    for(int j=0; j<height; j++){
		        for(int k=0; k<length; k++){
		            truck[i][j][k] = "-";
                }
            }
        }
	}

    /**
     * Default constructor for this project. Truck with size: 16.5 x 4.0 x 2.5 .
     */
    public Truck(){
        this(33,8,5);
    }

    public Truck(String[][][] truck, ArrayList<Parcel> list){
    	this();
    	this.truck = truck;
    	this.parcelList = list;
	}


	//finds the position to add at, currently not used but am planning to - Nic
	public int[] positionToAdd() {
		int[] position = new int[3];

		for(int i=0; i<width; i++) {
			for(int j=0; j<height; j++) {
				for(int k=0; k<length; k++) {
					if(truck[i][j][k].equals("-")) {
						position[0]=i;
						position[1]=j;
						position[2]=k;
						if(debug) System.out.println("Position to ADd"+position[0]+" "+position[1]+" "+position[2]);
						return position;
					}
				}
			}
		}
		return position;
	}

    /**
     * Method to get the width of the truck. (x axis)
     * @return The width of the truck.
     */
	public double getWidth()  { return (1.0*width);  }
	/**
     * Method to get the height of the truck. (y axis)
     * @return The height of the truck.
     */
    public double getHeight() { return (1.0*height); }
	/**
	 * Method to get the length of the truck. (z axis)
	 * @return The length of the truck.
	 */
	public double getLength() { return (1.0*length); }

    /**
     * Method to get the volume of the truck
     * @return The volume of the truck.
     */
    public int getVolume() {return height*width*length;}

    public ArrayList<Parcel> getParcelList() {
        return parcelList;
    }

    /**
     * Method to add a parcel object to the truck.
	 * Will Search from the origin to find the next possible position to place a parcel.
	 * If placed the parcel will be added to the truck's parcel list.
     * @param p The parcel to be added to the truck.
     */
    public boolean addParcel(Parcel p){
        for(int i = 0; i <= (width-p.getWidth()) ; i++){
            for(int j=0; j <= (height-p.getHeight()); j++){
                for(int k=0; k <= (length-p.getLength()); k++){

                    if(isPossible(p, new Point3D(i,j,k))){
						if(debug) System.out.println("Par Added " + new Point3D(i,j,k));
                        parcelList.add(p);
						p.setPos(new Point3D(i,j,k).multiply(1));
                        for(int a=0; a<p.getWidth(); a++){
                            for(int b=0; b<p.getHeight(); b++){
                                for(int c=0; c<p.getLength();c++){
                                    truck[a+i][b+j][c+k] = p.getID();
                                }
                            }
                        }
                        return true;
                    }
                }
            }
        }
        return false;
	}

	/**
	 * Method to check if a parcel can be added at a position.
	 * First Checks if the Parcel can actually fit inside the truck.
	 * Then Checks if anything is obstructing it.
	 * @param p The Parcel to Check
	 * @param pos The Point3D to check at
	 * @return Boolean - true if the parcel can be added.
	 */

	public boolean isPossible(Parcel p, Point3D pos){
        if((p.getWidth() + pos.getX()) > (getWidth())) {
            if(debug) System.out.println("Outside Truck - X");
            return false;
        }
        if((p.getHeight() + pos.getY()) > (getHeight())) {
            if(debug) System.out.println("Outside Truck - Y");
            return false;
        }
        if((p.getLength() + pos.getZ()) > (getLength())) {
            if(debug) System.out.println("Outside Truck - Z " + pos.getZ());
            return false;
        }
        if(debug) System.out.println("Par fits, ckng collision");
        for(int i=0; i<p.getWidth(); i++){
            for(int j=0; j<p.getHeight(); j++){
                for(int k=0; k<p.getLength();k++){
                    if(!truck[i+(int)pos.getX()][j+(int)pos.getY()][k+(int)pos.getZ()].equals("-")) return false;
                }
            }
        }
        return true;
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

    /**
     * Method used to return the total value of the truck.
     * @return The sum of the values of the parcels contained in the truck.
     */
    public int getValue(){
	    int total = 0;
        for (Parcel p: parcelList) {
            total += p.getValue();
        }
        return total;
    }

	public Truck copyTruck(){
		String[][][] newTruck = new String[truck.length][truck[0].length][truck[0][0].length];
		for(int i=0; i<width; i++) {
			for(int j=0; j<height; j++) {
				for(int k=0; k<length; k++) {
					newTruck[i][j][k]=truck[i][j][k];
				}
			}
		}
		ArrayList<Parcel> newList = new ArrayList<>();
		for(Parcel p: parcelList) newList.add(p);
		return new Truck(newTruck, newList);
	}

	public void printTruck() {
		System.out.println("NEW TRUCK");
		for(int i=0; i<truck[0][0].length; i++) {
			for(int j=0; j<truck.length; j++) {
				for(int k=0; k<truck[0].length; k++) {
					System.out.print(truck[j][k][i]+ " ");
				}
				System.out.println("");
			}
			System.out.println("");
		}
	}

    /**
     * Method used to have a String representation of the truck to be printed in the command line.
     * @return A string version of the content of the truck.
     */
	public String toString(){
		String truckString = "";
		//special assignment of i,j,k value for readability purpose.
		for(int i=0; i<length; i++) {
			for(int j=0; j<width; j++) {
				for(int k=0; k<height; k++) {
					truckString += truck[j][k][i]+ " ";
				}
				truckString += "\n";
			}
			truckString += "\n";
		}
		return truckString;
	}

	public void setParcelList(ArrayList<Parcel> parcelListInput) {
		parcelList = parcelListInput;
	}
	
	public ArrayList<Parcel> getParcelList() {
		return parcelList;
	}

    /**
     * Method to get the amounts of gap left in the truck. (debug purpose)
     * @return The amounts of gaps in truck.
     */
	public int getGapAmount(){
		int gaps = 0;
		for(int i=0; i<width; i++){
			for(int j=0; j<height; j++){
				for(int k=0; k<length; k++){
					if(truck[i][j][k].equals("-")) gaps++;
				}
			}
		}
		return gaps;
	}
}