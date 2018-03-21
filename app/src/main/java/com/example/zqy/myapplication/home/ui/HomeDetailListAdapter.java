package com.example.zqy.myapplication.home.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zqy.myapplication.R;
import com.example.zqy.myapplication.model.Food;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zqy on 17-10-29.
 */

public class HomeDetailListAdapter extends RecyclerView.Adapter<HomeDetailListAdapter.ViewHolder> {

    private Context mContext;
    private List<Food> mList;
    private HomeDetailListAdapter.OnItemClickListener onItemClickListener;

    public HomeDetailListAdapter(Context mContext, List<Food> mList) {
        this.mContext = mContext;
        this.mList = mList;
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
//        holder.mFoodName.setText(mList.get(position).food_name);
        // 设置food_name为private字段，对其GET和SET方法公开
        holder.mFoodName.setText(mList.get(position).getFood_name());

        holder.mFoodDescribe.setText(mList.get(position).getFood_describe());

        String food_price_str = String.valueOf(mList.get(position).getFood_price());
        holder.mFoodPrice.setText(food_price_str);

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

                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mFoodName, mFoodPrice, mFoodDescribe;

        public ViewHolder(View itemView) {
            super(itemView);
            mFoodName = (TextView) itemView.findViewById(R.id.item_food_name);
            mFoodPrice = (TextView) itemView.findViewById(R.id.item_food_price);
            mFoodDescribe = (TextView) itemView.findViewById(R.id.item_food_describe);
        }
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }



}
