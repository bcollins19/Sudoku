package com.bcSudokuMentor.sudokumentor;


import java.util.Arrays;
import java.util.Vector;

class NakedNumbers{
  
  public static boolean nakedNumbers(Set set, int number) {
    Vector<Square> possibleSquares = new Vector<Square>(0);
    boolean changedSomething = false;
    possibleSquares = squaresWithNumPoss(set, number);
    for (int i = 0; i < possibleSquares.size(); i++) {
      Vector<Square> squaresMatchingPossibilities = new Vector<Square>(0);
      for (int j = 0; j < possibleSquares.size(); j++) {
        if (Arrays.equals(possibleSquares.elementAt(i).possibles(), possibleSquares.elementAt(j).possibles())) {
          squaresMatchingPossibilities.addElement(possibleSquares.elementAt(j));
        }
      }
      if (squaresMatchingPossibilities.size() == number) {
        Vector<Integer> possibilities = new Vector<Integer>(0);
        for (int index = 1; index < 10; index++) {
          if (squaresMatchingPossibilities.elementAt(0).isPossible(index)) {
            possibilities.addElement(index);
          }
        }
        if (changedSomething == false) {
          changedSomething = nClaim(set, squaresMatchingPossibilities, possibilities);
        }
      }
    }
    return changedSomething;
  }
  
  public static boolean nClaim(Set set, Vector<Square> nakedSquares, Vector<Integer> claimed) {
    boolean result = false;
    for (int square = 0; square < 9; square++) {
      if (in(nakedSquares, set.getSquare(square)) == false) {
        for (int index = 0; index < claimed.size(); index++) {
          if (set.getSquare(square).isPossible(claimed.elementAt(index))) {
            result = true;
            set.getSquare(square).changePossibles(claimed.elementAt(index), set.getSquare(square).name() + 
                                                  " can not be a " + claimed.elementAt(index) + 
                                                  " because it is already claimed in " + set.returnName() +
                                                  "  by " + nakedSquares.elementAt(0).name() + " and " + 
                                                  nakedSquares.elementAt(1).name(), 3);
          }
        }
      }
    }
    return result;
  }
  
  private static boolean in(Vector<Square> setOfSquares, Square square) {
    for (int i = 0; i < setOfSquares.size(); i++) {
      if (square == setOfSquares.elementAt(i)) {
        return true;
      }
    }
    return false;
  }
  
  private static Vector<Square> squaresWithNumPoss(Set set, int numPoss) {
    Vector<Square> result = new Vector<Square>(0);
    for (int square = 0; square < 9; square++) {
      int possCount = 0;
      for (int checkNum = 1; checkNum < 10; checkNum++) {
        if (set.getSquare(square).isPossible(checkNum)) {
          possCount++;
        }
      }
      if (possCount == numPoss) {
        result.addElement(set.getSquare(square));
      }
    }
    return result;
  }
  
}
    
    
    
    
    
