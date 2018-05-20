package com.example.zqy.myapplication.model;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;

/**管理员账户
 * Created by zqy on 18-3-26.
 */

public class AdminAccount extends BmobObject {
    private Float account_money;
    private BmobUser bmobUser;
    private Boolean account_state;
    private String seller_uid;

    public String getSeller_uid() {
        return seller_uid;
    }

    public void setSeller_uid(String seller_uid) {
        this.seller_uid = seller_uid;
    }

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
