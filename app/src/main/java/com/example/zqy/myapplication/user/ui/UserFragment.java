package com.example.zqy.myapplication.user.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zqy.myapplication.R;
import com.example.zqy.myapplication.account.ui.AuthenticatorActivity;
import com.example.zqy.myapplication.model.Order;
import com.example.zqy.myapplication.model.UserAccount;
import com.example.zqy.myapplication.settings.ui.SettingsActivity;
import com.example.zqy.myapplication.utils.InitToolBarUtils;
import com.example.zqy.myapplication.utils.LogUtils;
import com.example.zqy.myapplication.utils.ToastUtils;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by zqy on 17-10-25.
 */

public class UserFragment extends Fragment {

    private LinearLayout mLinearLayout;
    private TextView tv_user_name, tv_money;
    private Button btn_money_init, btn_money_query;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        InitToolBarUtils.initToolbar(this, R.id.include_toolbar, R.string.user);
        mLinearLayout = view.findViewById(R.id.user_layout);
        tv_user_name = view.findViewById(R.id.tv_user_name);

        tv_money = view.findViewById(R.id.tv_money);
        btn_money_init = view.findViewById(R.id.btn_money_init);
        btn_money_query = view.findViewById(R.id.btn_money_query);

        btn_money_init.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserAccount userAccount = new UserAccount();
                userAccount.setAccount_money(1000f);
                userAccount.setAccount_state(true);

                // 一对一关系
                BmobUser bmobUser = BmobUser.getCurrentUser();
                userAccount.setBmobUser(bmobUser);
                LogUtils.d("money", String.valueOf(userAccount.getAccount_money()));
                ToastUtils.show(String.valueOf(userAccount.getAccount_money()), getActivity());

                userAccount.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if (e == null) {
                            ToastUtils.show("创建成功", getActivity());

                        } else {
                            LogUtils.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                        }
                    }
                });

            }
        });

        btn_money_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final UserAccount userAccount = new UserAccount();
                final BmobUser bmobUser = BmobUser.getCurrentUser();

                final BmobQuery<Order> query = new BmobQuery<>();
                query.addWhereEqualTo("order_user_id", bmobUser.getObjectId());

                query.findObjects(new FindListener<Order>() {
                    @Override
                    public void done(List<Order> list, BmobException e) {
                        if (e == null) {
                            Float all_prices = 0.0f;
                            Float prices;
                            ToastUtils.show("共"+ list.size() + "条数据", getActivity());
                            for (Order order : list) {
                                prices = order.getOrder_food_prices();
                                all_prices += prices;
                            }
                            final Float final_all_prices = all_prices;
                            ToastUtils.show(String.valueOf(all_prices), getActivity());


                            BmobQuery<UserAccount> query2 = new BmobQuery<>();
                            query2.addWhereEqualTo("bmobUser", bmobUser.getObjectId());

                            query2.findObjects(new FindListener<UserAccount>() {
                                @Override
                                public void done(List<UserAccount> list, BmobException e) {
                                    if (e == null) {

                                        for (UserAccount userAccount : list) {
                                            Float account_money = userAccount.getAccount_money();
                                            ToastUtils.show(String.valueOf(account_money), getActivity());

                                            Float final_account_money = account_money - final_all_prices;
                                            tv_money.setText(String.valueOf(final_account_money));


                                        }
                                    } else {
                                        LogUtils.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                                    }
                                }
                            });



                        } else {
                            LogUtils.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                        }
                    }
                });

            }
        });




        BmobUser bmobUser = BmobUser.getCurrentUser();
        if(bmobUser != null){
            // 允许用户使用应用
            tv_user_name.setText(bmobUser.getUsername());


        }else{
            //缓存用户对象为空时， 可打开用户注册界面…
            mLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), AuthenticatorActivity.class);
                    startActivity(intent);
                }
            });
        }
        return view;
    }

    // 刷新页面数据

//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        if (!hidden) {
//            firstRefresh();
//        }
//    }
//
//    private void firstRefresh() {
//        BmobUser bmobUser = BmobUser.getCurrentUser();
//        tv_user_name.setText(bmobUser.getUsername());
//    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_toolbar_user, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.app_bar_user:
                Intent intent = new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

}
