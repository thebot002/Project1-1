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


    private int squareSize = 30;
    private int[] grid = {5,17};
    private int[] defaultGrid = {5,15};

    private Thread gameThread;
    private PentPanel activePanel;
    private final int H = (grid[1]+2)*squareSize + 30;
    private final int W = (grid[0]+10)*squareSize;
    private Font font;
    private MenuCanvas menuCanvas;
    private Boolean addHighScore = false;
    

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
        setPreferredSize(new Dimension(W,H));
        setTitle("Pentris");
        //setMinimumSize(new Dimension(W,H));
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        createKeyInput();
        if(grid[0] == 5 && grid[1] == 15)
            addHighScore = true;
        try { font = Font.createFont(Font.TRUETYPE_FONT, new File("PixelFont.ttf")).deriveFont(16f); } catch (IOException e) {e.printStackTrace();} catch(FontFormatException e) {e.printStackTrace();}
        menuCanvas = new MenuCanvas(W, H, font, squareSize);
        setActivePanel(menuCanvas);
        setVisible(true);
        pack();
    }
    public PentWindow(boolean bot){
        this();
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

                if(k == 80) {
                    getActivePanel().pKeyPress();
                }

                if(k == 10) {
                    if(getActivePanel() instanceof MenuCanvas) {
                        int pos = ((MenuCanvas)getActivePanel()).getPos();
                        if( pos == 0) {
                            GameCanvas gameCanvas = new GameCanvas(W, H, font, squareSize, grid);
                            setActivePanel(gameCanvas);
                        } else if(pos == 1) {
                            ScoreCanvas scoreCanvas = new ScoreCanvas(W, H, font, squareSize);
                            setActivePanel(scoreCanvas);
                        }
                    } else if (getActivePanel() instanceof ScoreCanvas) {
                        setActivePanel(menuCanvas);
                    }
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
        repaint();
    }

    public PentPanel getActivePanel() {
        return activePanel;
    }

    public void endGame(int score) {
        ScoreCanvas scoreCanvas;
        if(addHighScore) {
            String name = JOptionPane.showInputDialog(this,"Enter Your Name: ");
            if(name == null || name.equals("")) {
                scoreCanvas = new ScoreCanvas(W, H, font, squareSize);
            } else {
                scoreCanvas = new ScoreCanvas(W, H, font, squareSize, name, score);
            }
        } else {
            scoreCanvas = new ScoreCanvas(W, H, font, squareSize);
        }
        setActivePanel(scoreCanvas);
    }
}
