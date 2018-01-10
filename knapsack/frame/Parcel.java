package knapsack.frame;

import knapsack.*;
import javafx.geometry.Point3D;
import java.util.*;

public class Parcel {
	public static void main(String[] args) {}

	public ArrayList<Point3D> points;
	private double length;
	private double height;
	private double width;
	private Point3D pos;
	private int[][][] parcelArr;

	//Create a parcel with a default position set to the origin and set its 8 vertices.
	public Parcel(double l, double h, double w) {
		length = l;
		height = h;
		width = w;

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

	public Parcel(int[][][] parcelArr ){
		this.parcelArr=parcelArr;
	}

	public void xRot(){
		int[][][] newParcelArr = new int[parcelArr.length][parcelArr[0][0].length][parcelArr[0].length];
		for(int i=0; i<parcelArr.length; i++){
			for(int j=0; j<parcelArr[0].length; j++){
				for(int k=0; k<parcelArr[0][0].length; k++){
					newParcelArr[i][newParcelArr[0].length-k][j]=parcelArr[i][j][k];
				}
			}
		}
	}

	public void yRot(){
		int[][][] newParcelArr = new int[parcelArr[0][0].length][parcelArr[0].length][parcelArr.length];
		for(int i=0; i<parcelArr.length; i++){
			for(int j=0; j<parcelArr[0].length; j++){
				for(int k=0; k<parcelArr[0][0].length; k++){
					newParcelArr[k][j][newParcelArr[0][0].length-i]=parcelArr[i][j][k];
				}
			}
		}
	}


	public void zRot(){
		int[][][] newParcelArr = new int[parcelArr[0].length][parcelArr.length][parcelArr[0][0].length];
		for(int i=0; i<parcelArr.length; i++){
			for(int j=0; j<parcelArr[0].length; j++){
				for(int k=0; k<parcelArr[0][0].length; k++){
					newParcelArr[newParcelArr.length-j][i][k]=parcelArr[i][j][k];
				}
			}
		}
	}

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
