package com.example.mvc;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import android.util.Log;
import android.view.LayoutInflater;

/**
 * Created by Yi on 16/4/1.
 */
public class ViewList extends LinearLayout implements Observer {
    public static ViewModel Vmodel;
    private ArrayList<ImageView> imageViews = new ArrayList<ImageView>();

    public ViewList(Context context, ViewModel m) {
        super(context);
        //Log.d("MVC", "View2 constructor");
        LayoutInflater.from(context).inflate(R.layout.viewlist, this, true);
        Vmodel = m;
        Vmodel.addObserver(this);
    }


    @Override
    public void update(Observable observable, Object data) {
        Log.d("ViewList","test");
        imageViews = new ArrayList<ImageView>();
        TableLayout layout = (TableLayout)findViewById(R.id.layout);
        layout.removeAllViews();


        for(int i = 0; i < this.imageViews.size(); ++i){
            this.imageViews.get(i).notifyV();
        }

        if(this.imageViews.size() != this.Vmodel.imageModels.size()){
            this.imageViews = new ArrayList<ImageView>();
            for(int i = 0; i < Vmodel.imageModels.size(); ++i){
                this.imageViews.add(new ImageView(this.Vmodel.imageModels.get(i),getContext()));
            }
        }
        for(int i =0 ; i < Vmodel.imageModels.size(); ++i){
            if(Vmodel.imageModels.get(i).rating >= Vmodel.filter ) {

                imageViews.add(new ImageView(Vmodel.imageModels.get(i), getContext()));
                layout.addView((imageViews.get(imageViews.size() - 1)));

            }
        }
    }
}

