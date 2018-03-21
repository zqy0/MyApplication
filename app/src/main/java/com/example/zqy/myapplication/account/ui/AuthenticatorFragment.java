package com.example.zqy.myapplication.account.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.zqy.myapplication.MainActivity;
import com.example.zqy.myapplication.R;
import com.example.zqy.myapplication.utils.ToastUtils;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by zqy on 17-12-4.
 */

public class AuthenticatorFragment extends Fragment {

    BmobUser bmobUser = new BmobUser();

    private EditText NameEditText;
    private EditText PasswordEditText;
    private Button button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_authenticator, container, false);

        button = view.findViewById(R.id.btn_login);
        NameEditText = view.findViewById(R.id.tv_login_username);
        PasswordEditText = view.findViewById(R.id.tv_login_password);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        return view;
    }

    private void login() {

        final String name = String.valueOf(NameEditText.getText());
        String password = String.valueOf(PasswordEditText.getText());

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

}
