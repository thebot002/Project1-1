package knapsack.frame;

//import knapsack.*;
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

    private Thread gameThread;
    private final int H = 700;
    private final int W = 1200;
    private Font font;

    private CubeDrawer c;

    public TruckViewer() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TruckViewer();
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

        c = new CubeDrawer(W, 400);
        c.setPreferredSize(new Dimension(W, 400));
        c.setVisible(true);

        add(c);
        //add(p);
        pack();
        setVisible(true);
    }
}
    