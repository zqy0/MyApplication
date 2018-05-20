package com.example.zqy.myapplication.home.ui;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.zqy.myapplication.BaseActivity;
import com.example.zqy.myapplication.R;
import com.example.zqy.myapplication.account.ui.AuthenticatorActivity;
import com.example.zqy.myapplication.model.AdminAccount;
import com.example.zqy.myapplication.model.Order;
import com.example.zqy.myapplication.model.UserAccount;
import com.example.zqy.myapplication.utils.LogUtils;
import com.example.zqy.myapplication.utils.ToastUtils;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by zqy on 17-11-2.
 * 菜品详情页
 * btn_add实现菜品增加，btn_remove实现减少
 */

public class HomeDetailActivity extends BaseActivity {

    private Button btn_order;

    private TextView tv_order_num,tv_detail_price;
    private int food_num = 1;
    static int status = 0;


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
        assert bundle != null;
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

                BmobUser user = BmobUser.getCurrentUser();
                if (user != null) {

                    Float order_food_prices = food_price * food_num;
                    // 更新账户余额
                    update_account_money(order_food_prices);


                } else {
                    //缓存用户对象为空时， 可打开用户注册界面…
                    Intent intent = new Intent(HomeDetailActivity.this, AuthenticatorActivity.class);
                    startActivity(intent);
                    ToastUtils.show("请注册或登录", HomeDetailActivity.this);
                }
            }
        });
    }

    /*
    更新账户金额 先获取当前用户，通过用户id关联到UserAccount表里的bmobUser字段，从而获得当前用户UserAccount的数据
    f
     */
    private void update_account_money(final Float order_prices) {
        final BmobUser user = BmobUser.getCurrentUser();
        BmobQuery<UserAccount> query = new BmobQuery<>();
        query.addWhereEqualTo("bmobUser", user.getObjectId());
        query.findObjects(new FindListener<UserAccount>() {
            @Override
            public void done(List<UserAccount> list, BmobException e) {
                if (e ==null) {
                    if (list != null && list.size() > 0) {
                        UserAccount userAccount = list.get(0);
                        Float account_money = userAccount.getAccount_money();
                        LogUtils.d("Home", String.valueOf(account_money));
                        LogUtils.d("Home", String.valueOf(order_prices));

                        Float current_money = account_money - order_prices;
                        // 如果用户账户金额足够
                        if (current_money >= 0.0) {
                            // 更新用户账户余额
                            userAccount.setAccount_money(current_money);
                            LogUtils.d("当前账户金额", String.valueOf(userAccount.getAccount_money()));

                            userAccount.update(new UpdateListener() {
                                @Override
                                public void done(BmobException e) {
                                    if (e == null) {
                                        LogUtils.d("Home","成功");
                                    } else {
                                        LogUtils.d("Home", "失败");
                                    }
                                }
                            });

                            // 更新商户账户余额
                            BmobQuery<AdminAccount> adminAccountBmobQuery = new BmobQuery<>();
                            // 从列表页获得传递的数据
                            Bundle bundle = getIntent().getExtras();
                            assert bundle != null;
                            String food_seller = bundle.getString("food_seller");

                            adminAccountBmobQuery.addWhereEqualTo("seller_uid", food_seller);
                            adminAccountBmobQuery.findObjects(new FindListener<AdminAccount>() {
                                @Override
                                public void done(List<AdminAccount> list, BmobException e) {
                                    if (list != null && list.size() > 0) {
                                        AdminAccount adminAccount = list.get(0);
                                        Float admin_account_money = adminAccount.getAccount_money();
                                        Float admin_current_money = admin_account_money + order_prices;
                                        // 设置商户增加的金额
                                        adminAccount.setAccount_money(admin_current_money);
                                        adminAccount.update(new UpdateListener() {
                                            @Override
                                            public void done(BmobException e) {
                                                if (e == null) {
                                                    LogUtils.d("Home","商户金额变动成功");
                                                } else {
                                                    LogUtils.d("Home","商户金额变动失败");
                                                }
                                            }
                                        });
                                    }

                                }
                            });



                            // 提交订单信息
                            push_order();

                        } else {
                            ToastUtils.show("账户余额不足",HomeDetailActivity.this);
                        }
                    }
                }
            }
        });
    }

    /*
    当账户余额足够时提交订单数据到数据库，否则不提交
     */
    private void push_order() {
        BmobUser user = BmobUser.getCurrentUser();

        // 从列表页获得传递的数据
        Bundle bundle = getIntent().getExtras();

        assert bundle != null;
        String food_id = bundle.getString("food_id");
        String food_name = bundle.getString("food_name");
        String food_seller = bundle.getString("food_seller");
        Float food_price = bundle.getFloat("food_price");

        //    订购菜品
        Order order = new Order();

        order.setOrder_food_id(food_id);
        LogUtils.d("order", food_id);

        order.setOrder_food_name(food_name);
        LogUtils.d("order", food_name);

        order.setOrder_food_num(food_num);
        LogUtils.d("order", String.valueOf(food_num));

        order.setOrder_food_price(food_price);
        LogUtils.d("order", String.valueOf(food_price));

        // 订单总额
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

}
