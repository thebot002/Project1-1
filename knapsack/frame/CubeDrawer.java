package knapsack.frame;

import knapsack.components.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.image.BufferedImage;
import javafx.geometry.Point3D;
import java.awt.Point;
import java.lang.Math;

/**
 *Will Create a scene to draw a truck, a Truck Object can be passed on creation to be drawn.
 */
public class CubeDrawer extends JPanel {
    public static void main(String[] args) {
        new TruckViewer();
    }

    //swing
    private int H;
    private int W;
    private BufferedImage image;
    private int unit = 13; //scaling factor


    private Parcel truckParcel;
    private int angle = 90;
    private int elevation = 35;
    private Truck truck;
    private Point3D deltaO = new Point3D(0, 0, 0);
    private Boolean debug = true;


    public CubeDrawer(int w, int h) {
        this(w, h, new Truck());
    }

    public CubeDrawer(int w, int h, Truck truck) {
        this.truck = truck;
        W = w;
        H = h;
        image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        truckParcel = new Parcel(truck.getWidth(), truck.getHeight(), truck.getLength());
        populateTruck();
        renderScene();
    }

    private void populateTruck() {
        for(int i=0; i<11; i++){
            Parcel A = new Parcel("A");
            Parcel B = new Parcel("B");
            Parcel C = new Parcel("C");
            truck.addParcel(A);
            truck.addParcel(B);
            truck.addParcel(C);
        }
    }

    /**
     *  Draws the 'Truck' and all of the Parcels it holds.<br>
     *  The Truck is drawn at the Center of the Screen and Scene.
     */
    private void renderScene() {
        Graphics2D g = (Graphics2D) image.getGraphics();
        g.setColor(Color.WHITE);
        g.clearRect(0, 0, W, H);

        //Get the center of the Truck so it can be used to draw all objects with it as a fake origin.
        //This means the trick will be at the center of the Scene and when rotating the Scene its rotated around the
        //center of the truck.
        deltaO = (truckParcel.get(0).midpoint(truckParcel.get(6))).multiply(-1);

        //draw a parcel to represent the truck
        drawParcelPro(truckParcel, Color.CYAN, false);

        for (Parcel parcel : truck.parcelList) {
            drawParcelPro(parcel, Color.WHITE, true);
        }

        //Origin and Rotation point, Indication/Axis Lines
        if(debug)
            drawDebug();
        repaint();
    }

    /**
     * Will draw Origin and Rotation Axis.
     */
    private void drawDebug() {
        Parcel I = new Parcel(3, 0, 0);
        Parcel J = new Parcel(0, 3, 0);
        Parcel K = new Parcel(0, 0, 3);
        I.setPos(deltaO.multiply(-1));
        J.setPos(deltaO.multiply(-1));
        K.setPos(deltaO.multiply(-1));
        drawParcelPro(I, Color.RED, false);
        drawParcelPro(J, Color.RED, false);
        drawParcelPro(K, Color.RED, false);
        drawParcelPro(new Parcel(5, 0, 0), Color.YELLOW, false);
        drawParcelPro(new Parcel(0, 5, 0), Color.YELLOW, false);
        drawParcelPro(new Parcel(0, 0, 5), Color.YELLOW, false);
    }


    /**
     * Takes a Point3D and produces a 2D Point of its Projection from 3D to the 2D Plane.<br>
     * This is done in relation to a Viewpoint at a fixed distance but a variable elevation and angle.<br>
     * The 2D point is in relation to a shifted origin <code>deltaO</code> the center of the Truck.<br>
     * The 2D point is also scaled using the zoom/ unit variable.<br>
     * <a href="http://www.dgp.toronto.edu/~mjmcguff/learn/java/11-3d/">Projection Code Used</a>
     * @param point Point3D object representing a Vertex of a Parcel to be converted.
     * @return 2D Point Object of where the 3D Point should be drawn to emulate depth
     */
    private Point convertPointPro(Point3D point) {
        point = point.add(deltaO);

        double theta = Math.PI * angle / 180.0;
        double phi = Math.PI * elevation / 180.0;
        float cosT = (float) Math.cos(theta), sinT = (float) Math.sin(theta);
        float cosP = (float) Math.cos(phi), sinP = (float) Math.sin(phi);
        float cosTcosP = cosT * cosP, cosTsinP = cosT * sinP, sinTcosP = sinT * cosP, sinTsinP = sinT * sinP;

        double x0 = point.getX();
        double y0 = point.getY();
        double z0 = point.getZ();

        double x1 = cosT * x0 + sinT * z0;
        double y1 = -sinTsinP * x0 + cosP * y0 + cosTsinP * z0;

        double z1 = cosTcosP * z0 - sinTcosP * x0 - sinP * y0;
        x1 = x1 * 20f / (z1 + 50f);
        y1 = y1 * 20f / (z1 + 50f);

        int ox = (int) (x1 * unit * 2 + 0.5);
        int oy = (int) (y1 * unit * 2 + 0.5);
        return new Point(W / 2 + ox, H / 2 - oy);
    }


    //Methods Used to adjust the Camera And Debug Options

    /**
     * Rotate the Truck.<br>
     * @param i int amount to rotate - Default range: -3 ~ 3.
     */
    public void rotate(int i) {
        angle += i;
        renderScene();
    }

    /**
     * Roll the Truck.<br>
     * @param i int amount to roll - Default range -3 ~ 3.
     */
    public void roll(int i) {
        elevation += i;
        renderScene();
    }

    /**
     * Change the scale of the Truck (Zoom). <br>
     * @param i int amount to change the scale - Default range -1 ~ 1.
     */
    public void zoom(int i) {
        unit += i;
        renderScene();
    }

    /**
     * Toggle if Debug Graphics should be drawn.
     */
    public void toggleDebug() {
        debug = !debug;
        renderScene();
    }

    /* Drawing Methods for different parts of the 'Truck' Display */

    /**
     * Draws a <code>Parcel</code> in Projected Perspective at the Parcel's Position with a given Color.<br>
     * The Parcel can Either be drawn with just a wire frame or with a wire frame and filled, by passing true to the 'fill' param.<br>
     * @param p The Parcel to draw.
     * @param c The Color to use for the wire frame.
     * @param fill Sets if the Parcel Should also be filled with a transparent fill.
     */
    private void drawParcelPro(Parcel p, Color c, Boolean fill) {
        Point3D pos = p.getPos();
        ArrayList<Point3D> points = p.getPoints();
        ArrayList<Point> pp = new ArrayList<>();

        //Convert all 3D points to Projected 2D points relative to the Parcels position.
        for (Point3D point : points) {
            pp.add(convertPointPro(point.add(pos)));
        }
        drawWireFrame(pp, c);
        if(fill)
            fillCube(pp, new Color(1,0,0,0.3f));
    }

    /**
     * Draws a Wire Frame of a Cube represented as an <code>ArrayList</code> of (2d) <code>Point</code> Objects and a <code>Color</code>.<br>
     * The set of Points can be generated using the <code>convertPointPro()</code> method.
     *
     * @param p The ArrayList of the 2D points of the Cube to draw.
     * @param c A Color to draw the Wire Frame from.
     */
    private void drawWireFrame(ArrayList<Point> p, Color c) {
        Graphics2D g = (Graphics2D) image.getGraphics();
        g.setColor(c);
        drawLine(p.get(0), p.get(1), c);
        drawLine(p.get(0), p.get(3), c);
        drawLine(p.get(0), p.get(4), c);

        drawLine(p.get(1), p.get(2), c);
        drawLine(p.get(1), p.get(5), c);

        drawLine(p.get(2), p.get(3), c);
        drawLine(p.get(2), p.get(6), c);

        drawLine(p.get(3), p.get(7), c);

        drawLine(p.get(4), p.get(5), c);
        drawLine(p.get(4), p.get(7), c);

        drawLine(p.get(6), p.get(5), c);
        drawLine(p.get(6), p.get(7), c);
    }

    /**
     * Draws a transparent Cube represented as an <code>ArrayList</code> of (2d) <code>Point</code> Objects and a <code>Color</code>.<br>
     * The set of Points can be generated using the <code>convertPointPro()</code> method.
     *
     * @param p An ArrayList of the 2D points of a Cube.
     * @param c A Color to draw the Cube from.
     */
    private void fillCube(ArrayList<Point> p, Color c) {
        fillPoly(p.get(0),p.get(3),p.get(2),p.get(1), c);
        fillPoly(p.get(1),p.get(2),p.get(6),p.get(5), c);
        fillPoly(p.get(2),p.get(3),p.get(7),p.get(6), c);
        fillPoly(p.get(0),p.get(3),p.get(7),p.get(4), c);
        fillPoly(p.get(1),p.get(0),p.get(4),p.get(5), c);
        fillPoly(p.get(4),p.get(7),p.get(6),p.get(5), c);
    }

    /**
     * Draws a Point/Oval at a (2d) <code>Point</code> with a given <code>Color</code>.
     *
     * @param p A 2D point to draw at
     * @param c A Color to draw the Oval from.
     */
    private void drawPoint(Point p, Color c) {
        Graphics2D g = (Graphics2D) image.getGraphics();
        g.setColor(c);
        g.fillOval((int) p.getX() - 3, (int) p.getY() - 3, 6, 6);
    }

    /**
     * Draws a Line Between two (2d) Points with a given <code>Color</code>.
     *
     * @param p1 2D Point to draw Line From.
     * @param p2 2D Point to draw Line To.
     * @param c The Color to use.
     */
    private void drawLine(Point p1, Point p2, Color c) {
        Graphics2D g = (Graphics2D) image.getGraphics();
        g.setColor(c);
        g.drawLine((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY());
    }

    /**
     * Draws a filled Polygon from 4 corner Points, Points need to be in 'Order' To transverse to Polygon Correctly.<br>
     * Also Takes a <code>Color</code> to Draw the Polygon From.
     *
     * @param p1 2D Point 1
     * @param p2 2D Point 2
     * @param p3 2D Point 3
     * @param p4 2D Point 4
     * @param c <code>Color To fill with</code>
     */
    private void fillPoly(Point p1, Point p2, Point p3, Point p4, Color c) {
        Graphics2D g = (Graphics2D) image.getGraphics();
        g.setColor(c);
        int[] xPoints = {(int) p1.getX(), (int) p2.getX(), (int) p3.getX(), (int) p4.getX()};
        int[] yPoints = {(int) p1.getY(), (int) p2.getY(), (int) p3.getY(), (int) p4.getY()};
        g.fillPolygon(xPoints, yPoints, 4);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }
}