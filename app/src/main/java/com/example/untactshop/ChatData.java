package com.example.untactshop;

//DTO
public class ChatData {

    private String msg;
    private String nickname;
    private String time;
    private String shop_name;

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

    public void setShop_name(String shop_name) { this.shop_name = shop_name; }

}