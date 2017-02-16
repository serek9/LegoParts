package com.example.dam.legoparts;


import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Callback;

public class Picasso {
    public static void imageFromURL(Context context, String url, ImageView v){
        com.squareup.picasso.Picasso.with(context).load(url).placeholder(R.mipmap.ic_launcher).into(v, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {

            }
        });
    }
}