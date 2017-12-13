import java.lang.*;
import java.util.*;

/**
Class defining the board containing Shape objects.
@see Shape
*/
public class PentrisBoard {

	private String[][] board;
	private int startX=0;
	private int startY=0;
	private int[] grid;

	/**
	Constructor of the object PentrisBoard. Fill a 2-D array (board) with "-".
	@param grid defining the size of the board. (Default: 5x15)
	*/
	public PentrisBoard(int[] grid){
		this.board = new String[grid[1]][grid[0]];
		for(int i=0; i<board.length; i++)
			for(int j=0; j<board[0].length; j++)
				board[i][j] = "-";
	}

	/**
	Constructor to create a PentrisBoard from an 2-D array
	@param board
	*/
	public PentrisBoard(String[][] board){
		this.board = board;
	}
	/**
	Return a 2-D representing the PentrisBoard.
	@return A 2-D array filled with "-" and shape ID's.
	@see Shape
	*/
	public String[][] getBoard() {
		return board;
	}

	/**
	This method adds Shape objects to the PentrisBoard 2-D array at given x and y coordinates.
	@param shape The shape to be added to the board.
	@param x The x position of the shape in the board (top row).
	@param y The y position of the shape in the board (left columns).
	@see Shape
	*/
	public void addShapeToBoard(Shape shape, int x, int y) {
		for (int i = 0; i < shape.getHeight(); i++) {
			for (int j = 0; j < shape.getWidth(); j++) {
				if (!shape.getElement(i,j).equals("-") && y+i<board.length && x+j<board[0].length) {
					board[y + i][x + j] = shape.getElement(i,j);
				}
			}
		}
	}

	/**
	This method adds Shape objects to the board at position determined by startY and at the middle of the board values.
	@param shape The shape to be added.
	@see Shape
	*/
	public boolean addShapeToBoard(Shape shape){
		int boardW = board[0].length;
		int x = (int)((boardW-shape.getWidth())*1.0)/2;
		int y = startY;
		if(insertionPossible(shape,x,y)){
			addShapeToBoard(shape,x,y);
			return true;
		}
		return false;
	}

	/**
	Method to check wether the placement of the Shape object is possible at the specified x and y coordinates.
	@param shape The Shape object to check.
	@param x The x coordinate where the shape wants to be placed.
	@param y The y coordinate where the shape wants to be placed.
	@return True is the insertion is possible. False if the insertion is not possible.
	@see Shape
	*/
	private boolean insertionPossible(Shape shape,int x, int y){
		for(int i=0; i<shape.getHeight(); i++){
			for(int j=0; j<shape.getWidth(); j++){
				if(!shape.getElement(i,j).equals("-") && !board[y+i][x+j].equals("-")) return false;
			}
		}
		return true;
	}

	/**
	Method to remove a shape from the board. The x and y coordinates are specified to avoid removing other shapes of the same ID.
	@param shape The Shape object to remove.
	@param x The x coordinate where the shape wants to be removed.
	@param y The y coordinate where the shape wants to be removed.
	@see Shape
	*/
	public void removeShapeFromBoard(Shape shape, int x, int y) {
		for (int i = 0; i < shape.getHeight(); i++) {
			for (int j = 0; j < shape.getWidth(); j++) {
				if (!shape.getElement(i,j).equals("-") && y+i<board.length && x+j<board[0].length) {
					board[y + i][x + j] = "-";
				}
			}
		}
	}

	/**
	Method checking whether the Shape object is placed by checking if next going one tile down is possible.
	@param shape The Shape object to check.
	@param x The x coordinate where the shape wants to be placed.
	@param y The y coordinate where the shape wants to be placed.
	@return True if the Shape object is placed. False if the Shape object is not placed.
	@see Shape
	*/
	public boolean isPlaced(Shape shape, int x, int y) {
		for(int i = shape.getHeight()-1; i>=0; i--){
			for(int j=0; j<shape.getWidth();j++){
				if(!shape.getElement(i,j).equals("-")){
					if(i>=shape.getHeight()-1 || (i<shape.getHeight()-1 && shape.getElement(i+1,j).equals("-"))){
						if(i+y+1>=board.length || !board[i+y+1][j+x].equals("-")){
						 	return true;
						}
					}
				}
			}
		}
		return false;
	}

	/**
	This method check whether a given line is full (do not have any "-").
	@param line The y position of the line to be checked.
	@return True if the given line is full. False if the given line is not full.
	*/
	public boolean isLineFull(int line) {
		for (int j = 0; j < board[line].length; j++) {
			if (board[line][j].equals("-"))
				return false;
		}
		return true;
	}

	/**
	This method empties a line of the board 2-D array.
	@param line The y position of the line to be checked.
	*/
	public void emptyLine(int line) {
		for (int i = 0; i < board[line].length; i++) {
			board[line][i] = "-";
		}
	}

	/**
	This method check whether a given line is empty (do not have any letter).
	@param line The y position of the line to be checked.
	@return True if the given line is empty. False if the given line is not empty.
	*/
	public boolean isLineEmpty(int line) {
		for(int j=0; j<board[line].length; j++) {
			if(!board[line][j].equals("-"))
				return false;
		}
		return true;
	}

	/**
	This method checks if lines of the board are full. If some are, it deletes the line(s) and shift the upper rows down.
	@return The amount of broken lines.
	*/
	public int breakLines() {
		int counter = 0;
		String[][] newBoard = board;
		for (int i = board.length - 1; i >= 0; i--) {

			if(isLineFull(i)) {
				int j=i;
				while(!isLineEmpty(j) && j>0) {
					for(int k=0; k<board[0].length; k++) {
						newBoard[j][k]=newBoard[j-1][k];
					}
					j--;
				}
				System.out.println("test");
				board=newBoard;
				i++;
				counter++;
			}
		}
		return counter;
	}

	/**
	Checks if the rotation of a given Shape object is possible at the given x, y positon.
	@param shape The Shape object to check.
	@param x The x coordinate where the shape wants to be rotated.
	@param y The y coordinate where the shape wants to be rotated.
	@return True if the rotation of the Shape object is possible. False if the rotation of the Shape object is not possible.
	@see Shape
	*/
	public boolean rotatePossible(Shape shape, int x, int y){
		if(shape.getWidth()>shape.getHeight() && (2*shape.getWidth())-shape.getHeight()+y>=board.length+2){
			return false;
		}
 		if(shape.getHeight()>shape.getWidth() && x+(shape.getHeight()-shape.getWidth())+shape.getWidth()-1 > board[0].length-1){
 			return false;
 		}
 		String[][] sShape = shape.sRotateR();
 		for(int i=0;i<sShape.length;i++){
 			for(int j=0;j<sShape[0].length;j++){
 				if(i<shape.getHeight() && j<shape.getWidth()){
 					if(!sShape[i][j].equals("-") && shape.getElement(i,j).equals("-") && !board[y+i][x+j].equals("-")){
 						return false;
 					}
 				}
 				else if(!sShape[i][j].equals("-") && !board[y+i][x+j].equals("-")){
 					return false;
 				}
 			}
 		}
		return true;
	}

	/**
	Rotates a given Shape object in the board at an x, y positon.
	@param shape The Shape object to rotate.
	@param x The x coordinate where the shape wants to be rotated.
	@param y The y coordinate where the shape wants to be rotated.
	@see Shape
	*/
	public void rotate(Shape shape, int x, int y) {
 		removeShapeFromBoard(shape,x,y);
 		shape.rotateR();
 		addShapeToBoard(shape,x,y);
 	}

	/**
	Checks if the left movement of a given Shape object is possible at the given x, y positon.
	@param shape The Shape object to check.
	@param x The x coordinate where the shape wants to be moved.
	@param y The y coordinate where the shape wants to be moved.
	@return True if the movement of the Shape object is possible. False if the movement of the Shape object is not possible.
	@see Shape
	*/
	public boolean moveLeftPossible(Shape shape, int x, int y){
		if(x==0) return false;
		for(int i=0;i<shape.getHeight();i++){
			for(int j=0;j<shape.getWidth();j++){
				if(j==0 && !shape.getElement(i,j).equals("-") && !board[y+i][x+j-1].equals("-")) return false;
				if(j>0 && !shape.getElement(i,j).equals("-") && shape.getElement(i,j-1).equals("-") && !board[y+i][x+j-1].equals("-")) return false;
			}
		}
		return true;
	}

	/**
	Checks if the right movement of a given Shape object is possible at the given x, y positon.
	@param shape The Shape object to check.
	@param x The x coordinate where the shape wants to be moved.
	@param y The y coordinate where the shape wants to be moved.
	@return True if the movement of the Shape object is possible. False if the movement of the Shape object is not possible.
	@see Shape
	*/
	public boolean moveRightPossible(Shape shape,int x, int y){
		if(x+shape.getWidth()-1==board[0].length-1) return false;
		for(int i=0;i<shape.getHeight();i++){
			for(int j=0;j<shape.getWidth();j++){
				if(j==shape.getWidth()-1 && !shape.getElement(i,j).equals("-") && !board[y+i][x+j+1].equals("-")) return false;
				if(j<shape.getWidth()-1 && !shape.getElement(i,j).equals("-") && shape.getElement(i,j+1).equals("-") && !board[y+i][x+j+1].equals("-")) return false;
			}
		}
		return true;
	}

	/**
	Method to move a given Shape object one tile left.
	@param shape The Shape object to move.
	@param xCoordinateBoard The x coordinate where the shape wants to be moved.
	@param yCoordinateBoard The y coordinate where the shape wants to be moved.
	@see Shape
	*/
	public void moveLeft(Shape shape, int xCoordinateBoard, int yCoordinateBoard) {
		removeShapeFromBoard(shape, xCoordinateBoard, yCoordinateBoard);
		xCoordinateBoard--;
		addShapeToBoard(shape, xCoordinateBoard, yCoordinateBoard);
	}

	/**
	Method to move a given Shape object one tile right.
	@param shape The Shape object to move.
	@param xCoordinateBoard The x coordinate where the shape wants to be moved.
	@param yCoordinateBoard The y coordinate where the shape wants to be moved.
	@see Shape
	*/
	public void moveRight(Shape shape, int xCoordinateBoard, int yCoordinateBoard) {
		removeShapeFromBoard(shape, xCoordinateBoard, yCoordinateBoard);
		xCoordinateBoard++;
		addShapeToBoard(shape, xCoordinateBoard, yCoordinateBoard);
	}

	/**
	Method to move a given Shape object one tile down.
	@param shape The Shape object to move.
	@param xCoordinateBoard The x coordinate where the shape wants to be moved.
	@param yCoordinateBoard The y coordinate where the shape wants to be moved.
	@see Shape
	*/
	public void moveDown(Shape shape, int xCoordinateBoard, int yCoordinateBoard){
		removeShapeFromBoard(shape, xCoordinateBoard, yCoordinateBoard);
		yCoordinateBoard++;
		addShapeToBoard(shape, xCoordinateBoard, yCoordinateBoard);
	}

	/**
	Method to drop a given Shape object at the lowest possible position in the board.
	@param shape The Shape object to move.
	@param xCoordinateBoard The initial x coordinate of the Shape object.
	@param yCoordinateBoard The initial y coordinate of the Shape object.
	@see Shape
	*/
	public void dropDown(Shape shape, int xCoordinateBoard, int yCoordinateBoard){
		removeShapeFromBoard(shape, xCoordinateBoard, yCoordinateBoard);
		yCoordinateBoard=0;
		while(!isPlaced(shape, xCoordinateBoard, yCoordinateBoard)){
			yCoordinateBoard++;
		}
		addShapeToBoard(shape, xCoordinateBoard, yCoordinateBoard);
	}

	/**
	Method to print the representation of the board in the command prompt.
	*/
	public void printBoard() {
		for (int m = 0; m < board.length; m++) {
			for (int n = 0; n < board[m].length; n++) {
				System.out.print(board[m][n] + " ");

			}
			System.out.println("");
		}
	}

	/**
	Method to create a new identitical board
	@return the new board1*/
	public PentrisBoard copyBoard(){
		return new PentrisBoard(board);
	}

	/**
	Method to get the width of the PentrisBoard object.
	@return The width of the board.
	*/
	public int getWidth(){
		return board[0].length;
	}
}
