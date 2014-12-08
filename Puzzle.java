package com.bcSudokuMentor.sudokumentor;


import java.util.Vector;
class Puzzle {
  
  // data that puzzle has
  private Set[] sets;
  private Square[][] square;
  private Vector<String> reason;
  
 /* // I think I need these three in order to sync with the userPuzzle
  private Vector<Square> squareModified;
  private Vector<Integer> whatNumber;
  private Vector<Boolean> solvedOrRemoved;*/
  
  private Square[] solveOrder;
  private Vector<Integer> solveImportance;
  private int atHintNumber;
  private int numberSolved;
  private int importanceWanted;
  private Controller controller;
  
  // function createPuzzle
  // creates 27 sets and 81 squares in a 9 by 9 array
  // first 9 sets are "rows" second 9 sets are "cols" and
  // third are "boxes"
  public Puzzle(Controller cont) {
    sets = new Set[27];
    square = new Square[9][9];
    reason = new Vector<String>(0);
    solveOrder = new Square[81];
    solveImportance = new Vector<Integer>(0);
    atHintNumber = 0;
    numberSolved = 0;
    controller = cont;
    for (int i=0; i < 27; i++) {
      if (i < 9) {
        sets[i] = new Set("row" + Integer.toString(i+1), this);
      }
      else if (i > 8 && i < 18) {
        sets[i] = new Set("col" + Integer.toString(i%9+1), this);
      }
      else {
        sets[i] = new Set("box" + Integer.toString(i%9+1), this);
      }
    }
    for (int row=0; row <= 8; row++) {
      for (int col=0; col <= 8; col++) {
        int boxnum = ((row)/3)*3 + ((col)/3);
        String squareName = "r" + Integer.toString(row+1) + "c" + Integer.toString(col+1);
        square[row][col] = new Square(squareName, sets[row], sets[col+9], sets[boxnum+18], this, row, col);
      }
    }
  }
  
  public Set getSet(int setNumber) {
    return this.sets[setNumber];
  }
  
  public Square getSquare(int row, int col) {
    return this.square[row][col];
  }
  
  public void nextSolved(Square square, String reasonWhy, int importance) {
    this.solveImportance.addElement(importance);
    this.reason.addElement(reasonWhy);
    solveOrder[numberSolved] = square;
    numberSolved++;
  }
  
  public int getHintNum() {
	  return atHintNumber;
  }
  
  public void incrementHintNum() {
	  atHintNumber++;
  }
  
  public int getReasonSize() {
	  return reason.size();
  }
  
  public int getImportanceAtIndex(int i) {
	  return solveImportance.elementAt(i);
  }
  
  public int getImportanceWanted() {
	  return importanceWanted;
  }
  
  public String getReasonAtIndex(int index) {
	  return reason.elementAt(index);
  }
  
  public void nextStep(String reasonWhy) {
    this.reason.addElement(reasonWhy);
  }
  
  public void nextStepImportance(int importance) {
    this.solveImportance.addElement(importance);
  }
  
  public void solvePuzzle() {
    boolean restart = false;
    for (int number = 9; number > 0; number--) {
      for (int set = 0; set < 27; set++) {
        restart = NeedNumber.needNumberInSet(sets[set], number);
        if (restart == true) {
          solvePuzzle();
          return;
        }
      }
    }
    for (int number = 9; number > 0; number--) {
      for (int set = 0; set < 27; set++) {
        restart = SetClaim.setClaim(sets[set], number);
        if (restart == true) {
          solvePuzzle();
          return;
        }
      }
    }
    for (int set = 0; set < 27; set++) {
      restart = OnlyNum.onlyNumInSquare(sets[set]);
      if (restart == true) {
        solvePuzzle();
        return;
      }
    }
    for (int number = 2; number < 5 ; number++) {
      for (int set = 0; set < 27; set++) {
        restart = NakedNumbers.nakedNumbers(sets[set], number);
        if (restart == true) {
          solvePuzzle();
          return;
        }
      }
    } 
  }
  
  public Controller getController() {
	  return controller;
  }
  
  
  public boolean validPuzzle() {
    for (int set = 0; set < 27; set++) {
      if (!sets[set].validSet()) {
        return false;
      }
    }
    return true;
  }
  
  public Vector<Square> puzzleOnTrack(Puzzle solution) {
    Vector<Square> result = new Vector<Square>(0);
    for (int row = 0; row < 9; row++) {
      for (int col = 0; col < 9; col++) {
        if (this.square[row][col].value() != solution.square[row][col].value() && this.square[row][col].value() != 0) {
         result.addElement(this.square[row][col]);
        }
      }
    }
    return result;
  }
  
  public String nextHint() {
    while(solveImportance.elementAt(atHintNumber) < importanceWanted && atHintNumber < reason.size() - 1) {
      atHintNumber++;
    }
    if (atHintNumber == reason.size()-1 && solveImportance.elementAt(atHintNumber) < importanceWanted) {
    	return "No more hints!";
    }
    return reason.elementAt(atHintNumber++);
  }
  
  public boolean puzzleSolved(Puzzle solution) {
    for (int row = 0; row < 9; row++) {
      for (int col = 0; col < 9; col++) {
        if (this.square[row][col].value() != solution.square[row][col].value()) {
          return false;
        }
      }
    }
    return true;  
  }
  
  public void setImportance(int importance) {
	  this.importanceWanted = importance;
  }
  
  public Square solveOrderIndex(int index) {
	  return solveOrder[index];
  }
  
  public boolean squareSolved(String squareName) {
	  for (int i = 0; i < 9; i++) {
		  for (int j = 0; j < 9; j++) {
			  if (square[i][j].name().equals(squareName)) {
				  return square[i][j].isSolved();
			  }
		  }
	  }
	  return false;
  }
  
}