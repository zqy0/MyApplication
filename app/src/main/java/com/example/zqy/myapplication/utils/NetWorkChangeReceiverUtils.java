package com.example.zqy.myapplication.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * 网络状态改变接收器
 * Created by zqy on 18-3-26.
 */

public class NetWorkChangeReceiverUtils extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        // ConnectivityManager是系统服务类，专门用于管理网络连接
        // 通过context.getSystemService(Context.CONNECTIVITY_SERVICE)获得ConnectivityManager对象
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);


        assert connectivityManager != null;
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isAvailable()) {
            Toast.makeText(context, "网络已连接", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "网络不可用", Toast.LENGTH_SHORT).show();
        }



    }
}
