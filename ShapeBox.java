import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;


public class ShapeBox extends ScoreBox {
	public static void main(String[] args){}

    private int shape;

	public ShapeBox(int x, int y, Font f, int s, String t) {
        super(x, y, f, s, t, s*5, 200);
        shape = 0;
        drawValue();
        
    }

    // public void drawIntial() {
    //     Graphics g = image.getGraphics();

    //     g.setColor(Color.white);
    //     g.fillRect(0,0,w,h);

    //     g.setColor(BACKGROUND);
    //     g.fillRect(3,3,w-6,h-6);

    //     g.setColor(Color.white);

    //     repaint();
    //     g.dispose();
    // }

    public void drawValue() {
        Graphics g = image.getGraphics();

        g.setColor(BACKGROUND);
        g.fillRect(w,h,w-40,w);

        repaint();
        g.dispose();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }
}