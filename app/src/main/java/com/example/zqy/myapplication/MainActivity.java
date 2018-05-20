package com.example.zqy.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.zqy.myapplication.explore.ui.ExploreFragment;
import com.example.zqy.myapplication.home.ui.HomeFragment;
import com.example.zqy.myapplication.shopping.ui.ShoppingFragment;
import com.example.zqy.myapplication.user.ui.UserFragment;
import com.example.zqy.myapplication.utils.BottomNavigationViewHelper;
import com.example.zqy.myapplication.utils.FragmentUtils;
import com.example.zqy.myapplication.utils.LogUtils;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.BmobInstallationManager;
import cn.bmob.v3.InstallationListener;
import cn.bmob.v3.exception.BmobException;

/**
 * TODO 1. RecyclerView的点击事件 ok 2. Tag no 3. Bmob数据分食堂查询 ok 4. 订单详情页的试图 5. 交易的数据表
 *
 * Created by zqy on 17-10-21.
 */

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bmob.initialize(this, "e01990f453876ec8667dbc94fb0ff9ef");

        BmobInstallationManager.getInstance().initialize(new InstallationListener<BmobInstallation>() {
            @Override
            public void done(BmobInstallation bmobInstallation, BmobException e) {
                if (e == null) {
                    LogUtils.i("bmobInstallationInfo", bmobInstallation.getObjectId() + "-" + bmobInstallation.getInstallationId());
                } else {
                    LogUtils.e("error", e.getMessage());
                }
            }
        });


        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        // 使用反射去除大于3个item时产生的效果
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.homepage);

        FragmentUtils.add(new HomeFragment(), this, R.id.frameLayout);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.explore:
                        item.setChecked(true);
                        replaceFragment(new ExploreFragment());

                        break;
                    case R.id.account_user:
                        item.setChecked(true);
                        replaceFragment(new UserFragment());

                        break;
                    case R.id.homepage:
                        item.setChecked(true);
                        replaceFragment(new HomeFragment());

                        break;
                    case R.id.shopping:
                        item.setChecked(true);
                        replaceFragment(new ShoppingFragment());
                        break;
                    default:
                        break;
                }
                return false;
            }
        });

    }


    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.commit();
    }


}
