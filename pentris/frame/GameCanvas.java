package pentris.frame;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import pentominoe.*;
import pentominoe.Shape;
import pentris.bot.CheckMoves;
import pentris.bot.FindBestFit;

class GameCanvas extends PentPanel implements ActionListener {
	public static void main(String[] args) {}

	private boolean bot = true;

	private ShapeBox shapeBox;
	private TimeBox timeBox;
	private TextBox scoreBox;
	private Timer timer;
	private ArrayList<ScoreBox> scoreBoxes = new ArrayList<ScoreBox>();

	private Random random = new Random();
	private int time = 0;

	public PentrisBoard board;

	private Shape activeShape;
	private Shape nextShape;
	private ShapeList shapeList;

	private PentrisBoard checkBoard;
	private Shape checkShape;
	private Shape checkShape2;

	private int speedDefault = 600;
	private int speedUp = 100;
	private int score = 0;
	private int[] grid;
	private boolean gameRunning = false;

	private CheckMoves psblMoves=null;

	public GameCanvas(int W, int H,  Font f, int s, int[] grid) {
		super(0, 0, W, H, f, s);
		this.grid = grid;
		board = new PentrisBoard(grid);
		shapeList = new ShapeList();
		activeShape = shapeList.getRandomShape();
		nextShape = shapeList.getRandomShape();

		drawGame();
		startGame();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}

	public void startGame() {
		gameRunning = true;

		if(activeShape.getHeight()>activeShape.getWidth()) activeShape.rotateR();
		if(!board.insertShapeToBoard(activeShape)) gameOver();
		x=(int)((board.getWidth()-activeShape.getWidth())*1.0)/2;
		drawBoard(board);

		if(bot){
			checkBoard = board.copyBoard();
			checkShape = activeShape.copyShape();
			checkShape2= nextShape.copyShape();
			speedDefault = 100;
			psblMoves = new CheckMoves(checkBoard,checkShape,checkShape2);
			psblMoves.findPossibleMoves();
		}

		timer = new Timer(speedDefault, this);
	   	timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(bot){

			//		for(int i=0; i<psblMoves.getXList().size(); i++) {System.out.println(psblMoves.getXList());}
			ArrayList<Integer> checkXList = new ArrayList<Integer>();
			ArrayList<PentrisBoard> checkBoardList = new ArrayList<PentrisBoard>();
			ArrayList<pentominoe.Shape> checkShapeList = new ArrayList<pentominoe.Shape>();
			//System.out.println(psblMoves.getXList().size());
			for(int i=0; i<psblMoves.getXList().size(); i++) {
				checkXList.add(psblMoves.getXList().get(i));
				checkBoardList.add(psblMoves.getBoardList().get(i));
				checkShapeList.add(psblMoves.getShapeList().get(i));
			}
			FindBestFit bestFit = new FindBestFit(checkBoardList, checkShapeList, checkXList);
			bestFit.findOptimalState(178,128,101);

			playingBot(bestFit.getBestShape(), bestFit.getOptimalX());
		}

		//stops tick events that were created before the game ended trying to do things after the game ends.
		if(gameRunning && !paused) {
			//shape is touching another shape
			if(board.isPlaced()) {
				activeShape = nextShape.copyShape();
				nextShape = shapeList.getRandomShape();

				shapeBox.drawValue(nextShape);
				if(activeShape.getHeight() > activeShape.getWidth()) activeShape.rotateR();
				int lines = board.breakLines();
				score += lines * lines * 10;
				//board.printBoard();
				if(!board.insertShapeToBoard(activeShape)) gameOver();

				scoreBox.setTarget(score);
				if(bot && gameRunning){
					checkBoard=board.copyBoard();
					if(psblMoves != null) psblMoves.emptyArrayList();
					checkShape = activeShape.copyShape();
					checkShape2 = nextShape.copyShape();
					psblMoves = new CheckMoves(checkBoard,checkShape,checkShape2);
					psblMoves.findPossibleMoves();
				}
			} else {
				board.moveDown();
			}
			drawBoard(board);
			repaint();
		}
	}

	private void playingBot(pentominoe.Shape bestShape, int optimalX) {
		int br1 = 0;
		int br2 = 0;
		int br3 = 0;

		while(!activeShape.equals(bestShape) && br1<10) {
			upKeyPress();
			br1++;
		}
		while (optimalX<board.getX() && br2<10) {
			leftKeyPress();
			br2++;
		}
		while(optimalX>board.getX() && br3<10) {
			rightKeyPress();
			br3++;
		}
	}

	public Boolean isRunning() {
		return gameRunning;
	}

	public void upKeyPress() {
		  if(gameRunning && !paused)
		  	board.rotate();
		  drawBoard(board);
	}

	public void downKeyPress() {
		if(!paused)
			timer.setDelay(speedUp);
	}

	public void downKeyRelease() {
		if(!paused)
			timer.setDelay(speedDefault);
	}

	public void spaceKeyPress() {
		if(gameRunning && !paused)	{
			board.dropDown();
			activeShape = nextShape;
			nextShape = shapeList.getRandomShape();
			shapeBox.drawValue(nextShape);
			if(activeShape.getHeight() > activeShape.getWidth()) activeShape.rotateR();
			int lines = board.breakLines();
			score += lines * lines * 10;
			scoreBox.setTarget(score);
			if(bot){
				checkShape=activeShape.copyShape();
				checkBoard=board.copyBoard();
			}
			if(!board.insertShapeToBoard(activeShape)) gameOver();
			drawBoard(board);
			repaint();
		}
	}

    public void leftKeyPress() {
         if(gameRunning && !paused && board.moveLeftPossible()) {
		  		board.moveLeft();
         }
		 drawBoard(board);
    }

    public void rightKeyPress() {
		 	if(gameRunning && !paused && board.moveRightPossible()) {
        		board.moveRight();
	  		}
		   drawBoard(board);
	}

	public void pKeyPress() {
		paused = !paused;
	}

	private void drawGame() {
		Graphics g = image.getGraphics();
		g.setColor(BACKGROUND);
		g.fillRect(0,0,w,h);


		//'origin' x and y positions to draw the board from.
		int ox = (w-SQ*grid[0])/2;
		int oy = SQ;

		//draw border of board
		for(int y=-1; y<(grid[1]-4); y++) {
			drawBlock(g, ox - SQ, oy + SQ * y, BACKGROUND, SQ);
			drawBlock(g, ox + grid[0] * SQ, oy + SQ * y, BACKGROUND, SQ);
		}

		for(int x=0; x<(grid[0]); x++) {
			drawBlock(g, ox + SQ * x, oy - SQ, BACKGROUND, SQ);
			drawBlock(g, ox + SQ * x, oy + (grid[1]-5) * SQ, BACKGROUND, SQ);
		}


		//create score_management boxes
		TextBox highScoreBox = new TextBox(w - ((int) (SQ*3.5)), SQ*6, font, SQ, "High score", this);
		shapeBox = new ShapeBox(w - ((int) (SQ*3.5)), SQ, font, SQ, "Next pentominoe", nextShape, this);

		scoreBox = new TextBox(SQ/2, SQ*4, font, SQ, "Score", this);
		timeBox = new TimeBox(SQ/2, SQ, font, SQ, "Time", this);
		//TextBox levelBox = new TextBox(SQ/2, SQ*7, font, SQ, "Level", this);

		scoreBoxes.add(scoreBox);
		//scoreBoxes.add(levelBox);
		scoreBoxes.add(highScoreBox);

		add(highScoreBox);
		//add(levelBox);
		add(scoreBox);
		add(timeBox);
		add(shapeBox);

		if(scoreManager.doesHighScoresExist())
			highScoreBox.setValue(scoreManager.getMaxScore().getScore());

		repaint();
	}

	private void drawBoard(PentrisBoard pBoard) {

		int ox = (w-SQ*grid[0])/2;
		int oy = -4*SQ;

		Graphics g = image.getGraphics();
		g.setColor(BACKGROUND);
		g.fillRect(ox,oy+5*SQ,SQ*grid[0],SQ*(grid[1]-5));

		String[][] board = pBoard.getBoard();


		for(int x=0; x<board[0].length; x++) {
			for(int y=5; y<board.length; y++) {
				if(!board[y][x].equals("-")) {
					drawBlock(g, ox + x * SQ, oy + y * SQ, colorList.get(board[y][x]), SQ);
				}
			}
		}

		repaint();
	}

	public int getScore() {
		return score;
	}

	private void gameOver() {
	 	gameRunning = false;
		activeShape = null;
		timer.stop();
		PentWindow p = (PentWindow)SwingUtilities.getRoot(this);
		p.endGame(getScore());
	}
}
