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
	private TextBox timeBox;
	private TextBox scoreBox;
	private Timer timer;
	private ArrayList<ScoreBox> scoreBoxes= new ArrayList<ScoreBox>();

	private Random random = new Random();
	private int time = 0;

	 String[][] b = {
		{"-","-","-","-","-"},
		{"-","-","-","-","-"},
		{"-","-","-","-","-"},
		{"-","-","-","-","-"},
		{"-","-","-","-","-"},
		{"-","-","-","-","-"},
		{"-","-","-","-","-"},
		{"-","-","-","-","-"},
		{"-","-","-","-","-"},
		{"-","-","-","-","-"},
		{"-","-","-","-","-"},
		{"-","-","-","-","-"},
		{"-","-","-","-","-"},
		{"-","-","-","-","-"},
		{"-","-","-","-","-"},
	};

	public PentrisBoard board = new PentrisBoard(b);

	public Shape activeShape;
	public Shape nextShape;
	public ShapeList shapeList;
	public Timer runtime;

	 private int speedDefault = 600;
	 private int speedUp = 100;
	 private int x=0;
	 private int y=0;
	 private int score = 0;
	 private HashMap<String, Color> colorList;

	public GameCanvas(int W, int H,  Font f, int s) {
		super(W, H, f, s, 0, 0);
		shapeList = new ShapeList();
		activeShape = shapeList.getRandomShape();
		nextShape = shapeList.getRandomShape();
		colorList = new PentColors();
		drawGame();
		startGame();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}

	private void tick() {
		time++;
		timeBox.setTarget(time);
		drawScoreBoxes();
	}

	public void startGame(){
		if(activeShape.getHeight()>activeShape.getWidth()) activeShape.rotateR();
		if(!board.addShapeToBoard(activeShape)) gameOver();
		drawBoard(board);
		
		class ActionTick implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				//shape is touching another shape
				if(board.isPlaced(activeShape,x,y)) {
					if(y==0) 
						gameOver();
					else
						activeShape = nextShape;
						nextShape = shapeList.getRandomShape();
						shapeBox.drawValue(nextShape);
						if(activeShape.getHeight()> activeShape.getWidth()) activeShape.rotateR();
						x=0;
						y=0;
          	switch(board.breakLines()){
						  case 1: score += 10;
						  break;
						  case 2: score += 30;
						  break;
						  case 3: score += 50;
						  break;
						  case 4: score += 70;
						  break;
						  case 5: score += 90;
						  break;
				  	}
						scoreBox.setTarget(score);
						if(!board.addShapeToBoard(activeShape)) gameOver();
				} else {
					board.moveDown(activeShape,x,y);
					y++;
				}
				drawBoard(board);
			}
		}
		ActionTick gameTick = new ActionTick();

		runtime = new Timer(speedDefault,gameTick);

		timer = new Timer(1000, this);
		timer.start();
	   	runtime.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		tick();
		repaint();
	}

	public void upKeyPress() {
		  if(board.isRotatePossible(activeShape,x,y))board.rotate(activeShape,x,y);
		  drawBoard(board);
	}

	public void downKeyPress() {
		 runtime.setDelay(speedUp);
	}
	 public void downKeyRelease(){
		 runtime.setDelay(speedDefault);
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
         if(board.moveLeftPossible(activeShape, x, y)) {
		  		board.moveLeft(activeShape,x,y);
		  		x--;
			}
		   drawBoard(board);
    }
    public void rightKeyPress() {
		 	if(board.moveRightPossible(activeShape, x, y)) {
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


		//draw border
		Color border = new Color(48, 48, 48);
		for(int y=-1; y<16; y++) {
			drawBlock(g, ox - SQ, oy + SQ * y, border, SQ);
			drawBlock(g, ox + 5 * SQ, oy + SQ * y, border, SQ);
		}

		for(int x=0; x<5; x++) {
			drawBlock(g, ox + SQ * x, oy - SQ, border, SQ);
			drawBlock(g, ox + SQ * x, oy + 15 * SQ, border, SQ);
		}


		//create score boxes
		TextBox highScoreBox = new TextBox((SQ*23)/2, SQ*6, font, SQ, "High Score");
		TextBox levelBox = new TextBox(SQ/2, SQ*7, font, SQ, "Level");
		scoreBox = new TextBox(SQ/2, SQ*4, font, SQ, "Score");
		timeBox = new TextBox(SQ/2, SQ, font, SQ, "Time");
		shapeBox = new ShapeBox((SQ*23)/2, SQ, font, SQ, "Next Shape",nextShape);

		scoreBoxes.add(timeBox);
		scoreBoxes.add(scoreBox);
		scoreBoxes.add(levelBox);
		scoreBoxes.add(highScoreBox);
		//scoreBoxes.add(shapeBox);

		add(shapeBox);

		for(ScoreBox box : scoreBoxes) {
			add(box);
		}

		repaint();
	}

	private void drawScoreBoxes() {
		for(ScoreBox box : scoreBoxes) {
			box.tick();
		}
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

	 public int getScore(){
		 return score;
	 }

	 private void gameOver(){
		 runtime.stop();
		 activeShape = null;
		 timer.stop();
		 System.out.println("Game over...");
	 }
}
