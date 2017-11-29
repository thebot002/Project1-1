import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;


class ScoreCanvas extends PentPanel {
	public static void main(String[] args){}
    private HighScoreManager scoreManager;
    private ArrayList<Score> scoreList;


	public ScoreCanvas(int W, int H, Font f, int s) {
        super(0, 0, W, H, f, s);
        scoreManager = new HighScoreManager();
        drawHighScore();
    }

    public ScoreCanvas(int W, int H, Font f, int s, String name, int score) {
        super(0, 0, W, H, f, s);
        scoreManager = new HighScoreManager();
        scoreManager.addScore(name, score);
        drawHighScore();
    }

 	@Override
   	protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
   	}

    public void drawHighScore() {
        Graphics g = image.getGraphics();

        g.setColor(BACKGROUND);
        g.fillRect(0,0,w,h);

        g.setFont(font.deriveFont(40f));
        g.setColor(Color.white);

        int textWidth = g.getFontMetrics().stringWidth("High Score");
        g.drawString("High Score", (w-textWidth)/2, 100);
        g.fillRect((w-textWidth)/2, 100, textWidth, 4);

        scoreList = scoreManager.getScores();
        g.setFont(font.deriveFont(18f));
        g.drawString("Name", 250, 200);
        g.drawString("Score", 400, 200);

        for(int i=0; i<Math.min(10,scoreList.size()); i++) {
            g.drawString("" + (i+1) + ".", 130, 240 + i*40);
            g.drawString("" + scoreList.get(i).getName(), 250, 240 + i*40);
            g.drawString("" + scoreList.get(i).getScore(), 400, 240 + i*40);
        }

        int tw = g.getFontMetrics().stringWidth("< Press Enter to return >");
        g.drawString("< Press Enter to return >", (w-tw)/2, 650);
    }  
}