package com.example.zqy.myapplication.settings.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.zqy.myapplication.R;
import com.example.zqy.myapplication.account.ui.AuthenticatorActivity;
import com.example.zqy.myapplication.utils.InitToolBarUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 用户未登录时跳转显示的界面
 * Created by zqy on 18-3-27.
 */

public class SettingsFragmentNotLogin extends Fragment {

    @BindView(R.id.btn_settings_login)
    Button btnSettingsLogin;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings_not_login, container, false);
        unbinder = ButterKnife.bind(this, view);

        InitToolBarUtils.initToolbar(this, R.id.include_toolbar, R.string.settings,
                true);

        btnSettingsLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AuthenticatorActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
