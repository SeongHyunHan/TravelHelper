package com.han.seong.travelhelper.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.han.seong.travelhelper.vo.TravelVo;
import com.han.seong.travelhelper.R;

import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder>{

    private List<TravelVo> travelList;
    private int itemLayout;
}

