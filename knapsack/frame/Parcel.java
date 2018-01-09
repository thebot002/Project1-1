package knapsack.frame;

import javafx.geometry.Point3D;
import java.util.*;

	public class Parcel {
		public ArrayList<Point3D> points;
		private double length;
		private double height;
		private double width;
		private Point3D pos;
		private double value;

	//Create a parcel with a default position set to the origin and set its 8 vertices and a value.
	public Parcel(double l, double h, double w, double val) {
		length = l;
		height = h;
		width = w;
		value = val;

		pos = new Point3D(0,0,0);

		points = new ArrayList<Point3D>();
		points.add(new Point3D(0, 0, 0));
		points.add(new Point3D(0, h, 0));
		points.add(new Point3D(l, h, 0));
		points.add(new Point3D(l, 0, 0));

		points.add(new Point3D(0, 0, w));
		points.add(new Point3D(0, h, w));
		points.add(new Point3D(l, h, w));
		points.add(new Point3D(l, 0, w));
	}

	public Parcel(double l, double h, double w) {
		this(l, h, w, 1);
	}

	public void setValue(double value) { this.value = value; }

	public void setPos(Point3D pos) { this.pos = pos; }

	public void translate(Point3D vector) { pos = pos.add(vector); }

	//return all vertices of Parcel
	public ArrayList<Point3D> getPoints() {
		return points;
	}

	public double getLength() { return length; }
	public double getHeight() { return height; }
	public double getWidth()  { return width;  }
	public Point3D getPos() {return pos;}

	//Return a vertex of the Parcel relative to its position
	public Point3D get(int index) {
		return (points.get(index)).add(pos);
	}
}