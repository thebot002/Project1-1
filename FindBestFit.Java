import java.util.ArrayList;

public class FindBestFit {
	private ArrayList<PentrisBoard> boardList;
	private ArrayList<Shape> shapeList;
	private ArrayList<Integer> xList;
	private Shape bestShape;
	private PentrisBoard bestBoard;
	private int optimalX;
	private int energy=Integer.MIN_VALUE;

	private final boolean DEBUG = false;

	public FindBestFit (ArrayList<PentrisBoard> boardList,  ArrayList<Shape> shapeList,  ArrayList<Integer> xList) {
		this.boardList=boardList;
		this.shapeList=shapeList;
		this.xList=xList;
		for(int i=0; i<shapeList.size(); i++) {
//			System.out.println("The number of shape: "+i);
//			shapeList.get(i).printShape();
//			System.out.println("");
		}
		for(int i=0; i<boardList.size(); i++){
			if(DEBUG)System.out.println("The number of board: "+i);
			if(DEBUG)boardList.get(i).printBoard();
			if(DEBUG)System.out.println("");
		}
	}

	public int getOptimalX() {
		return optimalX;
	}

	public Shape getBestShape() {
		return bestShape;
	}

	public void findOptimalState(int lines, int h, int gaps) {

		int newEnergy;


		for(int i=0; i<boardList.size(); i++) {

//			System.out.println("");
			newEnergy=evalFunction(boardList.get(i),lines,h,gaps);

			if(newEnergy > energy /*|| (newEnergy == energy && Math.random() > 0.5))*/) {
//				System.out.println("Energy Before: "+energy);
				energy=newEnergy;
//				System.out.println("Energy After: "+energy);
				bestShape=shapeList.get(i);
				bestBoard=boardList.get(i);
				optimalX=xList.get(i);
//				System.out.println("New Energy: "+newEnergy);
//				System.out.println("Optimal X: "+optimalX);
//				System.out.println("*****BestBoard: ");
//				boardList.get(i).printBoard();
//				System.out.println("******BestShape: ");
//				shapeList.get(i).printShape();
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
		int gaps=0;
		int checkY=1;
		for(int i=1; i<board.getBoard().length-1; i++) {
			for(int j=0; j<board.getBoard()[0].length; j++) {
				checkY=i;
				while(board.getBoard()[checkY][j].equals("-")&& !board.getBoard()[checkY-1][j].equals("-") && checkY<board.getBoard().length) {
					gaps++;
					checkY++;

//					System.out.println("checkY = "+checkY+" i = "+i);
				}
			}
		}
		for(int j=0; j<board.getBoard()[0].length; j++) {
			if(board.getBoard()[board.getBoard().length-1][j].equals("-") &&
					!board.getBoard()[board.getBoard().length-2][j].equals("-"))
				gaps++;
		}
		return gaps;
	}

	public int evalFunction(PentrisBoard board, int lines, int h, int gaps) {
		int energy=0;
		int count=0;
		for(int i=1; i<board.getBoard().length; i++) {
			if(board.isLineFull(i)) {
				count++;
			}
		}
		energy+=count*count*lines;
		energy-=h*height(board);
		energy-=gaps*findGaps(board);
		return energy;
	}
}
