package com.example.zqy.myapplication.home.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RatingBar;

import com.example.zqy.myapplication.BaseActivity;
import com.example.zqy.myapplication.R;
import com.example.zqy.myapplication.utils.LogUtils;
import com.example.zqy.myapplication.utils.MyDividerItemDecoration;
import com.example.zqy.myapplication.utils.ToastUtils;

import java.util.ArrayList;

/**
 * Created by zqy on 17-10-29.
 */

public class HomeDetailListActivity extends BaseActivity{

    private RecyclerView mRecyclerView;
    private HomeDetailListAdapter mAdapter;
//    private RecyclerView.Adapter mAdapter;

    private RecyclerView.LayoutManager mLayoutManager;

    private RatingBar ratingBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LogUtils.d("1", getClass().getSimpleName());
        setContentView(R.layout.activity_home_detail_list);
        initToolbar();
        initData();
        initView();



    }

    private void initData() {
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new HomeDetailListAdapter(getData());
//        mAdapter = new HomeDetailListAdapter(setData());
    }


    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.home_detail_recyclerView);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new HomeDetailListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ToastUtils.show("位置"+position,HomeDetailListActivity.this);

                switch (position) {
                    case 0:
                        Intent intent1 = new Intent(HomeDetailListActivity.this, HomeDetailActivity.class);
                        startActivity(intent1);
                        break;
                }

            }

            @Override
            public void onItemLongClick(View view, int position) {
                //
            }
        });

    }




    private ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<>();
        String temp = " 深海烤鱼";
        for(int i = 9; i < 20; i++) {
            data.add(i + temp);
        }

        return data;
    }


}
