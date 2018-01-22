package knapsack;

import knapsack.components.Truck;
import knapsack.filling.SimulatedAnnealing;
import knapsack.frame.CubeDrawer;


public class Knapsack {
    public static void main(String[] args) {
        new Knapsack();
    }

    public Knapsack() {
        posiInit();
    }

    private void posiInit() {
        SimulatedAnnealing s = new SimulatedAnnealing();
        Truck truck = s.fillTruck();
        CubeDrawer c = new CubeDrawer(1000, 600, truck);

        //Greedy.fill(truck);
        c.renderScene();
    }
}
