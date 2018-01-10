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

	//swing
    private int H;
    private int W;
	private BufferedImage image;
	private int unit = 15; //scaling factor

    //
    private Parcel truckParcel;
	private int angle = 90;
	private int elevation = 35;
	private Truck truck;
	private Point3D deltaO = new Point3D(0,0,0);

	public CubeDrawer(int w, int h) {
		W = w;
		H = h;
		image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

		drawShapes();
	}

	private void drawShapes() {
		Parcel A = new Parcel("A");
		truck = new Truck();
		truckParcel = new Parcel(truck.getWidth(), truck.getHeight(), truck.getLength());
		truck.addParcel(A, A.getPos());
		drawTruck();
	}

	private void drawTruck() {
        Graphics2D g = (Graphics2D)image.getGraphics();
        g.setColor(Color.WHITE);
        g.clearRect(0,0,W,H);

		deltaO = (truckParcel.get(0).midpoint(truckParcel.get(6))).multiply(-1);
		drawParcelPro(truckParcel);

		for(Parcel parcel : truck.parcelList) {
			drawParcelPro(parcel);
		}

		//Origin and Rotation point, Indication/Axis Lines
		drawDebug();
		repaint();
	}

	private void drawDebug() {
        Parcel I = new Parcel(3, 0, 0);
        Parcel J = new Parcel(0, 3, 0);
        Parcel K = new Parcel(0, 0, 3);
        I.setPos(deltaO.multiply(-1));
        J.setPos(deltaO.multiply(-1));
        K.setPos(deltaO.multiply(-1));
        drawParcelPro(I, Color.RED);
        drawParcelPro(J, Color.RED);
        drawParcelPro(K, Color.RED);
        drawParcelPro(new Parcel(5, 0, 0), Color.YELLOW);
        drawParcelPro(new Parcel(0, 5, 0), Color.YELLOW);
        drawParcelPro(new Parcel(0, 0, 5), Color.YELLOW);
    }

	private Point convertPointPro(Point3D point) {
		point = point.add(deltaO);

		double theta = Math.PI * angle / 180.0;
		double phi = Math.PI * elevation / 180.0;
		float cosT = (float)Math.cos( theta ), sinT = (float)Math.sin( theta );
		float cosP = (float)Math.cos( phi ), sinP = (float)Math.sin( phi );
		float cosTcosP = cosT*cosP, cosTsinP = cosT*sinP, sinTcosP = sinT*cosP, sinTsinP = sinT*sinP;

		float near = 20f;  // distance from eye to near plane
		float nearToObj = 30f;

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
		drawTruck();
	}
	public void rotateRight() {
		angle--;
		drawTruck();
	}
	public void rollUp() {
		elevation++;
		drawTruck();
	}
	public void rollDown() {
		elevation--;
		drawTruck();
	}


	private void drawParcelPro(Parcel p) { drawParcelPro(p, Color.WHITE); }
	private void drawParcelPro(Parcel p, Color c) {
		Graphics2D g = (Graphics2D)image.getGraphics();
		Point3D pos = p.getPos();
		ArrayList<Point3D> points = p.getPoints();
		ArrayList<Point> pp = new ArrayList<>();

		for(Point3D point : points) {
			pp.add(convertPointPro(point.add(pos)));
		}
		drawWireFrame(pp, c);
	}

	private void drawWireFrame(ArrayList<Point> p, Color c) {
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

	private void drawLine(Graphics g, Point a, Point b) {
		g.drawLine((int)a.getX(), (int)a.getY(), (int)b.getX(), (int)b.getY());
	}

	private void fillPoly(Graphics g, Point a, Point b, Point c, Point d) {
		//Fills a poly using 4 Point Objects
		int[] xPoints = {(int)a.getX(), (int)b.getX(), (int)c.getX(), (int)d.getX()};
		int[] yPoints = {(int)a.getY(), (int)b.getY(), (int)c.getY(), (int)d.getY()};

		g.fillPolygon(xPoints, yPoints, 4);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}
}