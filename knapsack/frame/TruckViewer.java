package knapsack.frame;

import javafx.geometry.Point3D;
import knapsack.frame.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;
import java.io.*;
import java.awt.FlowLayout;
import java.util.concurrent.ThreadLocalRandom;

public class TruckViewer extends JFrame {
    public static void main(String[] args) {
        new TruckViewer();
    }
    private Thread gameThread;
    private final int H = 700;
    private final int W = 1200;
    private Font font;

    private CubeDrawer c;

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

        JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(W, 300));

        c = new CubeDrawer(W, H);
        c.setPreferredSize(new Dimension(W, H));
        c.setVisible(true);

        add(c);
        //add(p);
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
    