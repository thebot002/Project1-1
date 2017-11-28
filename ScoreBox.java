import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;


public class ScoreBox extends PentPanel{
	public static void main(String[] args){}

    protected String title, text;
    protected int X, Y;
    protected BufferedImage image;
    protected Font font;

	public ScoreBox(int x, int y, Font f, int s, String t, int w, int h) {
        super(w, h, f, s, x, y);
        X = x; Y = y;
        image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        title = t;
        font = f;
        drawIntial();
    }

    public String getTitle() {
    	return title;
    }

    public void drawIntial() {
        Graphics g = image.getGraphics();

        g.setColor(Color.white);
        g.fillRect(0,0,w,h);

        g.setColor(BACKGROUND);
        g.fillRect(3,3,w-6,h-6);

        g.setFont(font.deriveFont(10f));
        g.setColor(Color.white);

        int textWidth = g.getFontMetrics().stringWidth(title);
        g.drawString(title, (w-textWidth)/2, 25);
        repaint();
        g.dispose();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public BufferedImage getimage() {
        return image;
    }

    public void tick() {}
}