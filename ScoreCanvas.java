import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;


class ScoreCanvas extends PentPanel {
	public static void main(String[] args){SwingUtilities.invokeLater(new Runnable() {
            @Override //gets called when the thread is run
            public void run() {
                new PentWindow();
            }});}

    private ArrayList<Score> scoreList;


	public ScoreCanvas(int W, int H, Font f, int s) {
        super(0, 0, W, H, f, s);
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
        g.setFont(font.deriveFont(15f));
        g.drawString("Rank", 90, 200);
        g.drawString("Name", 170, 200);
        g.drawString("Score", 430, 200);

        g.setColor(new Color(182, 221, 232));

        for(int i=0; i<Math.min(10,scoreList.size()); i++) {
            g.drawString("" + position(i+1), 90, 240 + i*30);

            String name = scoreList.get(i).getName();

            name = name.substring(0, Math.min(name.length(), 13));

            g.drawString("" + name, 170, 240 + i*30);
            g.drawString("" + pad(scoreList.get(i).getScore(), len(scoreManager.getMaxScore().getScore())), 440, 240 + i*30);
        }

        g.setColor(Color.WHITE);

        int tw = g.getFontMetrics().stringWidth("< Press Enter to return >");
        g.drawString("< Press Enter to return >", (w-tw)/2, 650);
    }  

    public String pad(int s, int p) {
        return String.format("%0" + p + "d", s);
    }

    public String position(int i) {
        if(i == 1) return "1st";
        else if(i == 2) return "2nd";
        else if(i == 3) return "3rd";
        else return "" + i + "th";
    }


    public int len(int i) {
        return  (int)(Math.log10(i)+1);
    }
}