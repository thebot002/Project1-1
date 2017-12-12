import java.util.ArrayList;

/**
This class is an ArraList of Shape objects.
@see ArraList
@see Shape
*/
public class ShapeList extends ArrayList<Shape> {
	/**
	Constructor of the ShapeList object. Also fills it with all the Shape objects and their mirror using the ShapeFactory class.
	@see Shape
	@see ShapeFactory
	*/
	public ShapeList() {
		Shape shapeI = ShapeFactory.buildShapeI();
		Shape shapeL = ShapeFactory.buildShapeL();
		Shape shapeN = ShapeFactory.buildShapeN();
		Shape shapeF = ShapeFactory.buildShapeF();
		Shape shapeP = ShapeFactory.buildShapeP();
		Shape shapeT = ShapeFactory.buildShapeT();
		Shape shapeU = ShapeFactory.buildShapeU();
		Shape shapeV = ShapeFactory.buildShapeV();
		Shape shapeW = ShapeFactory.buildShapeW();
		Shape shapeX = ShapeFactory.buildShapeX();
		Shape shapeY = ShapeFactory.buildShapeY();
		Shape shapeZ = ShapeFactory.buildShapeZ();
		Shape shapeJ = ShapeFactory.buildShapeJ();
		Shape shapeH = ShapeFactory.buildShapeH();
		Shape shapeQ = ShapeFactory.buildShapeQ();
		Shape shapeM = ShapeFactory.buildShapeM();
		Shape shapeS = ShapeFactory.buildShapeS();
		Shape shapeE = ShapeFactory.buildShapeE();

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
		add(shapeZ);
		add(shapeJ);
		add(shapeH);
		add(shapeQ);
		add(shapeM);
		add(shapeS);
		add(shapeE);
	}

	/**
	Returns a random Shape object from the list.
	@return A Shape object randomly picked.
	@see shape
	*/
	public Shape getRandomShape(){
		return get((int)(Math.random()*size()));
	}
}
