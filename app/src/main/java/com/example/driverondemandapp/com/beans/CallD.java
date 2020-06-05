package com.example.driverondemandapp.com.beans;

public class CallD {
    @Override
    public String toString() {
        return name+"   "+phone;
    }
    public CallD(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    String name;



    public CallD() {
    }

    String phone;
}
