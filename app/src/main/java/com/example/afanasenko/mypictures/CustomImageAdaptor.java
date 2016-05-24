package com.example.afanasenko.mypictures;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Afanasenko on 23.05.2016.
 */
public class CustomImageAdaptor extends BaseAdapter {
    private Context context;
    private List<String> items;

    public CustomImageAdaptor(Context context, List<String> items) {
        this.context = context;
        this.items = items;

    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
       /* ViewHolder viewHolder;

        if (view == null) {
            view = View.inflate(context,R.layout.img_view,null);
            viewHolder = new ViewHolder();
            viewHolder.image =(ImageView) view.findViewById(R.id.imgView);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
*/
        view = View.inflate(context,R.layout.img_view,null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imgView);

        Picasso.with(context).load(items.get(position)).into(imageView);

        return imageView;

    }

    static class ViewHolder {
        public ImageView image;

    }
}
