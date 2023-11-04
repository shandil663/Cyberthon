package com.example.cyberthon;

import androidx.annotation.NonNull;

public class Messagemodal {
    String message ,name,key;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Messagemodal(String message, String name, String key) {
        this.message = message;
        this.name = name;
        this.key = key;
    }

    public Messagemodal() {
    }

    @NonNull
    @Override
    public String toString() {
        return "Messagemodal{"+
                "message='"+ message + '\''+
                ", name='" + name + '\'' +
                ", key='" + key + '\'' +'}';
    }
}
