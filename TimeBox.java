import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;


public class TimeBox extends ScoreBox {
	public static void main(String[] args){}

    private int value;

	public TimeBox(int x, int y, Font f, int s, String t, GameCanvas g) {
        super(x, y, f, s, t, s*3, s*2, 1000, g);
        value = 0;
        drawIntial();
        drawValue();
    }

    public void drawValue() {
        Graphics g = image.getGraphics();

        g.setColor(BACKGROUND);
        g.fillRect(20,40,w-40,30);

        g.setFont(font.deriveFont(14f));
        g.setColor(Color.white);

        int textWidth = g.getFontMetrics().stringWidth("" + value);
        g.drawString(("" + value), (w-textWidth)/2, 60);

        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }

    public void tick() {
        value++;
        drawValue();
    }
}