package com.bcSudokuMentor.sudokumentor;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;

public class PuzzleOnClickListener implements OnClickListener{
	Context mContext;
	Controller cont;
	int index;
	PuzzleButtons view;
	BottomPanel bottom;
	
	public PuzzleOnClickListener(int position, Controller controller, Context context, PuzzleButtons puzzleButtons, BottomPanel bot) {
		cont = controller;
		mContext = context;
		index = position;
		view = puzzleButtons;
		bottom = bot;
	}
	
	@Override
	public void onClick(View v) {
		if (cont.delPos == false) {
			if (cont.solvedPuzzle.getSquare(index/9, index%9).value() == cont.numberSet) {
				cont.displayPuzzle.getSquare(index/9, index%9).solvedWith(cont.numberSet, "Because", 1);
				view.setText(index, cont.numberSet);
				view.setColor(index, GlobalColor.HIGHLIGHT_COLOR);
				bottom.doClick(cont.numberSet-1);
				if (cont.displayPuzzle.puzzleSolved(cont.solvedPuzzle) == true) {
					AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
					alertDialog.setTitle("Congrats!");
					alertDialog.setMessage("You solved the Sudoku! Good work!");
					alertDialog.show();
				}
			}
			
			else {
				AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
				alertDialog.setTitle("Sorry!");
				alertDialog.setMessage("That is not a legal move!");
				alertDialog.show();
			}
		}
		else if (cont.delPos == true) {
			if (cont.solvedPuzzle.getSquare(index/9, index%9).value() != cont.numberSet) {
				cont.displayPuzzle.getSquare(index/9, index%9).changePossibles(cont.numberSet, "Because", 1);
				bottom.doClick(cont.numberSet-1);
			}
			else {
				AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
				alertDialog.setTitle("Sorry!");
				alertDialog.setMessage("That is a possibility!");
				alertDialog.show();
			}
			
		}
	}
}
