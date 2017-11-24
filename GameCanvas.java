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
    private ShapeList shapeList;
    private Timer timer;
    private Shape nextShape;
    private ArrayList<ScoreBox> scoreBoxes= new ArrayList<ScoreBox>();
    private Random random = new Random();

    public int[][] board = {
        {0,0,0,0,0},
        {0,0,0,0,0},
        {0,0,1,1,0},
        {0,0,1,0,0},
        {0,0,1,0,0},
        {0,0,1,0,0},
        {0,0,0,0,0},
        {0,0,0,0,0},
        {0,0,0,0,0},
        {0,0,0,0,0},
        {0,0,0,0,0},
        {0,0,0,0,0},
        {0,0,0,0,0},
        {0,0,0,0,0},
        {0,0,0,0,0},
    };

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
        drawScoreBoxes();

        int rint = random.nextInt(12);

        nextShape = shapeList.get(rint);
        shapeBox.drawValue(nextShape);
    }

    public void startGame() {

        drawBoard(board);

        timer = new Timer(1000, this);
        timer.start();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        tick();
        repaint();
    }

    public void upKeyPress() {
        //rotateShape();
    }

    public void downKeyPress() {
        //speedUp();
    }

    public void spaceKeyPress() {
        //dropDown();
    }

    public void leftKeyPress() {
        //moveLeft();
    }
    public void rightKeyPress() {
        //moveRight();
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
        TextBox timeBox = new TextBox(SQ/2, SQ, font, SQ, "Time");
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

    private void drawBoard(int[][] board) {

        int ox = (w-SQ*5)/2;
        int oy = SQ;

        Graphics g = image.getGraphics();
        g.setColor(BACKGROUND);
        g.fillRect(ox,oy,SQ*5,SQ*15);


        for(int x=0; x<board[0].length; x++) {
            for(int y=0; y<board.length; y++) {
                if(board[y][x] != 0)
                    drawBlock(g, ox + x * SQ, oy + y * SQ, pentColors[board[y][x]], SQ);
            }
        }

        repaint();
    }
}