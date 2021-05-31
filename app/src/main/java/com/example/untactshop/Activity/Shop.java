package com.example.untactshop.Activity;

import java.io.Serializable;

public class Shop implements Serializable {

    private String category;
    private String location;
    private String shop_name;

    @Override
    public String toString() {
        return "ShopInfo{" +
                "shop_name" + shop_name + '\'' +
                "category='" + category + '\'' +
                ", location='" + location +'\'' +
                '}';
    }

    public Shop(){

    }
    public void setShopname(String shop_name) {
        this.shop_name = shop_name;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }
    public String getShopname() {
        return shop_name;
    }
    public String getLocation() {
        return location;
    }


    public Shop(String category, String location, String name) {
        this.category = category;
        this.location = location;
        this.shop_name = name;
    }
}


