package com.example.zqy.myapplication.home.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;

import com.example.zqy.myapplication.BaseActivity;
import com.example.zqy.myapplication.R;
import com.example.zqy.myapplication.model.Food;
import com.example.zqy.myapplication.utils.LogUtils;
import com.example.zqy.myapplication.utils.MyDividerItemDecoration;
import com.example.zqy.myapplication.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by zqy on 17-10-29.
 */

public class HomeDetailListActivity extends BaseActivity{

    private RecyclerView mRecyclerView;
    private HomeDetailListAdapter mAdapter;

    private RecyclerView.LayoutManager mLayoutManager;

    private RatingBar ratingBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LogUtils.d("activity_now", getClass().getSimpleName());
        setContentView(R.layout.activity_home_detail_list);
        initToolbar();

        inquireListData();

    }

    private void inquireListData() {

        final List<Food> datas = new ArrayList<>();
        BmobQuery<Food> query = new BmobQuery<Food>();
        final Bundle bundle = this.getIntent().getExtras();
        String hall = null;
        if (bundle != null) {
            hall = bundle.getString("hall");
            query.addWhereEqualTo("food_seller", hall);
        }

        query.setLimit(200);
        query.findObjects(new FindListener<Food>() {
            @Override
            public void done(final List<Food> list, BmobException e) {
                if (e == null) {
                    Log.d("food_info", String.valueOf(list.size())+"条食物信息");
                    datas.addAll(list);

                    mRecyclerView = (RecyclerView) findViewById(R.id.home_detail_recyclerView);

                    mLayoutManager = new LinearLayoutManager(HomeDetailListActivity.this,
                            LinearLayoutManager.VERTICAL, false);
                    mAdapter = new HomeDetailListAdapter(HomeDetailListActivity.this,datas);

                    mRecyclerView.setLayoutManager(mLayoutManager);
                    mRecyclerView.addItemDecoration(new MyDividerItemDecoration(
                            HomeDetailListActivity.this, LinearLayoutManager.VERTICAL));

                    mRecyclerView.setAdapter(mAdapter);

                    mAdapter.setOnItemClickListener(new HomeDetailListAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            ToastUtils.show("位置"+position,HomeDetailListActivity.this);

                            switch (position) {
                                default:
                                    Food food = datas.get(position);
                                    LogUtils.d("food1", food.getFood_name());

                                    Bundle bundle1 = new Bundle();

                                    bundle1.putString("food_name", food.getFood_name());
                                    bundle1.putString("food_seller", food.getFood_seller());
                                    bundle1.putString("food_id", food.getObjectId());
                                    bundle1.putFloat("food_price", food.getFood_price());

                                    Intent intent1 = new Intent(HomeDetailListActivity.this, HomeDetailActivity.class);
                                    intent1.putExtras(bundle1);
                                    startActivity(intent1);
                                    break;
                            }
                        }

                        @Override
                        public void onItemLongClick(View view, int position) {
                            //
                        }
                    });

                } else {
                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }

}
