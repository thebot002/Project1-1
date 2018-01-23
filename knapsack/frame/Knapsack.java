package knapsack.frame;

import knapsack.components.Parcel;
import knapsack.components.Truck;
import knapsack.filling.BruteForce;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;

public class Knapsack extends JFrame {
    private Truck truck;

    public static void main(String[] args) {
        new Knapsack();
    }

    private CubeDrawer c;
    private Menu m;

    public Knapsack() {
        setTitle("Knapsack");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addKeyInput();
        addComponentListener(new ResizeListener());


        truck = new Truck();
        BruteForce.fill(truck);

        Color scene_BACKGROUND = Color.BLACK;   //Background of scene
        Color scene_FOREGROUND = Color.WHITE;   //Wireframe color

        Color cube_FOREGROUND = Color.YELLOW;   //Cube Wireframe color


        truck.setBackground(scene_BACKGROUND);
        truck.setForeground(scene_FOREGROUND);
        truck.setCubeColor(cube_FOREGROUND);

        c = new CubeDrawer(800, 600, truck);

        m = new Menu(this);
        m.setCubeDrawer(c);

        add(m, BorderLayout.EAST);
        add(c, BorderLayout.CENTER);
        pack();
        setVisible(true);
        //setResizable(false);
        c.renderScene();
        addComponentListener(new ResizeListener());
    }


    public class ResizeListener implements ComponentListener {

        @Override
        public void componentResized(ComponentEvent e) {
            c.doResize(getWidth() - m.getWidth() - 16, c.getHeight());
        }

        @Override
        public void componentMoved(ComponentEvent e) {}
        @Override
        public void componentShown(ComponentEvent e) { }
        @Override
        public void componentHidden(ComponentEvent e) { }
    }

    private void addKeyInput() {
        KeyboardFocusManager keyManager;
        keyManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        keyManager.addKeyEventDispatcher(new KeyEventDispatcher() {

            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if(e.getID()==KeyEvent.KEY_PRESSED ) {
                    int key = e.getKeyCode();

                    CubeDrawer.Camera cam = c.getCamera();

                    if (key == 40)  //down arrow
                        cam.roll(-3);

                    if (key == 38)  //up arrow
                        cam.roll(3);

                    if (key == 39)  //right arrow
                        cam.rotate(3);

                    if (key == 37)  //left arrow
                        cam.rotate(-3);

                    if (key == 45 || key == 109)  //minus
                        cam.zoom(-1);

                    if (key == 61 || key == 107)  //plus
                        cam.zoom(1);
                    if (key == 68)  // d
                        c.toggleDebug();

                    if (key == 80)  // p
                        c.toggleCoodDrawing();

                    if (key == 70)  // f
                        c.toggleFill();

                    m.getInfoTab().setElevation(cam.elevation);
                    m.getInfoTab().setElevation(cam.elevation);
                    m.getInfoTab().setAngle(cam.angle);
                    m.getInfoTab().setZoom(cam.scale);
                }
                return false;
            }
        });
    }

}
