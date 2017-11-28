import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;


class ScoreCanvas extends PentPanel {
	public static void main(String[] args){}

	public ScoreCanvas(int W, int H, Font f, int s) {
        super(W, H, f, s, 0, 0);
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

        int textWidth = g.getFontMetrics().stringWidth("Game Over");
        g.drawString("Game Over", (w-textWidth)/2, h/5);
    }  
}