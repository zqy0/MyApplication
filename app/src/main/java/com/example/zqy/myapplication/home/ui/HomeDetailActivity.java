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
import com.example.zqy.myapplication.model.Order;
import com.example.zqy.myapplication.utils.LogUtils;
import com.example.zqy.myapplication.utils.ToastUtils;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by zqy on 17-11-2.
 * 菜品详情页
 * btn_add实现菜品增加，btn_remove实现减少
 */

public class HomeDetailActivity extends BaseActivity {

    private Button btn_order;

    private TextView tv_order_num,tv_detail_price;
    private int food_num = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home_detail);

        Button btn_add = findViewById(R.id.btn_add);
        Button btn_remove = findViewById(R.id.btn_remove);
        btn_order = findViewById(R.id.btn_order);

        tv_order_num = findViewById(R.id.tv_order_num);
        tv_detail_price = findViewById(R.id.tv_detail_price);

        // 从列表页获得传递的数据
        Bundle bundle = getIntent().getExtras();
        final String food_id = bundle.getString("food_id");
        final String food_name = bundle.getString("food_name");
        final String food_seller = bundle.getString("food_seller");

        final Float food_price = bundle.getFloat("food_price");
        String food_price_str = String.valueOf(food_price);

        tv_detail_price.setText(food_price_str);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (food_num >= 0){
                    btn_order.setEnabled(true);
                    String food_num_str = String.valueOf(++food_num);
                    Float food_total_prices = food_price*food_num;
                    LogUtils.d("food_num_str", food_num_str);
                    String food_total_prices_str = String.valueOf(food_total_prices);

                    tv_order_num.setText(food_num_str);
                    tv_detail_price.setText(food_total_prices_str);
                }

            }
        });

        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (food_num > 0) {
                    LogUtils.d("before", String.valueOf(food_num));
                    String food_num_str = String.valueOf(--food_num);
                    LogUtils.d("after", String.valueOf(food_num));

                    Float food_total_prices = food_price * food_num;
                    LogUtils.d("food_num_str", food_num_str);

                    String food_total_prices_str = String.valueOf(food_total_prices);
                    tv_order_num.setText(food_num_str);
                    tv_detail_price.setText(food_total_prices_str);
                    if (food_num == 0) {
                        btn_order.setEnabled(false);
                    }
                }
            }
        });

        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ToastUtils.show("我能点击", HomeDetailActivity.this);
                Order order = new Order();
//                BmobUser user = new BmobUser();
                BmobUser user = BmobUser.getCurrentUser();


                order.setOrder_food_id(food_id);
                LogUtils.d("order", food_id);

                order.setOrder_food_name(food_name);
                LogUtils.d("order", food_name);

                order.setOrder_food_num(food_num);
                LogUtils.d("order", String.valueOf(food_num));

                order.setOrder_food_price(food_price);
                LogUtils.d("order", String.valueOf(food_price));

                Float order_food_prices = food_price * food_num;
                order.setOrder_food_prices(order_food_prices);
                LogUtils.d("order", String.valueOf(order_food_prices));

                order.setOrder_user_id(user.getObjectId());
                LogUtils.d("order", user.getObjectId());

                order.setOrder_user_name(user.getUsername());
                LogUtils.d("order", user.getUsername());

                order.setOrder_food_seller(food_seller);

                LogUtils.d("order", food_seller);

                order.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if (e==null) {
                            ToastUtils.show("订购成功",HomeDetailActivity.this);

                        } else {
                            LogUtils.i("bmob", "失败："+e.getMessage()+","+e.getErrorCode());
                        }
                    }
                });

            }
        });



    }
}
