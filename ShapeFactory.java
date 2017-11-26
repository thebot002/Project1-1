//In this Class we build the shapes and some methods to rotate or mirroring them
public class ShapeFactory {
	public static void main(String[] args){}
	public ShapeFactory() {}
	
	public String[][] buildShapes(int length, int width){
		String[][] shape = new String[length][width];

		for(int i=0;i<length;i++) {
			for (int j=0; j<width;j++) {
				shape[i][j]="-";
			}
		}
		return shape;
	}

	public Shape buildShapeI() {

	    String[][]  shapeI=buildShapes(5,1);

	    for (int i=0; i<shapeI.length; i++) {
	    		shapeI[i][0]="I";
	    }
	    return new Shape(shapeI);
	}

public Shape buildShapeL(){
	String[][] shapeL=buildShapes(4,2);

		for (int i=0; i<shapeL.length; i++) {
			for (int j=0; j<shapeL[i].length; j++) {
				if(j==0 	|| i==shapeL.length-1 & j==1) {
				shapeL[i][j]="L";
				}
			}
		}
		return new Shape(shapeL);
	}

public Shape buildShapeY(){
	String[][] shapeY=buildShapes(4,2);

	for (int i=0; i<shapeY.length; i++) {
		for (int j=0; j<shapeY[i].length; j++) {
			if(j==0 	|| i==1 && j==1) {
			shapeY[i][j]="Y";
			}
		}
	}
	return new Shape(shapeY);
}

public Shape buildShapeP(){
	String[][] shapeP=buildShapes(3,2);


	for (int i=0; i<shapeP.length; i++) {
		for (int j=0; j<shapeP[i].length; j++) {
			if(j==0 || (i<=1 && j==1)) {
			shapeP[i][j]="P";
			}
		}
	}
	return new Shape(shapeP);
}

public Shape buildShapeN(){
	String[][] shapeN=buildShapes(4,2);

	for (int i=0; i<shapeN.length; i++) {
		for (int j=0; j<shapeN[i].length; j++) {
			if(j==0 && i<3 || i>1 && j==1) {
			shapeN[i][j]="N";
			}
		}
	}
	return new Shape(shapeN);
}

public Shape buildShapeU(){
	String[][] shapeU=buildShapes(3,2);

	for (int i=0; i<shapeU.length; i++) {
		for (int j=0; j<shapeU[i].length; j++) {
			if(j==0 || i==0 && j==1 || i==2 && j==1) {
			shapeU[i][j]="U";
			}
		}
	}
	return new Shape(shapeU);
}

public Shape buildShapeV(){
	String [][] shapeV=buildShapes(3,3);

	for (int i=0; i<shapeV.length; i++) {
		for (int j=0; j<shapeV[i].length; j++) {
			if(j==0 	|| i==0) {
			shapeV[i][j]="V";
			}
		}
	}
	return new Shape(shapeV);
}

public Shape buildShapeT(){
	String [][] shapeT=buildShapes(3,3);

	for (int i=0; i<shapeT.length; i++) {
		for (int j=0; j<shapeT[i].length; j++) {
			if(j==1 	|| i==0 ) {
			shapeT[i][j]="T";
			}
		}
	}
	return new Shape(shapeT);
}

public Shape buildShapeX(){
	String [][] shapeX=buildShapes(3,3);

	for (int i=0; i<shapeX.length; i++) {
		for (int j=0; j<shapeX[i].length; j++) {
			if(j==1 	|| i==1) {
			shapeX[i][j]="X";
			}
		}
	}
	return new Shape(shapeX);
}

public Shape buildShapeZ(){
	String [][] shapeZ=buildShapes(3,3);

	for (int i=0; i<shapeZ.length; i++) {
		for (int j=0; j<shapeZ[i].length; j++) {
			if((j==0 && i==0 || j==2 &&i==2) || i==1 ) {
			shapeZ[i][j]="Z";
			}
		}
	}
	return new Shape(shapeZ);
}

public Shape buildShapeF(){
	String [][] shapeF=buildShapes(3,3);

	for (int i=0; i<shapeF.length; i++) {
		for (int j=0; j<shapeF[i].length; j++) {
			if(j==0 && i==0 || j==1 && i==2 || i==1 ) {
			shapeF[i][j]="F";
			}
		}
	}
	return new Shape(shapeF);
}

public Shape buildShapeW(){
	String [][] shapeW=buildShapes(3,3);

	for (int i=0; i<shapeW.length; i++) {
		for (int j=0; j<shapeW[i].length; j++) {
			if(i<2 && j==0	|| i==1 && j<2 || j>0 && i==2) {
			shapeW[i][j]="W";
			}
		}
	}
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
