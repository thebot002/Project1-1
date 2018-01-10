package knapsack.frame;

import knapsack.components.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.image.BufferedImage;
import javafx.geometry.Point3D;
import java.awt.Point;
import java.lang.Math;


public class CubeDrawer extends JPanel {
	public static void main(String[] args) {
		new TruckViewer();
	}
	private BufferedImage image;
	private Point origin = new Point(100, 350);
	private int unit = 40;
	private ShapeColors colors;

	//Will be obsolete with new drawing method:
	private double xr = 0.5;
	private double yr = 0.5;
	//
	private int H;
	private int W;

	private final Point3D i = new Point3D(1, 0, 0);
	private final Point3D j = new Point3D(0, 1, 0);
	private final Point3D k = new Point3D(0, 0, 1);

	private int angle = 90;
	private int elevation = 35;

	private Truck truck;
	private Point3D deltaO = new Point3D(0,0,0);

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


		Parcel A = new Parcel(0.5, 0.5, 0.5);
		A.setPos(new Point3D(0,0,0));

		truck = new Truck();
		truck.addParcel(A, A.getPos());
		drawTruck();

		//origin points
		Parcel I = new Parcel(5, 0, 0);
		Parcel J = new Parcel(0, 5, 0);
		Parcel K = new Parcel(0, 0, 5);
		drawParcelPro(I, Color.YELLOW);
		drawParcelPro(J, Color.YELLOW);
		drawParcelPro(K, Color.YELLOW);
	}

	public void drawTruck() {
		Parcel t = new Parcel(truck.getWidth()/2, truck.getHeight()/2, truck.getLength()/2);
		deltaO = (t.get(0).midpoint(t.get(6))).multiply(-1);
		drawParcelPro(t);

		for(Parcel parcel : truck.parcelList) {
			drawParcelPro(parcel);
		}
	}

	public Point convertPointPro(Point3D point) {
		point = point.add(deltaO);

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
		return new Point(W/2 + ox, H/2 - oy);
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


	public void drawParcelPro(Parcel p) { drawParcelPro(p, Color.WHITE); }
	public void drawParcelPro(Parcel p, Color c) {
		Graphics2D g = (Graphics2D)image.getGraphics();
		Point3D pos = p.getPos();
		ArrayList<Point3D> points = p.getPoints();
		ArrayList<Point> pp = new ArrayList<>();

		for(Point3D point : points) {
			pp.add(convertPointPro(point.add(pos)));
		}
		drawWireFrame(pp, c);
	}

	public void drawWireFrame(ArrayList<Point> p, Color c) {
		Graphics2D g = (Graphics2D)image.getGraphics();
		g.setColor(c);
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
		g.setColor(Color.YELLOW);
		g.fillOval((int)p.getX()-3, (int)p.getY()-3, 6, 6);
	}

	private Point convertPointOrt(Point3D p) {
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

	private void drawParcelOrt(Parcel p) { drawParcelOrt(p, colors.getRand()); }
	private void drawParcelOrt(Parcel p, Color color) {
		Graphics2D g = (Graphics2D)image.getGraphics();
		ArrayList<Point> f = new ArrayList<Point>(8);

		//convert 3D points to 2D points
		for(int inc = 0; inc < 8; inc++) {
			f.add(convertPointOrt(p.get(inc)));
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

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}
}