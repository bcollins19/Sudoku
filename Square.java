package com.bcSudokuMentor.sudokumentor;

//import com.bcSudokuMentor.sudokumentorPuzzle;
//import com.bcSudokuMentor.sudoumentor.Set;

class Square {
	  
	  // data that the squares need to know
	  private String name;
	  private Puzzle puzzle;
	  private Set row;
	  private Set col;
	  private Set box;
	  private int value;
	  private boolean solved;
	  private String[] numStatus;
	  private String[] numReason;
	  private int rowNum;
	  private int colNum;
	  
	  
	  // function: Square
	  // input: a name and the three sets the square is in
	  // creates a square with the specified information
	  // and tells the sets that it owns this square
	  public Square( String n, Set r, Set c, Set b, Puzzle myPuzzle, int rowN, int colN ) {
	    this.name = n;
	    this.puzzle = myPuzzle;
	    this.row = r;
	    this.col = c;
	    this.box = b;
	    this.value = 0;
	    this.solved = false;
	    this.row.ownSquare(this);
	    this.col.ownSquare(this);
	    this.box.ownSquare(this);
	    numStatus = new String[9];
	    numReason = new String[9];
	    for (int index=0; index < 9; index++) {
	      numStatus[index] = "possible";
	      numReason[index] = "";
	    }
	    this.rowNum = rowN;
	    this.colNum = colN;
	  }
	  
	  
	  // function: name
	  // returns the name of the square
	  public String name() {
	    return name;
	  }
	  
	  public int value() {
	    return value;
	  }
	  
	  // function: isSolved
	  // returns true if a square is solved, false otherwise
	  public boolean isSolved() {
	    return solved;
	  }
	  
	  // function: isPossible
	  // input: an integer
	  // returns true if that number is a possibility for the square
	  public boolean isPossible(int n) {
	    if (this.isSolved() == true) {
	      return false;
	    }
	    else {
	      if (numStatus[n-1] == "possible") {  // has to be n-1 because array starts at 0
	        return true;
	      }
	      else {
	        return false;
	      }
	    }
	  }
	  
	  // function: solvedWith
	  // input: an integer and a string for the reason
	  // makes it so the square is solved with that integer and tells everything
	  public void solvedWith(int solvedNum, String solveReason, int importance) {
	    if (this.isSolved() == true) {
	      return;
	    }
	    this.solved = true;
	    numStatus[solvedNum-1] = "solved";
	    numReason[solvedNum-1] = solveReason;
	    this.value = solvedNum;
	    this.row.squareSolved(this, solvedNum);
	    this.col.squareSolved(this, solvedNum);
	    this.box.squareSolved(this, solvedNum);
	    // Needs to send this information to puzzle
	    this.puzzle.nextSolved(this, solveReason, importance); 
	  }
	  
	  
	  // function: solvedWith
	  // input: an integer and a string for the reason
	  // makes it so the square is solved with that integer and tells everything
	  public void otherSquareSolved(int notPossible, String reason) {
	    if (this.isSolved() == true) {
	      return;
	    }
	    changePossibles(notPossible, reason, 0);
	    numReason[notPossible-1] = reason;
	  }
	  
	  // funtion: whichSet
	  // input: a square 
	  // output: an array of Sets so that it can tell what sets it is in
	  public Set[] whichSet() {
	    Set[] Sets = new Set[3];
	    Sets[0] = this.row;
	    Sets[1] = this.col;
	    Sets[2] = this.box;
	    return Sets;
	  }
	  
	  public boolean[] possibles() {
	    boolean[] result = new boolean[9];
	    for (int i = 1; i < 10; i++) {
	      result[i-1] = this.isPossible(i);
	    }
	    return result;
	  }
	  
	  // function: changePossibles
	  // input: an int
	  // output: it will return void but will change the square so the inputted number is
	  // no longer a possibility for that square
	  public void changePossibles(int num, String reason, int importance) {
	    if (numStatus[num-1] == "possible") {
	      numStatus[num-1] = "not possible";
	      numReason[num-1] = reason;
	      this.puzzle.nextStep(reason);
	      this.puzzle.nextStepImportance(importance);
	      return;
	    }
	    else {
	      return;
	    }               
	  }
	  
	  public int[] getPosition() {
		  int[] pos = {rowNum, colNum};
		  return pos;
	  }
	  
	  
	}
