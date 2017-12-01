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

	public PentrisBoard board = new PentrisBoard();

	private Shape activeShape;
	private Shape nextShape;
	private ShapeList shapeList;
	private Timer runtime;

	private int speedDefault = 600;
	private int speedUp = 100;
	//shape x and y:
	private int x=0;
	private int y=0;
	private int score = 0;
	private boolean gameRunning = false;


	public GameCanvas(int W, int H,  Font f, int s) {
		super(0, 0, W, H, f, s);

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
		if(!board.addShapeToBoard(activeShape)) gameOver();
		drawBoard(board);

		timer = new Timer(speedDefault, this);
	   	timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//stops tick events that were created before the game ended trying to do things after the game ends.
		if(gameRunning) {
			//shape is touching another shape
			if(board.isPlaced(activeShape,x,y)) {
				if(y==0) {
					gameOver();
				} else {
					activeShape = nextShape;
					nextShape = shapeList.getRandomShape();
					shapeBox.drawValue(nextShape);
					if(activeShape.getHeight() > activeShape.getWidth()) activeShape.rotateR();
					x=0;
					y=0;
				}
				int lines = board.breakLines();
				score += lines * lines * 10;

				scoreBox.setTarget(score);
				//the line below sometines throws an error in PentrisBoard.class after the game ends.
				if(!board.addShapeToBoard(activeShape))
					gameOver();

			} else {
				board.moveDown(activeShape,x,y);
				y++;
			}
			drawBoard(board);
			repaint();
		}
	}

	public void upKeyPress() {
		  if(gameRunning && board.rotatePossible(activeShape,x,y))board.rotate(activeShape,x,y);
		  drawBoard(board);
	}

	public void downKeyPress() {
		timer.setDelay(speedUp);
	}

	public void downKeyRelease() {
		timer.setDelay(speedDefault);
	}

	public void spaceKeyPress() {
		board.dropDown(activeShape, x, y);
		  activeShape = nextShape;
		  nextShape = shapeList.getRandomShape();
		  if(activeShape.getHeight()>activeShape.getWidth()) activeShape.rotateR();
		  x=0;
		  y=0;
		  score += board.breakLines();
		  board.addShapeToBoard(activeShape,x,y);
		  drawBoard(board);
	}

    public void leftKeyPress() {
         if(gameRunning && board.moveLeftPossible(activeShape, x, y)) {
		  		board.moveLeft(activeShape,x,y);
		  		x--;
			}
		   drawBoard(board);
    }

    public void rightKeyPress() {
		 	if(gameRunning && board.moveRightPossible(activeShape, x, y)) {
        		board.moveRight(activeShape,x,y);
		  		x++;
	  		}
		   drawBoard(board);
	}

	private void drawGame() {
		Graphics g = image.getGraphics();
		g.setColor(BACKGROUND);
		g.fillRect(0,0,w,h);


		//'origin' x and y positions to draw the board from.
		int ox = (w-SQ*5)/2;
		int oy = SQ;

		//draw border of board
		for(int y=-1; y<16; y++) {
			drawBlock(g, ox - SQ, oy + SQ * y, BACKGROUND, SQ);
			drawBlock(g, ox + 5 * SQ, oy + SQ * y, BACKGROUND, SQ);
		}

		for(int x=0; x<5; x++) {
			drawBlock(g, ox + SQ * x, oy - SQ, BACKGROUND, SQ);
			drawBlock(g, ox + SQ * x, oy + 15 * SQ, BACKGROUND, SQ);
		}


		//create score boxes
		TextBox highScoreBox = new TextBox((SQ*23)/2, SQ*6, font, SQ, "High Score");
		TextBox levelBox = new TextBox(SQ/2, SQ*7, font, SQ, "Level");
		scoreBox = new TextBox(SQ/2, SQ*4, font, SQ, "Score");
		timeBox = new TimeBox(SQ/2, SQ, font, SQ, "Time");
		shapeBox = new ShapeBox((SQ*23)/2, SQ, font, SQ, "Next Shape", nextShape);

		scoreBoxes.add(scoreBox);
		scoreBoxes.add(levelBox);
		scoreBoxes.add(highScoreBox);

		add(highScoreBox);
		add(levelBox);
		add(scoreBox);
		add(timeBox);
		add(shapeBox);

		highScoreBox.setValue(scoreManager.getMaxScore().getScore());

		repaint();
	}

	private void drawBoard(PentrisBoard pBoard) {

		int ox = (w-SQ*5)/2;
		int oy = SQ;

		Graphics g = image.getGraphics();
		g.setColor(BACKGROUND);
		g.fillRect(ox,oy,SQ*5,SQ*15);

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

	private void gameOver() {
	 	gameRunning = false;
		activeShape = null;
		timer.stop();
		String name = JOptionPane.showInputDialog(this,"Enter Your Name: ");
		PentWindow p = (PentWindow)SwingUtilities.getRoot(this);
		p.endGame(name, getScore());
	}
}
