package com.example.zqy.myapplication.explore.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zqy.myapplication.R;
import com.example.zqy.myapplication.utils.InitToolBarUtils;

/**
 * Created by zqy on 17-10-26.
 */

public class ExploreFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore, container, false);
        InitToolBarUtils.initToolbar(this, R.id.include_toolbar, R.string.explore);

        return view;
    }
}
