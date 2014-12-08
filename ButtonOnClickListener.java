package com.bcSudokuMentor.sudokumentor;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class ButtonOnClickListener implements OnClickListener{
	Controller cont;
	Context mContext;
	//View view;
	boolean nextMove;
	
	public ButtonOnClickListener(Controller controller, boolean whichButton, Context m) {
		cont = controller;
		//view = v;
		nextMove = whichButton;
		mContext = m;
	}
	
	@Override
	public void onClick(View v) {
		Puzzle userPuz = cont.displayPuzzle;
		Puzzle solution = cont.solvedPuzzle;
		if (nextMove) {
			for (int i = 0; i < 81; i++) {
				String squareName = solution.solveOrderIndex(i).name();
				if (!userPuz.squareSolved(squareName)) {
					Square solSq = solution.solveOrderIndex(i);
					int[] posArr = solSq.getPosition();
					int pos = 9*posArr[0] + posArr[1];
					int sqVal = solSq.value();
					TextView panelBut = cont.panelButtons.getTextView(sqVal-1);
					panelBut.performClick();
					cont.puzButtons.changeToColor(pos, Color.YELLOW);
					int value = solution.solveOrderIndex(i).value();
					/*
					AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
					alertDialog.setTitle("Solved It!");
					alertDialog.setMessage(squareName + " is a " + Integer.toString(value));
					alertDialog.show();
					*/
					return;
				}
			}
		}
		
		// This means it is the hint button
		else {
			for (int i = 0; i < 81; i++) {
				String squareName = solution.solveOrderIndex(i).name();
				// Not solved so need to change color or just put a popup?
				if (!userPuz.squareSolved(squareName)) {
					Square solSq = solution.solveOrderIndex(i);
					int[] posArr = solSq.getPosition();
					int pos = 9*posArr[0] + posArr[1];
					cont.puzButtons.changeToColor(pos, Color.YELLOW);
					/*
					AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
					alertDialog.setTitle("Here you go!");
					alertDialog.setMessage(squareName + " can be solved next!");
					alertDialog.show();
					*/
					return;
				}
			}
			return;
		}
		
	}
}
