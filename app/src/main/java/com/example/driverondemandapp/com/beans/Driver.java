package com.example.driverondemandapp.com.beans;

public class Driver {
    @Override
    public String toString() {
        return id+"  "+name;
    }

    String id;



    String name;

    public Driver(String id, String name, String email, String phone,String age, String gender, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.address = address;
        this.age = age;
    }

    String email;
    String phone;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge(){return age;}


    public void setAge(String age) {
        this.age = age;
    }

    String gender;
    String address;
    String age;
}
