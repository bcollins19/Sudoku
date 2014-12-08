package com.bcSudokuMentor.sudokumentor;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.TextView;





public class BottomPanel extends BaseAdapter{
	private Context mContext;
	private String numbers = "";
	private Controller cont;
	private buttonAdapter puz;
	
	private TextView[] views = new TextView[9];
	
	public BottomPanel(Context c, Controller controller, buttonAdapter puzzle) {
		mContext = c;
		numbers += "123456789";
		cont = controller;
		cont.panelButtons = this;
		for (int i = 0; i < 9; i++) {
			views[i] = new TextView(mContext);
			//LayoutParams params = views[i].getLayoutParams();
			//params.height = 10;
			views[i].setLayoutParams(new android.widget.AbsListView.LayoutParams(50, 50));
			views[i].setText(numbers.substring(i, i+1));
			views[i].setBackgroundColor(Color.WHITE);
			views[i].setTextColor(Color.BLACK);
	        views[i].setGravity(Gravity.CENTER);
	        views[i].setOnClickListener(new MyOnClickListener(i, cont, mContext, this, puzzle));
		}
		if (puzzle == null) {
			AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
			alertDialog.setTitle("Alert Dialog");
			alertDialog.setMessage("shit");
			alertDialog.show(); 
		}
		puz = puzzle;
		
	}
	
	public Controller getController() {
		return cont;
	}
	
	public int getCount() {
		return 9;
	}
	
	public Object getItem(int position) {
		return numbers.substring(position, position+1);
	}
	
	public long getItemId(int position) {
		return position;
	}
	
	public TextView getTextView(int position) {
		return views[position];
	}
	
	public buttonAdapter getPuzzleButtons() {
		return puz;
	}
	
	public TextView getView(int position, View convertView, ViewGroup parent) {
		/*TextView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new TextView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams( 40,  40));
            //imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (TextView) convertView;
        }
        
        imageView.setText(numbers.substring(position, position+1));
        imageView.setBackgroundColor(Color.WHITE);
        imageView.setTextColor(Color.BLACK);
        imageView.setGravity(Gravity.CENTER);
        //imageView.setWidth(10);
        imageView.setOnClickListener(new MyOnClickListener(position, cont, mContext, this));
        //return imageView;*/
        
        
        return views[position];
		
	}
	
	public void doClick(int position) {
		views[position].performClick();
	}
}
