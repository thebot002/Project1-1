package knapsack.components;

import javafx.geometry.Point3D;

import java.awt.*;
import java.util.ArrayList;

public interface Scene {

    void updateOrigin();
    Point3D getOrigin();

    ArrayList<Cube> getCubeList();
    Cube getCube();

    void empty();

    public Color getBackground();
    public Color getFill();
    public Color getForeground();
    public Color getCubeColor();
}
