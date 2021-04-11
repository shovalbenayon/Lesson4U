package com.example.lesson4u;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class FirebaseTeacher {

    public static void writeNewTeacher(String uid, String mail, String phone, String fname, String lname, String city){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("teachers");
        TeacherObj newTeacher = new TeacherObj(mail , fname , lname , city , phone);
        myRef.child(uid).setValue(newTeacher);
    }

    public static DatabaseReference getTeacher(String uid){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("teachers");
        return myRef.getRef().child(uid);
    }
}
