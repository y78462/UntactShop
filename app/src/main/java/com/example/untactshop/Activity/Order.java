package com.example.untactshop;

public class Order {

    private String uId;
    private String itemName;
    private String shopName;
    private String itemPrice;
    private String imageUrl;


    public Order(){

    }

    public Order(String uId, String itemName, String shopName, String itemPrice) {
        this.uId = uId;
        this.itemName = itemName;
        this.shopName = shopName;
        this.itemPrice = itemPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getuId() {
        return uId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getShopName() {
        return shopName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "uId='" + uId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", shopName='" + shopName + '\'' +
                ", itemPrice=" + itemPrice +
                '}';
    }
}

