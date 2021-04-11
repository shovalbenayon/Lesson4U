package com.example.lesson4u;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseLesson {
    public static void writeNewLesson(String teacherUID, String sub, String lev, int pr, String date, String time) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("lessons");
        LessonObj newLesson = new LessonObj(teacherUID, sub, lev, pr, date, time);
        myRef.push().setValue(newLesson);
    }

    public static DatabaseReference getLesson(String uid) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("lessons");
        return myRef.getRef().child(uid);
    }
}
