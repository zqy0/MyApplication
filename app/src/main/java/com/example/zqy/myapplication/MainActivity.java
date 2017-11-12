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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.zqy.myapplication.explore.ui.ExploreFragment;
import com.example.zqy.myapplication.home.ui.HomeFragment;
import com.example.zqy.myapplication.shopping.ui.ShoppingFragment;
import com.example.zqy.myapplication.user.ui.UserFragment;
import com.example.zqy.myapplication.utils.BottomNavigationViewHelper;
import com.example.zqy.myapplication.utils.LogUtils;

/**
 * Created by zqy on 17-10-21.
 */

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d("1", getClass().getSimpleName());
        setContentView(R.layout.activity_main);

        initToolbar();

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        // 使用反射去除大于3个item时产生的效果
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.homepage);
        replaceFragment(new HomeFragment());

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

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.include_toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.app_bar_search:
                Toast.makeText(this, "搜索中", Toast.LENGTH_SHORT).show();
                break;
            case R.id.app_bar_setting:
                break;
        }
        return true;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.commit();
    }


}
