package com.example.mvc;


import android.graphics.Path;
import android.util.Log;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
//import java.util.Observer;
import  android.view.View;

/**
 * Created by Yi on 16/4/1.
 */
public class ViewModel extends Observable{

    public ArrayList<ImageModel> imageModels = new ArrayList<ImageModel>();
    public int filter;
    public MainActivity mainActivity = null;

    public ViewModel(MainActivity activity) {
        filter = 0;
        mainActivity = activity;
    }

    public void loadImages() {
        imageModels = new ArrayList<ImageModel>();
        imageModels.add(new ImageModel(this, R.drawable.a));
        imageModels.add(new ImageModel(this, R.drawable.b));
        imageModels.add(new ImageModel(this, R.drawable.c));
        imageModels.add(new ImageModel(this, R.drawable.d));
        imageModels.add(new ImageModel(this, R.drawable.e));
        imageModels.add(new ImageModel(this, R.drawable.f));
        imageModels.add(new ImageModel(this, R.drawable.g));
        imageModels.add(new ImageModel(this, R.drawable.h));
        imageModels.add(new ImageModel(this, R.drawable.gif));
        imageModels.add(new ImageModel(this, R.drawable.list));
        change();
    }

    public void change() {
        super.notifyObservers();
        super.setChanged();
    }


    @Override
    public void addObserver(Observer observer) {
        Log.d("MVC", "Model: Observer added");
        super.addObserver(observer);
    }
}
