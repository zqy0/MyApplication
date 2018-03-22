package com.example.zqy.myapplication.settings.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.zqy.myapplication.MainActivity;
import com.example.zqy.myapplication.R;
import com.example.zqy.myapplication.utils.InitToolBarUtils;
import com.example.zqy.myapplication.utils.ToastUtils;

import cn.bmob.v3.BmobUser;

/**
 * Created by zqy on 17-12-6.
 */

public class SettingsFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        InitToolBarUtils.initToolbar(this, R.id.include_toolbar, R.string.settings,
                true);


        Button button1 = view.findViewById(R.id.btn_logout);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BmobUser.logOut();
                ToastUtils.show("退出成功", getActivity());
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);

            }
        });
        return view;

    }

}
