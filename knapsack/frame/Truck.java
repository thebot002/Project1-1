package knapsack.frame;

import javafx.geometry.Point3D; 
import java.util.*;

public class Truck {
	public ArrayList<Parcel> ParcelList;
	private double length;
	private double height;
	private double width;

	//Create a parcel with a default position set to the origin and set its 8 vertices.
	public Truck(double l, double h, double w) {
		length = l;
		height = h;
		width = w;
	}

	public double getLength() { return length; }
	public double getHeight() { return height; }
	public double getWidth()  { return width;  }
}
