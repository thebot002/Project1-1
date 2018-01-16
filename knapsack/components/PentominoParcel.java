package knapsack.components;

public class PentominoParcel extends Parcel{
    public PentominoParcel(String id) {
        super(1,2,2);
        int w=0,h=0,l=0;
        switch (id){
            case "L": w=4; h=2; l=1; break;
            case "P": w=3; h=2; l=1; break;
            case "T": w=3; h=3; l=1; break;
        }
    }
}
