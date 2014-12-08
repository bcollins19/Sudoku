package com.bcSudokuMentor.sudokumentor;


class OnlyNum {
	  
	  // funciton: onlyNum
	  // takes in a set and will check every square in the set 
	  // and determine if there is only one number that can go 
	  // into the square, and if it is it will call other functions
	  // such as Square's solvedWith().
	  public static boolean onlyNumInSquare(Set set) {
	    int count = 0;
	    int possibleNum = 0;
	    for (int square = 0; square < 9; square++) {
	      for (int number = 1; number < 10; number++) {
	         if (set.getSquare(square).isPossible(number) == true) {
	           count++;
	           possibleNum = number;
	         }
	      }
	      if (count == 1) {
	        set.getSquare(square).solvedWith(possibleNum, "Only a " + possibleNum + " can go in: "
	                                      + set.getSquare(square).name(), 2);
	        return true;
	      }
	    }
	    return false;
	  }
	  
	}
