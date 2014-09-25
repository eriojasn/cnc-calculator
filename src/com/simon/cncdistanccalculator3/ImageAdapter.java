package com.simon.cncdistanccalculator3;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private String mLineType;

    public ImageAdapter(Context c, String lineType) {
        mContext = c;
        mLineType = lineType;
    }

    public int getCount() {
        return 4;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }
        
        Log.i("Line type", mLineType);
        
        if(mLineType.equals("horizontal"))
        	imageView.setImageResource(mThumbIdsH[position]);
        else
        	imageView.setImageResource(mThumbIdsV[position]);
        return imageView;
    }

    // references to our images
    private Integer[] mThumbIdsH = {
            R.drawable.h1, R.drawable.h2,
            R.drawable.h3, R.drawable.h4
    };
    
    private Integer[] mThumbIdsV = {
            R.drawable.v1, R.drawable.v2,
            R.drawable.v3, R.drawable.v4
    };
}