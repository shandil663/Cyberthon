package com.example.cyberthon;

public class Modal {
    private String  name,email,mobile,house,location;

    public Modal() {
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Modal(String name, String email, String mobile, String house, String location) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.house = house;
        this.location = location;
    }
}
