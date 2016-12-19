package com.example.mvc;

import java.util.Observable;
import java.util.Observer;

import android.content.Context;
import android.util.*;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class View1 extends LinearLayout implements Observer {
	
	private Model model;
	private Button button;

	public View1(Context context, Model m) {
		super(context);

	    Log.d("MVC", "View1 constructor");
		
		// get the xml description of the view and "inflate" it
		// into the display (kind of like rendering it)
		//View.inflate(context, R.layout.view1, this);

		// save the model reference
		model = m;
		// add this view to model's list of observers
		model.addObserver(this);

		// get a reference to widgets to manipulate on update
		//button = (Button) findViewById(R.id.view1_button);
		
		// create a controller for the button
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// do this each time the button is clicked
				model.incrementCounter();
			}
		});
		
	}

	// the model call this to update the view
	public void update(Observable observable, Object data) {
	    Log.d("MVC", "update View1");

		// update button text with click count
	    // (convert to string, or else Android uses int as resource id!)
		button.setText(String.valueOf(model.getCounterValue()));
	}
}
