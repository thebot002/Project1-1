package pentris.bot;

import pentominoe.*;
import pentominoe.Shape;

import java.util.ArrayList;


public class BotStats {
	private PentrisBoard board;
	private Shape shape;
	private Shape shape2;
	private int score=0;
	private ShapeList shapeList;
//	pentris.bot.CheckMoves possibleMoves;
//	pentris.bot.FindBestFit bestFit;
	private PentrisBoard checkBoard;
	private Shape checkShape;
	private Shape checkShape2;
	private PentrisBoard testBoard;
	private Shape testShape;
	private Shape testShape2;


	public BotStats(PentrisBoard board) {
		this.board=board;

		shapeList=new ShapeList();
		this.shape=shapeList.getRandomShape();
		this.shape2=shapeList.getRandomShape();

	}

	public int game(int x, int y, int line, int h, int gaps) {
		int[] scoreList = new int[1000];

		boolean gameRunning = true;
		for (int n = 0; n < scoreList.length; n++) {
//			System.out.println("BEGIN OF FOR LOOP");
			score=0;
			gameRunning=true;
			x=0;
			y=0;
			checkBoard = board.copyBoard();
			checkShape = shape.copyShape();
			checkShape2 = shape2.copyShape();

			while (gameRunning) {
//				System.out.println("BEGIN OF WHILE LOOP");
				testBoard = checkBoard.copyBoard();
				testShape = checkShape.copyShape();
				testShape2 = checkShape2.copyShape();

//				checkBoard = board.copyBoard();
//				checkShape = shape.copyShape();
//				checkShape2 = shape2.copyShape();
//				if(!board.insertShapeToBoard(shape)) gameRunning=false;
//				checkBoard = board.copyBoard();
//				checkBoard = board.copyBoard();

//
//
//				checkShape = shape.copyShape();
//				checkShape2 = shape2.copyShape();

				CheckMoves possibleMoves = new CheckMoves(testBoard, testShape, testShape2);
				possibleMoves.findPossibleMoves();


				ArrayList<Integer> checkXList = new ArrayList<Integer>();
				ArrayList<PentrisBoard> checkBoardList = new ArrayList<PentrisBoard>();
				ArrayList<Shape> checkShapeList = new ArrayList<Shape>();
				for (int i = 0; i < possibleMoves.getXList().size(); i++) {
					checkXList.add(possibleMoves.getXList().get(i));
					checkBoardList.add(possibleMoves.getBoardList().get(i));
					checkShapeList.add(possibleMoves.getShapeList().get(i));
				}
//				System.out.println("TEST1");
				FindBestFit bestFit = new FindBestFit(checkBoardList, checkShapeList, checkXList);
				bestFit.findOptimalState(line,h,gaps);

				if(x < bestFit.getOptimalX()) {
					while (x < bestFit.getOptimalX()) {
						x++;
//						System.out.println("TEST2");
					}
				}
				else if(x>bestFit.getOptimalX()) {
					while (x > bestFit.getOptimalX()) {
						x--;
//						System.out.println("TEST3");
					}
				}
				if(!checkShape.equals(bestFit.getBestShape())){
					while (!checkShape.equals(bestFit.getBestShape())) {
						checkShape.rotateR();
					}
				}

				if (!checkBoard.isPlaced()) {
//					System.out.println("NOT PLACED YET");
					while (!checkBoard.isPlaced()) {
//						System.out.println("TEST 5");
						y++;
					}
				}
			    if (checkBoard.isPlaced()) {
//					System.out.println("SHAPE IS PLACED");
					if (y < 5) {
//						System.out.println("GAME OVER");
						scoreList[n] = score;
						x = 0;
						y = 0;
						gameRunning = false;
//						this.shape = shapeList.getRandomShape();
//						checkShape = new pentominoe.pentominoe(shape.copyShape());
//						this.shape2 = shapeList.getRandomShape();
//						checkShape2 = new pentominoe.pentominoe(shape2.copyShape());
						possibleMoves.emptyArrayList();
					} else {
						checkBoard.insertShapeToBoard(checkShape);
//						System.out.println("SHAPE ADDED");
						testBoard = checkBoard.copyBoard();
						int lines = checkBoard.breakLines();
						score += lines * lines * 10;
//						System.out.println("END OF LOOP");
						possibleMoves.emptyArrayList();
						checkShape = checkShape2;
						testShape = checkShape.copyShape();
						checkShape2 = shapeList.getRandomShape();
						testShape2 = checkShape2.copyShape();



						x = 0;
						y = 0;
						while (y + checkShape.getHeight() < 5) {
							y++;
						}

					}
				}

			}
		}
		int totalSum=0;
		for (int i = 0; i < scoreList.length; i++) {
//			System.out.println(scoreList[i]);
			totalSum+=scoreList[i];
		}
		int averageScore=totalSum/scoreList.length;
//		System.out.println("AVERAGE SCORE= "+averageScore);
		return averageScore;
	}

	public static void main(String[] args) {
		String[][] grid = new String[20][5];
		for(int i=0; i<grid.length;i++) {
			for(int j=0; j<grid[0].length; j++) {
				grid[i][j]="-";
			}
		}
		int[] gridSize = {5,20};
		PentrisBoard newBoard = new PentrisBoard(gridSize);
		BotStats bot = new BotStats(newBoard);
//		ArrayList<int[]> weights = new ArrayList<int[]>();
		int i=0;
		int[][] stats = new int[1000][4];
		System.out.println(stats.length);
		System.out.println(stats[0].length);
		for(int lines=61; lines<=70; lines++) {
			for(int h=61; h<=70; h++) {
				for(int gaps=46; gaps<=55; gaps++) {
//					System.out.println("lines = "+2*lines);
//					System.out.println("heigth = -"+2*h);
//					System.out.println("gaps = -"+2*gaps);

					stats[i][0]=bot.game(0,0,lines,h,gaps);
					stats[i][1]=2*lines;
					stats[i][2]=2*h;
					stats[i][3]=2*gaps;
//					weights.add(stats[i]);
					i++;


				}
			}
		}
		System.out.println("Average pentris.score_management.Score:   Line Weight:   Height Weight:   Gap Weight:");
		System.out.println("");
		for(int j=0; j<stats.length; j++) {

		System.out.print  ("     "+stats[j][0]+"           "+stats[j][1]+"             "+stats[j][2]+"             "+stats[j][3]);
		System.out.println("");
		}

	}

}
