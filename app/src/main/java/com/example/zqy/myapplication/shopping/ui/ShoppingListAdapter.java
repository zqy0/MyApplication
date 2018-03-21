package com.example.zqy.myapplication.shopping.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zqy.myapplication.R;
import com.example.zqy.myapplication.model.Order;

import java.util.List;

/**
 * Created by zqy on 17-12-14.
 */

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ViewHolder> {

    private Context mContext;
    private List<Order> mList;

    public ShoppingListAdapter(Context mContext, List<Order> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_shopping_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ShoppingListAdapter.ViewHolder holder, int position) {
        holder.mFoodName.setText(mList.get(position).getOrder_food_name());
        holder.mFoodPrices.setText(String.valueOf(mList.get(position).getOrder_food_prices()));
        holder.mFoodTime.setText(mList.get(position).getCreatedAt());

    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 :mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView mFoodName, mFoodPrices, mFoodTime;


        public ViewHolder(View itemView) {
            super(itemView);
            mFoodName = itemView.findViewById(R.id.item_shopping_food_name);
            mFoodPrices = itemView.findViewById(R.id.item_shopping_food_prices);
            mFoodTime = itemView.findViewById(R.id.item_shopping_food_time);
        }
    }
}
