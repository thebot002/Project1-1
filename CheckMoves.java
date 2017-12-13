
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
		this.shape=shape;
		this.board=board;
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
				int y=0;
				board.moveDown();
				while(!board.isPlaced()) {

					y++;
				}
				board.insertShapeToBoard(shape);
				PentrisBoard checkingBoard = board.copyBoard();
				Shape checkingShape = shape.copyShape();
				int x=i;
				findPossibleMoves2(checkingBoard,checkingShape,x);

//				if (board.isPlaced(shape, i, y)) {
//
//					boardList.add(checkingBoard);
//					shapeList.add(checkingShape);
//					xList.add(i);
					board.removeShapeFromBoard();
//				}
				y=0;
			}

			shape.rotateR();
			rot1++;
		}


	}


public void findPossibleMoves2(PentrisBoard checkingBoard, Shape checkingShape, int x) {

		int rot2 = 0;

		while (rot2 < 4) {

			for (int i = 0; i <= checkingBoard.getBoard()[0].length - shape2.getWidth(); i++) {

				int y=0;
				while(!checkingBoard.isPlaced()) {
					checkingBoard.moveDown();
					y++;
				}
				checkingBoard.insertShapeToBoard(shape2);
				PentrisBoard checkingBoard2 = checkingBoard.copyBoard();
				Shape checkingShape2 = checkingShape.copyShape();
				if (checkingBoard.isPlaced()) {

					boardList.add(checkingBoard2);
					shapeList.add(checkingShape);
					xList.add(x);

				}
				checkingBoard.removeShapeFromBoard();
				y=0;
			}

			shape2.rotateR();
			rot2++;
		}

//
	}


}
