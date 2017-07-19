package com.han.seong.travelhelper.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.han.seong.travelhelper.MainActivity;
import com.han.seong.travelhelper.travelDetail.TravelDetail;
import com.han.seong.travelhelper.vo.Travel;
import com.han.seong.travelhelper.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class Main_RecyclerAdapter extends RecyclerView.Adapter<Main_RecyclerAdapter.MyViewHolder>{

    private List<Travel> travelList;
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_title;
        TextView tv_content;
        TextView tv_startDate;
        TextView tv_endDate;

        public MyViewHolder(View itemView){
            super(itemView);
            final View view = itemView;
            this.tv_title = (TextView)itemView.findViewById(R.id.tv_CVTitle);
            this.tv_content = (TextView)itemView.findViewById(R.id.tv_CVCountry);
            this.tv_startDate = (TextView)itemView.findViewById(R.id.tv_CVStart);
            this.tv_endDate = (TextView)itemView.findViewById(R.id.tv_CVEnd);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(view.getContext(), TravelDetail.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("realmSearch", tv_title.getText().toString());
                    bundle.putString("Title", tv_title.getText().toString() + " (" + tv_content.getText().toString() + ")");
                    bundle.putString("Subtitle", tv_startDate.getText().toString() + " ~ " + tv_endDate.getText().toString());
                    intent.putExtras(bundle);
                    view.getContext().startActivity(intent);
                }
            });
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
        TextView tv_startDate = holder.tv_startDate;
        TextView tv_endDate = holder.tv_endDate;

        tv_title.setText(travelList.get(position).getTitle());
        tv_content.setText(travelList.get(position).getCountry());

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
        tv_startDate.setText("Start Date: " + sdf.format(travelList.get(position).getStartDate()));
        tv_endDate.setText("End Date: " + sdf.format(travelList.get(position).getEndDate()));

    }

    @Override
    public int getItemCount() {
        return travelList.size();
    }
}

