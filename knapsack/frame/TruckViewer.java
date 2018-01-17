package knapsack.frame;

import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.Dimension;
import javax.swing.JPanel;

public class TruckViewer extends JFrame {
    public static void main(String[] args) {
        new TruckViewer();
    }
    private Thread gameThread;
    private final int H = 700;
    private final int W = 1400;
    private Font font;

    private CubeDrawer c;
    private Menu m;
    private JPanel container;

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
        //m.setPreferredSize(new Dimension(W/3, H));
        m.setVisible(true);

        c = new CubeDrawer(W/3*2, H);
        c.setPreferredSize(new Dimension(W/3*2, H));

        m.setGapsFound(c.getGapAmount());
        m.setCurrentValue(c.getValue());
        //m.setTimeTook(c.getTimeTook());
        add(m, BorderLayout.EAST);
        add(c, BorderLayout.CENTER);
        c.setVisible(true);
        pack();
        addKeyInput();
        setVisible(true);
    }

    private void addKeyInput() {

        KeyboardFocusManager keyManager;

        keyManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        keyManager.addKeyEventDispatcher(new KeyEventDispatcher() {

            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if(e.getID()==KeyEvent.KEY_PRESSED ) {
                    int key = e.getKeyCode();
                    //System.out.println(key);

                    if (key == 40)  //down arrow
                        c.roll(-3);

                    if (key == 38)  //up arrow
                        c.roll(3);

                    if (key == 39)  //right arrow
                        c.rotate(3);

                    if (key == 37)  //left arrow
                        c.rotate(-3);

                    if (key == 45 || key == 109)  //minus
                        c.zoom(-1);

                    if (key == 61 || key == 107)  //plus
                        c.zoom(1);

                    if (key == 68)  // d
                        c.toggleDebug();
                }
                return false;
            }
        });
    }

}
