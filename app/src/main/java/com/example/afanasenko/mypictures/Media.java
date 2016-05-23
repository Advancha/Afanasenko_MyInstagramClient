package com.example.afanasenko.mypictures;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Media extends ListActivity {

    private CustomAdapter adapter;
    private ListView listView;

    List<String> urls = new ArrayList<>(Arrays.asList(
            "https://pp.vk.me/c543103/v543103825/10577/VPkYJHG7SZg.jpg",
            "https://pp.vk.me/c543103/v543103825/1057f/Dm51PuuIaME.jpg",
            "https://pp.vk.me/c543103/v543103825/1058d/4KqzTGQvAag.jpg",
            "https://pp.vk.me/c543103/v543103825/10595/mk_2nOIgB4E.jpg",
            "https://pp.vk.me/c543103/v543103825/1059d/-VtpuADRZU8.jpg",
            "https://pp.vk.me/c543103/v543103825/105a5/wP7SqKI2Spw.jpg",
            "https://pp.vk.me/c543103/v543103825/105ad/_0ZtJrxbrbI.jpg",
            "https://pp.vk.me/c543103/v543103825/105b5/RqFKAzdee3A.jpg"
    ));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);

        Intent intent=getIntent();
        List<String> userMedia=intent.getStringArrayListExtra("USER_MEDIA");

        adapter=new CustomAdapter(this, userMedia);
        setListAdapter(adapter);

    }
}
