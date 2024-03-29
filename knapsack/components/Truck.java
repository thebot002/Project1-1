package knapsack.components;

import javafx.geometry.Point3D;

import java.awt.*;
import java.util.*;

/**
 * Class used to define the truck where the parcels or pentominoes are going to be placed.
 */
public class Truck implements Scene {
	private ArrayList<Cube> parcelList;
	private int length;
	private int height;
	private int width;
	private String[][][] truck;

	public static Parcel truckParcel;
	private Point3D deltaO = new Point3D(0, 0, 0);
	private boolean debug = false;

	private Color foreground;
	private Color fill;
	private Color background;
	private Color cubeColor;

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
		truckParcel = new Parcel(w, h, l);
		deltaO = (truckParcel.get(0).midpoint(truckParcel.get(6))).multiply(-1);
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
     * Default constructor for this project. Truck with size: 33 x 8 x 5 .
     */
    public Truck(){
        this(33,8,5);
    }

    /**
     * Constructor a a truck object. Used mainly by copy method.
     * @param truck The truck array that defines the truck.
     * @param list The list of all parcels stored in the truck.
     */
    private Truck(String[][][] truck, ArrayList<Cube> list){
    	this();
    	this.truck = truck;
    	this.parcelList = list;
	}

    /**
     * Method used to create an identical truck to the actual truck.
     * @return A new, identical truck.
     */
    public Truck copy(){
        String[][][] newTruck = new String[truck.length][truck[0].length][truck[0][0].length];
        for(int i=0; i<width; i++) {
            for(int j=0; j<height; j++) {
                for(int k=0; k<length; k++) {
                    newTruck[i][j][k]=truck[i][j][k];
                }
            }
        }
        ArrayList<Cube> newList = new ArrayList<>();
        for(Cube c: parcelList)
            newList.add(c.copy());
        return new Truck(newTruck, newList);
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
						if(debug) System.out.println("Position to Add"+position[0]+" "+position[1]+" "+position[2]);
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

    /**
     * Method to add a parcel object to the truck.
	 * Will Search from the origin to find the next possible position to place a parcel.
	 * If placed the parcel will be added to the truck's parcel list.
     * @param p The parcel to be added to the truck.
     */
    public boolean addParcel(Parcel p){
        for(int i = 0; i <= (width-p.getWidth()); i++){
            for(int j=0; j <= (height-p.getHeight()); j++){
                for(int k=0; k <= (length-p.getLength()); k++){
                    Point3D pos = new Point3D(i,j,k);
                    if(isPossible(p, pos)){
                        addParcel(p,pos);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Method that adds a parcel to the truck at the specified position.
     * @param p
     * @param pos
     */
	public void addParcel(Parcel p, Point3D pos){
        if(debug) System.out.println("Par Added " + pos);
        parcelList.add(p);
        p.setPos(pos);
        for(int a=0; a<p.getWidth(); a++){
            for(int b=0; b<p.getHeight(); b++){
                for(int c=0; c<p.getLength();c++){
                    if(!p.getArray()[a][b][c].equals("-"))
                        truck[a+(int)pos.getX()][b+(int)pos.getY()][c+(int)pos.getZ()] = p.getID();
                }
            }
        }
    }

	/**
	 * Method to add a parcel object to the truck.
     * Will Search from the origin to find the next possible position to place a parcel.
     * If placed the parcel will be added to the truck's parcel list.
	 * @param p The parcel to be added to the truck.
	 * @param pAr The parcel Array of the parcel to be added to the truck.
	 * @param pos The position of the parcel to be added to the truck.
	 */
	public void addParcel(Parcel p, String[][][] pAr, Point3D pos){
     	int check1=0;

     	if (pAr[0][0].length>1 ) {
     		check1=0;
     		while(pAr[0][0][check1].equals("-")) {
         		pos = new Point3D(pos.getX(),pos.getY(),pos.getZ()-1);
         		check1++;

            }
     	} else if(pAr.length>1 && pAr[0][0].length==1) {
     		check1=0;
            while(pAr[check1][0][0].equals("-")) {
         		pos = new Point3D(pos.getX()-1,pos.getY(),pos.getZ());
         		check1++;
            }
     	}

        parcelList.add(p);
        for(int a=0; a<pAr.length; a++){
            for(int b=0; b<pAr[0].length; b++){
                for(int c=0; c<pAr[0][0].length;c++){
                   if(!pAr[a][b][c].equals("-"))
                    truck[a+(int)pos.getX()][b+(int)pos.getY()][c+(int)pos.getZ()] = p.getID();
                }
            }
        }
        p.setPos(new Point3D(pos.getX(),pos.getY(),pos.getZ()));
    }

    /**
     * Method to check if a parcel can be added at a position.
     * First Checks if the Parcel can actually fit inside the truck.
     * Then Checks if anything is obstructing it.
     * @param p The Parcel to Check
     * @param pAr The Parcel Array to Check
     * @param pos The Point3D to check at
     * @return Boolean - true if the parcel can be added.
     */
    public boolean isPossible(Parcel p, String[][][] pAr, Point3D pos) {
		int check1=0;

		/* In the case that the first element in the parcel array is empty,
		we shift the parcel array by one until it is placed correctly.  */
		if (pAr[0][0].length>1) {
    		while(pAr[0][0][check1].equals("-")) {
        		pos = new Point3D(pos.getX(),pos.getY(),pos.getZ()-1);
        		check1++;

            }
    	}
        else if(pAr.length>1 && pAr[0][0].length==1) {
		    check1=0;
            while(pAr[check1][0][0].equals("-")) {
                    pos = new Point3D(pos.getX()-1,pos.getY(),pos.getZ());
                    check1++;
            }
    	}

		if(pos.getX()<0) return false;

		if(pos.getZ()<0) return false;

		if(pos.getX()+pAr.length>truck.length || pos.getY()+pAr[0].length>truck[0].length ||
				pos.getZ()+pAr[0][0].length>truck[0][0].length)
			return false;

		for (int i = 0; i < pAr.length; i++) {
			for (int j = 0; j < pAr[0].length; j++) {
				for (int k = 0; k < pAr[0][0].length; k++) {

					 if (!truck[i + (int) pos.getX()][j + (int) pos.getY()][k + (int) pos.getZ()].equals("-") &&
							 !pAr[i][j][k].equals("-"))
						return false;

				}
			}
		}
		return true;
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
        String[][][] array = p.getArray();
        for(int i=0; i<p.getWidth(); i++){
            for(int j=0; j<p.getHeight(); j++){
                for(int k=0; k<p.getLength();k++){
                    if(!truck[i+(int)pos.getX()][j+(int)pos.getY()][k+(int)pos.getZ()].equals("-") && !array[i][j][k].equals("-")) return false;
                }
            }
        }
        return true;
    }

    /**
     * Method used to return the total value of the truck.
     * @return The sum of the values of the parcels contained in the truck.
     */
    public int getValue(){
	    int total = 0;
        for (Cube p: parcelList) {
            total += ((Parcel)p).getValue();
        }
        return total;
    }

    /**
     * Method to print the actual truck in the command prompt.
     */
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

	public void updateOrigin() {
		deltaO = (truckParcel.get(0).midpoint(truckParcel.get(6))).multiply(-1);
	}

	public Point3D getOrigin() {
		return deltaO;
	}

	@Override
	public ArrayList<Cube> getCubeList() {
		return parcelList;
	}

	@Override
	public Cube getCube() {
		return truckParcel;
	}

	@Override
	public void empty() {
		parcelList = new ArrayList<>();
		truck = new String[width][height][length];
		for(int i = 0; i<width ; i++){
			for(int j=0; j<height; j++){
				for(int k=0; k<length; k++){
					truck[i][j][k] = "-";
				}
			}
		}
	}

	public void setForeground(Color foreground) {
		this.foreground = foreground;
	}

	public void setBackground(Color background) {
		this.background = background;
	}

	public void setFill(Color fill) {
		this.fill = fill;
	}

	public Color getBackground() {
		return background;
	}

	public Color getFill() {
		return fill;
	}

	public Color getForeground() {
		return foreground;
	}

	public void setCubeColor(Color cubeColor) {
		this.cubeColor = cubeColor;
	}

	public Color getCubeColor() {
		return cubeColor;
	}

	public int getParcelVolume(){
	    return getVolume()-getGapAmount();
    }

    /**
     * Method to get the amounts of gap left in the truck as a whole. (debug purpose)
     * @return The amounts of gaps in truck.
     */
    public int getGapAmount(){
        return getGapAmount(new Point3D(width,height,length));
    }

    /**
     * Method to get the amounts of gaps in the truck from the origin position until the specified point. Useful to get the amount of gaps of a smaller version of the truck.
     * @param pos The point up to which it is going to return the gaps.
     * @return The amounts of gaps between the origin and the specified point.
     */
	public int getGapAmount(Point3D pos){
        int gaps =0;
        for(int i=0; i<pos.getX(); i++){
            for(int j=0; j<pos.getY(); j++){
                for(int k=0; k<pos.getZ(); k++){
                    if(truck[i][j][k].equals("-")) gaps++;
                }
            }
        }
        return gaps;
    }
}