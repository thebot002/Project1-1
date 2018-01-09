package knapsack.frame;

import knapsack.frame.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javafx.geometry.Point3D;
import java.awt.Point;

public class CubeDrawer extends JPanel {
	private BufferedImage image;
	private Point origin = new Point(100, 350);
	private int unit = 50;
	private double xr = 0.5;
	private double yr = 0.5;
	private ShapeColors colors;

	private double height = 4; //4
	private double width = 2.5; //2.5
	private double length = 16.5; //16.5

	private final Point3D i = new Point3D(1, 0, 0);
	private final Point3D j = new Point3D(0, 1, 0);
	private final Point3D k = new Point3D(0, 0, 1);

	public CubeDrawer(int w, int h) {
		image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		colors = new ShapeColors();

		//drawGrid();

		Parcel bottom = new Parcel(length, 0, width); //bounds of truck represented by "parcels"
		Parcel front = new Parcel(0, height, width);

		Parcel A = new Parcel(1.0, 1.0, 2.0);
		Parcel B = new Parcel(1.0, 1.5, 2.0);
		Parcel C = new Parcel(1.5, 1.5, 1.5);

		bottom.setPos(new Point3D(0,0,0));
		front.setPos(new Point3D(0,0,0));

		A.setPos(new Point3D(0,0,1));
		B.setPos(new Point3D(5,2,0));
		C.setPos(new Point3D(8,2,0));

		drawParcel(front, Color.WHITE);
		drawParcel(bottom, Color.WHITE);

		drawGrid();

		drawParcel(A, Color.RED);
		drawParcel(B, Color.BLUE);
		drawParcel(C, Color.YELLOW);


	}

	private void drawPoint(Graphics g, Point3D point) {
		Point p = convertPoint(point);
		g.fillOval((int)p.getX()-3, (int)p.getY()-3, 6, 6);
	}

	//Flat perspective
	//converts a Point3D to a 2d Point by adding to its x and y positions to force perspective.
	//also inverts sign of y value to draw as if in the top-right quadrant
	//also moves point so its relative to an origin point
	private Point convertPoint(Point3D p) {
		double x = p.getX();
		double y = p.getY();
		double z = p.getZ();

		x = (x * unit) + (z * unit/2);
		y = (y * unit) + (z * unit/2);
		int ax = origin.x + (int)x;
		int ay = origin.y - (int)y;

		return new Point(ax, ay);
	}

	private void drawLine(Graphics g, Point3D A, Point3D B) {
		Point a = convertPoint(A);
		Point b = convertPoint(B);
		g.drawLine((int)a.getX(), (int)a.getY(), (int)b.getX(), (int)b.getY());
	}

	//Fills a poly using 4 Point Objects
	private void fillPoly(Graphics g, Point a, Point b, Point c, Point d) {
		int[] xPoints = {(int)a.getX(), (int)b.getX(), (int)c.getX(), (int)d.getX()};
		int[] yPoints = {(int)a.getY(), (int)b.getY(), (int)c.getY(), (int)d.getY()};

		g.fillPolygon(xPoints, yPoints, 4);
	}

	//draws a parcel at its location
	private void drawParcel(Parcel p) {
		drawParcel(p, colors.getRand());
	}

	private void drawParcel(Parcel p, Color color) {
		Graphics2D g = (Graphics2D)image.getGraphics();
		ArrayList<Point> f = new ArrayList<Point>(8);

		//convert 3D points to 2D points
		for(int inc = 0; inc < 8; inc++) {
			f.add(convertPoint(p.get(inc)));
		}

		//draw 3 visable faces of Parcel
		g.setColor(color.darker().darker());
		fillPoly(g, f.get(0), f.get(1), f.get(2), f.get(3));
		g.setColor(color.darker());
		fillPoly(g, f.get(2), f.get(3), f.get(7), f.get(6));
		g.setColor(color);
		fillPoly(g, f.get(1), f.get(5), f.get(6), f.get(2));

		repaint();
		g.dispose();
	}

	//draws the grid lines to help show distance and perspective
	private void drawGrid() {
		Graphics2D g = (Graphics2D)image.getGraphics();
		g.setColor(Color.GRAY);

		int l = (int)Math.ceil(length);
		int w = (int)Math.ceil(width);
		int h = (int)Math.ceil(height);


		for(int inc = 0; inc <= w; inc++) {
			drawLine(g, j.multiply(h).add(k.multiply(inc)), k.multiply(inc));
		}

		for(int inc = 1; inc <= h; inc++) {
			drawLine(g, k.multiply(w).add(j.multiply(inc)), j.multiply(inc));
		}

		for(int inc = 0; inc <= l; inc++) {
			drawLine(g, k.multiply(w).add(i.multiply(inc)), i.multiply(inc));
		}

		for(int inc = 0; inc <= w; inc++) {
			drawLine(g, i.multiply(l).add(k.multiply(inc)), k.multiply(inc));
		}

		repaint();
		g.dispose();
	}


	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}
}
