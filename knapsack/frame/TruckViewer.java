package knapsack.frame;

import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TruckViewer extends JFrame {

    private Thread gameThread;
    private final int H = 700;
    private final int W = 1200;
    private Font font;

    private CubeDrawer c;
    private Menu m;

    public TruckViewer() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                create();
            }
        });
    }

    private void create() {
        setSize(W, H);
        setTitle("Truck");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        m = new Menu();
        m.setPreferredSize(new Dimension(W/4*1, H));
        m.setVisible(true);

        c = new CubeDrawer(W, H);
        c.setPreferredSize(new Dimension(W/4*3, H));
        c.setVisible(true);

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
        container.add(c);
        container.add(m);
        add(container);
        pack();
        setVisible(true);
        addKeyInput();
    }

    public void addKeyInput() {
        this.addKeyListener( new KeyAdapter() {    //Key listener
            public void keyPressed (KeyEvent e){
                int key = e.getKeyCode();
                //System.out.println(key);

                if (key == 40) { //down arrow
                    c.rollDown();
                }

                if (key == 38) { //up arrow
                    c.rollUp();
                }

                if (key == 39) { //right arrow
                    c.rotateRight();
                }

                if (key == 37) { //left arrow
                    c.rotateLeft();
                }
            }
        });
    }
}
