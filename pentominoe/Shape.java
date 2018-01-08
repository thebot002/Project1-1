package pentominoe;

import java.util.*;

/**
This class defines the pentominoe shapes objects used for the game of pentris.
*/
public class Shape {
	private String[][] shape;

	/**
	Constructor of a shape object
	@param shape Array of strings defining the shape. Filled with "-" when empty space and the letter ID in filled spaces.
	*/
	public Shape(String[][] shape) {
		this.shape = shape;
	}

	/**
	Returns the string array version of the shape
	@return An 2-D array representing the shape.
	*/
	public String[][] getShape(){
		return shape;
	}

	/**
	Returns the ID of the shape, i.e. the letter it represents.
	@return A letter representing the shape.
	*/
	public String getShapeID(){
		for(int i=0;i<shape.length;i++)
			for(int j=0;j<shape[i].length;j++)
				if(!shape[i][j].equals("-")) return shape[i][j];
		return "-";
	}

	/**
	Returns the width of the shape.
	@return An integer value of the width of the shape.
	*/
	public int getWidth(){
		return shape[0].length;
	}

	/**
	Returns the height of the shape.
	@return An integer value of the height of the shape.
	*/
	public int getHeight(){
		return shape.length;
	}

	/**
	Returns the string contained at that position of the shape array.
	@return A string containing either "-" or the shape ID.
	*/
	public String getElement(int i, int j){
		return shape[i][j];
	}

	/**
	Clockwise rotation of the shape array.
	*/
	public void rotateR(){
		 String[][] temp = new String[shape[0].length][shape.length];
		 for(int i=0; i<shape.length;i++){
			  for(int j=0;j<shape[0].length;j++){
					temp[j][i] = shape[i][j];
			  }
		 }
		 shape = Arrays.copyOf(temp, temp.length);
		 flipH();
	}
	/**
	Returns a clockwise rotated string array representing the shape.
	@return A 2-D array representing a clockwise rotation of the shape.
	*/
	public String[][] sRotateR(){
 		String[][] sShape = shape;
 		String[][] temp = new String[sShape[0].length][sShape.length];
 		for(int i=0; i<sShape.length;i++){
 			 for(int j=0;j<sShape[0].length;j++){
 				   temp[j][i] = sShape[i][j];
 			 }
 		}
 		sShape = Arrays.copyOf(temp, temp.length);
 		return sFlipH(sShape);
 	}

	/**
	Counter-clockwise rotation of the shape array.
	*/
	public void rotateL(){ //counter clockwise
		 String[][] temp = new String[shape[0].length][shape.length];
		 for(int i=0; i<shape.length;i++){
			  for(int j=0;j<shape[0].length;j++){
					temp[j][i] = shape[i][j];
			  }
		 }
		 shape = Arrays.copyOf(temp, temp.length);
		 flipV();
	}

	/**
	Horizontal mirroring of the shape array.
	*/
	public void flipH(){ //horizontal
		 for(int i=0; i<shape.length; i++){
			  for(int j=0;j<shape[0].length/2;j++){
					String temp = shape[i][j];
					shape[i][j] = shape[i][shape[0].length-1-j];
					shape[i][shape[0].length-1-j] = temp;
			  }
		 }
	}
	/**
	Returns a horizontal mirrored string array representing the shape.
	@return A 2-D array representing a horizontal mirror of the shape.
	*/
	public String[][] sFlipH(String[][] sShape1){
 		String[][] sShape = sShape1;
 		for(int i=0; i<sShape.length; i++){
 			 for(int j=0;j<sShape[0].length/2;j++){
 				   String tempS = sShape[i][j];
 				   sShape[i][j] = sShape[i][sShape[0].length-1-j];
 				   sShape[i][sShape[0].length-1-j] = tempS;
 			 }
 		}
 		return sShape;
 	}

	/**
	Vertical mirroring of the shape array.
	*/
	public void flipV(){
		 for(int i=0; i<shape.length/2; i++){
			  for(int j=0;j<shape[0].length;j++){
					String temp = shape[i][j];
					shape[i][j] = shape[shape.length-1-i][j];
					shape[shape.length-1-i][j] = temp;
			  }
		 }
	}

	/**
	Prints the shape representation in the console.
	*/
	public void printShape() {
		for (int i=0; i<shape.length; i++) {
			for (int j=0; j<shape[0].length; j++) {
				if (j<shape[0].length-1) {
				System.out.print(shape[i][j]+ " ");
				}else {
					System.out.println(shape[i][j]);
				}
			}
		}
	}

	/**
	Method to create a new identitical pentominoe.pentominoe object.
	@return A new pentominoe.pentominoe object.
	*/
	public Shape copyShape(){
		String[][] nShape = new String[shape.length][shape[0].length];
		for (int i=0; i<shape.length; i++)
			for (int j=0; j<shape[0].length; j++)
				nShape[i][j] = shape[i][j];
		return new Shape(nShape);
	}

	/**
	Method that checks if a given shape is the same as this one.
	@param shape The shape to compare.
	@return Wether the 2 shapes are equal or not.
	*/
	public boolean equals(Shape shape2){
		if(shape2.getWidth() != getWidth() || shape2.getHeight() != getHeight()) return false;
		for(int i=0; i<shape2.getHeight(); i++){
			for(int j=0; j<shape2.getWidth(); j++){
				if(!shape2.getElement(i,j).equals(shape[i][j])) return false;
			}
		}
		return true;
	}
}
