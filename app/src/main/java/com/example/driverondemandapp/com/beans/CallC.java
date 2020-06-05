package com.example.driverondemandapp.com.beans;

public class CallC {
    @Override
    public String toString() {
        return name+"   "+phone;
    }
    public CallC(String name, String phone) {
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



    public CallC() {
    }

    String phone;
}
