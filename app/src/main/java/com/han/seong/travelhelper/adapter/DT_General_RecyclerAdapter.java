package com.han.seong.travelhelper.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.han.seong.travelhelper.R;
import com.han.seong.travelhelper.vo.Finance;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;


public class DT_General_RecyclerAdapter extends RecyclerView.Adapter<DT_General_RecyclerAdapter.ViewHolder>{

    private List<Finance> financeList;
    private int itemLayout;

    public DT_General_RecyclerAdapter(List<Finance> finances, int itemLayout){
        this.financeList = finances;
        this.itemLayout = itemLayout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");

        Finance item = financeList.get(position);
        holder.payTitle.setText((item.getPaymentTitle()));
        holder.payDate.setText((sdFormat.format(item.getDate())));
        holder.payAmount.setText(String.valueOf(item.getPrice()));

    }

    @Override
    public int getItemCount() {
        return financeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView payTitle, payDate, payAmount;

        public ViewHolder(View view) {
            super(view);

            payTitle = (TextView)view.findViewById(R.id.tv_dt_payTitle);
            payDate = (TextView)view.findViewById(R.id.tv_dt_date);
            payAmount = (TextView)view.findViewById(R.id.tv_dt_amount);


        }
    }
}