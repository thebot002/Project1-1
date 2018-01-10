package knapsack.frame;

import knapsack.frame.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javafx.geometry.Point3D;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.Math;


public class CubeDrawer extends JPanel {
	public static void main(String[] args) {
		new TruckViewer();
	}
	private BufferedImage image;
	private Point origin = new Point(100, 350);
	private int unit = 80;
	private ShapeColors colors;

	private int H;
	private int W;

	private double height = 4; //4
	private double width = 2.5; //2.5
	private double length = 16.5; //16.5

	private final Point3D i = new Point3D(1, 0, 0);
	private final Point3D j = new Point3D(0, 1, 0);
	private final Point3D k = new Point3D(0, 0, 1);

	private int angle = 30;
	private int elevation = 35;

	//2 6 -7
	public Point3D viewPoint = new Point3D(-5, 6, -5);

	public CubeDrawer(int w, int h) {
		W = w;
		H = h;
		image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		colors = new ShapeColors();

		drawShapes();
	}

	public void drawShapes() {
		Graphics2D g = (Graphics2D)image.getGraphics();
		g.setColor(Color.WHITE);
		g.clearRect(0,0,W,H);
		repaint();

		Parcel A = new Parcel(1, 1, 1);
		A.setPos(new Point3D(0,0,0));

		Parcel B = new Parcel(1, 1, 1);
		B.setPos(new Point3D(6,0,1));

		Parcel truck = new Parcel(length, width, height);
		truck.setPos(new Point3D(0,0,0));

		drawParcelBeta(truck);
		drawParcelBeta(A);

	}

	public Point convertBeta(Point3D point) {

		double theta = Math.PI * angle / 180.0;
		double phi = Math.PI * elevation / 180.0;
		float cosT = (float)Math.cos( theta ), sinT = (float)Math.sin( theta );
		float cosP = (float)Math.cos( phi ), sinP = (float)Math.sin( phi );
		float cosTcosP = cosT*cosP, cosTsinP = cosT*sinP, sinTcosP = sinT*cosP, sinTsinP = sinT*sinP;

		float near = 10f;  // distance from eye to near plane
		float nearToObj = 20f;

		double x0 = point.getX();
		double y0 = point.getY();
		double z0 = point.getZ();

		double x1 = cosT*x0 + sinT*z0;
		double y1 = -sinTsinP*x0 + cosP*y0 + cosTsinP*z0;

		double z1 = cosTcosP*z0 - sinTcosP*x0 - sinP*y0;
		x1 = x1*near/(z1+near+nearToObj);
		y1 = y1*near/(z1+near+nearToObj);

		int ox = (int) (x1 * unit*2 + 0.5);
		int oy = (int) (y1 * unit*2 + 0.5);
		return new Point(100 + ox, H - 300 -  oy);
	}

	public void rotateLeft() {
		angle++;
		drawShapes();
	}

	public void rotateRight() {
		angle--;
		drawShapes();
	}

	public void rollUp() {
		elevation++;
		drawShapes();
	}

	public void rollDown() {
		elevation--;
		drawShapes();
	}

	public void drawParcelBeta(Parcel p) {
		Graphics2D g = (Graphics2D)image.getGraphics();
		Point3D pos = p.getPos();
		ArrayList<Point3D> points = p.getPoints();
		ArrayList<Point> pp = new ArrayList<>();

		for(Point3D point : points) {
			pp.add(convertBeta(point.add(pos)));
			drawPoint(g, convertBeta(point.add(pos)));
		}

		Color color = new Color(255,127,132);

		drawWireFrame(pp);
	}

	public void drawWireFrame(ArrayList<Point> p) {
		Graphics2D g = (Graphics2D)image.getGraphics();
		g.setColor(Color.WHITE);
		drawLine(g, p.get(0), p.get(1));
		drawLine(g, p.get(0), p.get(3));
		drawLine(g, p.get(0), p.get(4));

		drawLine(g, p.get(1), p.get(2));
		drawLine(g, p.get(1), p.get(5));

		drawLine(g, p.get(2), p.get(3));
		drawLine(g, p.get(2), p.get(6));

		drawLine(g, p.get(3), p.get(7));

		drawLine(g, p.get(4), p.get(5));
		drawLine(g, p.get(4), p.get(7));

		drawLine(g, p.get(6), p.get(5));
		drawLine(g, p.get(6), p.get(7));
	}

	private void drawPoint(Graphics g, Point p) {
		g.setColor(Color.BLACK);
		g.fillOval((int)p.getX()-3, (int)p.getY()-3, 6, 6);
	}

	private Point convertPoint(Point3D p) {
		/*	Flat perspective
			converts a Point3D to a 2d Point by adding to its x and y positions to force perspective.
			also inverts sign of y value to draw as if in the top-right quadrant
			also moves point so its relative to an origin point */

		double x = p.getX();
		double y = p.getY();
		double z = p.getZ();

		x = (x * unit) + (z * unit/2);
		y = (y * unit) + (z * unit/2);
		int ax = origin.x + (int)x;
		int ay = origin.y - (int)y;

		return new Point(ax, ay);
	}

	private void drawLine(Graphics g, Point a, Point b) {
		g.drawLine((int)a.getX(), (int)a.getY(), (int)b.getX(), (int)b.getY());
	}

	private void fillPoly(Graphics g, Point a, Point b, Point c, Point d) {
		//Fills a poly using 4 Point Objects
		int[] xPoints = {(int)a.getX(), (int)b.getX(), (int)c.getX(), (int)d.getX()};
		int[] yPoints = {(int)a.getY(), (int)b.getY(), (int)c.getY(), (int)d.getY()};

		g.fillPolygon(xPoints, yPoints, 4);
	}

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
			//drawLine(g, j.multiply(h).add(k.multiply(inc)), k.multiply(inc));
		}

		for(int inc = 1; inc <= h; inc++) {
			//drawLine(g, k.multiply(w).add(j.multiply(inc)), j.multiply(inc));
		}

		for(int inc = 0; inc <= l; inc++) {
			//drawLine(g, k.multiply(w).add(i.multiply(inc)), i.multiply(inc));
		}

		for(int inc = 0; inc <= w; inc++) {
			//drawLine(g, i.multiply(l).add(k.multiply(inc)), k.multiply(inc));
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