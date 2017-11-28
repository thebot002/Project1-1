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

public class PentWindow extends JFrame{

    private Thread gameThread;
    private PentPanel activePanel;
    private int squareSize = 40;
    private final int H = 17*squareSize;
    private final int W = 7*squareSize + squareSize*8;
    private Font font;

    public static void main(String[] args){
        //Use a thread to ensure the ui is updated correctly (internal swing requirement)
        SwingUtilities.invokeLater(new Runnable() {

            @Override //gets called when the thread is run
            public void run() {
                new PentWindow();
            }
        });
    }

    public PentWindow() {
        setSize(W, H);
        setTitle("Pentris");
        setMinimumSize(new Dimension(W,H));
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        createKeyInput();

        try { font = Font.createFont(Font.TRUETYPE_FONT, new File("PixelFont.ttf")).deriveFont(16f); } catch (IOException e) {e.printStackTrace();} catch(FontFormatException e) {e.printStackTrace();}

        MenuCanvas menuCanvas = new MenuCanvas(W, H, font, squareSize);
        setActivePanel(menuCanvas);
        setVisible(true);
    }

    public void createGame() {
        GameCanvas gameCanvas = new GameCanvas(W, H, font, squareSize);
        setActivePanel(gameCanvas);
    }

    public void createKeyInput() {
        this.addKeyListener( new KeyAdapter() {    //Key listener
            public void keyPressed(KeyEvent e) {
                int k = e.getKeyCode();
                //System.out.println("" + k);

                if(k == 87 || k == 38) {
                    getActivePanel().upKeyPress();
                }

                if(k == 83 || k == 40) {
                    getActivePanel().downKeyPress();
                }

                if(k == 68 || k == 39) {
                    getActivePanel().rightKeyPress();
                }

                if(k == 65 || k == 37) {
                    getActivePanel().leftKeyPress();
                }

                if(k == 32) {
                    getActivePanel().spaceKeyPress();
                }

                if(k == 10) {
                    if(getActivePanel() instanceof MenuCanvas)
                        if(((MenuCanvas)getActivePanel()).getPos() == 0)
                            createGame();
                }
            }
            public void keyReleased(KeyEvent e){
               int k = e.getKeyCode();
               if(k == 83 || k == 40) {
                  getActivePanel().downKeyRelease();
               }
            }
        });
    }

    private void setActivePanel(PentPanel panel) {
        if(activePanel != null) {
            getContentPane().remove(activePanel);
        }
        activePanel = panel;
        getContentPane().add(panel);
        pack();
    }

    public PentPanel getActivePanel() {
        return activePanel;
    }
}
