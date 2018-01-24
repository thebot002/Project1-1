package knapsack.components;

import javafx.geometry.Point3D;


public class Edge3D {

        public Point3D a;
        public Point3D b;

        public Edge3D(Point3D a, Point3D b) {
            this.a = a;
            this.b = b;
        }
        public Edge3D copy(){
            return new Edge3D(new Point3D(a.getX(),a.getY(),a.getZ()),new Point3D(b.getX(),b.getY(),b.getZ()));
        }
}
