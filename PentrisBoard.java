import java.lang.*;

public class PentrisBoard {

	public static void main(String[] args){}
	private String[][] board;
	private int counter;
	private final boolean DEBUG = true;
	private int startX=0;
	private int startY=0;
	//private ShapeFactory shapeOp = new ShapeFactory();

	public PentrisBoard(){
		this. board = new String[15][5];
	}
	public PentrisBoard(String[][] board) {
		this.board = board;
	}

	public String[][] getBoard() {
		return board;
	}

	// We use this method to add shapes to board
	public void addShapeToBoard(Shape shape, int x, int y) {
		for (int i = 0; i < shape.getHeight(); i++) {
			for (int j = 0; j < shape.getWidth(); j++) {
				/* If the element of the shape array is not empty we copy it to the board */
				if (!shape.getElement(i,j).equals("-") && y+i<board.length && x+j<board[0].length) {
					board[y + i][x + j] = shape.getElement(i,j);
				}
			}
		}
	}
	public boolean addShapeToBoard(Shape shape){
		int y = startY;
		do{
			if(insertionPossible(shape,startX,y)){
				addShapeToBoard(shape,startX,y);
				return true;
			}
			y--;
		}while(y>=0);
		return false;
	}
	private boolean insertionPossible(Shape shape,int x, int y){
		for(int i=0; i<shape.getHeight(); i++){
			for(int j=0; j<shape.getWidth(); j++){
				if(!shape.getElement(i,j).equals("-") && !board[y+i][x+j].equals("-")) return false;
			}
		}
		return true;
	}

	public void removeShapeFromBoard(Shape shape, int x, int y) {
		for (int i = 0; i < shape.getHeight(); i++) {
			for (int j = 0; j < shape.getWidth(); j++) {
				if (!shape.getElement(i,j).equals("-") && y+i<board.length && x+j<board[0].length) {
					board[y + i][x + j] = "-";
				}
			}
		}
	}

	public boolean isPlaced(Shape shape, int x, int y) {
		for(int i = shape.getHeight()-1; i>=0; i--){
			for(int j=0; j<shape.getWidth();j++){
				if(!shape.getElement(i,j).equals("-")){
					if(i>=shape.getHeight()-1 || (i<shape.getHeight()-1 && shape.getElement(i+1,j).equals("-"))){
						if(i+y+1>=board.length || !board[i+y+1][j+x].equals("-")) return true;
					}
				}
			}
		}
		return false;
	}

	public boolean isLineFull(int line) {
		for (int j = 0; j < board[line].length; j++) {
			if (board[line][j].equals("-"))
				return false;
		}
		return true;
	}

	public void emptyLine(int line) {
		for (int i = 0; i < board[line].length; i++) {
			board[line][i] = "-";
		}
	}

	public boolean isLineEmpty(int line) {
		for(int j=0; j<board[line].length; j++) {
			if(!board[line][j].equals("-"))
				return false;
		}
		return true;
	}

	public int breakLines() {
		int counter = 0;
		String[][] newBoard = board;
		for (int i = board.length - 1; i >= 0; i--) {

			if(isLineFull(i)) {
				int j=i;
				while(!isLineEmpty(j)) {
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

	public boolean isRotatePossible(Shape shape, int x, int y) {
		if(shape.getHeight()>shape.getWidth() && x+shape.getWidth()-1 == board[0].length-1) return false;
		return true;
	}

	public boolean moveLateralPossible(Shape shape, int x, int y, int dir){
		for(int i=0;i<shape.getHeight();i++){
			for(int j=0;j<shape.getWidth();j++){
				if(dir == 1 && (x+shape.getWidth()==board[0].length || !shape.getElement(i,j).equals("-") && !board[y+i][x+j+dir].equals("-"))){
					if(j<shape.getWidth()-1 && shape.getElement(i,j+1).equals("-"))
						return false;
				}
				else if(dir == -1 && (x==0 || !shape.getElement(i,j).equals("-") && !board[y+i][x+j-1].equals("-"))){
					if(j>0 && shape.getElement(i,j-1).equals("-"))
						return false;
				}
				else if(j>0&&j<shape.getWidth()-1){
					if(!shape.getElement(i,j).equals("-") && shape.getElement(i,j+dir).equals("-") && !board[y+i][x+j+dir].equals("-"))
						return false;
				}
			}
		}
		return true;
	}

	public void moveLeft(Shape shape, int xCoordinateBoard, int yCoordinateBoard) {
		removeShapeFromBoard(shape, xCoordinateBoard, yCoordinateBoard);
		xCoordinateBoard--;
		addShapeToBoard(shape, xCoordinateBoard, yCoordinateBoard);
	}

	public void moveRight(Shape shape, int xCoordinateBoard, int yCoordinateBoard) {
		removeShapeFromBoard(shape, xCoordinateBoard, yCoordinateBoard);
		xCoordinateBoard++;
		addShapeToBoard(shape, xCoordinateBoard, yCoordinateBoard);
	}

	// no need for collisition check because already checked is placed in GameCanvas
	public void moveDown(Shape shape, int xCoordinateBoard, int yCoordinateBoard){
		removeShapeFromBoard(shape, xCoordinateBoard, yCoordinateBoard);
		yCoordinateBoard++;
		addShapeToBoard(shape, xCoordinateBoard, yCoordinateBoard);
	}

	public void rotate(Shape shape, int xCoordinateBoard, int yCoordinateBoard){
		//collisiton check
		removeShapeFromBoard(shape, xCoordinateBoard, yCoordinateBoard);
		shape.rotateR();
		addShapeToBoard(shape, xCoordinateBoard, yCoordinateBoard);
	}

	public void dropDown(Shape shape, int xCoordinateBoard, int yCoordinateBoard){
		removeShapeFromBoard(shape, xCoordinateBoard, yCoordinateBoard);
		int newY = board.length - (shape.getHeight()-1) -1;
		for(int j=0; j<shape.getWidth(); j++){
			int i=shape.getHeight()-1;
			while(shape.getElement(i,j).equals("-")){
				i--;
			}
			for(int k=newY; k>yCoordinateBoard; k--){
				if(!board[k][j].equals("-")){
					newY -= k;
				}
			}
		}
		addShapeToBoard(shape, xCoordinateBoard, newY);
	}

	public void printBoard() {
		for (int m = 0; m < board.length; m++) {
			for (int n = 0; n < board[m].length; n++) {
				System.out.print(board[m][n] + " ");

			}
			System.out.println("");
		}
	}
}
