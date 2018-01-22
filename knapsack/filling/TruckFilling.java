package knapsack.filling;

import knapsack.components.ParcelList;
import knapsack.components.Truck;

public interface TruckFilling {
    Truck fillTruck();
    void setParcelList(ParcelList list);
}
