package com.bcSudokuMentor.sudokumentor;


import java.util.Vector;

class SetClaim {
  
  // function: possSquares
  // input: a set and number to check
  // output: a vector of squares that are in the inputted set,
  // are not solved, and have checkNum as "possible".
  private static Vector<Square> possSquares( Set set, int checkNum) {
    Vector<Square> possibles = new Vector<Square>(0);
    for (int index = 0; index < 9; index++) {
        if (set.getSquare(index).isPossible(checkNum) == true) {
          possibles.addElement(set.getSquare(index));
        }
    }
    return possibles;
  }
  
  // function: shareSet
  // input: an array of squares and a set
  // output: returns true if the inputted array has a set incommon 
  // other than the claimingSet, returns false otherwise. This way
  // if they don't share a set other than claimingSet I can just return
  // false and we can stop the process there.
  private static Boolean shareSet( Vector<Square> squares, Set claimingSet) {
    if (squares.size() < 2) {
      return false;
    }
    Set row = squares.firstElement().whichSet()[0];
    Set col = squares.firstElement().whichSet()[1];
    Set box = squares.firstElement().whichSet()[2];
    int rowCount = 0;
    int colCount = 0;
    int boxCount = 0;
    for (int index = 0; index < squares.size(); index++) {
      if (squares.elementAt(index).whichSet()[0] == row) {
        rowCount++;
      }
      if (squares.elementAt(index).whichSet()[1] == col) {
        colCount++;
      }
      if (squares.elementAt(index).whichSet()[2] == box) {
        boxCount++;
      }
    }
    if (rowCount == squares.size() && row != claimingSet) {
      return true;
    }
    else if (colCount == squares.size() && col != claimingSet) {
      return true;
    }
    else if (boxCount == squares.size() && box != claimingSet) {
      return true;
    }
    return false;
  }
  
  // function: findSharedSet
  // input: an array of squares and a set
  // output: will output the set the squares in the array all share
  // that is different from the claimingSet.
  private static Set findSharedSet( Vector<Square> squares, Set claimingSet) {
    Set row = squares.firstElement().whichSet()[0];
    Set col = squares.firstElement().whichSet()[1];
    Set box = squares.firstElement().whichSet()[2];
    int rowCount = 0;
    int colCount = 0;
    int boxCount = 0;
    for (int index = 0; index < squares.size(); index++) {
      if (squares.elementAt(index).whichSet()[0] == row) {
        rowCount++;
      }
      if (squares.elementAt(index).whichSet()[1] == col) {
        colCount++;
      }
      if (squares.elementAt(index).whichSet()[2] == box) {
        boxCount++;
      }
    }
    if (rowCount == squares.size() && row != claimingSet) {
      return row;
    }
    else if (colCount == squares.size() && col != claimingSet) {
      return col;
    }
    else if (boxCount == squares.size() && box != claimingSet) {
      return box;
    }
    else {
      return claimingSet;
    }
  }
  
  // function: setClaim
  // input: a set and a number
  // This will implement the helper functions above to actually do the 
  // strategy, it will be the part that is actually called and will 
  // return nothing but will call other functions to run.
  public static boolean setClaim(Set set, int checkNum) {
    /*if (set.setHas(checkNum) == true) {
      return false;
    }*/
    boolean changedSomething = false;
    if (shareSet(possSquares(set, checkNum), set) == true) {
      Set setCantUseNum = findSharedSet(possSquares(set, checkNum), set); 
      for (int index = 0; index < 9; index++) {
        if (set != setCantUseNum.getSquare(index).whichSet()[0] &&
            set != setCantUseNum.getSquare(index).whichSet()[1] &&
            set != setCantUseNum.getSquare(index).whichSet()[2]) {
          if (setCantUseNum.getSquare(index).isPossible(checkNum)) {
            changedSomething = true;
            setCantUseNum.getSquare(index).changePossibles(checkNum, set.returnName() 
                                                             + " claimed " + checkNum + " from square " 
                                                             + setCantUseNum.getSquare(index).name(), 2);
          }
        }
      }
    }
    return changedSomething;
            
  }
  
}
