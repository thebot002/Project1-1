import java.util.ArrayList;

public class FindBestFit {
	private ArrayList<PentrisBoard> boardList;
	private ArrayList<Shape> shapeList;
	private ArrayList<Integer> xList;
	private Shape bestShape;
	private PentrisBoard bestBoard;
	private int optimalX;
	private int energy=Integer.MIN_VALUE;

	public FindBestFit (ArrayList<PentrisBoard> boardList,  ArrayList<Shape> shapeList,  ArrayList<Integer> xList) {
		this.boardList=boardList;
		this.shapeList=shapeList;
		this.xList=xList;
		for(int i=0; i<shapeList.size(); i++) {
			System.out.println("The number of shape: "+i);
			shapeList.get(i).printShape();
			System.out.println("");
		}
	}

	public int getOptimalX() {
		return optimalX;
	}

	public Shape getBestShape() {
		return bestShape;
	}

	public void findOptimalState() {

		int newEnergy;


		for(int i=0; i<boardList.size(); i++) {

			System.out.println("");
			newEnergy=evalFunction(boardList.get(i));

			if(newEnergy > energy /*|| (newEnergy == energy && Math.random() > 0.5))*/) {
//				System.out.println("Energy Before: "+energy);
				energy=newEnergy;
//				System.out.println("Energy After: "+energy);
				bestShape=shapeList.get(i);
				bestBoard=boardList.get(i);
				optimalX=xList.get(i);
//				System.out.println("New Energy: "+newEnergy);
//				System.out.println("Optimal X: "+optimalX);
				System.out.println("*****BestBoard: ");
				boardList.get(i).printBoard();
				System.out.println("******BestShape: ");
				shapeList.get(i).printShape();
			}
		}
//		System.out.println("*****BestBoard: ");
//		bestBoard.printBoard();
//		System.out.println("******BestShape: ");
//		bestShape.printShape();

	}



	public int height(PentrisBoard board) {
		int height=0;
		for(int i=board.getBoard().length-1; i>=0; i--) {
			if(!board.isLineEmpty(i) && !board.isLineFull(i))
				height++;
		}
		return height;
	}

	public int findGaps(PentrisBoard board) {
		int counter=0;
		for(int j=0; j<board.getBoard()[0].length; j++) {
			for(int i=1; i<board.getBoard().length; i++) {
				if(board.getBoard()[i][j].equals("-") && j>0 &&j<board.getBoard()[0].length-1) {
					if(!board.getBoard()[i-1][j].equals("-")) {
						int k=1;
						while(board.getBoard()[i+k-1][j].equals("-") && i+k<board.getBoard().length) {
							k++;
							counter++;
						}
					}
				} else if (board.getBoard()[i][j].equals("-") && j == 0) {
					if (!board.getBoard()[i - 1][j].equals("-")) {
						int k = 0;

						while (i+k < board.getBoard().length && board.getBoard()[i+k][j].equals("-")) {
							k++;
							counter++;
						}
					}
				} else if (board.getBoard()[i][j].equals("-") && j == board.getBoard()[0].length - 1) {
					if (!board.getBoard()[i - 1][j].equals("-") ) {
						int k = 1;
						while (board.getBoard()[i + k-1][j].equals("-") && i + k < board.getBoard().length) {
							k++;
							counter++;
						}
					}
				}
			}
		}
		return counter;
	}

	public int evalFunction(PentrisBoard board) {
		int energy=0;
		int count=0;
		for(int i=1; i<board.getBoard().length; i++) {
			if(board.isLineFull(i)) {
				count++;
			}
		}
		energy+=count*500;
		energy-=80*height(board);
		energy-=50*findGaps(board);
		return energy;
	}
}
