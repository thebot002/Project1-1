package function;

import java.util.ArrayList;

public class ShapeList {

	private ShapeFactory shapeFactory = new ShapeFactory();
	private ArrayList<Shape> listOfShapes = new ArrayList<>();

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

		listOfShapes.add(shapeZ);
		listOfShapes.add(shapeY);
		listOfShapes.add(shapeX);
		listOfShapes.add(shapeW);
		listOfShapes.add(shapeV);
		listOfShapes.add(shapeU);
		listOfShapes.add(shapeT);
		listOfShapes.add(shapeP);
		listOfShapes.add(shapeF);
		listOfShapes.add(shapeN);
		listOfShapes.add(shapeL);
		listOfShapes.add(shapeI);
	}

	public ArrayList<Shape> getShapeList(){
		return listOfShapes;
	}

	public Shape getShape(int nb){
		listOfShapes.get(nb);
	}
	public int getLength(){
		return listOfShapes.size();
	}

}
