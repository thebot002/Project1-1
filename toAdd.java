package function;

import javax.swing.timer;

private final double SPEED = 1000.0;

public class toAdd{
   public static void main(String[] args) {
      ShapeList shapeSet = new ShapeList(); //maybe get it from somewhere isntead of recreating it every  time.
      PentrisBoard board = new PentrisBoard();
      Shape shape = new Shape();
      class Action implements ActionListener{
         if(board.isPlaced(shape,x,y)){ //hwo to keep track of coordinates?
            shape = shapeSet.getShape(Math.round( Math.random() ) * shapeSet.getLength());
            board.addShapeToBoard(shape,);
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
