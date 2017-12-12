import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

class GameCanvas extends PentPanel implements ActionListener {
	public static void main(String[] args) {}

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
	//shape x and y:
	private int x=0;
	private int y=0;
	private int score = 0;
	private int[] grid;
	private boolean gameRunning = false;

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

		checkBoard = board.copyBoard();
		checkShape = activeShape.copyShape();
		checkShape2= nextShape.copyShape();
		if(activeShape.getHeight()>activeShape.getWidth()) activeShape.rotateR();
		if(!board.addShapeToBoard(activeShape)) gameOver();
		drawBoard(board);

		timer = new Timer(speedDefault, this);
	   	timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		CheckMoves psblMoves = new CheckMoves(checkBoard,checkShape,checkShape2);
		psblMoves.findPossibleMoves();

//		for(int i=0; i<psblMoves.getXList().size(); i++) {System.out.println(psblMoves.getXList());}
		ArrayList<Integer> checkXList = new ArrayList<Integer>();
		ArrayList<PentrisBoard> checkBoardList = new ArrayList<PentrisBoard>();
		ArrayList<Shape> checkShapeList = new ArrayList<Shape>();
		for(int i=0; i<psblMoves.getXList().size(); i++) {
			checkXList.add(psblMoves.getXList().get(i));
			checkBoardList.add(psblMoves.getBoardList().get(i));
			checkShapeList.add(psblMoves.getShapeList().get(i));
		}
		FindBestFit bestFit = new FindBestFit(checkBoardList, checkShapeList, checkXList);
		bestFit.findOptimalState();

			playingBot(bestFit.getBestShape(), bestFit.getOptimalX());

		//stops tick events that were created before the game ended trying to do things after the game ends.
		if(gameRunning && !paused) {
			//shape is touching another shape
			if(board.isPlaced(activeShape,x,y)) {
				if(y<5) {
					gameOver();
					psblMoves.emptyArrayList();
				} else {
					checkBoard=board.copyBoard();
					psblMoves.emptyArrayList();
					activeShape = nextShape;
					checkShape = activeShape.copyShape();
					nextShape = shapeList.getRandomShape();
					checkShape2 = nextShape.copyShape();
					shapeBox.drawValue(nextShape);
					if(activeShape.getHeight() > activeShape.getWidth()) activeShape.rotateR();
					x=0;
					y=0;
					while(y+activeShape.getHeight()<5) {
						y++;
					}
				}
				int lines = board.breakLines();
				score += lines * lines * 10;

				scoreBox.setTarget(score);
			} else {
				board.moveDown(activeShape,x,y);
				y++;
			}
			drawBoard(board);
			repaint();
		}
	}

	public void playingBot(Shape bestShape, int optimalX) {
		int br1 = 0;
		int br2 = 0;
		int br3 = 0;

		while(!activeShape.equals(bestShape) && br1<10) {
			upKeyPress();
			br1++;
		}
		while (optimalX<x && br2<10) {
			leftKeyPress();
			br2++;
		}
		while(optimalX>x && br3<10) {
			rightKeyPress();
			br3++;
		}
//		if(optimalX==x && activeShape.equals(bestShape))
//		downKeyPress();
	}

	public Boolean isRunning() {
		return gameRunning;
	}

	public void upKeyPress() {
		  if(gameRunning && !paused && board.rotatePossible(activeShape,x,y))
		  	board.rotate(activeShape,x,y);
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
			board.dropDown(activeShape, x, y);
			timer.start();
			activeShape = nextShape;
      checkShape=activeShape.copyShape();
			nextShape = shapeList.getRandomShape();
			shapeBox.drawValue(nextShape);
			if(activeShape.getHeight() > activeShape.getWidth()) activeShape.rotateR();
			x=0;
			y=0;
			int lines = board.breakLines();
			score += lines * lines * 10;
			scoreBox.setTarget(score);
      checkBoard=board.copyBoard();
			drawBoard(board);
			repaint();
		}
	}

    public void leftKeyPress() {
         if(gameRunning && !paused && board.moveLeftPossible(activeShape, x, y)) {
		  		board.moveLeft(activeShape,x,y);
		  		x--;
			}
		   drawBoard(board);
    }

    public void rightKeyPress() {
		 	if(gameRunning && !paused && board.moveRightPossible(activeShape, x, y)) {
        		board.moveRight(activeShape,x,y);
		  		x++;
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
		for(int y=-1; y<(grid[1]+1); y++) {
			drawBlock(g, ox - SQ, oy + SQ * y, BACKGROUND, SQ);
			drawBlock(g, ox + grid[0] * SQ, oy + SQ * y, BACKGROUND, SQ);
		}

		for(int x=0; x<(grid[0]); x++) {
			drawBlock(g, ox + SQ * x, oy - SQ, BACKGROUND, SQ);
			drawBlock(g, ox + SQ * x, oy + grid[1] * SQ, BACKGROUND, SQ);
		}


		//create score boxes
		TextBox highScoreBox = new TextBox(w - ((int) (SQ*3.5)), SQ*6, font, SQ, "High Score", this);
		shapeBox = new ShapeBox(w - ((int) (SQ*3.5)), SQ, font, SQ, "Next Shape", nextShape, this);

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
		int oy = SQ;

		Graphics g = image.getGraphics();
		g.setColor(BACKGROUND);
		g.fillRect(ox,oy,SQ*grid[0],SQ*grid[1]);

		String[][] board = pBoard.getBoard();


		for(int x=0; x<board[0].length; x++) {
			for(int y=0; y<board.length; y++) {
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
	public int getSpeed(){
		return timer.getDelay();
	}
	public boolean getGameState(){
		return gameRunning;
	}
	public Timer getTimer(){
		return timer;
	}

	private void gameOver() {
	 	gameRunning = false;
		activeShape = null;
		timer.stop();
		PentWindow p = (PentWindow)SwingUtilities.getRoot(this);
		p.endGame(getScore());
	}
}
