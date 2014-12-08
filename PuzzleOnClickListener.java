package com.bcSudokuMentor.sudokumentor;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class PuzzleOnClickListener implements OnClickListener{
	Context mContext;
	Controller cont;
	int index;
	buttonAdapter view;
	
	public PuzzleOnClickListener( int position, Controller controller, Context context, buttonAdapter imageView) {
		cont = controller;
		mContext = context;
		index = position;
		view = imageView;
	}
	
	@Override
	public void onClick(View v) {
		if (cont.solvedPuzzle.getSquare(index/9, index%9).value() == cont.numberSet) {
			cont.displayPuzzle.getSquare(index/9, index%9).solvedWith(cont.numberSet, "Because", 1);
			TextView dead = new TextView(mContext);
			TextView square = view.getView(index, dead, (ViewGroup) v.getParent());
			square.setText(Integer.toString(cont.numberSet));
			square.setBackgroundColor(Color.GREEN);
		}
		
		else {
			AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
			alertDialog.setTitle("Sorry!");
			alertDialog.setMessage("That is not a legal move");
			alertDialog.show();
		}
		
	}
}
