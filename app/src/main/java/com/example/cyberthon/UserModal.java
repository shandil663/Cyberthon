package com.example.cyberthon;

public class UserModal {
    String uid;
    String name,email;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public UserModal(String uid, String name, String email) {
        this.uid = uid;
        this.name = name;
        this.email = email;
    }

    public UserModal() {
    }

    @Override
    public String toString() {
        return "UserModal{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
