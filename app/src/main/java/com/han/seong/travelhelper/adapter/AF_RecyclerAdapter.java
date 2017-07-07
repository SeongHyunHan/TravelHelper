package com.han.seong.travelhelper.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.han.seong.travelhelper.R;
import com.han.seong.travelhelper.vo.Person;

import java.util.ArrayList;
import java.util.List;

public class AF_RecyclerAdapter extends RecyclerView.Adapter<AF_RecyclerAdapter.MyViewHolder>{
    private List<Person> peopleList;
    private int itemLayout;

    public AF_RecyclerAdapter(ArrayList<Person> peopleList, int itemLayout){
        this.peopleList = peopleList;
        this.itemLayout = itemLayout;
    }
    @Override
    public AF_RecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new AF_RecyclerAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AF_RecyclerAdapter.MyViewHolder holder, int position) {
        TextView tv_name = holder.tv_name;

        tv_name.setText(peopleList.get(position).getFirstName() +
                ' ' + peopleList.get(position).getLastName());
    }

    @Override
    public int getItemCount() {
        return peopleList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_name;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.tv_name = (TextView) itemView.findViewById(R.id.af_tv_cvName);
        }
    }
}
