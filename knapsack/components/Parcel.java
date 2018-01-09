package knapsack.components;

import javafx.geometry.Point3D; 
import java.util.*;

public class Parcel {
	public ArrayList<Point3D> points;
	private int length;
	private int height;
	private int width;
	private Point3D pos;
	private int value = 1;
	private String id;

	//Create a parcel with a default position set to the origin and set its 8 vertices.
	public Parcel(double l, double h, double w) {
		length = (int)(l/0.5);
		height = (int)(h/0.5);
		width = (int)(w/0.5);
		setPoints();
	}
	public Parcel(String id){
	    switch (id){
            case "A": length = 4; width = 2; height = 2; break;
            case "B": length = 4; width = 2; height = 3; break;
            case "C": length = 3; width = 3; height = 3; break;
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

	public int getLength() { return length; }
	public int getHeight() { return height; }
	public int getWidth()  { return width;  }
	public int getArea()   { return length*width*height; }
	public Point3D getPos() {return pos;}

	//Return a vertex of the Parcel relative to its position
	public Point3D get(int index) {
		return (points.get(index)).add(pos);
	}

	public String getID(){
	    return id;
    }
}
