import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class PentPanel extends JPanel {
	public static void main(String[] args){}

	protected BufferedImage image;
    protected Font font;
    protected int h, w;
    protected Color BACKGROUND = new Color(39,40,34);
    protected int SQ;
    protected Color[] pentColors = {new Color(255,255,0), new Color(255,0,255), new Color(0,255,255)}; 

	public PentPanel(int W, int H, Font f, int s, int X, int Y) {
        SQ = s; w = W; h = H; font = f;
        setBackground(BACKGROUND);
        setPreferredSize(new Dimension(W,H));
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
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

    public void drawShape(Graphics g, Shape shape, int x, int y, int s) {

        String[][] stringShape = shape.getShape();

        for (int i=0; i<stringShape.length; i++) {
            for (int j=0; j<stringShape[0].length; j++) {
                if(stringShape[i][j] != "-")
                    drawBlock(g, i*s+x, j*s+y, pentColors[0], s);
            }
        }
    }
    

	public void upKeyPress() {}
    public void downKeyPress() {}
    public void spaceKeyPress() {}
    public void leftKeyPress() {}
    public void rightKeyPress() {}
 	 public void downKeyRelease() {}
}
