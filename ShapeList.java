//package function;

import java.util.ArrayList;

public class ShapeList extends ArrayList<Shape> {
	
	public static void main(String[] args){}
	private ShapeFactory shapeFactory = new ShapeFactory();
	//private ArrayList<Shape> listOfShapes = new ArrayList<>();
	
	public ShapeList() {
		
		Shape shapeI = shapeFactory.buildShapeI();
		Shape shapeL = shapeFactory.buildShapeL();
		Shape shapeN = shapeFactory.buildShapeN();
		Shape shapeF = shapeFactory.buildShapeF();
		Shape shapeP = shapeFactory.buildShapeP();
		Shape shapeT = shapeFactory.buildShapeT();
		Shape shapeU = shapeFactory.buildShapeU();
		Shape shapeV = shapeFactory.buildShapeV();
		Shape shapeW = shapeFactory.buildShapeW();
		Shape shapeX = shapeFactory.buildShapeX();
		Shape shapeY = shapeFactory.buildShapeY();
		Shape shapeZ = shapeFactory.buildShapeZ();

		add(shapeZ);
		add(shapeY);
		add(shapeX);
		add(shapeW);
		add(shapeV);
		add(shapeU);
		add(shapeT);
		add(shapeP);
		add(shapeF);
		add(shapeN);
		add(shapeL);
		add(shapeI);
	}
	
	public ArrayList<Shape> getShapeList(){
		return this;
	}
	
	
	
}
