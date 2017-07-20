package com.han.seong.travelhelper.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.han.seong.travelhelper.R;

public class AF_SpinnerAdapter extends BaseAdapter {

    Context context;
    int categories[];
    LayoutInflater inflater;

    public AF_SpinnerAdapter(Context applicationContext, int categories[]){
        this.context = applicationContext;
        this.categories = categories;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return categories.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = inflater.inflate(R.layout.af_custom_spinner, null);
        ImageView image = (ImageView)view.findViewById(R.id.af_spn_iv);
        image.setImageResource(categories[position]);
        return view;
    }
}
