package com.bcSudokuMentor.sudokumentor;

//import android.app.AlertDialog;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyOnClickListener implements OnClickListener{
	int index;
	Controller cont;
	Context mContext;
	BottomPanel view;
	buttonAdapter puz;
	
	public MyOnClickListener(int index, Controller controller, Context context, BottomPanel textView, buttonAdapter puzzle) {
		this.index = index;
		this.cont = controller;
		mContext = context;
		view = textView;
		puz = puzzle;
	}
	
	@Override
	public void onClick(View v) {
		for (int i = 0; i < 81; i++) {
			if (cont.displayPuzzle.getSquare(i/9, i%9).value() == index+1) {
				/*TextView dead = new TextView(mContext);
				TextView square = puz.getView(i, dead, (ViewGroup) v.getParent());
				square.setBackgroundColor(Color.GREEN);
				puz.puzzle[i].setBackgroundColor(Color.GREEN); */
				puz.changeToGreen(i);
			}
			else {
				if (cont.puzButtons.getColor(i) != Color.YELLOW) {
					puz.changeToWhite(i);
				}
				//puz.changeToWhite(i);
				//puz.puzzle[i].setBackgroundColor(Color.WHITE);
			}
		}
		for (int i = 0; i < view.getCount(); i++) {
			TextView dead = new TextView(mContext);
			TextView square = view.getView(i, dead, (ViewGroup) v.getParent());
			square.setBackgroundColor(Color.WHITE);
			square.setText(Integer.toString(i+1));
			
		}
		cont.numberSet(Integer.parseInt((String) this.view.getItem(this.index)));
		v.setBackgroundColor(Color.GREEN);
		/*
		AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
		alertDialog.setTitle("Alert Dialog");
		alertDialog.setMessage((String) this.view.getItem(this.index));
		alertDialog.show(); 
		*/
	}
}
