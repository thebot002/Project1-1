import java.util.*;

public class Shape {
	private String[][] shape;

	public Shape(String[][] shape) {
		this.shape = shape;
	}

	public String[][] getShape(){
		return shape;
	}
  
	public static void main(String[] args){}
	
	public String getShapeID(){
		for(int i=0;i<shape.length;i++)
			for(int j=0;j<shape[i].length;j++)
				if(!shape[i][j].equals("-")) return shape[i][j];
		return "-";
	}

	public int getWidth(){
		return shape[0].length;
	}
	public int getHeight(){
		return shape.length;
	}

	public String getElement(int i,int j){
		return shape[i][j];
	}

	public String getString() {
		String shapeString = "-";
		for(int i=0; i<shape.length; i++) {
			for(int j=0; j<shape[0].length; j++) {
				if(!shape[i][j].equals("-"))
					shapeString = shape[i][j];
					break;
			}
			if(!shapeString.equals("-"))
				break;
		}
		return shapeString;
	}

	public void rotateR(){ //clockwise
		 String[][] temp = new String[shape[0].length][shape.length];
		 for(int i=0; i<shape.length;i++){
			  for(int j=0;j<shape[0].length;j++){
					temp[j][i] = shape[i][j];
			  }
		 }
		 shape = Arrays.copyOf(temp, temp.length);
		 flipH();
	}
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
	public void flipH(){ //horizontal
		 for(int i=0; i<shape.length; i++){
			  for(int j=0;j<shape[0].length/2;j++){
					String temp = shape[i][j];
					shape[i][j] = shape[i][shape[0].length-1-j];
					shape[i][shape[0].length-1-j] = temp;
			  }
		 }
	}
	public void flipV(){ //Vertical
		 for(int i=0; i<shape.length/2; i++){
			  for(int j=0;j<shape[0].length;j++){
					String temp = shape[i][j];
					shape[i][j] = shape[shape.length-1-i][j];
					shape[shape.length-1-i][j] = temp;
			  }
		 }
	}
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
}
