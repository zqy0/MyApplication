package com.example.zqy.myapplication.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * Created by zqy on 17-12-5.
 */

public class FragmentUtils {
    public FragmentUtils() {}


    @Deprecated
    public static void add(Fragment fragment, FragmentManager fragmentManager, int containerViewId,
                           String tag) {
        fragmentManager.beginTransaction()
                .add(containerViewId, fragment, tag)
                .commit();
    }

    @Deprecated
    public static void add(Fragment fragment, FragmentManager fragmentManager,
                           int containerViewId) {
        //noinspection deprecation
        add(fragment, fragmentManager, containerViewId, null);
    }

    public static void add(Fragment fragment, FragmentActivity activity, int containerViewId) {
        //noinspection deprecation
        add(fragment, activity.getSupportFragmentManager(), containerViewId);
    }

    public static void add(Fragment fragment, Fragment parentFragment, int containerViewId) {
        //noinspection deprecation
        add(fragment, parentFragment.getChildFragmentManager(), containerViewId);
    }

    @Deprecated
    public static void add(Fragment fragment, FragmentManager fragmentManager, String tag) {
        // Pass 0 as in {@link android.support.v4.app.BackStackRecord#add(Fragment, String)}.
        //noinspection deprecation
        add(fragment, fragmentManager, 0, tag);
    }

    public static void add(Fragment fragment, FragmentActivity activity, String tag) {
        //noinspection deprecation
        add(fragment, activity.getSupportFragmentManager(), tag);
    }

    public static void add(Fragment fragment, Fragment parentFragment, String tag) {
        //noinspection deprecation
        add(fragment, parentFragment.getChildFragmentManager(), tag);
    }

    public static void add(Fragment fragment, FragmentActivity activity) {
        //noinspection deprecation
        add(fragment, activity.getSupportFragmentManager(), null);
    }

    public static void add(Fragment fragment, Fragment parentFragment) {
        //noinspection deprecation
        add(fragment, parentFragment.getChildFragmentManager(), null);
    }



    /*
        replace
     */
    @Deprecated
    public static void replace(Fragment fragment, FragmentManager fragmentManager,
                               int containerViewId, String tag) {
        fragmentManager.beginTransaction()
                .replace(containerViewId, fragment, tag)
                .commit();
    }

    @Deprecated
    public static void replace(Fragment fragment, FragmentManager fragmentManager,
                               int containerViewId) {
        //noinspection deprecation
        replace(fragment, fragmentManager, containerViewId, null);
    }

    public static void replace(Fragment fragment, FragmentActivity activity, int containerViewId) {
        //noinspection deprecation
        replace(fragment, activity.getSupportFragmentManager(), containerViewId);
    }

    public static void replace(Fragment fragment, Fragment parentFragment, int containerViewId) {
        //noinspection deprecation
        replace(fragment, parentFragment.getChildFragmentManager(), containerViewId);
    }

    @Deprecated
    public static void replace(Fragment fragment, FragmentManager fragmentManager, String tag) {
        // Pass 0 as in {@link android.support.v4.app.BackStackRecord#replace(Fragment, String)}.
        //noinspection deprecation
        replace(fragment, fragmentManager, 0, tag);
    }

    public static void replace(Fragment fragment, FragmentActivity activity, String tag) {
        //noinspection deprecation
        replace(fragment, activity.getSupportFragmentManager(), tag);
    }

    public static void replace(Fragment fragment, Fragment parentFragment, String tag) {
        //noinspection deprecation
        replace(fragment, parentFragment.getChildFragmentManager(), tag);
    }

    public static void replace(Fragment fragment, FragmentActivity activity) {
        //noinspection deprecation
        replace(fragment, activity.getSupportFragmentManager(), null);
    }

    public static void replace(Fragment fragment, Fragment parentFragment) {
        //noinspection deprecation
        replace(fragment, parentFragment.getChildFragmentManager(), null);
    }
}
