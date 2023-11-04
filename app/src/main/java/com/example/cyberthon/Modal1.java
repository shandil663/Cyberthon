package com.example.cyberthon;

public class Modal1 {
    private String place,datefrom,dateto,desc ,loc2,nature;

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDatefrom() {
        return datefrom;
    }

    public void setDatefrom(String datefrom) {
        this.datefrom = datefrom;
    }

    public String getDateto() {
        return dateto;
    }

    public void setDateto(String dateto) {
        this.dateto = dateto;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLoc2() {
        return loc2;
    }

    public void setLoc2(String loc2) {
        this.loc2 = loc2;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public Modal1(String place, String datefrom, String dateto, String desc, String loc2, String nature) {
        this.place = place;
        this.datefrom = datefrom;
        this.dateto = dateto;
        this.desc = desc;
        this.loc2 = loc2;
        this.nature = nature;
    }

    public Modal1() {
    }
}

