package com.example.untactshop;

public class MemberInfo {

    private String name;
    private String phone;
    private String bday;
    private String address;

    public MemberInfo(String name,String phone,String bday,String address)
    {
        this.name = name;
        this.phone = phone;
        this.bday = bday;
        this.address = address;
    }
    public MemberInfo()
    {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setBday(String bday) {
        this.bday = bday;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getBday() {
        return bday;
    }

    public String getAddress() {
        return address;
    }
}
