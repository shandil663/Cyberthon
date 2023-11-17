package com.example.cyberthon;

public class helperforinsight {

    String Case_Description,Case_name,Case_date;

    public String getCase_Description() {
        return Case_Description;
    }

    public void setCase_Description(String case_Description) {
        Case_Description = case_Description;
    }

    public String getCase_name() {
        return Case_name;
    }

    public void setCase_name(String case_name) {
        Case_name = case_name;
    }

    public String getCase_date() {
        return Case_date;
    }

    public void setCase_date(String case_date) {
        Case_date = case_date;
    }

    public helperforinsight(String case_Description, String case_name, String case_date) {
        Case_Description = case_Description;
        Case_name = case_name;
        Case_date = case_date;
    }

    public helperforinsight() {
    }
}
