package com.example.zqy.myapplication.explore.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.zqy.myapplication.R;
import com.example.zqy.myapplication.model.UserAccount;
import com.example.zqy.myapplication.utils.InitToolBarUtils;
import com.example.zqy.myapplication.utils.LogUtils;
import com.example.zqy.myapplication.utils.ToastUtils;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by zqy on 17-10-26.
 */

public class ExploreFragment extends Fragment {
    private Button button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore, container, false);
        InitToolBarUtils.initToolbar(this, R.id.include_toolbar, R.string.explore);

        button = view.findViewById(R.id.btn_explore);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobUser user = BmobUser.getCurrentUser();
                UserAccount userAccount = new UserAccount();
                userAccount.setAccount_money(100f);
//                user.setValue();

            }
        });

        return view;
    }
}
