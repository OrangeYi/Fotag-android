package com.example.mvc;
import java.io.InputStream;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.util.*;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.res.Resources;
import java.io.IOException;

public class ImageView extends LinearLayout {


    public ImageModel Imodel;
    public Ratebar ratebar;

    public ImageView(ImageModel model, Context context) {
        super(context);
        Imodel = model;
        ratebar = new Ratebar(Imodel.rating, getContext());

        setGravity(Gravity.CENTER_HORIZONTAL);
        setOrientation(LinearLayout.VERTICAL);

        final android.widget.ImageView photo = new android.widget.ImageView(context);

        if(Imodel.url  == null){
            //photo.setImageResource(model.ID);
            photo.setImageBitmap(decodeSampledBitmapFromResource(getResources(), Imodel.ID, 60, 60));
        }
        else{
            new AsyncTask<Void, Void, Void>() {
                InputStream tempin = null;
                Bitmap bm = null;
                @Override
                protected Void doInBackground(Void... params) {
                    try {
                        tempin = new URL(Imodel.url).openStream();
                        bm = BitmapFactory.decodeStream(tempin);
                        Log.d("testurl", Imodel.url);
                    }catch (Exception e){}

                    return null;
                }

                @Override
                protected void onPostExecute(Void result) {
                    if (bm != null)
                        photo.setImageBitmap(bm);
                }
            }.execute();
        }
        photo.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                final Dialog imageDialog = new Dialog(context);
                LayoutInflater inflater = LayoutInflater.from(context);

                View layout = inflater.inflate(R.layout.view1, null, true);

                android.widget.ImageView image = (android.widget.ImageView) layout.findViewById(R.id.viewphoto);
                image.setImageBitmap(decodeSampledBitmapFromResource(getResources(), Imodel.ID, 250, 250));
                imageDialog.setContentView(layout);

                image.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        imageDialog.dismiss();
                    }
                });

                imageDialog.create();
                imageDialog.show();
            }
        });
        addView(photo);
        addView(ratebar);
    }








    //from android developer
    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    //from android developer
    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }







    private class Ratebar extends LinearLayout{

        private static final long serialVersionUID = 1L;
        //public int thisrate;
        TextView reset;
        public Ratebar(int rate, Context context) {
            super(context);
            reset = new TextView(getContext());
            reset.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    ratebar.update(0);
                    Imodel.rating = 0;
                    Imodel.Vmodel.change();
                }
            });
            reset.setText("\u21BA");
            reset.setTextSize(50);

            //JPanel panel = new JPanel();
            for(int i = 1; i <= 5; ++i){
                if(i<= rate){
                    addView(new Star(i,getContext(),"f"));
                }else{
                    addView(new Star(i,getContext(),"n"));
                }
            }
            addView(reset);
        }
        public void update(int rate){
            //thisrate = rate;
            removeAllViews();
            //JPanel panel = new JPanel();
            for(int i = 1; i <= 5; ++i){
                if(i<= rate){
                    addView(new Star(i,getContext(), "f"));
                }else{
                    addView(new Star(i,getContext(), "n"));
                }
            }
            addView(reset);
            //this.add(panel);
            //this.revalidate();
            //this.repaint();
        }
    }

    private class Star extends TextView{

        private static final long serialVersionUID = 1L;
        public int index;



        public Star(int i, Context context, String s) {
            super(context);
            //this.setOpaque(true);
            this.index = i;
            this.setTextSize(50);
            if(s == "f"){
                this.setText("\u2605");
            }
            else{
                this.setText("\u2606");
            }
            //this.addMouseListener(new MouseAdapter() {
            //    @Override
            //    public void mousePressed(MouseEvent e) {
            //        Model.Rating = index;
            //        ratebar.update(Model.Rating);
            //        Model.Model.change();
            //    }
            //});
            setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Imodel.rating = i;
                    ratebar.update(Imodel.rating);
                    Imodel.Vmodel.change();
                }
            });
        }
    }


    public void notifyV(){
        this.ratebar.update(this.Imodel.rating);
    }
}

