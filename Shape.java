package function;

public class Shape {
	private String[][] shape;
	
	public Shape(String[][] shape) {
		this.shape = shape;
	}
	
	public String[][] getShape(){
		return shape;
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
