package knapsack.components;

import javafx.geometry.Point3D;
import java.util.*;

/**
 * Class defining the parcels to be placed in a truck object.
 */
public class Parcel {
	protected ArrayList<Point3D> points;
	protected ArrayList<Edge3D> edges = new ArrayList<>();
	protected int length;
	protected int height;
	protected int width;
	private Point3D pos;
	private int value = 1;
	private String id;

	private int state = 0;
    private ArrayList<ArrayList<Point3D>> rotations;
    /**
     * Create a parcel with a default position set to the origin and set its 8 vertices.
     * Constructor of a parcel object with given dimensions.
     * @param w The width of the parcel. (x axis)
     * @param h The height of the parcel. (y axis)
     * @param l The length of the parcel. (z axis)
     */
	public Parcel(double w, double h, double l) {
        width = (int)(w);
        height = (int)(h);
        length = (int)(l);
        id = "u";
        setPoints();
	}

    /**
     * Creates a parcel with an ID. Parcel size are set in the project definition.
     * @param id The ID of the parcel to be constructed.
     */
	public Parcel(String id){
	    switch (id){
            case "A": width = 2; height = 2; length = 4; setPoints(); break;
            case "B": width = 2; height = 3; length = 4; setPoints(); break;
            case "C": width = 3; height = 3; length = 3; setPoints(); break;
        }
        this.id = id;
    }

    /**
     * Creates a parcel with an ID and a value.
     * @param id The ID of the parcel to be constructed.
     * @param value The value to be given to the parcel.
     */
    public Parcel(String id, int value){
	    this(id);
	    setValue(value);
    }

    public Parcel() {
        this(1,1,1);
    }

    /**
     * Method to create a copy of this parcel.
     * @return A new parcel object with the exact same attributes.
     */
    public Parcel copy(){
        Parcel newP = new Parcel(width,height,length);
        newP.setPos(pos);
        newP.setID(id);
        newP.setValue(value);
        return newP;
    }

    /**
     * Method that sets the points of each corners of the parcel.
     */
    private void setPoints() {
        pos = new Point3D(0,0,0);

        points = new ArrayList<>();
        points.add(new Point3D(0, 0, 0));
        points.add(new Point3D(0, height, 0));
        points.add(new Point3D(width, height, 0));
        points.add(new Point3D(width, 0, 0));

        points.add(new Point3D(0, 0, length));
        points.add(new Point3D(0, height, length));
        points.add(new Point3D(width, height, length));
        points.add(new Point3D(width, 0, length));


        edges.add(new Edge3D(points.get(0),points.get(1)));
        edges.add(new Edge3D(points.get(1),points.get(2)));
        edges.add(new Edge3D(points.get(2),points.get(3)));
        edges.add(new Edge3D(points.get(3),points.get(0)));

        edges.add(new Edge3D(points.get(0),points.get(4)));
        edges.add(new Edge3D(points.get(1),points.get(5)));
        edges.add(new Edge3D(points.get(2),points.get(6)));
        edges.add(new Edge3D(points.get(3),points.get(7)));

        edges.add(new Edge3D(points.get(4),points.get(5)));
        edges.add(new Edge3D(points.get(5),points.get(6)));
        edges.add(new Edge3D(points.get(6),points.get(7)));
        edges.add(new Edge3D(points.get(7),points.get(4)));
    }

    /**
     * Method to set the value of this parcel.
     * @param value The value to be given.
     */
	public void setValue(int value) { this.value = value; }

    /**
     * Method to set the position of the parcel in the 3D space.
     * @param pos A point in the 3D space where to place the parcel.
     */
	public void setPos(Point3D pos) { this.pos = pos; }

    /**
     * Method to translate the parcel along a given vector.
     * @param vector The vector along which to move the parcel.
     */
	public void translate(Point3D vector) { pos = pos.add(vector); }

	/**
	 * Method to rotate the parcel along the x Axis
	 */
	public void xRotate() {
		int newWidth=height;
		height=width;
		width=newWidth;
	}
	
	/**
	 * Method to rotate the parcel along the y Axis
	 */
	public void yRotate() {
		int newWidth=length;
		length=width;
		width=newWidth;
	}
	
	/**
	 * Method to rotate the parcel along the y Axis
	 */
	public void zRotate() {
		int newHeight=length;
		length=height;
		height=newHeight;
	}
	
    /**
     * Method to get a list of the coordinates of the corners of this parcel.
     * @return An ArrayList containing 3D points.
     */
	public ArrayList<Point3D> getPoints() {
		return points;
	}

    /**
     * Returns a HashMap of connected points of a Parcel
     * @return a HashMap<Point3D, Point3D> of the edges.
     */
	public ArrayList<Edge3D> getEdges() {return edges;}

    /**
     * Method to get the width of the parcel. (x axis)
     * @return The width of the parcel.
     */
    public double getWidth()  { return (1.0*width);  }

    /**
     * Method to get the height of the parcel. (y axis)
     * @return The height of the parcel.
     */
    public double getHeight() { return (1.0*height); }

    /**
     * Method to get the length of the parcel. (z axis)
     * @return The length of the parcel.
     */
    public double getLength() { return (1.0*length); }

    /**
     * Method to get the volume of the parcel.
     * @return The volume of the parcel.
     */
    public int getVolume() {return height*length*width; }

    /**
     * Method to get the position of the parcel in the 3D space.
     * @return A 3D point.
     */
    public Point3D getPos() {return pos;}

    /**
     * Return a vertex of the Parcel relative to its position
     */
    public Point3D get(int index) {
		return (points.get(index)).add(pos);
	}

    /**
     * Method to get the ID of the parcel.
     * @return A string ID of this parcel.
     */
	public String getID(){
	    return id;
    }

    /**
     * Method to set the ID of the parcel. (Used for building a copy of this parcel)
     * @param id The parcel ID.
     */
    protected void setID(String id){
	    this.id = id;
    }

    /**
     * Method to get the value of the parcel.
     * @return The value of this parcel.
     */
    public int getValue(){
        return value;
    }

    public void rotateAroundX(){
        int temp = length;
        length = height;
        height = temp;
        setPoints();
    }

    public void rotateAroundY(){
        int temp = length;
        length = width;
        width = temp;
        setPoints();
    }

    public void rotateAroundZ(){
        int temp = width;
        width = height;
        height = temp;
        setPoints();
    }

    protected void setSize(int w, int h, int l) {
        width = w;
        height = h;
        length = l;
    }

    public boolean nextState(){
        state ++;
        if(state == 1){

            return false;
        }
        else{
            if(state%2 != 0) rotateAroundX();
            else rotateAroundZ();
        }
        return true;
    }
}
