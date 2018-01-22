package knapsack.frame;

import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.Dimension;
import javax.swing.JPanel;

public class CubeViewer extends JFrame {
    private Font font;

    private CubeDrawer c;
    private Menu m;
    private JPanel container;

    public CubeViewer(CubeDrawer c) {
        this.c = c;
        setTitle("Truck");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addKeyInput();

        m = new Menu();
        m.setCubeDrawer(c);
        m.setVisible(true);

        add(m, BorderLayout.EAST);
        add(c, BorderLayout.CENTER);
        pack();
        setVisible(true);
        setResizable(false);
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
                    	m.getInfoTab().setElevation(c.getElevation());
                    if (key == 38)  //up arrow
                        c.roll(3);
                    	m.getInfoTab().setElevation(c.getElevation());
                    if (key == 39)  //right arrow
                        c.rotate(3);
                    	m.getInfoTab().setAngle(c.getAngle());
                    if (key == 37)  //left arrow
                        c.rotate(-3);
                    	m.getInfoTab().setAngle(c.getAngle());
                    if (key == 45 || key == 109)  //minus
                        c.zoom(-1);
                    	m.getInfoTab().setZoom(c.getZoom());
                    if (key == 61 || key == 107)  //plus
                        c.zoom(1);
                    	m.getInfoTab().setZoom(c.getZoom());
                    if (key == 68)  // d
                        c.toggleDebug();

                    if (key == 80)  // p
                        c.toggleCoodDrawing();
                }
                return false;
            }
        });
    }

}
