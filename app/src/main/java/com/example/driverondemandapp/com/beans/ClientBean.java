package com.example.driverondemandapp.com.beans;

public class ClientBean {
    @Override
    public String toString() {
        return id+"                  "+phone;
    }

    public ClientBean(String id, String name, String email, String phone, String address) {

        this.id = id;

        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public ClientBean(String id, String name) {
    }

    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    String name;
    String email;
    String phone;
    String address;


}
