import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;


class MenuCanvas extends PentPanel {
	public static void main(String[] args){}

    private int pos = 0;

	public MenuCanvas(int W, int H, Font f, int s) {
        
        super(W, H, f, s, 0, 0);

        drawMainMenu();
        drawBlinker();
    }

 	@Override
   	protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
   	}

    public void upKeyPress() {
        if(pos == 0)
            pos = 2;
        else 
            pos--;
        drawBlinker();
    }

    public void downKeyPress() {
        if(pos == 2)
            pos = 0;
        else
            pos++;
        drawBlinker();
    }

    public int getPos() {
        return pos;
    }

    public void drawBlinker() {
        Graphics g = image.getGraphics();
        g.setColor(BACKGROUND);

        g.setFont(font.deriveFont(20f));
        int textWidth = g.getFontMetrics().stringWidth("Start Game");
        int ox = (w-textWidth)/2;

        g.fillRect(ox-40, 2*h/3-33, 35, 140);
        g.setFont(font.deriveFont(30f));
        g.setColor(Color.white);
        g.drawString(">", ox-40, 2*h/3 + (50 * pos));

        repaint();
    }

    private void drawMainMenu() {
        Graphics g = image.getGraphics();

        g.setColor(BACKGROUND);
        g.fillRect(0,0,w,h);

        g.setFont(font.deriveFont(40f));
        g.setColor(Color.white);

        int textWidth = g.getFontMetrics().stringWidth("Pentris");
        g.drawString("Pentris", (w-textWidth)/2, h/5);



        g.setFont(font.deriveFont(20f));
        textWidth = g.getFontMetrics().stringWidth("Start Game");
        g.drawString("Start Game",   (w-textWidth)/2, 2*h/3);
        g.drawString("High Scores",  (w-textWidth)/2, 2*h/3 + 50);
        g.drawString("Options",      (w-textWidth)/2, 2*h/3 + 100);

        //draw a pent on the main menu
        g.setColor(Color.yellow);
        int ox = (w-4*SQ)/2;
        int oy = SQ*5;
        g.fillRect(ox, oy, SQ, SQ);
        g.fillRect(ox, oy + SQ, SQ, SQ);
        g.fillRect(ox + SQ, oy, SQ, SQ);
        g.fillRect(ox + SQ*2, oy, SQ, SQ);
        g.fillRect(ox + SQ*3, oy, SQ, SQ);

    }
}