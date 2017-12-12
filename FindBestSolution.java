

 import java.awt.event.ComponentAdapter;
// import java.awt.event.ComponentEvent;
// import java.awt.event.ComponentListener;
import java.util.Scanner;

import ui.example.ExamplePanel;
import ui.example.MainExampleFrame;

 import javax.swing.JFrame;

public class BestFit {

	public static void main(String[] args) {
/* We create an instance of the class Shapes, or we can say the object shape */
	Shapes shape = new Shapes();

	/* We create a 4d array to store our shapes. The first position of this array is connected to the shape
	   while the second it is connected to the state of the shape (rotations, mirroring e.t.c),*/
		 	String[][][][] arrayOfShapes = shapeArray();


int height=12;
int  width=5 ;

/* We create our board array, we will use this to create our board object */
	String[][] board1 = new String[height][width];


	for (int i=0; i<board1.length; i++) {
		for (int j=0; j<board1[i].length; j++) {
			board1[i][j]="-";
		}
	}

//	String[][] board2 = new String[3][];
//		board2[0] = new String[4];
//		board2[1] = new String[4];
//		board2[2] = new String[2];
//

	/* We create our board object */
	PentominosBoard pentominosBoard = new PentominosBoard(board1);
    pentominosBoard.printBoard();

/* Depending on the user input we may need to store a different number of shapes in a new 4d array */
  String[][][][] newArrayOfShapes=createNewStartingShapeArray(arrayOfShapes,board1);

	/* We call the method that fills the board with shapes. This method actually uses all the rest
	   of the methods and fill the board in a proper way. It avoids misplacing shapes etc */
	fillTheBoard(pentominosBoard, newArrayOfShapes, 0, 0);
	System.out.println("number of steps = "+numberOfSteps+", number of solutions = "+numberOfSolutions);
}


/* We store all the 12 shapes in our 4d arrayOfShapes by using this method */
public static String[][][][] shapeArray() {
	Shapes shape = new Shapes();
	String[][][][] shapeArray = new String[12][][][];

	shapeArray[0] = new String[1][][];
	shapeArray[0][0] = shape.buildShapeI();
//	shapeArray[0][1] = shape.rowCollumnInterchange(shapeArray[0][0]);

	shapeArray[1] = new String[2][][];
	shapeArray[1][0] = shape.buildShapeL();
	shapeArray[1][1] = shape.horizontalMirroring(shapeArray[1][0]);
//	shapeArray[1][2] = shape.rowCollumnInterchange(shapeArray[1][0]);
//	shapeArray[1][3] = shape.rowCollumnInterchange(shapeArray[1][1]);
//	shapeArray[1][4] = shape.diagonalMirroring(shapeArray[1][0]);
//	shapeArray[1][5] = shape.diagonalMirroring(shapeArray[1][1]);
//	shapeArray[1][6] = shape.diagonalMirroring(shapeArray[1][2]);
//	shapeArray[1][7] = shape.diagonalMirroring(shapeArray[1][3]);

	shapeArray[2] = new String[8][][];
	shapeArray[2][0] = shape.buildShapeY();
	shapeArray[2][1] = shape.horizontalMirroring(shapeArray[2][0]);
	shapeArray[2][2] = shape.rowCollumnInterchange(shapeArray[2][0]);
	shapeArray[2][3] = shape.rowCollumnInterchange(shapeArray[2][1]);
	shapeArray[2][4] = shape.diagonalMirroring(shapeArray[2][0]);
	shapeArray[2][5] = shape.diagonalMirroring(shapeArray[2][1]);
	shapeArray[2][6] = shape.diagonalMirroring(shapeArray[2][2]);
	shapeArray[2][7] = shape.diagonalMirroring(shapeArray[2][3]);

	shapeArray[3] = new String[8][][];
	shapeArray[3][0] = shape.buildShapeP();
	shapeArray[3][1] = shape.horizontalMirroring(shapeArray[3][0]);
	shapeArray[3][2] = shape.rowCollumnInterchange(shapeArray[3][0]);
	shapeArray[3][3] = shape.rowCollumnInterchange(shapeArray[3][1]);
	shapeArray[3][4] = shape.diagonalMirroring(shapeArray[3][0]);
	shapeArray[3][5] = shape.diagonalMirroring(shapeArray[3][1]);
	shapeArray[3][6] = shape.diagonalMirroring(shapeArray[3][2]);
	shapeArray[3][7] = shape.diagonalMirroring(shapeArray[3][3]);

	shapeArray[4] = new String[8][][];
	shapeArray[4][0] = shape.buildShapeN();
	shapeArray[4][1] = shape.horizontalMirroring(shapeArray[4][0]);
	shapeArray[4][2] = shape.rowCollumnInterchange(shapeArray[4][0]);
	shapeArray[4][3] = shape.rowCollumnInterchange(shapeArray[4][1]);
	shapeArray[4][4] = shape.diagonalMirroring(shapeArray[4][0]);
	shapeArray[4][5] = shape.diagonalMirroring(shapeArray[4][1]);
	shapeArray[4][6] = shape.diagonalMirroring(shapeArray[4][2]);
	shapeArray[4][7] = shape.diagonalMirroring(shapeArray[4][3]);

	shapeArray[5] = new String[4][][];
	shapeArray[5][0] = shape.buildShapeU();
	shapeArray[5][1] = shape.rowCollumnInterchange(shapeArray[5][0]);
	shapeArray[5][2] = shape.diagonalMirroring(shapeArray[5][0]);
	shapeArray[5][3] = shape.diagonalMirroring(shapeArray[5][1]);

	shapeArray[6] = new String[4][][];
	shapeArray[6][0] = shape.buildShapeV();
	shapeArray[6][1] = shape.horizontalMirroring(shapeArray[6][0]);
	shapeArray[6][2] = shape.diagonalMirroring(shapeArray[6][0]);
	shapeArray[6][3] = shape.diagonalMirroring(shapeArray[6][1]);

	shapeArray[7] = new String[4][][];
	shapeArray[7][0] = shape.buildShapeT();
	shapeArray[7][1] = shape.rowCollumnInterchange(shapeArray[7][0]);
	shapeArray[7][2] = shape.diagonalMirroring(shapeArray[7][0]);
	shapeArray[7][3] = shape.diagonalMirroring(shapeArray[7][1]);

	shapeArray[8] = new String[1][][];
	shapeArray[8][0] = shape.buildShapeX();

	shapeArray[9] = new String[4][][];
	shapeArray[9][0] = shape.buildShapeZ();
	shapeArray[9][1] = shape.horizontalMirroring(shapeArray[9][0]);
	shapeArray[9][2] = shape.rowCollumnInterchange(shapeArray[9][0]);
	shapeArray[9][3] = shape.rowCollumnInterchange(shapeArray[9][1]);


	shapeArray[10] = new String[8][][];
	shapeArray[10][0] = shape.buildShapeF();
	shapeArray[10][1] = shape.horizontalMirroring(shapeArray[10][0]);
	shapeArray[10][2] = shape.rowCollumnInterchange(shapeArray[10][0]);
	shapeArray[10][3] = shape.rowCollumnInterchange(shapeArray[10][1]);
	shapeArray[10][4] = shape.diagonalMirroring(shapeArray[10][0]);
	shapeArray[10][5] = shape.diagonalMirroring(shapeArray[10][1]);
	shapeArray[10][6] = shape.diagonalMirroring(shapeArray[10][2]);
	shapeArray[10][7] = shape.diagonalMirroring(shapeArray[10][3]);

	shapeArray[11] = new String[4][][];
	shapeArray[11][0] = shape.buildShapeW();
	shapeArray[11][1] = shape.rowCollumnInterchange(shapeArray[11][0]);
	shapeArray[11][2] = shape.horizontalMirroring(shapeArray[11][0]);
	shapeArray[11][3] = shape.diagonalMirroring(shapeArray[11][2]);

	return shapeArray;
}

/* We create a new shape storing array which may or may not be needed, depending on the user input */
public static String[][][][] createNewStartingShapeArray(String[][][][] arrayOfShapes, String[][] board){
  int area=board.length*board[0].length;
	Scanner sc = new Scanner(System.in);
	System.out.println("Give me the number of pentominoes we need to use");
	int numberOfPentominos=sc.nextInt();
	while(numberOfPentominos<area/5){
		System.out.println("You gave us very few pentominoes to fill such a big board! Please give us more!");
		numberOfPentominos=sc.nextInt();
	}
	if (numberOfPentominos>area/5){
		System.out.println("You gave me more than enough of pentominoes to fill such a small board,");
		System.out.println("so I will only use "+(area/5)+" of them");
	}
	String[][][][] newArrayOfShapes=new String[numberOfPentominos][][][];
  String allThePentominoes="I L Y P N U V T X Z F W";
	System.out.println("Which pentominoes would you like us to try, press enter after each one you give");
    System.out.println("List of Pentominoes: "+allThePentominoes);
	String[]   whichPentominoes=new String[numberOfPentominos];

				if (numberOfPentominos!=12){
	         sc.nextLine();
					 for (int i=0; i<numberOfPentominos; i++){
						 whichPentominoes[i]=sc.nextLine();
						 if(!allThePentominoes.contains(whichPentominoes[i])){
							 i--;
							 System.out.println("you gave us a pentomino that does not exist, please give us an existing one!");
						 }
					 }
					 for (int i=0; i<numberOfPentominos; i++){
						 if (whichPentominoes[i].equals("I")){
							 newArrayOfShapes[i]=arrayOfShapes[0];
						 }else if(whichPentominoes[i].equals("L")){
							 newArrayOfShapes[i]=arrayOfShapes[1];
						 }else if(whichPentominoes[i].equals("Y")){
							 newArrayOfShapes[i]=arrayOfShapes[2];
						 }else if(whichPentominoes[i].equals("P")){
							 newArrayOfShapes[i]=arrayOfShapes[3];
						 }else if(whichPentominoes[i].equals("N")){
							 newArrayOfShapes[i]=arrayOfShapes[4];
						 }else if(whichPentominoes[i].equals("U")){
							 newArrayOfShapes[i]=arrayOfShapes[5];
						 }else if(whichPentominoes[i].equals("V")){
							 newArrayOfShapes[i]=arrayOfShapes[6];
						 }else if(whichPentominoes[i].equals("T")){
							 newArrayOfShapes[i]=arrayOfShapes[7];
						 }else if(whichPentominoes[i].equals("X")){
							 newArrayOfShapes[i]=arrayOfShapes[8];
						 }else if(whichPentominoes[i].equals("Z")){
							 newArrayOfShapes[i]=arrayOfShapes[9];
						 }else if(whichPentominoes[i].equals("F")){
							 newArrayOfShapes[i]=arrayOfShapes[10];
						 }else if(whichPentominoes[i].equals("W")){
							 newArrayOfShapes[i]=arrayOfShapes[11];
						 }

					 }
				 }else if(numberOfPentominos==12){
					 newArrayOfShapes=arrayOfShapes;
				 }
		return newArrayOfShapes;
}

public static String[][][][] permutateShapeArray(String[][][][] shapeArray){
	String[][][][] permutatedShapeArray = new String[shapeArray.length][][][];
	permutatedShapeArray[shapeArray.length-1]=shapeArray[0];
	for(int i = 0; i < shapeArray.length; i++) {
			if(i<shapeArray.length-1)
				permutatedShapeArray[i]=shapeArray[i+1];
	}
	return permutatedShapeArray;
}

/* We create a new array with all the shapes apart from the ones we have already placed on the board */
public static String[][][][] createNewShapeArray(String[][][][] shapeArray, int j) {
	String[][][][] newShapeArray = new String[shapeArray.length - 1][][][];

	for (int i = 0; i < shapeArray.length; i++) {
		if (i < j) {
			newShapeArray[i] = shapeArray[i];
		} else if (i > j) {
			newShapeArray[i - 1] = shapeArray[i];
		}
	}

	return newShapeArray;
}

// We create an exact copy of the storing array, so we can use it without worries when we nee it
public static String[][][][] copyShapeArray(String[][][][] shapeArray) {
	String[][][][] newShapeArray = new String[shapeArray.length][][][];
	for (int i = 0; i < shapeArray.length; i++) {
		newShapeArray[i] = shapeArray[i];
	}
	return newShapeArray;
}

static int numberOfSolutions=0;
static int numberOfSteps=0;

/* This is the most important method. This method takes the board, start checking from the beginning
   for empty positions in it. When it finds first empty position, it tries to fill it with a shape,
	 if it is unable to do so, it goes one step back (remove the last shape we placed),
	 and tries another shape. The recursion stops when it has actually tried all the reasonable combinations of
	 shapes. */
public static boolean fillTheBoard(PentominosBoard board, String[][][][] shapeArray, int i, int j) {

	int[] setPosition = new int[2];

	Shapes shape = new Shapes();


	if (board.isBoardFull()  ) {

		if(board.checkLocationOfI() && board.checkLocationOfL()) {
			numberOfSolutions++;
			System.out.println("Number of steps for the solution: "+numberOfSteps);
			System.out.println("");
			board.printBoard();
			System.out.println("");

			String[][] reverseBoard = board.ReverseBoard();
			PentominosBoard reversedBoard = new PentominosBoard(reverseBoard);
//			reversedBoard.printBoard();
//			PentominosBoard fullBoard = board;
			Screen scr=new Screen(reversedBoard);
			Frame frame = new Frame(reversedBoard,scr);
//			frame.add(scr);
		}
		return true;
	} else {
		setPosition = board.whereToPut();
		if (board.isCopyPossible(shapeArray[i][j], setPosition[0], setPosition[1])) {
			PentominosBoard newBoard = board.copyBoard();
			newBoard.addShapeToBoard(shapeArray[i][j], setPosition[0], setPosition[1]);
			numberOfSteps++;
			if (newBoard.checkILshapesLine() /*&& newBoard.checkSpecificSolution() */) {
			String[][][][] newShapeArray = copyShapeArray(shapeArray);
			newShapeArray = createNewShapeArray(shapeArray, i);
			if (fillTheBoard(newBoard, newShapeArray, 0, 0)) {
				return false;
			}
			}

		}
		j++;
		if (j == shapeArray[i].length) {
			j = 0;
			i++;
			if (i==shapeArray.length) {
				i=0;
				return false;
			}

		}
		return fillTheBoard(board, shapeArray, i, j);

	}
  }








//  public static int scoreCounter(PentominosBoard board) {
//	  String[][] arrBoard = board.getBoard();
//	  int scoreValue = 0;
//	  int fulLines = 0;
//	  boolean checkLine = true;
//	  for (int i=0; i<arrBoard.length; i++) {
//		  checkLine=true;
//		  for (int j=0; j<arrBoard[0].length; j++) {
//			  if(arrBoard[i][j].equals("-"))
//				   checkLine=false;
//		  }
//		  if (checkLine)
//			  fulLines++;
//	  }
//	  if (fulLines==0)
//	  	  scoreValue=0;
//	  else if (fulLines==1)
//		  scoreValue=100;
//	  else if (fulLines==2)
//		  scoreValue=250;
//	  else if (fulLines==3)
//		  scoreValue=425;
//	  else if (fulLines==4)
//		  scoreValue=600;
//	  else if (fulLines==5)
//		  scoreValue=900;
//
//	  return scoreValue;
//  }
}
