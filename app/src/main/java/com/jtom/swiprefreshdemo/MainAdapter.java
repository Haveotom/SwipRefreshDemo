package com.jtom.swiprefreshdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * author: Jing
 * date: 2017/7/9.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {
    private Context mContext;
    private List<Bean> mBeanList;

    public MainAdapter(Context context) {
        mContext = context;
    }

    public void setBeanList(List<Bean> beanList) {
        mBeanList = beanList;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_rv, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.name.setText(mBeanList.get(position).getName());
        holder.addr.setText(mBeanList.get(position).getAddr());

    }

    @Override
    public int getItemCount() {
        return mBeanList != null ? mBeanList.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name, addr;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.item_name);
            addr = (TextView) itemView.findViewById(R.id.item_addr);

        }
    }
}
