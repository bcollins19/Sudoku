package com.bcSudokuMentor.sudokumentor;


class NeedNumber {

	  // function: strategy1
	  // input: a set and a number
	  // if it finds a square that can be solved then it
	  // will tell the square to be solved with that number
	  public static boolean needNumberInSet(Set set, int checkNum) {
	    if (numSquaresPoss(set, checkNum) != 1) {
	      return false;
	    }
	    else {
	      for (int index = 0; index < 9; index++) {
	        if (set.getSquare(index).isPossible(checkNum)) {
	          set.getSquare(index).solvedWith(checkNum, "The only place a " + checkNum 
	                                            + " can go in " + set.returnName() + " is: " 
	                                            + set.getSquare(index).name(), 1);
	          return true;
	        }
	      }
	      return false;
	    }
	  }
	  
	  
	    private static int numSquaresPoss(Set set, int checkNum) {
	    int numPossibles = 0;
	    for (int index = 0; index < 9; index++) {
	      if (set.getSquare(index).isPossible(checkNum)) {
	        numPossibles++;
	      }
	    }
	    return numPossibles;
	  }
	  
	}
