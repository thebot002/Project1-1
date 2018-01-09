package knapsack.pentominoe3D;

import javafx.geometry.Point3D;
import pentominoe.PentrisBoard;

import java.util.*;

public class Truck {
	public ArrayList<Parcel> parcelList;
	private int length;
	private int height;
	private int width;
	private int[][][] truck;

	//Create a parcel with a default position set to the origin and set its 8 vertices.
	public Truck(double l, double h, double w) {
	    parcelList = new ArrayList<>();
		length = (int) (l/0.5);
		height = (int) (h/0.5);
		width = (int) (w/0.5);
		truck = new int[width][height][length];
	}

	public double getLength() { return length; }
	public double getHeight() { return height; }
	public double getWidth()  { return width;  }

    public void addParcel(Parcel p, double x, double y, double z){
	    parcelList.add(p);

    }
}
