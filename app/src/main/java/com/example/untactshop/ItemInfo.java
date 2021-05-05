package com.example.untactshop;

public class ItemInfo {
    String title;
    String shop_name;
    String category;
    String price;
    String photoUrl;

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

    public ItemInfo(String title, String shop_name, String category, String price, String photoUrl) {
        this.title = title;
        this.shop_name = shop_name;
        this.category = category;
        this.price = price;
        this.photoUrl = photoUrl;
    }
    public ItemInfo(){}
}
