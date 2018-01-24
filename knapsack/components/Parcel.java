package knapsack.components;

import javafx.geometry.Point3D;

import java.awt.*;
import java.util.*;

/**
 * Class defining the parcels to be placed in a truck object.
 */
public class Parcel implements Cube {
	protected ArrayList<Point3D> points;
	protected ArrayList<Edge3D> edges = new ArrayList<>();
    protected int width;
    protected int height;
    protected int length;
    private Point3D pos;
	private int value = 1;
	protected String id;

	protected String[][][] array;

    protected Color fillColor = new Color(0.5f,0.5f,0.5f,0.3f);

    /**
     * Constructor of a parcel object with given dimensions.
     * Mainly used for copy.
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
        setEdges();
	}

    /**
     * Creates a parcel with an ID. Parcel size are set in the project definition.
     * @param id The ID of the parcel to be constructed.
     */
	public Parcel(String id){
	    switch (id){
            case "A": width = 2; height = 2; length = 4; fillColor = new Color(1,0,0,0.3f); break;
            case "B": width = 2; height = 3; length = 4; fillColor = new Color(0,1,0,0.3f); break;
            case "C": width = 3; height = 3; length = 3; fillColor = new Color(0,0,1,0.3f); break;
        }
        setPoints();
	    setArray();
        setEdges();
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

    /**
     * Default constructor. Constructs a unit cube.
     */
    public Parcel() {
        this(1,1,1);
    }


    /**
     * Method to create a copy of this parcel.
     * @return A new parcel object with the exact same attributes.
     */
    public Parcel copy(){
        Parcel newP = new Parcel(width,height,length);

        String[][][] newAr = new String[width][height][length];
        for(int i=0; i<width; i++)
            for(int j=0; j<height; j++)
                for (int k=0; k<length; k++)
                    newAr[i][j][k] = array[i][j][k];
        newP.setArray(newAr);

        ArrayList<Point3D> newPoints = new ArrayList<>();
        for (Point3D pos: points) newPoints.add(new Point3D(pos.getX(),pos.getY(),pos.getZ()));
        newP.setPoints(newPoints);

        ArrayList<Edge3D> newEdges = new ArrayList<>();
        for (Edge3D e: edges) newEdges.add(e.copy());
        newP.setEdges(newEdges);

        newP.setPos(new Point3D(pos.getX(),pos.getY(),pos.getZ()));
        newP.setID(id);
        newP.setValue(value);
        newP.setColor(fillColor);
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
    }

    /**
     * Method to set the ArrayList of points defining the corners of the parcel. Mainly used to copy a Parcel object.
     * @param points The ArrayList to define the points of the corners of the parcel.
     */
    private void setPoints(ArrayList<Point3D> points) {
        this.points = points;
    }

    /**
     * Method to define the parcel as a 3D array filled with the ID.
     */
    private void setArray(){
        array = new String[width][height][length];
        for(int i=0; i<width; i++)
            for(int j=0; j<height; j++)
                for (int k=0; k<length; k++)
                    array[i][j][k] = id;
    }

    /**
     * Method used to set the array defining the parcel.
     * @param array The array that is going to define the Parcel.
     */
    private void setArray(String[][][] array) {
        this.array = array;
    }

    /**
     * Method to set the ArrayList defining the edges of the parcel.
     */
    protected void setEdges() {
        int s = (points.size()/2) - 1;
        for(int inc = 0; inc < s; inc++) {
            edges.add(new Edge3D(points.get(inc), points.get(inc+1)));
            edges.add(new Edge3D(points.get(inc), points.get(inc+s+1)));
            edges.add(new Edge3D(points.get(inc+s+1), points.get(inc+s+2)));
        }
        edges.add(new Edge3D(points.get(s),points.get(0)));
        edges.add(new Edge3D(points.get(s),points.get(s*2+1)));
        edges.add(new Edge3D(points.get(s*2+1),points.get(s+1)));
    }

    /**
     * Method to set the ArrayList defining the edges of the parcel. Mainly used for copying a Parcel object.
     * @param edges The ArrayList that is going to define the points.
     */
    private void setEdges(ArrayList<Edge3D> edges) {
        this.edges = edges;
    }

    /**
     * Method used to set the color of the Parcel filling.
     * @param c The color with which the parcel is going to get filled.
     */
    public void setColor(Color c) {
        fillColor = c;
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

    @Override
    public Color getColor() {
        return fillColor;
    }

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

    public void rotateAroundX() {
        String[][][] newPentAr = new String[array.length][array[0][0].length][array[0].length];
        for(int i=0; i<newPentAr.length; i++) {
            for(int j=0; j<newPentAr[0].length; j++) {
                for(int k=0; k<newPentAr[0][0].length; k++) {
                    newPentAr[i][j][k]=array[i][newPentAr[0][0].length-1-k][j];
                }
            }
        }
        array=newPentAr;

        width=newPentAr.length;
        height=newPentAr[0].length;
        length=newPentAr[0][0].length;
    }

    public void rotateAroundY() {
        String[][][] newPentAr = new String[array[0].length][array.length][array[0][0].length];
        for(int i=0; i<newPentAr.length; i++) {
            for(int j=0; j<newPentAr[0].length; j++) {
                for(int k=0; k<newPentAr[0][0].length; k++) {
                    newPentAr[i][j][k]=array[newPentAr[0].length-1-j][i][k];
                }
            }
        }
        array = newPentAr;

        width=newPentAr.length;
        height=newPentAr[0].length;
        length=newPentAr[0][0].length;
    }

    public void rotateAroundZ() {
        String[][][] newPentAr = new String[array[0][0].length][array[0].length][array.length];
        for(int i=0; i<newPentAr.length; i++) {
            for(int j=0; j<newPentAr[0].length; j++) {
                for(int k=0; k<newPentAr[0][0].length; k++) {
                    newPentAr[i][j][k]=array[k][j][newPentAr.length-1-i];
                }
            }
        }
        array = newPentAr;

        width=newPentAr.length;
        height=newPentAr[0].length;
        length=newPentAr[0][0].length;
    }

    @Override
    public boolean equals(Object obj) {
    	final Parcel other = (Parcel) obj;
        if(this.id.equals(other.id) && this.value == other.value) return true;
        else return false;
    }

    public String[][][] getArray() {
        return array;
    }

    public static Parcel[] createParcelsArrA(int value) {
    	Parcel[] parcelsA = new Parcel[6];
    	Parcel parcelA = new Parcel("A", value);
    	Parcel xRotParcelA = new Parcel("A", value);
		xRotParcelA.xRotate();
		Parcel yRotParcelA = new Parcel("A", value);
		yRotParcelA.yRotate();
		Parcel zRotParcelA = new Parcel("A", value);
		zRotParcelA.zRotate();
		Parcel xyRotParcelA = new Parcel("A", value);
		xyRotParcelA.xRotate();
		xyRotParcelA.yRotate();
		Parcel xzRotParcelA = new Parcel("A", value);
		xzRotParcelA.xRotate();
		xzRotParcelA.zRotate();
		parcelsA[0] = parcelA;
		parcelsA[1] = xRotParcelA;
		parcelsA[2] = yRotParcelA;
		parcelsA[3] = zRotParcelA;
		parcelsA[4] = xyRotParcelA;
		parcelsA[5] = xzRotParcelA;
		return parcelsA;
    }

    public static Parcel[] createParcelsArrB(int value) {
    	Parcel[] parcelsB = new Parcel[3];
    	Parcel parcelB = new Parcel("B", value);
    	Parcel yRotParcelB = new Parcel("B", value);
		yRotParcelB.yRotate();
		Parcel zRotParcelB = new Parcel("B", value);
		zRotParcelB.zRotate();
		parcelsB[0] = parcelB;
		parcelsB[1] = yRotParcelB;
		parcelsB[2] = zRotParcelB;
		return parcelsB;
    }

    public static Parcel[] createParcelsArrC(int value) {
    	Parcel[] parcelsC = new Parcel[1];
    	Parcel parcelC = new Parcel("C", value);
    	parcelsC[0] = parcelC;
    	return parcelsC;
    }

    /**
     * Method to output a representation of this object.
     */
    @Override
    public String toString(){
        return "Parcel[ID="+id+"]";
    }
}
