package com.example.mvc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by Yi on 16/4/1.
 */
public class ImageModel implements Serializable {
    public int ID;
    public int rating;
    public String url;
    transient public ViewModel Vmodel;

    public ImageModel(ViewModel model, int id) {
        ID = id;
        Vmodel = model;
        rating = 0;
        url = null;
    }

    //online image
    public ImageModel(ViewModel model, String url) {
        ID = -1;
        Vmodel = model;
        rating = 0;
        this.url = url;
    }

    public void notifyView(){

    }
}
