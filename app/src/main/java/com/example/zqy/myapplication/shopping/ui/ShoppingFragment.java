package com.example.zqy.myapplication.shopping.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.zqy.myapplication.R;

import com.example.zqy.myapplication.model.Order;
import com.example.zqy.myapplication.utils.InitToolBarUtils;
import com.example.zqy.myapplication.utils.LogUtils;
import com.example.zqy.myapplication.utils.MyDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by zqy on 17-10-26.
 */

public class ShoppingFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private static ShoppingListAdapter mAdapter;
    private static RecyclerView.LayoutManager mLayoutManager;
    private static ArrayList<Order> datas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping, container, false);

        InitToolBarUtils.initToolbar(this,R.id.include_toolbar, R.string.shopping);

        ininquireListData(view);

        return view;
    }

    private void ininquireListData(final View view) {
        final List<Order> datas = new ArrayList<>();
        BmobQuery<Order> query = new BmobQuery<Order>();
        BmobUser bmobUser = BmobUser.getCurrentUser();

        query.addWhereEqualTo("order_user_id", bmobUser.getObjectId());

        query.order("-createdAt");
        query.findObjects(new FindListener<Order>() {
            @Override
            public void done(List<Order> list, BmobException e) {
                if (e == null) {
                    LogUtils.d("shop", String.valueOf(list.size()) + "信息");
                    datas.addAll(list);
                    mRecyclerView = (RecyclerView) view.findViewById(R.id.shopping_recyclerView);

                    mLayoutManager = new LinearLayoutManager(getActivity(),
                            LinearLayoutManager.VERTICAL,false);
                    mRecyclerView.setLayoutManager(mLayoutManager);

                    mRecyclerView.addItemDecoration(new MyDividerItemDecoration(
                            getActivity(), LinearLayoutManager.VERTICAL));

                    mAdapter = new ShoppingListAdapter(getActivity(), datas);
                    mRecyclerView.setAdapter(mAdapter);

                } else {
                    LogUtils.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());

                }

            }
        });
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_toolbar_shopping, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.app_bar_user1:
                break;
        }
        return true;
    }
}
