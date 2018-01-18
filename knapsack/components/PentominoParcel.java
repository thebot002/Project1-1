package knapsack.components;

import javafx.geometry.Point3D;

import java.util.ArrayList;

public class PentominoParcel extends Parcel{

    public PentominoParcel(String id) {
        super(id, 0);
        setPos(new Point3D(0,0,0));
        switch (id){
            case "L": width=4; height=1; length=2; setPointsL(); break;
            case "P": width=3; height=1; length=2; setPointsP(); break;
            case "T": width=3; height=1; length=3; setPointsT(); break;
        }
    }

    private void setPointsT() {
        setID("T");

        points = new ArrayList<>();
        points.add(new Point3D(0, 0, 0));
        points.add(new Point3D(1, 0, 0));
        points.add(new Point3D(1, 0, 1));
        points.add(new Point3D(3, 0, 1));
        points.add(new Point3D(3, 0, 2));
        points.add(new Point3D(1, 0, 2));
        points.add(new Point3D(1, 0, 3));
        points.add(new Point3D(0, 0, 3));

        points.add(new Point3D(0, 1, 0));
        points.add(new Point3D(1, 1, 0));
        points.add(new Point3D(1, 1, 1));
        points.add(new Point3D(3, 1, 1));
        points.add(new Point3D(3, 1, 2));
        points.add(new Point3D(1, 1, 2));
        points.add(new Point3D(1, 1, 3));
        points.add(new Point3D(0, 1, 3));

        for(int inc = 0; inc < 7; inc++) {
            edges.add(new Edge3D(points.get(inc), points.get(inc+1)));
            edges.add(new Edge3D(points.get(inc), points.get(inc+8)));
            edges.add(new Edge3D(points.get(inc+8), points.get(inc+9)));
        }
        edges.add(new Edge3D(points.get(7),points.get(0)));
        edges.add(new Edge3D(points.get(7),points.get(15)));
        edges.add(new Edge3D(points.get(15),points.get(8)));
    }

    private void setPointsP() {
        setID("P");

        points = new ArrayList<>();
        points.add(new Point3D(0, 0, 0));
        points.add(new Point3D(3, 0, 0));
        points.add(new Point3D(3, 0, 1));
        points.add(new Point3D(2, 0, 1));
        points.add(new Point3D(2, 0, 2));
        points.add(new Point3D(0, 0, 2));

        points.add(new Point3D(0, 1, 0));
        points.add(new Point3D(3, 1, 0));
        points.add(new Point3D(3, 1, 1));
        points.add(new Point3D(2, 1, 1));
        points.add(new Point3D(2, 1, 2));
        points.add(new Point3D(0, 1, 2));

        for(int inc = 0; inc < 5; inc++) {
            edges.add(new Edge3D(points.get(inc), points.get(inc+1)));
            edges.add(new Edge3D(points.get(inc), points.get(inc+6)));
            edges.add(new Edge3D(points.get(inc+6), points.get(inc+7)));
        }
        edges.add(new Edge3D(points.get(5),points.get(0)));
        edges.add(new Edge3D(points.get(5),points.get(11)));
        edges.add(new Edge3D(points.get(11),points.get(6)));
    }

    private void setPointsL() {
        setID("L");

        points = new ArrayList<>();
        points.add(new Point3D(0, 0, 0));
        points.add(new Point3D(4, 0, 0));
        points.add(new Point3D(4, 0, 2));
        points.add(new Point3D(3, 0, 2));
        points.add(new Point3D(3, 0, 1));
        points.add(new Point3D(0, 0, 1));

        points.add(new Point3D(0, 1, 0));
        points.add(new Point3D(4, 1, 0));
        points.add(new Point3D(4, 1, 2));
        points.add(new Point3D(3, 1, 2));
        points.add(new Point3D(3, 1, 1));
        points.add(new Point3D(0, 1, 1));

        for(int inc = 0; inc < 5; inc++) {
            edges.add(new Edge3D(points.get(inc), points.get(inc+1)));
            edges.add(new Edge3D(points.get(inc), points.get(inc+6)));
            edges.add(new Edge3D(points.get(inc+6), points.get(inc+7)));
        }
        edges.add(new Edge3D(points.get(5),points.get(0)));
        edges.add(new Edge3D(points.get(5),points.get(11)));
        edges.add(new Edge3D(points.get(11),points.get(6)));
    }
}
