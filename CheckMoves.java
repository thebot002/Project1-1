
import java.util.ArrayList;

public class CheckMoves {

	private PentrisBoard board;
	private Shape shape;
	private Shape shape2;
	private Shape testShape;
	private Shape testShape2;
	private ArrayList<PentrisBoard> boardList = new ArrayList<PentrisBoard>();
	private ArrayList<Shape> shapeList = new ArrayList<Shape>();
	private ArrayList<Shape> shapeList2 = new ArrayList<Shape>();
	private ArrayList<Integer> xList = new ArrayList<Integer>();
	private ArrayList<Integer> xList2 = new ArrayList<Integer>();

	public CheckMoves(PentrisBoard board, Shape shape, Shape shape2) {
		this.board=board;
		this.shape=board.getShape();
		this.shape2=shape2;
	}

	public ArrayList<PentrisBoard> getBoardList(){
		return boardList;
	}

	public ArrayList<Shape> getShapeList(){
		return shapeList;
	}

	public ArrayList<Integer> getXList(){
		return xList;
	}

	public void emptyArrayList() {
		boardList.removeAll(boardList);
		shapeList.removeAll(shapeList);
		xList.removeAll(xList);
	}

	public void findPossibleMoves() {

		int rot1 = 0;

		while (rot1 < 4) {

			for (int i = 0; i <= board.getBoard()[0].length - shape.getWidth(); i++) {

				while(board.getX() != i){
					if(i<board.getX()) {
						board.moveLeft();
					}
					else if(i>board.getX()) {
						board.moveRight();
					}
				}

				while(!board.isPlaced()) {
					board.moveDown();
				}
				PentrisBoard checkingBoard = board.copyBoard();
				Shape checkingShape = shape.copyShape();
				int x=i;
				findPossibleMoves2(checkingBoard,checkingShape,x);

				board.removeShapeFromBoard();
				board.setY(2);
				board.addShapeToBoard();
			}
			board.rotate();
			rot1++;
		}
		board.removeShapeFromBoard();

	}


public void findPossibleMoves2(PentrisBoard checkingBoard, Shape checkingShape, int x) {

		int rot2 = 0;
		checkingBoard.insertShapeToBoard(shape2);

		while (rot2 < 4) {

			for (int i = 0; i <= checkingBoard.getBoard()[0].length - shape2.getWidth(); i++) {
				checkingBoard.addShapeToBoard();
				while(checkingBoard.getX() != i){
					if(i<checkingBoard.getX()) checkingBoard.moveLeft();
					else if(i>checkingBoard.getX()) checkingBoard.moveRight();
				}
				while(!checkingBoard.isPlaced()) {
					checkingBoard.moveDown();
				}
				PentrisBoard checkingBoard2 = checkingBoard.copyBoard();
				if (checkingBoard.isPlaced()) {
					boardList.add(checkingBoard2.copyBoard());
					shapeList.add(checkingShape.copyShape());
					xList.add(i);

				}
				checkingBoard.removeShapeFromBoard();
				checkingBoard.setY(2);
			}
			checkingBoard.addShapeToBoard();
			checkingBoard.rotate();
			rot2++;
		}
		checkingBoard.removeShapeFromBoard();
//
	}


}
