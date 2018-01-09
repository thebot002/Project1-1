package pentominoe;

import java.lang.*;

/**
Class defining the board containing pentominoe.pentominoe objects.
@see Shape
*/
public class PentrisBoard {

	private String[][] board;
	private int x = 0;
	private int y = 0;
	private Shape shape;
	private final int START_Y = 5;
	private int[] grid;

	/**
	Constructor of the object pentominoe.PentrisBoard. Fill a 2-D array (board) with "-".
	@param grid defining the size of the board. (Default: 5x15)
	*/
	public PentrisBoard(int[] grid){
		this.board = new String[grid[1]][grid[0]];
		for(int i=0; i<board.length; i++)
			for(int j=0; j<board[0].length; j++)
				board[i][j] = "-";
	}

	/**
	Constructor to create a pentominoe.PentrisBoard from an 2-D array
	@param board
	*/
	public PentrisBoard(String[][] board, Shape shape, int x, int y){
		this.board = board;
		this.shape = shape;
		this.x = x;
		this.y = y;
	}
	/**
	Return a 2-D representing the pentominoe.PentrisBoard.
	@return A 2-D array filled with "-" and shape ID's.
	@see Shape
	*/
	public String[][] getBoard() {
		return board;
	}

	/**
	This method adds pentominoe.pentominoe objects to the pentominoe.PentrisBoard 2-D array.
	@see Shape
	*/
	public void addShapeToBoard() {
		for (int i = 0; i < shape.getHeight(); i++) {
			for (int j = 0; j < shape.getWidth(); j++) {
				if (!shape.getElement(i,j).equals("-") && y+i<board.length && x+j<board[0].length) {
					board[y + i][x + j] = shape.getElement(i,j);
				}
			}
		}
	}

	/**
	This method adds pentominoe.pentominoe objects to the board at position determined by START_Y and at the middle of the board values.
	@param shape The shape to be added.
	@see Shape
	*/
	public boolean insertShapeToBoard(Shape shape){
		this.shape = shape;
		int boardW = board[0].length;
		x = (int)((boardW-shape.getWidth())*1.0)/2;
		y = START_Y;
		do{
			if(insertionPossible()){
				for (int i = 0; i < shape.getHeight(); i++) {
					for (int j = 0; j < shape.getWidth(); j++) {
						if (!shape.getElement(i,j).equals("-") && y+i<board.length && x+j<board[0].length) {
							board[y + i][x + j] = shape.getElement(i,j);
						}
					}
				}
				return true;
			}
			y--;
			if(shape.getHeight()+y < START_Y) return false;
		}while(y>=0);
		return false;
	}

	/**
	Method to check wether the placement of the pentominoe.pentominoe object is possible.
	@return True is the insertion is possible. False if the insertion is not possible.
	@see Shape
	*/
	private boolean insertionPossible(){
		for(int i=0; i<shape.getHeight(); i++){
			for(int j=0; j<shape.getWidth(); j++){
				if(y+i>=board.length || x+j>=board[0].length || x<0 || y<0) return false;
				if(!shape.getElement(i,j).equals("-") && !board[y+i][x+j].equals("-")) return false;
			}
		}
		return true;
	}

	/**
	Method to remove a shape from the board.
	@see Shape
	*/
	public void removeShapeFromBoard() {
		for (int i = 0; i < shape.getHeight(); i++) {
			for (int j = 0; j < shape.getWidth(); j++) {
				if (!shape.getElement(i,j).equals("-") && y+i<board.length && x+j<board[0].length) {
					board[y + i][x + j] = "-";
				}
			}
		}
	}

	/**
	Method checking whether the pentominoe.pentominoe object is placed by checking if next going one tile down is possible.
	@return True if the pentominoe.pentominoe object is placed. False if the pentominoe.pentominoe object is not placed.
	@see Shape
	*/
	public boolean isPlaced() {
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
				while(!isLineEmpty(j) && j>=0) {
					for(int k=0; k<board[0].length; k++) {
						newBoard[j][k]=newBoard[j-1][k];
					}
					j--;
				}
				i++;
				counter++;
			}
		}
		board=newBoard;
		return counter;
	}

	/**
	Checks if the left movement of a given pentominoe.pentominoe object is possible.
	@return True if the movement of the pentominoe.pentominoe object is possible. False if the movement of the pentominoe.pentominoe object is not possible.
	@see Shape
	*/
	public boolean moveLeftPossible(){
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
	Checks if the right movement of a given pentominoe.pentominoe object is possible.
	@return True if the movement of the pentominoe.pentominoe object is possible. False if the movement of the pentominoe.pentominoe object is not possible.
	@see Shape
	*/
	public boolean moveRightPossible(){
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
	Rotates a given pentominoe.pentominoe object in the board.
	@see Shape
	*/
	public void rotate() {
		int delta = (int)(shape.getWidth()-shape.getHeight())/2;
		removeShapeFromBoard();
		x+=delta;
		y-=delta;
		shape.rotateR();
		do{
			if(shape.getHeight()>shape.getWidth() && (2*shape.getHeight())-shape.getWidth()+y>=board.length+2){
				y--;
			}
			if(shape.getWidth()>shape.getHeight() && x+shape.getWidth() > board[0].length){
				x--;
			}
			if(x<0){
				x=0;
			}
			for(int i=0; i<shape.getHeight(); i++){
				for(int j=0; j<shape.getWidth(); j++){
					if(x+j<board[0].length && y+i<board.length && y>=0 && x>=0 && !shape.getElement(i,j).equals("-") && !board[y+i][x+j].equals("-")){
						y--;
					}
				}
			}
		}while(!insertionPossible());
		addShapeToBoard();
	}

	/**
	Method to move a given pentominoe.pentominoe object one tile left.
	@see Shape
	*/
	public void moveLeft() {
		removeShapeFromBoard();
		x--;
		addShapeToBoard();
	}

	/**
	Method to move a given pentominoe.pentominoe object one tile right.
	@see Shape
	*/
	public void moveRight() {
		removeShapeFromBoard();
		x++;
		addShapeToBoard();
	}

	/**
	Method to move a given pentominoe.pentominoe object one tile down.
	@see Shape
	*/
	public void moveDown(){
		removeShapeFromBoard();
		y++;
		addShapeToBoard();
	}

	/**
	Method to drop a given pentominoe.pentominoe object at the lowest possible position in the board.
	@see Shape
	*/
	public void dropDown(){
		removeShapeFromBoard();
		y=0;
		while(!isPlaced()){
			y++;
		}
		addShapeToBoard();
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
		Shape s = shape.copyShape();
		String[][] nBoard = new String[board.length][board[0].length];
		for(int i=0;i<board.length;i++)
			for(int j=0;j<board[0].length;j++)
				nBoard[i][j] = board[i][j];
		return new PentrisBoard(nBoard,s,x,y);
	}

	/**
	Method to get the width of the pentominoe.PentrisBoard object.
	@return The width of the board.
	*/
	public int getWidth(){
		return board[0].length;
	}

	/**
	Method to get the x coordinate of the active shape.
	@return The x coordinate
	*/
	public int getX(){
		return x;
	}

	/**
	Method to get the y coordinate of the active shape.
	@return The y coordinate
	*/
	public int getY(){
		return y;
	}

	public void setY(int y){
		this.y=y;
	}

	public Shape getShape(){
		return shape;
	}
}
