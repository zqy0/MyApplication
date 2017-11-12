package com.example.zqy.myapplication.model;

/**
 * Created by zqy on 17-10-29.
 */

public class HomeDetailBean {
    private int foodId;
    private String foodName;
    private String publisher;
    private String publishedTime;
    private boolean status;
    private double foodPrice;
    private String description;


    public HomeDetailBean(int foodId, String foodName, String publisher, String publishedTime, boolean status, double foodPrice, String description) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.publisher = publisher;
        this.publishedTime = publishedTime;
        this.status = status;
        this.foodPrice = foodPrice;
        this.description = description;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishedTime() {
        return publishedTime;
    }

    public void setPublishedTime(String publishedTime) {
        this.publishedTime = publishedTime;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
