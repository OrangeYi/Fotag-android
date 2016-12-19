package com.example.mvc;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.EditText;
import android.app.AlertDialog;
import android.content.DialogInterface;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
public class MainActivity extends Activity {
	//Model model;
	private View menu;
    public ViewModel Model;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        System.out.print("MAIN");
		super.onCreate(savedInstanceState);


        Model = new ViewModel(this);
        ViewList.Vmodel = Model;

		// load the base UI (has places for the views)
		setContentView(R.layout.mainactivity);

		// Setup model
		//model = new Model();


        LayoutInflater Inflater = LayoutInflater.from(this);
        menu = Inflater.inflate(R.layout.actionlayout, null);
        android.app.ActionBar myActionBar = getActionBar();
        myActionBar.setDisplayShowHomeEnabled(false);
        myActionBar.setDisplayShowTitleEnabled(false);
        myActionBar.setCustomView(menu);
        myActionBar.setDisplayShowCustomEnabled(true);
        TextView titleTextView = (TextView) menu.findViewById(R.id.title_text);

        ImageView view;
        TextView star1;
        TextView star2;
        TextView star3;
        TextView star4;
        TextView star5;
        TextView reset;
        star1 = (TextView) menu.findViewById(R.id.one);
        star2 = (TextView) menu.findViewById(R.id.two);
        star3 = (TextView) menu.findViewById(R.id.three);
        star4 = (TextView) menu.findViewById(R.id.four);
        star5 = (TextView) menu.findViewById(R.id.five);
        reset = (TextView) menu.findViewById(R.id.reset);

        star1.setText("\u2606");
        star1.setTextSize(25);
        star2.setText("\u2606");
        star2.setTextSize(25);
        star3.setText("\u2606");
        star3.setTextSize(25);
        star4.setText("\u2606");
        star4.setTextSize(25);
        star5.setText("\u2606");
        star5.setTextSize(25);
        reset.setText("\u21BA");
        reset.setTextSize(25);
        titleTextView.setText("Fotag Mobile");

        view = (ImageView) menu.findViewById(R.id.clean);
        view.setImageResource(R.mipmap.clean);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MAIN", "HI");
                Model.imageModels = new ArrayList<ImageModel>();
                Model.change();
            }
        });

        view = (ImageView) menu.findViewById(R.id.search);
        view.setImageResource(R.mipmap.search);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText url = new EditText(v.getContext());
                final View tempv = v;
                new AlertDialog.Builder(v.getContext())
                        .setTitle("URI")
                        //.setMessage("URI")
                        .setView(url)
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                String url1 = url.getText().toString();
                                Log.d("main",url1);
                                url(url1, v);
                                Model.change();

                                //Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(url1).getContent());
                                //Model.imageModels.add(new ImageModel(Model, url1));

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }
                        })
                        .show();

            }
        });

        view = (ImageView) menu.findViewById(R.id.load);
        view.setImageResource(R.mipmap.load);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MAIN", "HI");
                Model.loadImages();

                //System.out.print("MAIN");
            }
        });


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                star1.setText("\u2606");
                star2.setText("\u2606");
                star3.setText("\u2606");
                star4.setText("\u2606");
                star5.setText("\u2606");
                Model.filter = 0;
                Model.change();
            }
        });
        star1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                star1.setText("\u2605");
                star2.setText("\u2606");
                star3.setText("\u2606");
                star4.setText("\u2606");
                star5.setText("\u2606");
                Model.filter = 1;
                Model.change();
            }
        });
        star2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                star1.setText("\u2605");
                star2.setText("\u2605");
                star3.setText("\u2606");
                star4.setText("\u2606");
                star5.setText("\u2606");
                Model.filter = 2;
                Model.change();
            }
        });
        star3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                star1.setText("\u2605");
                star2.setText("\u2605");
                star3.setText("\u2605");
                star4.setText("\u2606");
                star5.setText("\u2606");
                Model.filter = 3;
                Model.change();
            }
        });
        star4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                star1.setText("\u2605");
                star2.setText("\u2605");
                star3.setText("\u2605");
                star4.setText("\u2605");
                star5.setText("\u2606");
                Model.filter = 4;
                Model.change();
            }
        });
        star5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                star1.setText("\u2605");
                star2.setText("\u2605");
                star3.setText("\u2605");
                star4.setText("\u2605");
                star5.setText("\u2605");
                Model.filter = 5;
                Model.change();
            }
        });


    }

    public void url(String url, View v) {
        //final String url1 = url;
        final View v2 = v;
        final Bitmap[] bit = new Bitmap[1];
        Model.imageModels.add(new ImageModel(Model, url));
        //new AsyncTask<Void, Void, Void>() {
        //    @Override
        //    protected Void doInBackground(Void... params) {
        //        bit[0] = null;
        //        try {
        //            InputStream in = new URL(url).openStream();
//
        //            bit[0] = BitmapFactory.decodeStream(in);
        //        } catch (Exception e) {
        //        }
        //        return null;
        //    }
//
        //    @Override
        //    protected void onPostExecute(Void result) {
        //        //if (bit[0] == null) {
        //        //    new AlertDialog.Builder(v2.getContext())
        //        //            .setTitle("Error")
        //        //            .setMessage("Not a valid url!")
        //        //            .setPositiveButton("Ok", null)
        //        //            .show();
        //        //} else {
        //            Model.imageModels.add(new ImageModel(Model, url));
        //            //Model.change();
        //        //}
        //    }
        //}.execute();
    }
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// can only get widgets by id in onPostCreate for activity xml res

		// create the views and add them to the main activity

        ViewList viewlist = new ViewList(this, Model);
        ViewGroup v2 = (ViewGroup) findViewById(R.id.mainactivity_2);
        v2.addView(viewlist);
		// initialize views
		//model.setChanged();
		//model.notifyObservers();
	}

	// save and restore state (need to do this to support orientation change)

	@Override
	protected void onSaveInstanceState(Bundle outState) {

		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);
    }

}
