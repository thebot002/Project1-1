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

	public PentPanel(int W, int H, Font f, int s, int X, int Y) {
        SQ = s; w = W; h = H; font = f;
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(W,H));
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
    }

	public void upKeyPress() {}
    public void downKeyPress() {}
    public void spaceKeyPress() {}
    public void leftKeyPress() {}
    public void rightKeyPress() {}
 	 public void downKeyRelease() {}
}
