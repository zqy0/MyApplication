package com.example.zqy.myapplication.model;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;

/**
 * Created by zqy on 17-12-13.
 */

public class UserAccount extends BmobObject {


    private Float account_money;
//    private MyUser myUser;
    private BmobUser bmobUser;
    private Boolean account_state;

    public Float getAccount_money() {
        return account_money;
    }

    public void setAccount_money(Float account_money) {
        this.account_money = account_money;
    }

    public BmobUser getBmobUser() {
        return bmobUser;
    }

    public void setBmobUser(BmobUser bmobUser) {
        this.bmobUser = bmobUser;
    }

    public Boolean getAccount_state() {
        return account_state;
    }

    public void setAccount_state(Boolean account_state) {
        this.account_state = account_state;
    }
}
