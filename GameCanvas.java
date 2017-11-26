import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.util.Random;

class GameCanvas extends PentPanel implements ActionListener {
	public static void main(String[] args) {}

    private ShapeBox shapeBox;
    private TextBox timeBox;
    private Timer timer;
    private ArrayList<ScoreBox> scoreBoxes= new ArrayList<ScoreBox>();

    private Random random = new Random();
    private int time =0;

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
	 public Shape nextShape1;
	 public ShapeList list = new ShapeList();
	 public Timer runtime;

	 private int speedDefault = 600;
	 private int speedUp = 200;
	 private int x=0;
	 private int y=0;
	 private int score = 0;

	public GameCanvas(int W, int H,  Font f, int s) {
        super(W, H, f, s, 0, 0);
        shapeList = new ShapeList();
        nextShape = shapeList.get(0);
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
        int rint = random.nextInt(12);

        nextShape = shapeList.get(rint);
        shapeBox.drawValue(nextShape);
        drawScoreBoxes();
    }

    public void startGame(){
		  activeShape = list.getRandomShape();
		  if(activeShape.getHeight()>activeShape.getWidth()) activeShape.rotateR();
		  //x=3;
		  //x-=(int)(activeShape.getWidth() / 2);
		  board.addShapeToBoard(activeShape,x,y);

		  nextShape1 = list.getRandomShape();
        drawBoard(board);
		  class ActionTick implements ActionListener{
			  public void actionPerformed(ActionEvent e) {
				  if(board.isPlaced(activeShape,x,y)){
					  if(y==0) gameOver();
					  activeShape = nextShape1;
					  nextShape1 = list.getRandomShape();
					  if(activeShape.getHeight()>activeShape.getWidth()) activeShape.rotateR();
					  x=0;
					  y=0;
					  score += board.breakLines();
					  board.addShapeToBoard(activeShape,x,y);
					  drawBoard(board);
					  //x-=(int)((activeShape.getWidth() / 2));
				  }
				  else{
					  board.moveDown(activeShape,x,y);
					  drawBoard(board);
					  y++;
				  }
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
        board.rotate(activeShape,x,y);
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
		  activeShape = nextShape1;
		  nextShape1 = list.getRandomShape();
		  if(activeShape.getHeight()>activeShape.getWidth()) activeShape.rotateR();
		  x=0;
		  y=0;
		  score += board.breakLines();
		  board.addShapeToBoard(activeShape,x,y);
		  drawBoard(board);
    }

    public void leftKeyPress() {
         if(board.moveLateralPossible(activeShape, x, y,-1)) {
		  		board.moveLeft(activeShape,x,y);
		  		x--;
			}
		   drawBoard(board);
    }
    public void rightKeyPress() {
		 	if(board.moveLateralPossible(activeShape, x, y,1)) {
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
        for(int y=-1; y<16; y++) {
            drawBlock(g, ox - SQ, oy + SQ * y, pentColors[0], SQ);
            drawBlock(g, ox + 5 * SQ, oy + SQ * y, pentColors[0], SQ);
        }

        for(int x=0; x<5; x++) {
            drawBlock(g, ox + SQ * x, oy - SQ, pentColors[0], SQ);
            drawBlock(g, ox + SQ * x, oy + 15 * SQ, pentColors[0], SQ);
        }


        //create score boxes
        TextBox scoreBox = new TextBox(SQ/2, SQ*4, font, SQ, "Score");
        TextBox highScoreBox = new TextBox((SQ*23)/2, SQ*6, font, SQ, "High Score");
        TextBox levelBox = new TextBox(SQ/2, SQ*7, font, SQ, "Level");
        timeBox = new TextBox(SQ/2, SQ, font, SQ, "Time");
        shapeBox = new ShapeBox((SQ*23)/2, SQ, font, SQ, "Next Shape");

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
                if(board[y][x] != 0)
                    drawBlock(g, ox + x * SQ, oy + y * SQ, pentColors[board[y][x]], SQ);

            }
        }

        repaint();
    }
}
