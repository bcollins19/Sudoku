package com.bcSudokuMentor.sudokumentor;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.GridView;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Controller cont = new Controller();
		
		
		GridView gridview = (GridView) findViewById(R.id.puzzle);
		LayoutParams params = gridview.getLayoutParams();
		params.width *= .8;
		gridview.setLayoutParams(params);
		buttonAdapter stuff = new buttonAdapter(this, cont);
		gridview.setAdapter(stuff);
		
		/*
		Point size = new Point();
		getWindowManager().getDefaultDisplay().getSize(size);
		int screenWidth = size.x;
		int screenHeight = size.y;
		*/
		
		GridView bottomPanel = (GridView) findViewById(R.id.bottomPanel);
		BottomPanel bottom = new BottomPanel((Context) this, cont, stuff);
		bottomPanel.setAdapter(bottom);
		
		Button nextMoveButton = (Button) findViewById(R.id.nextMoveButton);
        nextMoveButton.setOnClickListener(new ButtonOnClickListener(cont, true, (Context) this));
		
        Button hintButton = (Button) findViewById(R.id.hintButton);
        hintButton.setOnClickListener(new ButtonOnClickListener(cont, false, (Context) this));
        
        bottom.doClick(0);
		
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
