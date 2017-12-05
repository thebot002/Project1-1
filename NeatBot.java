import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class NeatBot{
    private PentWindow window;
    private GameCanvas pannel;
    private String[][] boardS;
    private double[] inputs;

    private final int SIZE_POPULATION = 100;
    private final int FITNESS_GOAL = 40; //to be adjusted

    public static void main(String[] args){
        //Use a thread to ensure the ui is updated correctly (internal swing requirement)
        SwingUtilities.invokeLater(new Runnable() {

            @Override //gets called when the thread is run
            public void run() {
                NeatBot bot = new NeatBot();
                bot.train();
            }
        });
    }
    public NeatBot(){

    }

    private class Genome{
        private double[][] connections; //[in node (0-76) , out node(hidden: >76; output: -1 - -6), weight of connection]
        private int fitness;

        public Genome(){

        }
        public void initialize(){

        }
        public void setFitness(int score, int time){
            this.fitness = score/time;
        }
        public int getFitness(){
            return fitness;
        }
        public void generateNetwork(){

        }
        /*public double[] getOutput(){
            double[] outputs = new double[5];
            for(int i=0; i<connections.length; i++){
                if(connections[i][1]>=0){
                    hiddenNodes[connections[i][1]] += inputs[connections[i][0]] * connections[i][2];
                }
                else {
                    outputs[(-connections[i][1] - 1] += inputs[connections[i][0]] * connections[i][2];
                    outputs[(-connections[i][1] - 1] = sigmoid(outputs[(-connections[i][1] - 1]);
                }
            }
            return outputs;
        }
        private double sigmoid(double value){
            return value;
        }*/
    }
    private class Population{
        private Genome[] genomes;

        public Population(){
            genomes = new Genome[SIZE_POPULATION];
            for(Genome g: genomes){
                g = new Genome();
            }
        }
        public Genome getGenome(int n){
            return genomes[n];
        }
        public void CrossOver(Genome g1, Genome g2){

        }
        private void mutuation(Genome g1){

        }
        public int getGeneralFitness(){
            int sumFitness = 0;
            for(Genome g: genomes){
                sumFitness += g.getFitness();
            }
            return sumFitness/SIZE_POPULATION;
        }
    }

    private void getInputs(){
        //boardS = board.getBoard();
        int posInput = 0;
        for(int i=0; i<boardS.length; i++){
            for(int j=0; j<boardS[0].length; j++){
                if(!boardS[i][j].equals("-")) inputs[posInput] = 1;
                posInput++;
            }
        }
    }

    public void train(){
        Population pop = new Population();
        do{
            for(int i=0; i<SIZE_POPULATION; i++){
                Genome g = pop.getGenome(i);
                PlayGame game = new PlayGame();
                window = new PentWindow();
                try{
                    pannel = new GameCanvas(100,100,new Font(" TimesRoman ",Font.PLAIN,100),40); //random initialize
                    Robot b = new Robot();
                    b.keyPress(10);
                    if(window.getActivePanel() instanceof GameCanvas) pannel = (GameCanvas) window.getActivePanel();
                    PentrisBoard board = pannel.getBoard();
                    boardS = board.getBoard();
                    inputs = new double[(boardS.length * boardS[0].length)+1];
                    getInputs();
                }
                catch(AWTException e){
                    e.printStackTrace();
                }
                class BoardListener implements ActionListener {
                    public void actionPerformed(ActionEvent e){
                        System.out.println("yey");
                    }
                }
                Timer t = pannel.getTimer();
                Timer t2 = new Timer(t.getDelay(),new BoardListener());
                //game.run();
                //playGame(g);
                window.dispose();
            }
        }while(pop.getGeneralFitness() < FITNESS_GOAL);
    }
    private class PlayGame extends Thread{
        public void run(){
            /*t.addActionListener(new BoardListener());
            t.start();
            while(t.isRunning()){
            }*/
        }
    }
    /*public void playGame(Genome player){
        class BoardListener implements ActionListener {
            public void actionPerformed(ActionEvent e){
                System.out.println("yey");
            }
        }
        Timer t = pannel.getTimer();
        t.addActionListener(new BoardListener());
        while(t.isRunning()){
        }
        /*while(pannel.getGameState()){
            getInputs();
            //System.out.println("hey");
            /*double[] buttons = player.getOutput();
            try{
                Robot bot = new Robot();
                if(button[0]>0.5) bot.keyPress(37);
                if(button[1]>0.5) bot.keyPress(38);
                if(button[2]>0.5) bot.keyPress(39);
                if(button[3]>0.5) bot.keyPress(40);
                if(button[4]>0.5) bot.keyRelease(40);
            }
            catch(AWTException e){
                e.printStackTrace();
            }
        }
        //player.setFitness(pannel.getScore(),pannel.getTime());
    }*/
}
