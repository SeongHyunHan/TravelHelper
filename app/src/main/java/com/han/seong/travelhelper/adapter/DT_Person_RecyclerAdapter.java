package com.han.seong.travelhelper.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.han.seong.travelhelper.R;
import com.han.seong.travelhelper.vo.Person;

import java.util.List;

public class DT_Person_RecyclerAdapter extends RecyclerView.Adapter<DT_Person_RecyclerAdapter.ViewHolder>{

        private List<Person> peopleList;
        private int itemLayout;

    public DT_Person_RecyclerAdapter(List<Person> people, int itemLayout){
            this.peopleList = people;
            this.itemLayout = itemLayout;
        }

        @Override
        public DT_Person_RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
            return new DT_Person_RecyclerAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(DT_Person_RecyclerAdapter.ViewHolder holder, int position) {

            Person item = peopleList.get(position);
            holder.name.setText(item.getFirstName() + " " + item.getLastName());
            holder.budget.setText(String.valueOf(item.getOwnBudget()));
        }

        @Override
        public int getItemCount() {
            return peopleList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            TextView name, budget;

            public ViewHolder(View view) {
                super(view);

                name = (TextView)view.findViewById(R.id.tv_dt_name);
                budget = (TextView)view.findViewById(R.id.tv_dt_personBudget);
            }
        }

}
