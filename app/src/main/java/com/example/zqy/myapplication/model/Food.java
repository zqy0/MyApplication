package com.example.zqy.myapplication.model;

import cn.bmob.v3.BmobObject;

/**
 * Created by zqy on 17-11-3.
 */

public class Food extends BmobObject {

    private String food_name;
    private Float food_price;
    private String food_describe;
    private String food_seller;
    private Float food_stars;

    public Float getFood_stars() {
        return food_stars;
    }

    public void setFood_stars(Float food_stars) {
        this.food_stars = food_stars;
    }

    public String getFood_seller() {
        return food_seller;
    }

    public void setFood_seller(String food_seller) {
        this.food_seller = food_seller;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public Float getFood_price() {
        return food_price;
    }

    public void setFood_price(Float food_price) {
        this.food_price = food_price;
    }

    public String getFood_describe() {
        return food_describe;
    }

    public void setFood_describe(String food_describe) {
        this.food_describe = food_describe;
    }
}
