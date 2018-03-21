package com.example.zqy.myapplication.model;

import cn.bmob.v3.BmobObject;

/**
 * Created by zqy on 17-12-7.
 */

public class Order extends BmobObject {
    private String order_food_id;
    private String order_food_name;
    private Float order_food_price;
    private Integer order_food_num;
    private String order_food_seller;
    // 不能超过20个字符
    private Float order_food_prices;
    private String order_user_id;
    private String order_user_name;


    public Float getOrder_food_prices() {
        return order_food_prices;
    }

    public void setOrder_food_prices(Float order_food_prices) {
        this.order_food_prices = order_food_prices;
    }


    public String getOrder_user_name() {
        return order_user_name;
    }

    public void setOrder_user_name(String order_user_name) {
        this.order_user_name = order_user_name;
    }

    public String getOrder_food_id() {
        return order_food_id;
    }

    public void setOrder_food_id(String order_food_id) {
        this.order_food_id = order_food_id;
    }

    public String getOrder_food_name() {
        return order_food_name;
    }

    public void setOrder_food_name(String order_food_name) {
        this.order_food_name = order_food_name;
    }

    public Float getOrder_food_price() {
        return order_food_price;
    }

    public void setOrder_food_price(Float order_food_price) {
        this.order_food_price = order_food_price;
    }

    public Integer getOrder_food_num() {
        return order_food_num;
    }

    public void setOrder_food_num(Integer order_food_num) {
        this.order_food_num = order_food_num;
    }

    public String getOrder_food_seller() {
        return order_food_seller;
    }

    public void setOrder_food_seller(String order_food_seller) {
        this.order_food_seller = order_food_seller;
    }

    public String getOrder_user_id() {
        return order_user_id;
    }

    public void setOrder_user_id(String order_user_id) {
        this.order_user_id = order_user_id;
    }
}
