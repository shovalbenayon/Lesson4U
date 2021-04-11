package com.example.lesson4u;

import android.text.Editable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseStudent {

    public static void writeNewStudent(String uid, String mail, String phone, String fname, String lname, String city){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("students");
        StudentObj newStudent = new StudentObj(mail , fname , lname , city , phone);
        myRef.child(uid).setValue(newStudent);
    }

    public static DatabaseReference getStudent(String uid){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("students");
        return myRef.getRef().child(uid);
    }
}
