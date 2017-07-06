package com.han.seong.travelhelper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.han.seong.travelhelper.R;

public class AT_SpinnerAdapter extends BaseAdapter {

    Context context;
    int flags[];
    String[] countries;
    LayoutInflater inflater;

    public AT_SpinnerAdapter(Context applicationContext, int[] flags, String[] countries){
        this.context = applicationContext;
        this.flags = flags;
        this.countries = countries;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return flags.length;
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
        view = inflater.inflate(R.layout.at_custom_spinner, null);
        ImageView image = (ImageView)view.findViewById(R.id.spn_iv);
        TextView name = (TextView)view.findViewById(R.id.spn_tv);

        image.setImageResource(flags[position]);
        name.setText(countries[position]);
        return view;
    }
}
