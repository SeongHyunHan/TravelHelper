package com.han.seong.travelhelper.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.han.seong.travelhelper.MainActivity;
import com.han.seong.travelhelper.vo.Travel;
import com.han.seong.travelhelper.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class Main_RecyclerAdapter extends RecyclerView.Adapter<Main_RecyclerAdapter.MyViewHolder>{

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

    public Main_RecyclerAdapter(ArrayList<Travel> travelList){
        this.travelList = travelList;
    }



    @Override
    public Main_RecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_row, parent, false);

        view.setOnClickListener(MainActivity.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(Main_RecyclerAdapter.MyViewHolder holder, final int position) {
        TextView tv_title = holder.tv_title;
        TextView tv_content = holder.tv_content;

        tv_title.setText(travelList.get(position).getTitle());
        tv_content.setText(travelList.get(position).getCountry());
    }

    @Override
    public int getItemCount() {
        return travelList.size();
    }
}

