package com.example.untactshop;

import android.content.ClipData;

import java.io.Serializable;

public class ItemInfo implements Serializable, Comparable<ItemInfo> {

    private String title;
    private String shop_name;
    private String category;
    private String price;
    private String photoUrl;
    private String Key1;
    private String Key2;
    private String Key3;

    public ItemInfo(){}


    @Override
    public String toString() {
        return "ItemInfo{" +
                "title='" + title + '\'' +
                ", shop_name='" + shop_name + '\'' +
                ", category='" + category + '\'' +
                ", price='" + price + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", Key1='" + Key1 + '\'' +
                ", Key2='" + Key2 + '\'' +
                ", Key3='" + Key3 + '\'' +
                '}';
    }

    public String getKey1() {
        return Key1;
    }

    public void setKey1(String key1) {
        Key1 = key1;
    }

    public String getKey2() {
        return Key2;
    }

    public void setKey2(String key2) {
        Key2 = key2;
    }

    public String getKey3() {
        return Key3;
    }

    public void setKey3(String key3) {
        Key3 = key3;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getShop_name() {
        return shop_name;
    }

    public String getCategory() {
        return category;
    }

    public String getPrice() {
        return price;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public ItemInfo(String title, String shop_name, String category, String price, String photoUrl,String Key1,String Key2,String Key3) {
        this.title = title;
        this.shop_name = shop_name;
        this.category = category;
        this.price = price;
        this.photoUrl = photoUrl;
        this.Key1 = Key1;
        this.Key2 = Key2;
        this.Key3 = Key3;
    }


    @Override
    public int compareTo(ItemInfo itemInfo) {
        int targetPrice = Integer.parseInt(itemInfo.getPrice());

        if (Integer.parseInt(this.price) == targetPrice) return 0;
        else if (Integer.parseInt(this.price)>targetPrice) return 1;
        else
            return -1;
    }

}
