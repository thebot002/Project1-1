package pentominoe;

import pentominoe.Shape;

/**
This class contains the methods to build the pentominoe.pentominoe objects and their mirror.
@see Shape
*/
public class ShapeFactory {
	/**
	Builds a pentominoe.pentominoe object representing an I.
	@return A new pentominoe.pentominoe object.
	@see Shape
	*/
	public static Shape buildShapeI(){
		String[][] shapeI= {
			{"I"},
			{"I"},
			{"I"},
			{"I"},
			{"I"}
		};
		return new Shape(shapeI);
	}

	/**
	Builds a pentominoe.pentominoe object representing an L.
	@return A new pentominoe.pentominoe object.
	@see Shape
	*/
	public static Shape buildShapeL(){
		String[][] shapeL= {
			{"L", "-"},
			{"L", "-"},
			{"L", "-"},
			{"L", "L"}
		};
		return new Shape(shapeL);
	}

	/**
	Builds a pentominoe.pentominoe object representing an J (mirrored L).
	@return A new pentominoe.pentominoe object.
	@see Shape
	*/
	public static Shape buildShapeJ(){
		String[][] shapeJ= {
			{"-", "J"},
			{"-", "J"},
			{"-", "J"},
			{"J", "J"}
		};
		return new Shape(shapeJ);
	}

	/**
	Builds a pentominoe.pentominoe object representing an Y.
	@return A new pentominoe.pentominoe object.
	@see Shape
	*/
	public static Shape buildShapeY(){
		String[][] shapeY= {
			{"-", "Y"},
			{"Y", "Y"},
			{"-", "Y"},
			{"-", "Y"}
		};
		return new Shape(shapeY);
	}

	/**
	Builds a pentominoe.pentominoe object representing an H (mirrored Y).
	@return A new pentominoe.pentominoe object.
	@see Shape
	*/
	public static Shape buildShapeH(){
		String[][] shapeH= {
			{"H", "-"},
			{"H", "H"},
			{"H", "-"},
			{"H", "-"}
		};
		return new Shape(shapeH);
	}

	/**
	Builds a pentominoe.pentominoe object representing an P.
	@return A new pentominoe.pentominoe object.
	@see Shape
	*/
	public static Shape buildShapeP(){
		String[][] shapeP= {
			{"P", "P"},
			{"P", "P"},
			{"P", "-"}
		};
		return new Shape(shapeP);
	}

	/**
	Builds a pentominoe.pentominoe object representing an Q (mirrored P).
	@return A new pentominoe.pentominoe object.
	@see Shape
	*/
	public static Shape buildShapeQ(){
		String[][] shapeQ= {
			{"Q", "Q"},
			{"Q", "Q"},
			{"-", "Q"}
		};
		return new Shape(shapeQ);
	}

	/**
	Builds a pentominoe.pentominoe object representing an N.
	@return A new pentominoe.pentominoe object.
	@see Shape
	*/
	public static Shape buildShapeN(){
		String[][] shapeN= {
			{"-", "N"},
			{"-", "N"},
			{"N", "N"},
			{"N", "-"}
		};
		return new Shape(shapeN);
	}

	/**
	Builds a pentominoe.pentominoe object representing an M (mirrored N).
	@return A new pentominoe.pentominoe object.
	@see Shape
	*/
	public static Shape buildShapeM(){
		String[][] shapeM= {
			{"M", "-"},
			{"M", "-"},
			{"M", "M"},
			{"-", "M"}
		};
		return new Shape(shapeM);
	}

	/**
	Builds a pentominoe.pentominoe object representing an U.
	@return A new pentominoe.pentominoe object.
	@see Shape
	*/
	public static Shape buildShapeU(){
		String[][] shapeU= {
			{"U", "-", "U"},
			{"U", "U", "U"}
		};
		return new Shape(shapeU);
	}

	/**
	Builds a pentominoe.pentominoe object representing an V.
	@return A new pentominoe.pentominoe object.
	@see Shape
	*/
	public static Shape buildShapeV(){
		String[][] shapeV= {
			{"V", "-", "-"},
			{"V", "-", "-"},
			{"V", "V", "V"}
		};
		return new Shape(shapeV);
	}

	/**
	Builds a pentominoe.pentominoe object representing an T.
	@return A new pentominoe.pentominoe object.
	@see Shape
	*/
	public static Shape buildShapeT(){
		String[][] shapeT= {
			{"T", "T", "T"},
			{"-", "T", "-"},
			{"-", "T", "-"}
		};
		return new Shape(shapeT);
	}

	/**
	Builds a pentominoe.pentominoe object representing an X.
	@return A new pentominoe.pentominoe object.
	@see Shape
	*/
	public static Shape buildShapeX(){
		String[][] shapeX= {
			{"-", "X", "-"},
			{"X", "X", "X"},
			{"-", "X", "-"}
		};
		return new Shape(shapeX);
	}

	/**
	Builds a pentominoe.pentominoe object representing an Z.
	@return A new pentominoe.pentominoe object.
	@see Shape
	*/
	public static Shape buildShapeZ(){
		String[][] shapeZ= {
			{"Z", "Z", "-"},
			{"-", "Z", "-"},
			{"-", "Z", "Z"}
		};
		return new Shape(shapeZ);
	}

	/**
	Builds a pentominoe.pentominoe object representing an S (mirrored Z).
	@return A new pentominoe.pentominoe object.
	@see Shape
	*/
	public static Shape buildShapeS(){
		String[][] shapeS= {
			{"-", "S", "S"},
			{"-", "S", "-"},
			{"S", "S", "-"}
		};
		return new Shape(shapeS);
	}

	/**
	Builds a pentominoe.pentominoe object representing an F.
	@return A new pentominoe.pentominoe object.
	@see Shape
	*/
	public static Shape buildShapeF(){
		String[][] shapeF= {
			{"-", "F", "F"},
			{"F", "F", "-"},
			{"-", "F", "-"}
		};
		return new Shape(shapeF);
	}

	/**
	Builds a pentominoe.pentominoe object representing an E (mirrored F).
	@return A new pentominoe.pentominoe object.
	@see Shape
	*/
	public static Shape buildShapeE(){
		String[][] shapeE= {
			{"E", "E", "-"},
			{"-", "E", "E"},
			{"-", "E", "-"}
		};
		return new Shape(shapeE);
	}

	/**
	Builds a pentominoe.pentominoe object representing an W.
	@return A new pentominoe.pentominoe object.
	@see Shape
	*/
	public static Shape buildShapeW(){
		String[][] shapeW= {
			{"-", "-", "W"},
			{"-", "W", "W"},
			{"W", "W", "-"}
		};
		return new Shape(shapeW);
	}
}
