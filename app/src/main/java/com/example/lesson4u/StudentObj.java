package com.example.lesson4u;

import android.text.Editable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class StudentObj {
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNum;
    private String city;
    private List<String> lessons;



    public StudentObj(){}

    public StudentObj( String mail , String fname , String lname , String city , String phone){
        this.city = city;
        this.email = mail;
        this.firstName = fname;
        this.lastName = lname;
        this.phoneNum = phone;

        lessons = new ArrayList<>();

    }


    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getCity() {
        return city;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setCity(String city) {
        this.city = city;
    }

//    public ArrayList<LessonObj> getLessons() { return lessons;  }
//
    public void addLesson(LessonObj lesson) {
//        lessons.add(lesson);
   }
//    boolean removeLesson(LessonObj lesson) {
//        return(lessons.remove(lesson));
//    }

}
