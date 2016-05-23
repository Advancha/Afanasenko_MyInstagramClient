package com.example.afanasenko.mypictures;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Afanasenko on 22.05.2016.
 */
public class ImageLoader {
    private Context context;

    public ImageLoader(Context context) {
        this.context = context;
    }

    public void DisplayImage(String image_url, ImageView imageView){
        Picasso.with(context)
                .load(image_url)
                .into(imageView);
    }
}
