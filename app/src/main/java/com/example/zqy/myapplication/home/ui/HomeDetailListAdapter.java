package com.example.zqy.myapplication.home.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zqy.myapplication.R;

import java.util.ArrayList;

/**
 * Created by zqy on 17-10-29.
 */

public class HomeDetailListAdapter extends RecyclerView.Adapter<HomeDetailListAdapter.ViewHolder> {
    private ArrayList<String> mData;
    private HomeDetailListAdapter.OnItemClickListener onItemClickListener;

    public HomeDetailListAdapter(ArrayList<String> mData) {
        this.mData = mData;
    }

    /**
     * 设置回调监听
     *
     * @param listener
     */
    public void setOnItemClickListener(HomeDetailListAdapter.OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }


    @Override
    public HomeDetailListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_home_detail_list, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final HomeDetailListAdapter.ViewHolder holder, int position) {
        holder.mFoodName.setText(mData.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if(onItemClickListener != null) {
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView, pos);
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(onItemClickListener != null) {
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemLongClick(holder.itemView, pos);
                }
                //表示此事件已经消费，不会触发单击事件
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mFoodName, mFoodPrice;


        public ViewHolder(View itemView) {
            super(itemView);
            mFoodName = (TextView) itemView.findViewById(R.id.item_food_name);
            mFoodPrice = (TextView) itemView.findViewById(R.id.item_food_price);
        }
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }



}
