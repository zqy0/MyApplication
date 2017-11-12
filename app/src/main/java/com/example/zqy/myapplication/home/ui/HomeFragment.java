package com.example.zqy.myapplication.home.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.zqy.myapplication.R;
import com.example.zqy.myapplication.utils.ToastUtils;

/**
 * Created by zqy on 17-10-24.
 */

public class HomeFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Button button = (Button) view.findViewById(R.id.button2);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.food_1);
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


        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.show("一食堂", getActivity());
                Intent intent = new Intent(getActivity(), HomeDetailListActivity.class);
                startActivity(intent);
            }
        });

        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.show("二食堂", getActivity());
                Intent intent = new Intent(getActivity(), HomeDetailListActivity.class);
                startActivity(intent);
            }
        });
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.show("二食堂", getActivity());
                Intent intent = new Intent(getActivity(), HomeDetailListActivity.class);
                startActivity(intent);
            }
        });
        linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.show("三食堂", getActivity());
                Intent intent = new Intent(getActivity(), HomeDetailListActivity.class);
                startActivity(intent);
            }
        });
        linearLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.show("四食堂", getActivity());
                Intent intent = new Intent(getActivity(), HomeDetailListActivity.class);
                startActivity(intent);
            }
        });
        linearLayout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.show("五食堂", getActivity());
                Intent intent = new Intent(getActivity(), HomeDetailListActivity.class);
                startActivity(intent);
            }
        });

        return view;

    }

}
