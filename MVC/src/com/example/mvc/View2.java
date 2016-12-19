package com.example.mvc;

import java.util.Observable;
import java.util.Observer;

import android.content.Context;
import android.util.*;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class View2 extends LinearLayout implements Observer {
	
	private Model model;
	private TextView textview;

	public View2(Context context, Model m) {
		super(context);
		
	    Log.d("MVC", "View2 constructor");

		// get the xml description of the view and "inflate" it
		// into the display (kind of like rendering it)
		View.inflate(context, R.layout.viewlist, this);

		// save the model reference
		model = m;
		// add this view to model's list of observers
		model.addObserver(this);

		// get a reference to widgets to manipulate on update
		//textview = (TextView)findViewById(R.id.view2_textview);
		
		// this is a view only view, no controller 
		// (unlike the mvc java swing example)

	}

	// the model call this to update the view
	public void update(Observable observable, Object data) {
	    Log.d("MVC", "update View2");
	    
	    int n = model.getCounterValue();
	    
	    StringBuilder s = new StringBuilder(n);
	    for (int i = 0; i < n; i++) {
	    	s.append("x");
	    }

		// update button text with click count
	    // (convert to string, or else Android uses int as resource id!)
	    textview.setText(s);
	}
}
