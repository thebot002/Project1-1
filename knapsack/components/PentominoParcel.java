package knapsack.components;

import javafx.geometry.Point3D;

import java.util.ArrayList;

public class PentominoParcel extends Parcel {

    public String[][][] pentString;

    public PentominoParcel(String id) {
        super(id, 0);
        setPos(new Point3D(0,0,0));
        switch (id){
            case "L": width=4; height=1; length=2; setPointsL(); break;
            case "P": width=3; height=1; length=2; setPointsP(); break;
            case "T": width=3; height=1; length=3; setPointsT(); break;
        }
        setEdges();
    }

    private void setPointsT() {
        setID("T");

        String[][][] x = {
                {
                        {"T", "T", "T"}
                },

                {
                        {"-", "T", "-"}
                },

                {
                        {"-", "T", "-"}
                },
        };

        pentString = x;

        points = new ArrayList<>();
        for (int i = 0; i <= 1; i++) {
            points.add(new Point3D(0, i, 0));
            points.add(new Point3D(1, i, 0));
            points.add(new Point3D(1, i, 1));
            points.add(new Point3D(3, i, 1));
            points.add(new Point3D(3, i, 2));
            points.add(new Point3D(1, i, 2));
            points.add(new Point3D(1, i, 3));
            points.add(new Point3D(0, i, 3));
        }
    }

    private void setPointsP() {
        setID("P");

        String[][][] x = {
                {
                        {"P", "P", "-"},
                        {"P", "P", "-"},
                        {"P", "-", "-"}
                }
        };

        pentString = x;

        points = new ArrayList<>();
        for (int i = 0; i <= 1; i++) {
            points.add(new Point3D(0, i, 0));
            points.add(new Point3D(3, i, 0));
            points.add(new Point3D(3, i, 1));
            points.add(new Point3D(2, i, 1));
            points.add(new Point3D(2, i, 2));
            points.add(new Point3D(0, i, 2));
        }
    }

    private void setPointsL() {
        setID("L");

        String[][][] x = {
                {
                        {"L", "-"},
                        {"L", "-"},
                        {"L", "-"},
                        {"L", "L"}
                }
        };

        pentString = x;

        points = new ArrayList<>();
        for (int i = 0; i <= 1; i++) {
            points.add(new Point3D(0, i, 0));
            points.add(new Point3D(4, i, 0));
            points.add(new Point3D(4, i, 2));
            points.add(new Point3D(3, i, 2));
            points.add(new Point3D(3, i, 1));
            points.add(new Point3D(0, i, 1));
        }
    }
}
