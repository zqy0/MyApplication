package com.example.zqy.myapplication.settings.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.zqy.myapplication.R;
import com.example.zqy.myapplication.utils.FragmentUtils;

import cn.bmob.v3.BmobUser;

/**
 * Created by zqy on 17-12-6.
 * 设置页面
 */

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        BmobUser bmobUser = BmobUser.getCurrentUser();
        if(bmobUser != null){
            // 显示登出部分
            FragmentUtils.add(new SettingsFragment(), this, R.id.frameLayout_settings);

        }else{
            //缓存用户对象为空时， 可打开用户注册界面…

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
