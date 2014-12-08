package com.bcSudokuMentor.sudokumentor;


class Set {
	  private String name; // name of the set
	  private Square[] SquaresOwned;
	  private Puzzle puzzle;
	  private int nextSquare;
	  
	  // function name: Set
	  // input: a name for a set
	  // makes a set with the input name and creates an array of "squaresowned"
	  public Set(String setName, Puzzle myPuzzle) {
	    name = setName;
	    puzzle = myPuzzle;
	    SquaresOwned = new Square[9];
	    nextSquare = 0;
	  }
	  
	  public Puzzle getPuzzle() {
	    return puzzle;
	  }
	  
	  public Square getSquare(int index) {
	    return this.SquaresOwned[index];
	  }
	  
	  // function: ownSquare
	  // input: a square
	  // tells a set that it owns the inputted square
	  public void ownSquare(Square sq) {
	    SquaresOwned[nextSquare] = sq;
	    nextSquare++;
	    
	  } 
	  
	  
	  public Square getSquareAtIndex(int index) {
		  return SquaresOwned[index];
	  }
	  
	  
	  public boolean setHas(int checkNum) {
	    for (int index = 0; index < 9; index++) {
	      if (this.SquaresOwned[index].value() == checkNum) {
	        return true;
	      }
	    }
	    return false;
	  }
	  

	  public String returnName() {
	    return name;
	  }
	  
	  public void squareSolved(Square square, int solvedNum) {
	    for (int index = 0; index < 9; index++) {
	      if (SquaresOwned[index] != square) {
	        SquaresOwned[index].otherSquareSolved(solvedNum, square.name() + " is a " + solvedNum);
	      }
	    }
	  }
	  
	  public boolean validSet() {
	    for (int number = 1; number < 10; number++) {
	      int squaresSolved = 0;
	      for (int index = 0; index < 9; index++) {
	        if (this.SquaresOwned[index].value() == number) {
	          squaresSolved++;
	        }
	      }
	      if (squaresSolved > 1) {
	        return false;
	      }
	    }
	    return true;
	  }
	  
	  
	}