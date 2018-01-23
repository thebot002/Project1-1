package knapsack.components;

import javafx.geometry.Point3D;

import java.awt.*;
import java.util.ArrayList;

public interface Cube {

    Point3D getPos();

    ArrayList<Point3D> getPoints();

    ArrayList<Edge3D> getEdges();

    Color getColor();

    Cube copy();
}
