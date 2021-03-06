package com.example.zqy.myapplication.account.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.zqy.myapplication.MainActivity;
import com.example.zqy.myapplication.R;
import com.example.zqy.myapplication.home.ui.HomeFragment;
import com.example.zqy.myapplication.utils.FragmentUtils;
import com.example.zqy.myapplication.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by zqy on 18-3-24.
 */


public class AuthenticatorSignInFragment extends Fragment {


    @BindView(R.id.et_sign_in_account)
    EditText etSignInAccount;
    @BindView(R.id.et_sign_in_password)
    EditText etSignInPassword;
    @BindView(R.id.btn_login)
    AppCompatButton btnLogin;
    @BindView(R.id.tv_link_sign_up)
    TextView tvLinkSignUp;
    Unbinder unbinder;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_auth_sign_in, container, false);
        // 绑定
        unbinder = ButterKnife.bind(this, view);

        // 登录
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        // 没有账号跳转到注册页面
        tvLinkSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentUtils.add(new AuthenticatorSignUpFragment(), getActivity(), android.R.id.content);
            }
        });

        return view;
    }

    private void login() {

        BmobUser bmobUser = new BmobUser();

        String name = String.valueOf(etSignInAccount.getText());
        String password = String.valueOf(etSignInPassword.getText());

        bmobUser.setUsername(name);
        bmobUser.setPassword(password);

        bmobUser.login(new SaveListener<BmobUser>() {
            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                if(e==null){
                    ToastUtils.show("登录成功", getActivity());

                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);

                    //通过BmobUser user = BmobUser.getCurrentUser()获取登录成功后的本地用户信息
                    //如果是自定义用户对象MyUser，可通过MyUser user = BmobUser.getCurrentUser(MyUser.class)获取自定义用户信息
                }else{
                    ToastUtils.show("登录失败", getActivity());
                }

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // 解除绑定
        unbinder.unbind();
    }
}
