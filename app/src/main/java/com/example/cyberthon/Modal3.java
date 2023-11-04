package com.example.cyberthon;

public class Modal3 {
    private String algname,photo,id,current,mob,loc;

    public String getAlgname() {
        return algname;
    }

    public void setAlgname(String algname) {
        this.algname = algname;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public Modal3() {
    }

    public Modal3(String algname, String photo, String id, String current, String mob, String loc) {
        this.algname = algname;
        this.photo = photo;
        this.id = id;
        this.current = current;
        this.mob = mob;
        this.loc = loc;
    }
}
