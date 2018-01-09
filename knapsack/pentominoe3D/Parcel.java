package knapsack.pentominoe3D;

import javafx.geometry.Point3D; 
import java.util.*;

public class Parcel {
	public ArrayList<Point3D> points;
	private double length;
	private double height;
	private double width;
	private Point3D pos;
	private int value = 1;
	private String id;

	//Create a parcel with a default position set to the origin and set its 8 vertices.
	public Parcel(double l, double h, double w) {
		length = l;
		height = h;
		width = w;
		setPoints();
	}
	public Parcel(String id){
	    switch (id){
            case "A": length = 2.0; width = 1.0; height = 1.0; break;
            case "B": length = 2.0; width = 1.0; height = 1.5; break;
            case "C": length = 1.5; width = 1.5; height = 1.5; break;
        }
        this.id = id;
	    setPoints();
    }

    private void setPoints(){
        pos = new Point3D(0,0,0);

        points = new ArrayList<Point3D>();
        points.add(new Point3D(0, 0, 0));
        points.add(new Point3D(0, height, 0));
        points.add(new Point3D(length, height, 0));
        points.add(new Point3D(length, 0, 0));

        points.add(new Point3D(0, 0, width));
        points.add(new Point3D(0, height, width));
        points.add(new Point3D(length, height, width));
        points.add(new Point3D(length, 0, width));

    }

	public void setValue(int value) { this.value = value; }

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
