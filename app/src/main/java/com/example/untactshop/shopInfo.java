package com.example.untactshop;

import io.realm.RealmObject;

public class shopInfo extends RealmObject {

    private String station_name; //역이름
    private String shop_name; //가게이름
    private String room_name; //호수
    private String category;

    //좌표
    private double x1;
    private double x2;
    private double y1;
    private double y2;

    public shopInfo() {
    }

    public shopInfo(String station_name, String shop_name, String room_name, String category) {
        this.station_name = station_name;
        this.shop_name = shop_name;
        this.room_name = room_name;
        this.category = category;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }

    public String getStation_name() {
        return station_name;
    }

    public String getShop_name() {
        return shop_name;
    }

    public String getRoom_name() {
        return room_name;
    }

    public String getCategory() {
        return category;
    }

    public double getX1() {
        return x1;
    }

    public double getX2() {
        return x2;
    }

    public double getY1() {
        return y1;
    }

    public double getY2() {
        return y2;
    }


}
