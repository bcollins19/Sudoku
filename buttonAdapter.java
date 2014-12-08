package com.bcSudokuMentor.sudokumentor;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class buttonAdapter extends BaseAdapter{
	private Context mContext;
	private String numbers = "";
	private Controller cont;
	private TextView[] puzzle = new TextView[81];
	
	public buttonAdapter(Context c, Controller controller) {
		mContext = c;
		numbers += "000907000900000008030405020307040206000509000809020103070604030200000009000102000";
		cont = controller;
		cont.puzButtons = this;
		for (int i = 0; i < 81; i++) {
			puzzle[i] = new TextView(mContext);
			android.widget.AbsListView.LayoutParams param = new android.widget.AbsListView.LayoutParams(70, 86);
			puzzle[i].setLayoutParams(param);
			if (!numbers.substring(i, i+1).equals("0")) {
				puzzle[i].setText(numbers.substring(i, i+1));
			}
			puzzle[i].setPadding(20,  20,  20, 20);
			puzzle[i].setBackgroundColor(Color.WHITE);
			puzzle[i].setTextColor(Color.BLACK);
	        puzzle[i].setGravity(Gravity.CENTER);
	        puzzle[i].setOnClickListener(new PuzzleOnClickListener(i, cont, mContext, this));
		}
	}
	
	public Controller getController() {
		return cont;
	}
	
	public void changeToGreen(int pos) {
		if (pos < 81) {
			puzzle[pos].setBackgroundColor(Color.GREEN);
		}
	}
	
	public void changeToColor(int pos, int color) {
		if (pos < 81) {
			puzzle[pos].setBackgroundColor(color);
		}
	}
	
	public int getColor(int pos) {
		ColorDrawable i = (ColorDrawable) puzzle[pos].getBackground();
		return i.getColor();
	}
	
	public void changeToWhite(int pos) {
		puzzle[pos].setBackgroundColor(Color.WHITE);
	}
	
	public int getCount() {
		return 81;
	}
	
	public Object getItem(int position) {
		return cont.displayPuzzle.getSquare(position/9, position%9).value();
		//return numbers.substring(position, position+1);
	}
	
	public long getItemId(int position) {
		return position;
	}
	
	public TextView getView(int position, View convertView, ViewGroup parent) {
		/*
		TextView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new TextView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams( 60,  80));
            //imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (TextView) convertView;
        }

        if (numbers.substring(position, position+1).equals("0")) {
        	imageView.setText(" ");
        }
        else {
        	imageView.setText(numbers.substring(position, position+1));
        }
        imageView.setBackgroundColor(Color.WHITE);
        imageView.setTextColor(Color.BLACK);
        imageView.setGravity(Gravity.CENTER);
        //imageView.setWidth(10);
        imageView.setOnClickListener(new PuzzleOnClickListener(position, cont, mContext, this));
        //return imageView; */
        puzzle[position].setOnClickListener(new PuzzleOnClickListener(position, cont, mContext, this));
        return puzzle[position];
		
	}
	
	
	
}
