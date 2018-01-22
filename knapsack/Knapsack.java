package knapsack;

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
        CubeDrawer c = new CubeDrawer(1000, 600, truck);

        BruteForce.fill(truck);
        c.renderScene();
    }
}
