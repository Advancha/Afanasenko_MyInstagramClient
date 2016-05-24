package com.example.afanasenko.mypictures;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Media extends ListActivity {

    public CustomAdapter adapter;
    public ArrayList<String> userLinks=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);

        Intent intent=getIntent();
        ArrayList<String> userMedia=intent.getStringArrayListExtra("USER_MEDIA");
        userLinks = intent.getStringArrayListExtra("USER_LINKS");

        List<String> userMediaList=new ArrayList<String>();

        for (int i=0; i<userMedia.size(); i++){
            userMediaList.add(userMedia.get(i));
        }


        //ImageView imageView = (ImageView) findViewById(R.id.imgTest);
        //new ImageLoader(Media.this).DisplayImage(urls.get(0).toString(), imageView);

        adapter=new CustomAdapter(this, userMediaList);
        setListAdapter(adapter);



    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(userLinks.get(position)));
        startActivity(browserIntent);
    }
}
