package knapsack.components;

import javafx.geometry.Point3D;

import java.util.*;

public class Truck {
	public ArrayList<Parcel> parcelList;
	private int length;
	private int height;
	private int width;
	private String[][][] truck;

	//Create a parcel with a default position set to the origin and set its 8 vertices.
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

	public Truck(){
	    this(2.5,4.0,16.5);
    }

	public int getLength() { return length; }
	public int getHeight() { return height; }
	public int getWidth()  { return width;  }

    public void addParcel(Parcel p, Point3D pos){
	    parcelList.add(p);
	    int x = (int) (pos.getX()/0.5);
        int y = (int) (pos.getY()/0.5);
        int z = (int) (pos.getZ()/0.5);
        for(int i=0; i<p.getArea(); i++){
            truck[x][y][z] = p.getID();
        }
	}
}
