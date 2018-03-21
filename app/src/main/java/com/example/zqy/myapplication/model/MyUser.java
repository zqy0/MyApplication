package com.example.zqy.myapplication.model;

import cn.bmob.v3.BmobUser;

/**
 * Created by zqy on 17-12-4.
 */

public class MyUser extends BmobUser {
    private static final long serialVersionUID = 1L;
    private Integer age;
    private Integer num;
    private Boolean sex;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return getUsername()+"\n"+getObjectId()+"\n"+age+"\n"+num+"\n"+getSessionToken()+"\n"+getEmailVerified();
    }
}
