package com.example.zqy.myapplication.utils;

import android.app.Activity;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.zqy.myapplication.R;

/**
 * Created by zqy on 17-12-5.
 */

public class InitToolBarUtils {

    public InitToolBarUtils() {}


    // Fragment返回Activity
    public static void initToolbar(Fragment fragment, int containerViewId) {
        Toolbar toolbar = (Toolbar) fragment.getActivity().findViewById(containerViewId);
        ((AppCompatActivity) fragment.getActivity()).setSupportActionBar(toolbar);
        fragment.setHasOptionsMenu(true);
    }

    public static void initToolbar(Fragment fragment, int containerViewId, @StringRes int resId) {
        Toolbar toolbar = (Toolbar) fragment.getActivity().findViewById(containerViewId);

        toolbar.setTitle(resId);
        ((AppCompatActivity) fragment.getActivity()).setSupportActionBar(toolbar);
        fragment.setHasOptionsMenu(true);
    }

    public static void initToolbar(Fragment fragment, int containerViewId, @StringRes int resId,
                                   boolean homeback) {
        Toolbar toolbar = (Toolbar) fragment.getActivity().findViewById(containerViewId);

        toolbar.setTitle(resId);
        ((AppCompatActivity) fragment.getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) fragment.getActivity()).getSupportActionBar().
                setDisplayHomeAsUpEnabled(homeback);
        fragment.setHasOptionsMenu(true);


    }

}
