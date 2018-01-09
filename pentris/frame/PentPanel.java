package pentris.frame;

import pentominoe.Shape;
import pentris.score_management.HighScoreManager;

import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class PentPanel extends JPanel {
	public static void main(String[] args){}

	protected BufferedImage image;
    protected Font font;
    protected int h, w, x, y;
    protected Color BACKGROUND = new Color(39,40,34);
    protected int SQ;
    protected HashMap<String, Color> colorList;
    protected HighScoreManager scoreManager;
    protected boolean paused = false;

	public PentPanel(int x, int y, int w, int h, Font f, int s) {
        super(null, true);
        SQ = s; this.w = w; this.h = h; font = f; this.x = x; this.y = y;
        colorList = new PentColors();
        setBackground(BACKGROUND);
        setBounds(x,y,w,h);
        image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        scoreManager = new HighScoreManager();
    }

    public void drawBlock(Graphics g, int x, int y, Color c, int s) {
        int[] xp = {x, x, x + s};
        int[] yp = {y, y + s, y};

        g.setColor(c);
        g.fillPolygon(new Polygon(xp, yp, 3));


        xp[0] = x + s;
        yp[0] = y + s;

        g.setColor(c.darker().darker());
        g.fillPolygon(new Polygon(xp, yp, 3));

        int inner = (int)(s * 7)/10;
        int gap = (int)(s-inner)/2;

        g.setColor(c.darker());
        g.fillRect(x + gap, y + gap, inner, inner);
    }

    //draws a shape with a center (x,y)
    public void drawShape(Graphics g, Shape shape, int x, int y, int s) {

        String[][] stringShape = shape.getShape();

        x -= (shape.getWidth()*s)/2;
        y -= (shape.getHeight()*s)/2;

        for (int j=0; j<shape.getHeight(); j++) {
            for (int i=0; i<shape.getWidth(); i++) {
                if(stringShape[j][i] != "-")
                    drawBlock(g, x+(i*s), y+(j*s), colorList.get(stringShape[j][i]), s);
            }
        }
    }

    public Boolean isPaused() {
        return paused;
    }

    public void activate() {}
	public void upKeyPress() {}
    public void downKeyPress() {}
    public void spaceKeyPress() {}
    public void leftKeyPress() {}
    public void rightKeyPress() {}
 	public void downKeyRelease() {}
    public void pKeyPress() {}
}
