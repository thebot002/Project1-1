import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;


public class ShapeBox extends ScoreBox {
	public static void main(String[] args){}

	public ShapeBox(int x, int y, Font f, int s, String t) {
        super(x, y, f, s, t, s*3, s*4);
    }

    public void drawValue(Shape s) {
        Graphics g = image.getGraphics();

        g.setColor(BACKGROUND);
        g.fillRect(5,30,w-10,h-40);

        //smaller square sizes
        int ss = SQ/2;
        String[][] shape = s.getShape();

        //so the shape gets placed in the center of the box.
        int ix = (int)(SQ*1.5) - (ss*shape[1].length)/2;
        int iy = (int)(SQ*1.5) - (ss*shape.length)/2 + ss; 

        for (int i=0; i<shape.length; i++) {
            for (int j=0; j<shape[0].length; j++) {
                if(shape[i][j] != "-")
                    drawBlock(g, i*ss+ix, j*ss+iy, pentColors[0], ss);
            }
        }

        repaint();
        g.dispose();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }
}