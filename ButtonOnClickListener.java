package com.bcSudokuMentor.sudokumentor;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ButtonOnClickListener implements OnClickListener{
	Controller cont;
	Context mContext;
	int buttonType;
	BottomPanel bottom;
    MainActivity activity;
	
	public ButtonOnClickListener(Controller controller, int whichButton, Context m, BottomPanel bot, MainActivity act) {
		cont = controller;
		buttonType = whichButton;
		mContext = m;
		bottom = bot;
        activity = act;
	}
	
	@Override
	public void onClick(View v) {
		Puzzle userPuz = cont.displayPuzzle;
		Puzzle solution = cont.solvedPuzzle;
		if (buttonType == 0) {
			for (int i = 0; i < 81; i++) {
				String squareName = solution.solveOrderIndex(i).name();
				if (!userPuz.squareSolved(squareName)) {
					Square solSq = solution.solveOrderIndex(i);
					int[] posArr = solSq.getPosition();
					int pos = 9*posArr[0] + posArr[1];
					int sqVal = solSq.value();
					TextView panelBut = cont.panelButtons.getTextView(sqVal-1);
					panelBut.performClick();
					cont.puzButtons.setColor(pos, GlobalColor.SHOW_COLOR);
					int value = solution.solveOrderIndex(i).value();
					return;
				}
			}
		}
		
		// This means it is the hint button
		else if (buttonType == 1){
			for (int i = 0; i < 81; i++) {
				String squareName = solution.solveOrderIndex(i).name();
				// Not solved so need to change color or just put a popup?
				if (!userPuz.squareSolved(squareName)) {
					Square solSq = solution.solveOrderIndex(i);
					int[] posArr = solSq.getPosition();
					int pos = 9*posArr[0] + posArr[1];
					cont.puzButtons.setColor(pos, GlobalColor.SHOW_COLOR);
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
		// possibles button
		else if (buttonType == 2) {
			if (cont.showPos == false) {
				cont.showPos = true;
			}
			else {
				cont.showPos = false;
                if (cont.delPos == true) {
                    Button delPos = activity.getDelPosButton();
                    delPos.performClick();
                }
			}
			int buttonPush = cont.numberSet;
			bottom.doClick(buttonPush-1);
		}
		// delete possible button
		else if (buttonType == 3) {
			if (cont.delPos == false) {
				cont.delPos = true;
				cont.showPos = true;
                v.setBackgroundColor(GlobalColor.DELETE_COLOR);
				bottom.doClick(cont.numberSet-1);
				AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
			}
			else {
				cont.delPos = false;
                v.setBackgroundColor(GlobalColor.SQUARE_COLOR);
				AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
			}
			
		}
		
	}
}
