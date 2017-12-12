
// This class construct our board, and also have some methods to operate on this board
public class PentominosBoard {
	private String[][] board;

// The constructor of the class, takes a 2D array board and creates a board object of this class
	public PentominosBoard(String[][] board) {
		this.board = board;
	}

	public String[][] getBoard(){
		return board;
	}

// We use this method to add shapes to board
	public void addShapeToBoard(String[][] shape, int x, int y) {
		int k=0;

/* 	 We need to find out the first element of the shape array that is not empty
     and fill the first empty square of the board with it */

		while (shape[0][k].equals("-")) {
			y=y-1;
			k++;
		}
		for (int i = 0; i < shape.length; i++) {
			for (int j = 0; j < shape[0].length; j++) {

	/* If the element of the shape array is not empty we copy it to the board */
				if ( !shape[i][j].equals("-") ) {
					board[x + i][y + j] = shape[i][j];
				}
			}
		}
	}

/* This method removes shapes from the board, we dont need it for the specific algorithm we used right now
   but we will probably need it at some point */
	public void removeShapeFromBoard(String[][] shape, int x, int y) {
		for (int i = shape.length - 1; i >= 0; i--) {
			for (int j = shape[0].length - 1; j >= 0; j--) {
				if (!shape[i][j].equals("-")) {
					board[x + i][y + j] = "-";
				}
			}
		}
	}

	public void avoidColisions(String[][] shape, int x, int y) {
		for (int i = 0; i < shape.length; i++) {

			if (shape[i][shape[0].length - 1] != "-" && (shape[0].length - 1 + y > board[0].length - 1 ||
					                                      board[i][shape[0].length-1]!="-")) {
				y--;

				avoidColisions(shape, x, y);

			} else if (shape[i][0] != "-" && (y < 0 || board[x+i][y]!="-")) {

				y++;

				avoidColisions(shape, x, y);
			}
		}
	}

	public void breakLines() {

		for (int i = board.length - 1; i >= 0; i--) {
			if (isLineFull(i)) {
				for (int j = i - 1; j >= 0; j--) {
					if (!isLineFull(j)) {
						for (int k = 0; k < board[0].length; k++) {
							board[i][k] = board[j][k];
						}
						break;
					} else {
						emptyLine(i);
						emptyLine(j);
					}
				}
			}
		}
	}

	public void emptyLine(int line) {
		for(int i=0; i<board[line].length; i++) {
			board[line][i]="-";
		}
	}
	public boolean isLineFull(int line) {
		for(int j=0; j<board[line].length; j++) {
			if(board[line][j].equals("-"))
				return false;
		}
		return true;
	}

/* With this method we check if it is allowed for a shape to be placed on the board */

	public boolean isCopyPossible(String[][] shape, int x, int y) {
		boolean isPossible = false;
		boolean checkLineLength=false;
		int k=0;


/* Again we check if the first element of the shape array is empty and if it is we move it
   one position to the left, and check the second element of the same row e.t.c */
		while (shape[0][k].equals("-") ) {
			y=y-1;
			k++;
		}


		for(int i=0; i<shape.length; i++) {
			if(x+i<board.length) {
				if(board[x+i].length < (y+shape[i].length)) {
					checkLineLength=true;
				}
			}
		}

/* We check if the width of the board is big enough to fit a shape with its specific width and height
   in the specific coordinates  x an y */
		if (y<0 || board.length < (x + shape.length)|| checkLineLength) {
			return false;
		}

		for (int i = 0; i < shape.length; i++) {
			for (int j = 0; j < shape[0].length; j++) {

		/* Here we check that the shape will be added only in empty positions of the board,
		    and not on square that is already filled */
//				if(x+i<board.length-1) {

					if (!board[x + i][y + j].equals("-") && !shape[i][j].equals("-")) {
						return false;
					} else {
						isPossible = true;
					}
//				}else {
//					if (!board[x + i][y+j-1].equals("-") && !shape[i][j-1].equals("-")) {
//						return false;
//					} else {
//						isPossible = true;
//					}
				}
			}



		return isPossible;
	}

/* We need this method in order to create a board exactly the same as the one we already have,
   and add shapes on it, so if the adding of the shapes we do is not correct,
   we dont manipulate our main board  */

	public PentominosBoard copyBoard() {
		String[][] newBoard = new String[board.length][];
		for (int i = 0; i < board.length; i++) {
			newBoard[i] = new String[board[i].length];
		}

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				newBoard[i][j] = board[i][j];

			}
		}
		return new PentominosBoard(newBoard);
	}

// This method checks if the board is full, if it is it returns True and we know he got a solution

	public boolean isBoardFull() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j].equals("-")) {
					return false;
				}
			}
		}
		return true;
	}

// This method search for the next empty square of the board, which we need to fill with our next shape

	public int[] whereToPut() {
		int[] setPosition = new int[2];
		setPosition[0] = 0;
		setPosition[1] = 0;


			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board[i].length; j++) {
					if (board[i][j].equals("-")) {
						setPosition[0] = i;
						setPosition[1] = j;
						return setPosition;
					}
				}
			}

		return setPosition;
	}

// This method just print the board, so we can have a visual result of our solution
	public void printBoard() {
		for (int m=0; m<board.length; m++) {
			for (int n=0; n<board[board.length-m-1].length; n++) {
				System.out.print(board[board.length-m-1][n]+" ");

			}
			System.out.println("");
		}
	}

	public String[][] ReverseBoard() {
		String[][] reverseBoard = new String[board.length][board[0].length];
		for(int m=0; m<board.length; m++) {
			for (int n=0; n<board[m].length; n++ ) {
				reverseBoard[m][n]=board[board.length-m-1][n];
			}
			System.out.println("");
		}
		return reverseBoard;
	}

	public boolean[] locationOfI() {

		  boolean[] locationOfI = new boolean[board.length];
		  for (int i=0; i<board.length; i++) {
			  locationOfI[i]=false;
		  }
		  for (int i=0; i<board.length; i++) {
			  for ( int j = 0; j < board[j].length; j++ ) {
				  if(board[i][j].equals("I")) {
					 locationOfI[i]=true;
					 return locationOfI;
				  }
			  }
		  }
		  return locationOfI;
	  }

	public boolean checkLocationOfI() {
		  boolean[] locationI = locationOfI();
		  for(int i=0; i<locationI.length; i++) {
			  if(( i==4 ) && locationI[i])
					  return true;
		  }
		  return false;
	  }

	public boolean[] locationOfL() {

		  boolean[] locationOfL = new boolean[board.length];
		  for (int i=0; i<board.length; i++) {
			  locationOfL[i]=false;
		  }
		  for (int i=0; i<board.length; i++) {
			  for ( int j = 0; j < board[j].length; j++ ) {
				  if(board[i][j].equals("L")) {
					 locationOfL[i]=true;
					 return locationOfL;
				  }
			  }
		  }
		  return locationOfL;
	  }

	public boolean checkLocationOfL() {
		  boolean[] locationL = locationOfL();
		  for(int i=0; i<locationL.length; i++) {
			  if(( i==0) && locationL[i])
					  return true;
		  }
		  return false;
	  }

	public boolean checkILshapesLine() {
		  int checkI=0;
		  int checkL=0;
		  boolean[] locOfI=locationOfI();
		  for (int i=0; i<board.length; i++) {
			  checkI=0;
			  checkL=0;

			  for(int j=0; j<board[i].length; j++) {
				  if(board[i][j].equals("I"))
					  checkI++;
				  if(board[i][j].equals("L"))
					  checkL++;
			  }

				  if(checkL==1 && checkI+checkL==2 || checkL==2 && checkI+checkL==3)
					  return false;

				  }



		  return true;
	  }

	public boolean checkSpecificSolution() {
		int k=0;
		int l=0;
		for (int i=0; i<board.length-1; i++) {
			k=0;
			l=0;
			for (int j=0; j<board[i].length;j++) {
				if(!board[i][j].equals("-"))
					k++;
				if(!board[i+1][j].equals("-"))
					l++;
			}
			if(l>k)
				return false;
		}
		return true;
	}
}
