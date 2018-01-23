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

    Color getBackground();
    Color getForeground();
    Color getCubeColor();

    void setBackground(Color c);
    void setForeground(Color c);
    void setCubeColor(Color c);
}
