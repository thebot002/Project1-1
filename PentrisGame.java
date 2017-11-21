import javax.swing.*;

public class PentrisGame { 

    PentWindow pentWindow;

	public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PentrisGame();
            }
        });
    }
    

    public PentrisGame() {
        pentWindow = new PentWindow();
    }
}