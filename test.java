import javax.swing.timer;

private final double SPEED = 1000.0;

public class test{
    public static void main(String[] args) {
        class Action implements ActionListener{
            if(shape.isPlaced()){               //to be implemented
                shape = ShapeSet[Math.round( Math.random() ) * ShapeSet.length]; //shapeSet somewhere?
                placeNewShape(shape);           //to be implemented
            }
            else{
                oneDown();                      //to be implemented
            }
        }

        Action listener = new Action();
        Timer runtime = new Timer(SPEED,listener);
        runtime.start();
    }

    //PentominoeBoard Methods
    public void moveDown(String activeShape){ //with position or shift all?

    }
}
