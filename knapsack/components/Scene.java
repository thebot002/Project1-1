package knapsack.components;

import javafx.geometry.Point3D;

import java.util.ArrayList;

public interface Scene {

    void updateDeltaOrigin();

    Point3D getDeltaO();

    ArrayList<Cube> getCubeList();

    Cube getCube();

    void empty();
}
