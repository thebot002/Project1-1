import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class NeatBot{
    private static PentWindow window;
    private static GameCanvas pannel;
    private String[][] boardS;
    private double[] inputs;

    private final int SIZE_POPULATION = 100;
    private final int FITNESS_GOAL = 40; //to be adjusted

    public static void main(String[] args){
        new NeatBot();
    }
    public void initializeGame(){
        window = new PentWindow(true);
        try{
            Robot b = new Robot();
            if(window.getActivePanel() instanceof GameCanvas){
                pannel = (GameCanvas)window.getActivePanel();
            }
            Timer t = pannel.getTimer();
            t.addActionListener(new BoardListener());
        }
        catch(AWTException e){
            e.printStackTrace();
        }
    }
    public NeatBot(){
        initializeGame();
    }
    //next towo classes, create separate files...
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
    }
    private class BoardListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            if(pannel.getGameState()){
                System.out.println("yey");
            }
            else{
                System.out.println("test");
            }
        }
    }
}
