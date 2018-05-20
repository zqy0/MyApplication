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
import com.example.zqy.myapplication.model.AdminAccount;
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
    private TextView tv_user_name;
    private Button btn_money_init, btn_money_query, btn_money_admin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        InitToolBarUtils.initToolbar(this, R.id.include_toolbar, R.string.user);
        mLinearLayout = view.findViewById(R.id.user_layout);
        tv_user_name = view.findViewById(R.id.tv_user_name);


        btn_money_init = view.findViewById(R.id.btn_money_init);
        btn_money_query = view.findViewById(R.id.btn_money_query);

        btn_money_admin = view.findViewById(R.id.btn_money_admin);

        btn_money_init.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // 一对一关系
                BmobUser bmobUser = BmobUser.getCurrentUser();
                if (bmobUser != null) {
                    UserAccount userAccount = new UserAccount();
                    userAccount.setAccount_money(1000f);
                    userAccount.setAccount_state(true);

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
                } else {
                    ToastUtils.show("用户未登录，请登录", getActivity());
                }
            }
        });

        btn_money_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 一对一关系
                BmobUser bmobUser = BmobUser.getCurrentUser();

                if (bmobUser != null) {
                    AdminAccount adminAccount = new AdminAccount();
                    adminAccount.setAccount_money(0f);
                    adminAccount.setAccount_state(true);
                    adminAccount.setBmobUser(bmobUser);
                    adminAccount.setSeller_uid(bmobUser.getUsername());
                    LogUtils.d("money", String.valueOf(adminAccount.getAccount_money()));
                    ToastUtils.show(String.valueOf(adminAccount.getAccount_money()), getActivity());

                    adminAccount.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if (e == null) {
                                ToastUtils.show("创建成功", getActivity());

                            } else {
                                LogUtils.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                            }
                        }
                    });
                } else {
                    ToastUtils.show("用户未登录，请登录", getActivity());
                }
            }
        });





        BmobUser bmobUser = BmobUser.getCurrentUser();
        if(bmobUser != null){
            // 允许用户使用应用
            btn_money_query.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BmobUser user = BmobUser.getCurrentUser();
                    BmobQuery<UserAccount> query = new BmobQuery<>();
                    query.addWhereEqualTo("bmobUser", user.getObjectId());
                    query.findObjects(new FindListener<UserAccount>() {
                        @Override
                        public void done(List<UserAccount> list, BmobException e) {
                            if (e == null) {
                                // 获取单条账户数据
                                if (list != null && list.size() > 0) {
                                    UserAccount userAccount = list.get(0);
                                    ToastUtils.show("当前余额" +userAccount.getAccount_money(),getActivity());

                                }
                            }
                        }
                    });
                }
            });

            tv_user_name.setText(bmobUser.getUsername());

        }else{
            //缓存用户对象为空时，
            btn_money_query.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToastUtils.show("用户未登录，请登录", getActivity());
                }
            });
            //可打开用户注册界面…                                   }
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
