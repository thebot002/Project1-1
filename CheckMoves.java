
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
		int y=0;
		while (rot1 < 4) {

			for (int i = 0; i <= board.getBoard()[0].length - shape.getWidth(); i++) {
//				while(!board.isPlaced(shape, i, y)) {
//					y++;
//				}
//				board.addShapeToBoard(shape, i, y);
//				PentrisBoard checkingBoard = new PentrisBoard(board.copyBoard());
//				Shape checkingShape = new Shape(shape.copyShape());
//				if (board.isPlaced(shape, i, y)) {
//
//					boardList.add(checkingBoard);
//					shapeList.add(checkingShape);
//					xList.add(i);
//					board.removeShapeFromBoard(shape, i, y);
//				}
//			}



				for (int j = 1; j<=board.getBoard().length - shape.getHeight(); j++) {
					boolean check=true;
					for(int k=i; k<shape.getWidth(); k++) {

						int l=0;
						while(j-l>0 && l<board.getBoard().length) {
						if(!board.getBoard()[j-l][k].equals("-"))
							check=false;
						l++;
						}
					}
					if (board.insertionPossible(shape, i, j) && check) {
						board.addShapeToBoard(shape, i, j);
						PentrisBoard checkingBoard = new PentrisBoard(board.copyBoard());
						Shape checkingShape = new Shape(shape.copyShape());
						if (board.isPlaced(shape, i, j)) {

							boardList.add(checkingBoard);
							shapeList.add(checkingShape);
							xList.add(i);
							board.removeShapeFromBoard(shape, i, j);
						} else
							board.removeShapeFromBoard(shape, i, j);
					}
				}
			}


			shape.rotateR();
			rot1++;
		}

		for(int i=0; i<xList.size(); i++) {
			System.out.println("xLIST "+i);
			System.out.println(xList.get(i));
			System.out.println("BOARD LIST "+i);
			boardList.get(i).printBoard();
			System.out.println("SHAPE LIST "+i);
			shapeList.get(i).printShape();
			System.out.println("");
		}
	}


//		public void findPossibleMoves2() {
//
//			int rot2 = 0;
//			while (rot2 < 4) {
//				for (int i = 0; i <= board.getBoard()[0].length - shape.getWidth(); i++) {
//					for (int j = 0; j<=board.getBoard().length - shape.getHeight(); j++) {
//						if (board.insertionPossible(shape2, i, j)) {
//							board.addShapeToBoard(shape2, i, j);
//							PentrisBoard checkingBoard2 = new PentrisBoard(board.copyBoard());
//							Shape checkingShape2 = new Shape(shape2.copyShape());
//							if (board.isPlaced(shape2, i, j)) {
//
//								boardList.add(checkingBoard2);
//								shapeList2.add(checkingShape2);
//								xList2.add(i);
//								board.removeShapeFromBoard(shape2, i, j);
//							} else
//								board.removeShapeFromBoard(shape2, i, j);
//						}
//					}
//				}
//
//				shape2.rotateR();
//				rot2++;
//			}


//	}


}
