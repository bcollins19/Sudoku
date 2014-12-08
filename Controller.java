package com.bcSudokuMentor.sudokumentor;

//import java.util.Scanner;
//import java.util.Vector;


public class Controller {
	
	Puzzle solvedPuzzle;
	Puzzle displayPuzzle;
	buttonAdapter puzButtons;
	BottomPanel panelButtons;
	int numberSet;
	
	public Controller(){
		numberSet = 1;
		String puzNumbers = "000907000900000008030405020307040206000509000809020103070604030200000009000102000";
		int puzImportanceToShow = 1;
		solvedPuzzle = new Puzzle(this);
		displayPuzzle = new Puzzle(this);
		
		this.givens(puzNumbers, solvedPuzzle);
		this.givens(puzNumbers, displayPuzzle);
		
		solvedPuzzle.setImportance(puzImportanceToShow);
		solvedPuzzle.solvePuzzle();
		
	}
	
	public void numberSet(int number) {
		this.numberSet = number;
	}
	
	
	public static void program(Controller cont) {
	    Puzzle puzzle = new Puzzle(cont);
	    Puzzle displayPuzzle = new Puzzle(cont);
	    
	    //Scanner scanner = new Scanner (System.in);
	    //System.out.println("Enter the numbers in a long string with 0's as blanks: ");
		//String numbers = scanner.next();
	    String puzNumbers = "000907000900000008030405020307040206000509000809020103070604030200000009000102000";
	    int puzImportanceToShow = 1;
		cont.givens(puzNumbers, puzzle);
		cont.givens(puzNumbers, displayPuzzle);
	    //Scanner importanceToShow = new Scanner (System.in);
	    //System.out.println("What level of technique difficulty should be shown? 0 being everything 3 being only the toughest technique. ");
	    //puzzle.setImportance(Integer.parseInt(importanceToShow.next()));
	    //puzzle.getController().printPuzzle(puzzle);
		puzzle.setImportance(puzImportanceToShow);
	    puzzle.solvePuzzle();
	    //puzzle.getController().printPuzzle(puzzle);
	    //System.out.println("");
	    //System.out.println("");
	    //Puzzle userPuzzle = new Puzzle(cont);
	    
	    /*
	     * This is just old code used before I had the Sudoku app built
	    while (!userPuzzle.puzzleSolved(puzzle)) {
	      //Scanner userInput = new Scanner (System.in);
	      //System.out.println("What do you want to do next?");
	      //String nextJob = userInput.next();
	      //if (nextJob.equals("hint")) {
	        //System.out.println(puzzle.nextHint());
	      //}
	      //else if (nextJob.equals("finish")) {
	        //puzzle.getController().printPuzzle(puzzle);
	        break;
	      }
	      else if (nextJob.length() == 81) {
	        cont.givens(nextJob, userPuzzle);
	        Vector<Square> wrongSquares = userPuzzle.puzzleOnTrack(puzzle);
	        if (wrongSquares != null) {
	          for (int i = 0; i < wrongSquares.size(); i++) {
	            System.out.println(wrongSquares.elementAt(i).name() + " is not matching our solution");
	          }
	        }
	      }
	      else {  
	       System.out.println("We did not recognize your command.");
	      }
	    }
	    */
	}
	
	
	public Puzzle givens(String numbers, Puzzle puzzle) {
		/*
	    if (numbers.length() != 81) {
	      Scanner scanner = new Scanner (System.in);
	      System.out.println("You must enter a string of length 81");
	      String newNumbers = scanner.next();
	      return givens(newNumbers, puzzle);
	    } 
	    else {
	      for (int i = 0; i < 81; i++) {
	    	  
	    	  if (numbers.charAt(i) < 48 || numbers.charAt(i) >= 58) {
	    		  Scanner scanner = new Scanner (System.in);
	    	      System.out.println("Sorry that is not only numbers");
	    	      String newNumbers = scanner.next();
	    	      return givens(newNumbers, puzzle);
	          }
	      } */
	      int row = 0;
	      int col = 0;
	      for (int index = 0; index < 81; index++) {
	        row = index / 9;
	        col = index % 9;
	        
	        // 48 in ascii is 0
	        if (numbers.charAt(index) > 48 && numbers.charAt(index) < 58) {
	          // I need to offset by 48 because there is where 0 starts
	          puzzle.getSquare(row, col).solvedWith(numbers.charAt(index)-48, "given", 0);
	        }
	      }
	      /*
	      if (!puzzle.validPuzzle()) {
	        System.out.println("You did not give me a valid Sudoku");
	        puzzle.getController().printPuzzle(puzzle);
	        Scanner scannerNew = new Scanner (System.in);
	        System.out.println("Please give me a valid Sudoku");
	        String newNumbers = scannerNew.next();
	        puzzle = givens(newNumbers, puzzle);
	      } */
	      return puzzle;
	    //}
	  }
	
	public void printReasons(Puzzle puzzle) {
	    for (int i = puzzle.getHintNum(); i < puzzle.getReasonSize(); i++) {
	    	if (puzzle.getImportanceAtIndex(i) >= puzzle.getImportanceWanted()) {
	    		System.out.println(puzzle.getReasonAtIndex(i));
	    		puzzle.incrementHintNum();
	    	}
	    	else {
	    		puzzle.incrementHintNum();
	    	}
	    }
	  }
	
	public void printPuzzle(Puzzle puzzle) {
	    for (int set=0; set < 9; set++) {
	        printSquareValues(puzzle.getSet(set));
	        if (set%3 == 2) {
	    	    System.out.println();
	        }
	    }
	}
	
	public void printSquareValues(Set set) {
		for (int i=0; i <= 8; i++) {
			System.out.print(set.getSquareAtIndex(i).value() + " ");
		    	if (i%3 == 2) {
		    		System.out.print(" ");
		        }
		}
		System.out.println(" ");
	}
	
	/*public static void main(String[] args) {
		Controller cont = new Controller();
		program(cont);
	}*/
}