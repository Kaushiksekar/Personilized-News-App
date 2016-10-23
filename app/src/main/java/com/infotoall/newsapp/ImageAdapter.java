package com.infotoall.newsapp;

import android.content.Context;
import android.graphics.PorterDuff;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by kaushiksekar on 14/09/16.
 */
public class ImageAdapter extends BaseAdapter {

    private Context context;
    private int totalWidth;
    private static int count=0;

    private Integer[] mThumbIDs={
            R.drawable.steve,R.drawable.ic_launcher,R.drawable.bill,R.drawable.mark,
            R.drawable.steve,R.drawable.ic_launcher,R.drawable.bill,R.drawable.mark,
            R.drawable.steve,R.drawable.ic_launcher,R.drawable.bill,R.drawable.mark
    };

    public ImageAdapter(Context c,int w){
        context=c;
        totalWidth=w;
    }

    @Override
    public int getCount() {
        return mThumbIDs.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView;

        if(view==null){
            imageView=new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(totalWidth/3,300));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.setPadding(8,8,8,8);
        }
        else{
            imageView=(ImageView) view;
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                ImageView v = (ImageView) view;
                //overlay is black with transparency of 0x77 (119)
                if(count%2!=0) {
                    v.getDrawable().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                    v.invalidate();
                }
                else{
                    v.getDrawable().clearColorFilter();
                    v.invalidate();
                }
            }
        });

        imageView.setImageResource(mThumbIDs[i]);

        return imageView;
    }
}
