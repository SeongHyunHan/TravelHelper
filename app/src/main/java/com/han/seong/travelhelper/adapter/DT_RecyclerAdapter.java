package com.han.seong.travelhelper.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.han.seong.travelhelper.R;
import com.han.seong.travelhelper.vo.Finance;

import java.util.List;

/**
 * Created by TEST on 2017-07-03.


public class DT_RecyclerAdapter extends RecyclerView.Adapter {

    private List<Finance> financeList;
    private int itemLayout;

    public DT_RecyclerAdapter(List<Finance> finances, int itemLayout){
        this.financeList = finances;
        this.itemLayout = itemLayout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dt_overview_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Finance item = financeList.get(position);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder {
        public ViewHolder(View view) {
        }
    }
}
 */