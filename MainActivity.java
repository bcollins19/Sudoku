package com.bcSudokuMentor.sudokumentor;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.GridView;

public class MainActivity extends ActionBarActivity {


    public Button getDelPosButton() {
        return (Button) findViewById(R.id.deletePos);
    }


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Controller cont = new Controller();


		
		/*
		RelativeLayout gridview = (RelativeLayout) findViewById(R.id.puzzle);
		LayoutParams params = gridview.getLayoutParams();
		params.width *= .8;
		gridview.setLayoutParams(params); */
		
		GridView bottomPanel = (GridView) findViewById(R.id.bottomPanel);
		BottomPanel bottom = new BottomPanel((Context) this, cont);
		bottomPanel.setAdapter(bottom);
		
		
		PuzzleButtons stuff = new PuzzleButtons(this, cont, "000907000900000008030405020307040206000509000809020103070604030200000009000102000", bottom);
		
		bottom.finishInitialize(stuff);
		
		GridView set0 = (GridView) findViewById(R.id.Set0);
		set0.setAdapter(stuff.getSetAdapter(0));
		
		GridView set1 = (GridView) findViewById(R.id.Set1);
		set1.setAdapter(stuff.getSetAdapter(1));
		
		GridView set2 = (GridView) findViewById(R.id.Set2);
		set2.setAdapter(stuff.getSetAdapter(2));
		
		GridView set3 = (GridView) findViewById(R.id.Set3);
		set3.setAdapter(stuff.getSetAdapter(3));
		
		GridView set4 = (GridView) findViewById(R.id.Set4);
		set4.setAdapter(stuff.getSetAdapter(4));
		
		GridView set5 = (GridView) findViewById(R.id.Set5);
		set5.setAdapter(stuff.getSetAdapter(5));
		
		GridView set6 = (GridView) findViewById(R.id.Set6);
		set6.setAdapter(stuff.getSetAdapter(6));
		
		GridView set7 = (GridView) findViewById(R.id.Set7);
		set7.setAdapter(stuff.getSetAdapter(7));
		
		GridView set8 = (GridView) findViewById(R.id.Set8);
		set8.setAdapter(stuff.getSetAdapter(8));

		
		stuff.setBottomPanel(bottom);
		
		Button nextMoveButton = (Button) findViewById(R.id.nextMoveButton);
        nextMoveButton.setOnClickListener(new ButtonOnClickListener(cont, 0, (Context) this, bottom, this));
		
        Button hintButton = (Button) findViewById(R.id.hintButton);
        hintButton.setOnClickListener(new ButtonOnClickListener(cont, 1, (Context) this, bottom, this));
        
        Button possibleButton = (Button) findViewById(R.id.showPossiblesButton);
        possibleButton.setOnClickListener(new ButtonOnClickListener(cont, 2, (Context) this, bottom, this));
        
        Button delPos = (Button) findViewById(R.id.deletePos);
        delPos.setOnClickListener(new ButtonOnClickListener(cont, 3, (Context) this, bottom, this));
        delPos.setBackgroundColor(GlobalColor.SQUARE_COLOR);

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
