
package knapsack.components;

import javafx.geometry.Point3D;

import java.awt.*;

/**
 * Class defining Pentomino shaped Parcels, they extends Parcel because they are also defined by a 3D array, points and edges.
 */
public class PentominoParcel extends Parcel {

    /**
     * Constructor for a Pentomino Parcel.
     * @param id The id of the Parcel to be constructed.
     */
    public PentominoParcel(String id) {
        this.id = id;
        setPos(new Point3D(0,0,0));
        switch (id){
            case "L": width=4; height=1; length=2; setValue(3); setPoints(); setArrayL(); fillColor = new Color(1,0,0,0.3f); break;
            case "P": width=3; height=1; length=2; setValue(4); setPoints(); setArrayP(); fillColor = new Color(0,1,0,0.3f); break;
            case "T": width=3; height=1; length=3; setValue(5); setPoints(); setArrayT(); fillColor = new Color(0,0,1,0.3f); break;
        }
        setEdges();
    }

    /**
     * Constructor for a Pentomino Parcel where the value can be set.
     * @param id The id of the Parcel to be constructed.
     * @param value The value the parcel is going to have.
     */
    public PentominoParcel(String id, int value){
	    this(id);
	    setValue(value);
    }

    /**
     * Method to return the volume of the actual PentominoParcel.
     * @return An integer corresponding to the volume of the PentominoParcel
     */
    public int getVolume() {
    		return 5;
    }

    /**
     * Method used to mirror a PentominoParcel around the X axis.
     */
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

    /**
     * Method used to mirror a PentominoParcel around the Y axis.
     */
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

    /**
     * Method used to mirror a PentominoParcel around the Z axis.
     */
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

    /**
     * Method used to rotate a PentominoParcel around the X axis.
     */
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

    /**
     * Method used to rotate a PentominoParcel around the Y axis.
     */
    public void rotY() {
		String[][][] newPentAr = new String[array[0].length][array.length][array[0][0].length];
		for(int i=0; i<newPentAr.length; i++) {
			for(int j=0; j<newPentAr[0].length; j++) {
				for(int k=0; k<newPentAr[0][0].length; k++) {
					newPentAr[i][j][k]=array[j][newPentAr.length-1-i][k];
				}
			}
		}
		array = newPentAr;

		width=newPentAr.length;
    	height=newPentAr[0].length;
    	length=newPentAr[0][0].length;
    }

    /**
     * Method used to rotate a PentominoParcel around the Z axis.
     */
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

    /**
     * Method used to define the array of the PentominoParcel with id L.
     */
    private void setArrayL() {
        array = new String[][][] { {{"L", "-"}},
                                  {{"L", "-"}},
                                  {{"L", "-"}},
                                  {{"L", "L"}} };
    }

    /**
     * Method used to define the array of the PentominoParcel with id P.
     */
    private void setArrayP() {
        array = new String[][][] {{{"P", "P"}},
                                  {{"P", "P"}},
                                  {{"P", "-"}}};
        }

    /**
     * Method used to define the array of the PentominoParcel with id T.
     */
    private void setArrayT() {
		array = new String[][][] {{{"T", "T", "T"}},
			                      {{"-", "T", "-"}},
			                      {{"-", "T", "-"}}};
    }

    /**
     * Used to bulk create a Parcel array containing all variations of the L parcel with a certain (user inputted) value
     * @param value of parcels
     * @return Parcel array containing variations of L parcel
     */
    public static PentominoParcel[] createParcelsArrL(int value) {
		PentominoParcel parcelL = new PentominoParcel("L",value);
		PentominoParcel xMirrorL = new PentominoParcel("L",value);
		xMirrorL.mirrorX();
		PentominoParcel zMirrorL = new PentominoParcel("L",value);
		zMirrorL.mirrorZ();
		PentominoParcel xzMirrorL = new PentominoParcel("L",value);
		xzMirrorL.mirrorX();
		xzMirrorL.mirrorZ();
		PentominoParcel xRotParcelL = new PentominoParcel("L",value);
		xRotParcelL.rotX();
		PentominoParcel yRotParcelL = new PentominoParcel("L",value);
		yRotParcelL.rotY();
		PentominoParcel zRotParcelL = new PentominoParcel("L",value);
		zRotParcelL.rotZ();
		PentominoParcel xyRotParcelL = new PentominoParcel("L",value);
		xyRotParcelL.rotX();
		xyRotParcelL.rotY();
		PentominoParcel xzRotParcelL = new PentominoParcel("L",value);
		xzRotParcelL.xRotate();
		xzRotParcelL.zRotate();
		PentominoParcel xRotXMirrorL = new PentominoParcel("L",value);
		xRotXMirrorL.mirrorX();
		xRotXMirrorL.rotX();
		PentominoParcel yRotXMirrorL = new PentominoParcel("L",value);
		yRotXMirrorL.mirrorX();
		yRotXMirrorL.rotY();
		PentominoParcel zRotXMirrorL = new PentominoParcel("L",value);
		zRotXMirrorL.mirrorX();
		zRotXMirrorL.rotZ();
		PentominoParcel xyRotXmirrorL = new PentominoParcel("L",value);
		xyRotXmirrorL.mirrorX();
		xyRotXmirrorL.rotX();
		xyRotXmirrorL.rotY();
		PentominoParcel xzRotXmirrorL = new PentominoParcel("L",value);
		xzRotXmirrorL.mirrorX();
		xzRotXmirrorL.rotX();
		xzRotXmirrorL.rotZ();
		PentominoParcel xRotZMirrorL = new PentominoParcel("L",value);
		xRotZMirrorL.mirrorZ();
		xRotZMirrorL.rotX();
		PentominoParcel yRotZMirrorL = new PentominoParcel("L",value);
		yRotZMirrorL.mirrorZ();
		yRotZMirrorL.rotY();
		PentominoParcel zRotZMirrorL = new PentominoParcel("L",value);
		zRotZMirrorL.mirrorZ();
		zRotZMirrorL.rotZ();
		PentominoParcel xyRotZmirrorL = new PentominoParcel("L",value);
		xyRotZmirrorL.mirrorZ();
		xyRotZmirrorL.rotX();
		xyRotZmirrorL.rotY();
		PentominoParcel xzRotZmirrorL = new PentominoParcel("L",value);
		xzRotZmirrorL.mirrorZ();
		xzRotZmirrorL.rotX();
		xzRotZmirrorL.rotZ();
		PentominoParcel xRotXZMirrorL = new PentominoParcel("L",value);
		xRotXZMirrorL.mirrorX();
		xRotXZMirrorL.mirrorZ();
		xRotXZMirrorL.rotX();
		PentominoParcel yRotXZMirrorL = new PentominoParcel("L",value);
		yRotXZMirrorL.mirrorX();
		yRotXZMirrorL.mirrorZ();
		yRotXZMirrorL.rotY();
		PentominoParcel zRotXZMirrorL = new PentominoParcel("L",value);
		zRotXZMirrorL.mirrorX();
		zRotXZMirrorL.mirrorZ();
		zRotXZMirrorL.rotZ();
		PentominoParcel xyRotXZmirrorL = new PentominoParcel("L",value);
		xyRotXZmirrorL.mirrorX();
		xyRotXZmirrorL.mirrorZ();
		xyRotXZmirrorL.rotX();
		xyRotXZmirrorL.rotY();
		PentominoParcel xzRotXZmirrorL = new PentominoParcel("L",value);
		xzRotXZmirrorL.mirrorX();
		xzRotXZmirrorL.mirrorZ();
		xzRotXZmirrorL.rotX();
		xzRotXZmirrorL.rotZ();
		PentominoParcel[] parcelArr = new PentominoParcel[] {parcelL, xMirrorL, zMirrorL, xzMirrorL, xRotParcelL, yRotParcelL, zRotParcelL, xyRotParcelL, xzRotParcelL, xRotXMirrorL, yRotXMirrorL, zRotXMirrorL, xyRotXmirrorL, xzRotXmirrorL, xRotZMirrorL, yRotZMirrorL, zRotZMirrorL, xyRotZmirrorL, xzRotZmirrorL, xRotXZMirrorL, yRotXZMirrorL, zRotXZMirrorL, xyRotXZmirrorL, xzRotXZmirrorL};
		return parcelArr;
	}

    /**
     * Used to bulk create a Parcel array containing all variations of the P parcel with a certain (user inputted) value
     * @param value of parcels
     * @return Parcel array containing variations of P parcel
     */
	public static PentominoParcel[] createParcelsArrP(int value) {
		PentominoParcel parcelP = new PentominoParcel("P",value);
		PentominoParcel xMirrorP = new PentominoParcel("P",value);
		xMirrorP.mirrorX();
		PentominoParcel zMirrorP = new PentominoParcel("P",value);
		zMirrorP.mirrorZ();
		PentominoParcel xzMirrorP = new PentominoParcel("P",value);
		xzMirrorP.mirrorX();
		xzMirrorP.mirrorZ();
		PentominoParcel xRotParcelP = new PentominoParcel("P",value);
		xRotParcelP.rotX();
		PentominoParcel yRotParcelP = new PentominoParcel("P",value);
		yRotParcelP.rotY();
		PentominoParcel zRotParcelP = new PentominoParcel("P",value);
		zRotParcelP.rotZ();
		PentominoParcel xyRotParcelP = new PentominoParcel("P",value);
		xyRotParcelP.rotX();
		xyRotParcelP.rotY();
		PentominoParcel xzRotParcelP = new PentominoParcel("P",value);
		xzRotParcelP.xRotate();
		xzRotParcelP.zRotate();
		PentominoParcel xRotXMirrorP = new PentominoParcel("P",value);
		xRotXMirrorP.mirrorX();
		xRotXMirrorP.rotX();
		PentominoParcel yRotXMirrorP = new PentominoParcel("P",value);
		yRotXMirrorP.mirrorX();
		yRotXMirrorP.rotY();
		PentominoParcel zRotXMirrorP = new PentominoParcel("P",value);
		zRotXMirrorP.mirrorX();
		zRotXMirrorP.rotZ();
		PentominoParcel xyRotXmirrorP = new PentominoParcel("P",value);
		xyRotXmirrorP.mirrorX();
		xyRotXmirrorP.rotX();
		xyRotXmirrorP.rotY();
		PentominoParcel xzRotXmirrorP = new PentominoParcel("P",value);
		xzRotXmirrorP.mirrorX();
		xzRotXmirrorP.rotX();
		xzRotXmirrorP.rotZ();
		PentominoParcel xRotZMirrorP = new PentominoParcel("P",value);
		xRotZMirrorP.mirrorZ();
		xRotZMirrorP.rotX();
		PentominoParcel yRotZMirrorP = new PentominoParcel("P",value);
		yRotZMirrorP.mirrorZ();
		yRotZMirrorP.rotY();
		PentominoParcel zRotZMirrorP = new PentominoParcel("P",value);
		zRotZMirrorP.mirrorZ();
		zRotZMirrorP.rotZ();
		PentominoParcel xyRotZmirrorP = new PentominoParcel("P",value);
		xyRotZmirrorP.mirrorZ();
		xyRotZmirrorP.rotX();
		xyRotZmirrorP.rotY();
		PentominoParcel xzRotZmirrorP = new PentominoParcel("P",value);
		xzRotZmirrorP.mirrorZ();
		xzRotZmirrorP.rotX();
		xzRotZmirrorP.rotZ();
		PentominoParcel xRotXZMirrorP = new PentominoParcel("P",value);
		xRotXZMirrorP.mirrorX();
		xRotXZMirrorP.mirrorZ();
		xRotXZMirrorP.rotX();
		PentominoParcel yRotXZMirrorP = new PentominoParcel("P",value);
		yRotXZMirrorP.mirrorX();
		yRotXZMirrorP.mirrorZ();
		yRotXZMirrorP.rotY();
		PentominoParcel zRotXZMirrorP = new PentominoParcel("P",value);
		zRotXZMirrorP.mirrorX();
		zRotXZMirrorP.mirrorZ();
		zRotXZMirrorP.rotZ();
		PentominoParcel xyRotXZmirrorP = new PentominoParcel("P",value);
		xyRotXZmirrorP.mirrorX();
		xyRotXZmirrorP.mirrorZ();
		xyRotXZmirrorP.rotX();
		xyRotXZmirrorP.rotY();
		PentominoParcel xzRotXZmirrorP = new PentominoParcel("P",value);
		xzRotXZmirrorP.mirrorX();
		xzRotXZmirrorP.mirrorZ();
		xzRotXZmirrorP.rotX();
		xzRotXZmirrorP.rotZ();
		PentominoParcel[] parcelArr = new PentominoParcel[] {parcelP, xMirrorP, zMirrorP, xzMirrorP, xRotParcelP, yRotParcelP, zRotParcelP, xyRotParcelP, xzRotParcelP, xRotXMirrorP, yRotXMirrorP, zRotXMirrorP, xyRotXmirrorP, xzRotXmirrorP, xRotZMirrorP, yRotZMirrorP, zRotZMirrorP, xyRotZmirrorP, xzRotZmirrorP, xRotXZMirrorP, yRotXZMirrorP, zRotXZMirrorP, xyRotXZmirrorP, xzRotXZmirrorP};
		return parcelArr;
	}

    /**
     * Used to bulk create a Parcel array containing all variations of the T parcel with a certain (user inputted) value
     * @param value of parcels
     * @return Parcel array containing variations of T parcel
     */
	public static PentominoParcel[] createParcelsArrT(int value) {
        PentominoParcel parcelT = new PentominoParcel("T", value);
        PentominoParcel xMirrorT = new PentominoParcel("T",value);
        xMirrorT.mirrorX();
		PentominoParcel xRotParcelT = new PentominoParcel("T", value);
		xRotParcelT.rotX();
		PentominoParcel yRotParcelT = new PentominoParcel("T", value);
		yRotParcelT.rotY();
		PentominoParcel zRotParcelT = new PentominoParcel("T", value);
		zRotParcelT.rotZ();
		PentominoParcel xyRotParcelT = new PentominoParcel("T", value);
		xyRotParcelT.rotX();
		xyRotParcelT.rotY();
		PentominoParcel xzRotParcelT = new PentominoParcel("T", value);
		xzRotParcelT.xRotate();
		xzRotParcelT.zRotate();
		PentominoParcel xRotXMirrorT = new PentominoParcel("T", value);
		xRotXMirrorT.mirrorX();
		xRotXMirrorT.rotX();
		PentominoParcel yRotXMirrorT = new PentominoParcel("T", value);
		yRotXMirrorT.mirrorX();
		yRotXMirrorT.rotY();
		PentominoParcel zRotXMirrorT = new PentominoParcel("T", value);
		zRotXMirrorT.mirrorX();
		zRotXMirrorT.rotZ();
		PentominoParcel xyRotXmirrorT = new PentominoParcel("T", value);
		xyRotXmirrorT.mirrorX();
		xyRotXmirrorT.rotX();
		xyRotXmirrorT.rotY();
		PentominoParcel xzRotXmirrorT = new PentominoParcel("T", value);
		xzRotXmirrorT.mirrorX();
		xzRotXmirrorT.rotX();
		xzRotXmirrorT.rotZ();
		PentominoParcel[] parcelArr = new PentominoParcel[]{xRotParcelT, yRotParcelT, zRotParcelT, xyRotParcelT, xzRotParcelT, xRotXMirrorT, yRotXMirrorT, zRotXMirrorT, xyRotXmirrorT, xzRotXmirrorT};
		return parcelArr;
	}
}
