//In this Class we build the shapes and some methods to rotate or mirroring them
public class ShapeFactory {
	public static void main(String[] args){}
	public ShapeFactory() {}

		public String[][] buildShapes(int length, int width){
	String[][] shape = new String[length][width];

	for(int i=0;i<length;i++){
		for(int j=0;j<width;j++){
			shape[i][j]="-";
		}
	}
	return shape;
}

	public Shape buildShapeI(){
		String[][] shapeI= new String[][]{
			{"I"},
			{"I"},
			{"I"},
			{"I"},
			{"I"}
		};
		return new Shape(shapeI);
	}

	public Shape buildShapeL(){
		String[][] shapeL= new String[][]{
			{"L", "-"},
			{"L", "-"},
			{"L", "-"},
			{"L", "L"}
		};
		return new Shape(shapeL);
	}

	public Shape buildShapeJ(){
		String[][] shapeJ= new String[][]{
			{"-", "J"},
			{"-", "J"},
			{"-", "J"},
			{"J", "J"}
		};
		return new Shape(shapeJ);
	}

	public Shape buildShapeY(){
		String[][] shapeY= new String[][]{
			{"-", "Y"},
			{"Y", "Y"},
			{"-", "Y"},
			{"-", "Y"}
		};
		return new Shape(shapeY);
	}

	public Shape buildShapeH(){
		String[][] shapeH= new String[][]{
			{"H", "-"},
			{"H", "H"},
			{"H", "-"},
			{"H", "-"}
		};
		return new Shape(shapeH);
	}

	public Shape buildShapeP(){
		String[][] shapeP= new String[][]{
			{"P", "P"},
			{"P", "P"},
			{"P", "-"}
		};
		return new Shape(shapeP);
	}

	public Shape buildShapeQ(){
		String[][] shapeQ= new String[][]{
			{"Q", "Q"},
			{"Q", "Q"},
			{"-", "Q"}
		};
		return new Shape(shapeQ);
	}

	public Shape buildShapeN(){
		String[][] shapeN= new String[][]{
			{"-", "N"},
			{"-", "N"},
			{"N", "N"},
			{"N", "-"}
		};
		return new Shape(shapeN);
	}

public Shape buildShapeM(){
	String[][] shapeM= new String[][]{
		{"M", "-"},
		{"M", "-"},
		{"M", "M"},
		{"-", "M"}
	};
	return new Shape(shapeM);
}

public Shape buildShapeU(){
	String[][] shapeU= new String[][]{
		{"U", "-", "U"},
		{"U", "U", "U"}
	};
	return new Shape(shapeU);
}

public Shape buildShapeV(){
	String[][] shapeV= new String[][]{
		{"V", "-", "-"},
		{"V", "-", "-"},
		{"V", "V", "V"}
	};
	return new Shape(shapeV);
}

public Shape buildShapeT(){
	String[][] shapeT= new String[][]{
		{"T", "T", "T"},
		{"-", "T", "-"},
		{"-", "T", "-"}
	};
	return new Shape(shapeT);
}

public Shape buildShapeX(){
	String[][] shapeX= new String[][]{
		{"-", "X", "-"},
		{"X", "X", "X"},
		{"-", "X", "-"}
	};
	return new Shape(shapeX);
}

public Shape buildShapeZ(){
	String[][] shapeZ= new String[][]{
		{"Z", "Z", "-"},
		{"-", "Z", "-"},
		{"-", "Z", "Z"}
	};
	return new Shape(shapeZ);
}

public Shape buildShapeS(){
	String[][] shapeS= new String[][]{
		{"-", "S", "S"},
		{"-", "S", "-"},
		{"S", "S", "-"}
	};
	return new Shape(shapeS);
}

public Shape buildShapeF(){
	String[][] shapeF= new String[][]{
		{"-", "F", "F"},
		{"F", "F", "-"},
		{"-", "F", "-"}
	};
	return new Shape(shapeF);
}

public Shape buildShapeE(){
	String[][] shapeE= new String[][]{
		{"E", "E", "-"},
		{"-", "E", "E"},
		{"-", "E", "-"}
	};
	return new Shape(shapeE);
}

public Shape buildShapeW(){
	String[][] shapeW= new String[][]{
		{"-", "-", "W"},
		{"-", "W", "W"},
		{"W", "W", "-"}
	};
	return new Shape(shapeW);
}


/* With this method we just interchange the rows and the collumns of the shape */
public Shape rotate90(Shape shape){
	String [][] crShape = shape.getShape();
	String [][] rcShape=new String[crShape[0].length][crShape.length];
	for (int i=0; i<rcShape.length;i++){
		for (int j=0; j<rcShape[0].length; j++){
			rcShape[i][j]=crShape[j][i];
		}
	}
	Shape rotateShape = horizontalMirroring(new Shape(rcShape));
	return new Shape(rotateShape.getShape());
}


//With this method we get the horizontal mirror image of the shape

public Shape horizontalMirroring(Shape shape){
	String[][] nShape = shape.getShape();
	String[][] mirroringShape = new String[nShape.length][nShape[0].length];
	for (int i=0; i<mirroringShape.length; i++){
		for(int j=0; j<mirroringShape[0].length; j++){
			mirroringShape[i][j]=nShape[i][nShape[0].length-j-1];
		}
	}
	return new Shape(mirroringShape);
}

/* By using the above three methods we can get all the states of any shape */



}
