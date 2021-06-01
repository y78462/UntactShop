package com.example.untactshop;

//DTO
public class ChatData {

    private String msg;
    private String nickname;
    private String time;
    private String shop_name;
    private String identify;
    private int viewType;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }

    public void setViewType(int viewType){this.viewType = viewType;}

    public int getViewType(){return viewType;}

    public ChatData(String msg, String nickname, String time, String shop_name, String identify, int viewType){
        this.msg = msg;
        this.nickname = nickname;
        this.time = time;
        this.shop_name = shop_name;
        this.identify = identify;
        this.viewType = viewType;
    }

    public ChatData(){};
}