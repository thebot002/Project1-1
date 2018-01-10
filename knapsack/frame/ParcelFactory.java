package knapsack.frame;

public class ParcelFactory{
  private Parcel shape1;
	private Parcel shape2;
	private Parcel shape3;

	public ParcelFactory() {
		int[][][] shapeAr1 = { { {1,1,1},{1,1,1},{1,1,1} } ,
							             { {1,1,1},{1,1,1},{1,1,1} } ,
							             { {1,1,1},{1,1,1},{1,1,1} } };

		this.shape1 = new Parcel(shapeAr1);

		int[][][] shapeAr2 = { { {2,2} , {2,2}, {2,2} } ,
							             { {2,2} , {2,2}, {2,2} } ,
							             { {2,2} , {2,2}, {2,2} } ,
                           { {2,2} , {2,2}, {2,2}}};
		this.shape2 = new Parcel(shapeAr2);

		int[][][] shapeAr3 = { { {2,2} , {2,2} } ,
							             { {2,2} , {2,2} } ,
							             { {2,2} , {2,2} } ,
                           { {2,2} , {2,2} }};

		this.shape3 = new Parcel(shapeAr3);



	}

	public Parcel getShape1(){
		return shape1;
	}

	public Parcel getShape2(){
		return shape2;
	}

	public Parcel getShape3(){
		return shape3;
	}

	public Parcel[] shapeList() {
		Parcel[] listOfShapes = new Parcel[3];
		listOfShapes[0]=shape1;
		listOfShapes[1]=shape2;
		listOfShapes[2]=shape3;

		return listOfShapes;
	}

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
