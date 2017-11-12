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
import android.widget.Toast;

import com.example.zqy.myapplication.explore.ui.ExploreFragment;
import com.example.zqy.myapplication.home.ui.HomeFragment;
import com.example.zqy.myapplication.shopping.ui.ShoppingFragment;
import com.example.zqy.myapplication.user.ui.UserFragment;
import com.example.zqy.myapplication.utils.BottomNavigationViewHelper;

/**
 * Created by zqy on 17-10-29.
 */

public class BaseActivity extends AppCompatActivity{
    private Toolbar toolbar;




    public void initToolbar() {
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


}

