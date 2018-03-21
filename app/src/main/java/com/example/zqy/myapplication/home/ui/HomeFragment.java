package com.example.zqy.myapplication.home.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.zqy.myapplication.R;
import com.example.zqy.myapplication.utils.InitToolBarUtils;
import com.example.zqy.myapplication.utils.ToastUtils;

/**
 * Created by zqy on 17-10-24.
 */

public class HomeFragment extends Fragment {

    private Toolbar toolbar;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        InitToolBarUtils.initToolbar(this, R.id.include_toolbar, R.string.home);


        Button button = (Button) view.findViewById(R.id.button2);
        LinearLayout linearLayout1 = (LinearLayout) view.findViewById(R.id.food_1);
        LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.food_2);
        LinearLayout linearLayout3 = (LinearLayout) view.findViewById(R.id.food_3);
        LinearLayout linearLayout4 = (LinearLayout) view.findViewById(R.id.food_4);
        LinearLayout linearLayout5 = (LinearLayout) view.findViewById(R.id.food_5);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.show("heihei", getActivity());
            }
        });




        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.show("一食堂", getActivity());
                Intent intent = new Intent(getActivity(), HomeDetailListActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("hall", getString(R.string.home_hall_1));
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.show("二食堂", getActivity());
                Intent intent = new Intent(getActivity(), HomeDetailListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("hall", getString(R.string.home_hall_2));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.show("三食堂", getActivity());
                Intent intent = new Intent(getActivity(), HomeDetailListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("hall", getString(R.string.home_hall_3));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        linearLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.show("四食堂", getActivity());
                Intent intent = new Intent(getActivity(), HomeDetailListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("hall", getString(R.string.home_hall_4));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        linearLayout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.show("清真", getActivity());
                Intent intent = new Intent(getActivity(), HomeDetailListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("hall", getString(R.string.home_hall_qingzhen));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_toolbar_home, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.app_bar_search:
                break;
            case R.id.app_bar_setting:
                break;
        }
        return true;
    }

}
