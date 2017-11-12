package com.example.zqy.myapplication.home.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.zqy.myapplication.BaseActivity;
import com.example.zqy.myapplication.R;
import com.example.zqy.myapplication.utils.LogUtils;
import com.example.zqy.myapplication.utils.ToastUtils;

/**
 * Created by zqy on 17-11-2.
 * 菜品详情页
 */

public class HomeDetailActivity extends BaseActivity {

    private Button btn_add;
    private Button btn_remove;

    private TextView textView;
    private int food_num = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d("3", getClass().getSimpleName());
        setContentView(R.layout.activity_home_detail);

        initToolbar();
        btn_add = findViewById(R.id.btn_add);
        btn_remove = findViewById(R.id.btn_remove);

        textView = findViewById(R.id.tV_value);


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String food_num_str = Integer.toString(++food_num);

                textView.setText(food_num_str);
            }
        });

        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (food_num > 0) {
                    String food_num_str2 = Integer.toString(--food_num);
                    textView.setText(food_num_str2);
                }
            }
        });

    }
}
