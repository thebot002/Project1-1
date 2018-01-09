package pentris.frame;

import java.awt.*;
import java.lang.*;
import pentominoe.Shape;

public class ShapeBox extends ScoreBox {
	public static void main(String[] args){}//Use a thread to ensure the ui is updated correctly (internal swing requirement)

	public ShapeBox(int x, int y, Font f, int s, String t, Shape shape, GameCanvas g) {
        super(x, y, f, s, t, s*3, s*4, 0, g);
        drawValue(shape);
    }

    public void drawValue(Shape s) {
        Graphics g = image.getGraphics();

        g.setColor(BACKGROUND);
        g.fillRect(5,30,w-10,h-40);

        //smaller square sizes
        int ss = SQ/2;

        //so the shape gets placed in the center of the box.
        int ix = (int)(SQ*1.5);        int iy = SQ*2;

        drawShape(g, s, ix, iy, ss);
        repaint();
        g.dispose();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }
}
