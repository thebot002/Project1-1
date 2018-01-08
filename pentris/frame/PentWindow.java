package pentris.frame;

import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Dimension;
import java.io.*;

/**
This class defines the frame of the game. It is filled with different JPannels to serve different purposes.
@see JFrame
*/
public class PentWindow extends JFrame{


    private int squareSize = 40;
    private int[] grid = {5,20};
    private int[] defaultGrid = {5,15};

    private Thread gameThread;
    private PentPanel activePanel;
    private final int H = (grid[1]-2)*squareSize + 40;
    private final int W = (grid[0]+10)*squareSize;
    private Font font;
    private MenuCanvas menuCanvas;
    private Boolean addHighScore = false;

    /**
    Main method of the class. Used to run the pentris.frame.PentWindow from the class itself.
    */
    public static void main(String[] args){
        //Use a thread to ensure the ui is updated correctly (internal swing requirement)
        SwingUtilities.invokeLater(new Runnable() {

            @Override //gets called when the thread is run
            public void run() {
                new PentWindow();
            }
        });
    }

    /**
    Constructor of the pentris.frame.PentWindow. Build the different components and possile opperations.
    */
    public PentWindow() {
        setPreferredSize(new Dimension(W,H));
        setTitle("pentris");
        //setMinimumSize(new Dimension(W,H));
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        createKeyInput();
        if(grid[0] == 5 && grid[1] == 15)
            addHighScore = true;
        try { font = Font.createFont(Font.TRUETYPE_FONT, new File("pentris/frame/PixelFont.ttf")).deriveFont(16f); } catch (IOException e) {e.printStackTrace();} catch(FontFormatException e) {e.printStackTrace();}
        menuCanvas = new MenuCanvas(W, H, font, squareSize);
        setActivePanel(menuCanvas);
        setVisible(true);
        pack();
    }

    /**
    Creates and adds the key input listener on the window. Each key press is linked to a method of the active pannel.
    @see KeyAdapter
    */
    public void createKeyInput() {
        this.addKeyListener( new KeyAdapter() {
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

    /**
    Changes the active pannel to a new specified PentPannel.
    @param panel Pannel extending pentris.frame.PentPanel.
    @see PentPanel
    */
    private void setActivePanel(PentPanel panel) {
        if(activePanel != null) {
            getContentPane().remove(activePanel);
        }
        activePanel = panel;
        getContentPane().add(panel);
        pack();
        repaint();
    }

    /**
    Returns a reference to the active pentris.frame.PentPanel
    @return The active pentris.frame.PentPanel in the frame.
    @see PentPanel
    */
    public PentPanel getActivePanel() {
        return activePanel;
    }

    /**
    Method getting called from a pentris.frame.PentPanel when the game ends to ask for the name and add the highscore to the list and changes the active pentris.frame.PentPanel
    @param score The score_management when the game ends.
    @see ScoreCanvas
    */
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
