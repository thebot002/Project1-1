package knapsack.components;

import javafx.geometry.Point3D;

import java.util.ArrayList;

public class PentominoParcel extends Parcel {

	private String[][][] array;


    public PentominoParcel(String id) {
        super(id, 0);
        setPos(new Point3D(0,0,0));
        switch (id){
            case "L": width=4; height=1; length=2; setPointsL(); setArrayL(); break;
            case "P": width=3; height=1; length=3; setPointsP(); setArrayP(); break;
            case "T": width=3; height=1; length=3; setPointsT(); setArrayT(); break;
        }
        setEdges();
    }

    public PentominoParcel(String id, int value){
	    this(id);
	    setValue(value);
    }

    public static void main(String[] args) {
    		PentominoParcel test = new PentominoParcel("L");
    		System.out.println(test.getArray().length+ " " + test.getWidth());
    		System.out.println(test.getArray()[0].length + " " + test.getHeight());
    		System.out.println(test.getArray()[0][0].length + " " + test.getLength());
    		test.printPentomino();
    		test.mirrorX();
    		test.printPentomino();
    		test.mirrorX();
    		test.mirrorZ();
    		test.printPentomino();
    		test.rotX();
    		test.printPentomino();
    		System.out.println(test.getArray().length);
    		System.out.println(test.getArray()[0].length + " " + test.getHeight());
    		System.out.println(test.getArray()[0][0].length);


    }


    public String[][][] getArray() {
    		return array;
    }

    public int getVolume() {
    		return 5;
    }

    public void mirrorX() {
    	String[][][] newPentAr = new String[array.length][array[0].length][array[0][0].length];
    	for(int i=0; i<array.length; i++) {
    		for(int j=0; j<array[0].length; j++) {
    			for(int k=0; k<array[0][0].length; k++) {
    				newPentAr[i][j][k]=array[array.length-i-1][j][k];
    			}
    		}
    	}
    	array=newPentAr;
    }

    public void mirrorY() {
    	String[][][] newPentAr = new String[array.length][array[0].length][array[0][0].length];
    	for(int i=0; i<array.length; i++) {
    		for(int j=0; j<array[0].length; j++) {
    			for(int k=0; k<array[0][0].length; k++) {
    				newPentAr[i][j][k]=array[i][array[0].length-1-j][k];
    			}
    		}
    	}
    	array=newPentAr;

    }

    public void mirrorZ() {
    	String[][][] newPentAr = new String[array.length][array[0].length][array[0][0].length];
    	for(int i=0; i<array.length; i++) {
    		for(int j=0; j<array[0].length; j++) {
    			for(int k=0; k<array[0][0].length; k++) {
    				newPentAr[i][j][k]=array[i][j][array[0][0].length-1-k];
    			}
    		}
    	}
    	array=newPentAr;
    }

    public void rotX() {
    		String[][][] newPentAr = new String[array.length][array[0][0].length][array[0].length];
    		for(int i=0; i<newPentAr.length; i++) {
    			for(int j=0; j<newPentAr[0].length; j++) {
    				for(int k=0; k<newPentAr[0][0].length; k++) {
    					newPentAr[i][j][k]=array[i][newPentAr[0][0].length-1-k][j];
    				}
    			}
    		}
    		array=newPentAr;

    		width=newPentAr.length;
        	height=newPentAr[0].length;
        	length=newPentAr[0][0].length;
    }

    public void rotY() {
		String[][][] newPentAr = new String[array[0].length][array.length][array[0][0].length];
		for(int i=0; i<newPentAr.length; i++) {
			for(int j=0; j<newPentAr[0].length; j++) {
				for(int k=0; k<newPentAr[0][0].length; k++) {
					newPentAr[i][j][k]=array[newPentAr[0].length-1-j][i][k];
				}
			}
		}
		array = newPentAr;

		width=newPentAr.length;
    	height=newPentAr[0].length;
    	length=newPentAr[0][0].length;
    }

    public void rotZ() {
		String[][][] newPentAr = new String[array[0][0].length][array[0].length][array.length];
		for(int i=0; i<newPentAr.length; i++) {
			for(int j=0; j<newPentAr[0].length; j++) {
				for(int k=0; k<newPentAr[0][0].length; k++) {
					newPentAr[i][j][k]=array[k][j][newPentAr.length-1-i];
				}
			}
		}
		array = newPentAr;

		width=newPentAr.length;
    	height=newPentAr[0].length;
    	length=newPentAr[0][0].length;
    }

    public void printPentomino() {
    		for(int i=0; i<array[0].length; i++) {
    			for(int j=0; j<array.length; j++) {
    				for(int k=0; k<array[0][0].length; k++) {
    					System.out.print(array[j][i][k]+" ");
    				}
    				System.out.println("");
    			}
    			System.out.println("");
    		}
    }

    private void setPointsT() {
        setID("T");

        points = new ArrayList<>();
        for (int i = 0; i <= 1; i++) {
            points.add(new Point3D(0, i, 0));
            points.add(new Point3D(1, i, 0));
            points.add(new Point3D(1, i, 1));
            points.add(new Point3D(3, i, 1));
            points.add(new Point3D(3, i, 2));
            points.add(new Point3D(1, i, 2));
            points.add(new Point3D(1, i, 3));
            points.add(new Point3D(0, i, 3));
        }
    }

    private void setPointsP() {
        setID("P");

        points = new ArrayList<>();
        for (int i = 0; i <= 1; i++) {
            points.add(new Point3D(0, i, 0));
            points.add(new Point3D(3, i, 0));
            points.add(new Point3D(3, i, 1));
            points.add(new Point3D(2, i, 1));
            points.add(new Point3D(2, i, 2));
            points.add(new Point3D(0, i, 2));
        }
    }

    private void setPointsL() {
        setID("L");

        points = new ArrayList<>();
        for (int i = 0; i <= 1; i++) {
            points.add(new Point3D(0, i, 0));
            points.add(new Point3D(4, i, 0));
            points.add(new Point3D(4, i, 2));
            points.add(new Point3D(3, i, 2));
            points.add(new Point3D(3, i, 1));
            points.add(new Point3D(0, i, 1));
        }
    }

    private void setArrayL() {
    		setID("L");

    		array = new String[][][] { {{"L", "-"}},
    								  {{"L", "-"}},
    								  {{"L", "-"}},
    								  {{"L", "L"}} };
    }

    private void setArrayP() {
    		setID("P");

    		array = new String[][][] {{{"P", "P"}},
    			                      {{"P", "P"}},
    			                      {{"P", "-"}}};
    		}

    private void setArrayT() {
		setID("T");

		array = new String[][][] {{{"T", "T", "T"}},
			                      {{"-", "T", "-"}},
			                      {{"-", "T", "-"}}};
		}
}
