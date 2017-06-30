package com.han.seong.travelhelper.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.han.seong.travelhelper.MainActivity;
import com.han.seong.travelhelper.vo.Travel;
import com.han.seong.travelhelper.R;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>{

    private List<Travel> travelList;
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_title;
        TextView tv_content;

        public MyViewHolder(View itemView){
            super(itemView);
            this.tv_title = (TextView)itemView.findViewById(R.id.tv_CVTitle);
            this.tv_content = (TextView)itemView.findViewById(R.id.tv_CVCountry);
        }
    }

    public MyRecyclerAdapter(ArrayList<Travel> travelList){
        this.travelList = travelList;
    }



    @Override
    public MyRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_row, parent, false);

        view.setOnClickListener(MainActivity.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyRecyclerAdapter.MyViewHolder holder, final int position) {
        TextView tv_title = holder.tv_title;
        TextView tv_content = holder.tv_content;

        Log.i("CardView Position", String.valueOf(position));
        Log.i("CardView Title", travelList.get(position).getTitle());
        Log.i("CardView Country", travelList.get(position).getCountry());
        tv_title.setText(travelList.get(position).getTitle());
        tv_content.setText(travelList.get(position).getCountry());
    }

    @Override
    public int getItemCount() {
        return travelList.size();
    }
}

