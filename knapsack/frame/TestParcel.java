package knapsack.frame;

public class TestParcel{
  public static void main(String[] args){

    int[][][] shapeAr2 = { { {2,2} , {2,2}, {2,2} } ,
                           { {2,2} , {2,2}, {2,2} } ,
                           { {2,2} , {2,2}, {2,2} } ,
                           { {2,2} , {2,2}, {2,2}}};
    Parcel shape2 = new Parcel(shapeAr2);
    shape2.printParcel();

    shape2.xRot();

    shape2.printParcel();
}
}
