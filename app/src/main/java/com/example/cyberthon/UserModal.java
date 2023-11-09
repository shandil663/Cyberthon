package com.example.cyberthon;

public class UserModal {
    String uid;
    String email;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserModal(String uid, String email) {
        this.uid = uid;
        this.email = email;
    }

    public UserModal() {
    }

    @Override
    public String toString() {
        return "UserModal{" +
                "uid='" + uid + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
