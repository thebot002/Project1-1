package knapsack;

import knapsack.components.Parcel;
import knapsack.components.Truck;
import knapsack.filling.BruteForce;
import knapsack.frame.CubeDrawer;


public class Knapsack {
    public static void main(String[] args) {
        new Knapsack();
    }

    public Knapsack() {
        posiInit();
    }

    private void posiInit() {
        Truck truck = new Truck();
        truck.addParcel(new Parcel("A"));
        truck.addParcel(new Parcel("B"));
        truck.addParcel(new Parcel("C"));
        truck.addParcel(new Parcel(3,3,3));

        CubeDrawer c = new CubeDrawer(1000, 600, truck);

        //BruteForce.fill(truck);
        c.renderScene();
    }
}
