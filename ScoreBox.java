import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;


public class ScoreBox extends PentPanel implements ActionListener{
	public static void main(String[] args){}

    protected String title, text;
    protected BufferedImage image;
    protected GameCanvas parent;

	public ScoreBox(int x, int y, Font f, int s, String t, int w, int h, int time, GameCanvas g) {
        super(x, y, w, h, f, s);
        image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        title = t;
        drawIntial();
        parent = g;
        if(time != 0) {
            Timer timer = new Timer(time, this);
            timer.start();
        }
    }

    public void drawIntial() {
        Graphics g = image.getGraphics();

        g.setColor(Color.white);
        g.fillRect(0,0,w,h);

        g.setColor(BACKGROUND);
        g.fillRect(3,3,w-6,h-6);
        float fontSize = SQ/4;
        g.setFont(font.deriveFont(fontSize));
        g.setColor(Color.white);

        int textWidth = g.getFontMetrics().stringWidth(title);
        g.drawString(title, (w-textWidth)/2, 10 + g.getFontMetrics().getHeight());
        repaint();
        g.dispose();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }

    public void tick() {}

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!parent.isPaused() && parent.isRunning())
            tick();
    }
}