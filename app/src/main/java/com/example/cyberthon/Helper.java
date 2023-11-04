package com.example.cyberthon;

public class Helper {

private String  Case_Title,Case_Closing_date,Case_category,Start,Suspect,Victim,Case_name,Case_coordinates,Case_Link;

    public String getCase_Title() {
        return Case_Title;
    }

    public void setCase_Title(String case_Title) {
        Case_Title = case_Title;
    }

    public String getCase_Closing_date() {
        return Case_Closing_date;
    }



    public void setCase_Closing_date(String case_Closing_date) {
        Case_Closing_date = case_Closing_date;
    }

    public String getCase_category() {
        return Case_category;
    }

    public void setCase_category(String case_category) {
        Case_category = case_category;
    }

    public String getStart() {
        return Start;
    }

    public void setStart(String start) {
        Start = start;
    }

    public String getSuspect() {
        return Suspect;
    }

    public void setSuspect(String suspect) {
        Suspect = suspect;
    }

    public String getVictim() {
        return Victim;
    }

    public void setVictim(String victim) {
        Victim = victim;
    }

    public String getCase_name() {
        return Case_name;
    }

    public void setCase_name(String case_name) {
        Case_name = case_name;
    }

    public String getCase_coordinates() {
        return Case_coordinates;
    }

    public void setCase_coordinates(String case_coordinates) {
        Case_coordinates = case_coordinates;
    }

    public String getCase_Link() {
        return Case_Link;
    }

    public void setCase_Link(String case_Link) {
        Case_Link = case_Link;
    }

    public Helper(String case_Title, String case_Closing_date, String case_category, String start, String suspect, String victim, String case_name, String case_coordinates, String case_Link) {
        Case_Title = case_Title;
        Case_Closing_date = case_Closing_date;
        Case_category = case_category;
        Start = start;
        Suspect = suspect;
        Victim = victim;
        Case_name=case_name;
        Case_Link=case_Link;
        Case_coordinates=case_coordinates;

    }



    public Helper() {
    }
}
