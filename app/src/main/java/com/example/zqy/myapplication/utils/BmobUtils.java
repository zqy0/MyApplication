package com.example.zqy.myapplication.utils;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.widget.EditText;

import com.example.zqy.myapplication.MainActivity;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by zqy on 17-12-5.
 */

public class BmobUtils {

    public static void isLogined() {

        BmobUser bmobUser = BmobUser.getCurrentUser();
    }



}
