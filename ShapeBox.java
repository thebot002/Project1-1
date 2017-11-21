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
        super(x, y, f, s, t, s*3, s*3);
        shape = 0;
        drawValue();
        
    }

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