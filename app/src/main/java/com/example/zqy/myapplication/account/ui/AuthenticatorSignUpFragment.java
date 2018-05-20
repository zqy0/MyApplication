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
import com.example.zqy.myapplication.utils.FragmentUtils;
import com.example.zqy.myapplication.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by zqy on 18-3-26.
 * 注册界面
 */

public class AuthenticatorSignUpFragment extends Fragment {

    @BindView(R.id.et_sign_up_account)
    EditText etSignUpAccount;
    @BindView(R.id.et_sign_up_email)
    EditText etSignUpEmail;
    @BindView(R.id.et_sign_up_password)
    EditText etSignUpPassword;
    @BindView(R.id.btn_signup)
    AppCompatButton btnSignup;
    @BindView(R.id.tv_link_login)
    TextView tvLinkLogin;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_auth_sign_up, container, false);
        unbinder = ButterKnife.bind(this, view);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        // 跳转到登录界面
        tvLinkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentUtils.add(new AuthenticatorSignInFragment(), getActivity(), android.R.id.content);
            }
        });

        return view;
    }

    private void signup() {
        BmobUser bmobUser = new BmobUser();

        String name = String.valueOf(etSignUpAccount.getText());
        String password = String.valueOf(etSignUpPassword.getText());
        String email = String.valueOf(etSignUpEmail.getText());

        bmobUser.setUsername(name);
        bmobUser.setPassword(password);
        bmobUser.setEmail(email);

        bmobUser.signUp(new SaveListener<BmobUser>() {
            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                if (e == null) {
                    ToastUtils.show("注册成功", getActivity());

                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                } else {
                    ToastUtils.show("注册失败", getActivity());
                }
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
