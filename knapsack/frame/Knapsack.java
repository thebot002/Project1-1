package knapsack.frame;

import knapsack.components.Truck;
import knapsack.filling.BruteForce;

import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyEvent;

public class Knapsack extends JFrame {
    private final Truck truck;

    public static void main(String[] args) {
        new Knapsack();
    }

    private CubeDrawer c;
    private Menu m;

    public Knapsack() {
        setTitle("Knapsack");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addKeyInput();

        truck = new Truck();
        BruteForce.fill(truck);
        c = new CubeDrawer(800, 600, truck);

        m = new Menu(this);
        m.setCubeDrawer(c);

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

                    if (key == 70)  // f
                        c.toggleFill();
                }
                return false;
            }
        });
    }

}
