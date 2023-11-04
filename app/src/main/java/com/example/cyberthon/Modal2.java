package com.example.cyberthon;

public class Modal2
{

    private String  title,photourl,imageid,currentuserid;

    public Modal2() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }

    public String getImageid() {
        return imageid;
    }

    public void setImageid(String imageid) {
        this.imageid = imageid;
    }

    public String getCurrentuserid() {
        return currentuserid;
    }

    public void setCurrentuserid(String currentuserid) {
        this.currentuserid = currentuserid;
    }

    public Modal2(String title, String photourl, String imageid, String currentuserid) {
        this.title = title;
        this.photourl = photourl;
        this.imageid = imageid;
        this.currentuserid = currentuserid;
    }
}
