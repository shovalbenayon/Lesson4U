package com.example.lesson4u;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class removeLesson extends AppCompatActivity {

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();
    DatabaseReference myRef;
    DatabaseReference studRef, teacherRef;
    DatabaseReference lessonRef;
    TextView teacherName, studentName, price, time, subject, level;
    Button remove;
    LessonObj lesson;
    String lessonID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_lesson);

        teacherName = findViewById(R.id.tvTeacherName2);
        studentName = findViewById(R.id.tvStudentName3);
        price = findViewById(R.id.pp2);
        time = findViewById(R.id.tvTime2);
        subject = findViewById(R.id.tvSubject2);
        level = findViewById(R.id.tvLevel2);
        remove = findViewById(R.id.button4);

        lesson = getIntent().getParcelableExtra("lesson");
        lessonID = getIntent().getStringExtra("id");

        price.setText("Price per hour: " + lesson.getPrice() + "₪");
        time.setText("Time: " + lesson.getTime());
        subject.setText("Subject: " + lesson.getSubject());
        level.setText("Level: " + lesson.getLevel());

        teacherRef = database.getReference("teachers").child(lesson.getTeacherUID());
        studRef = database.getReference("students").child(lesson.getStudentUID());
        lessonRef = database.getReference("lessons").child(lessonID);

        teacherRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                TeacherObj t = dataSnapshot.getValue(TeacherObj.class);
                String teacher_name = t.getFirstName();
                teacherName.setText("Teacher: " + teacher_name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        studRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                StudentObj s = dataSnapshot.getValue(StudentObj.class);
                String teacher_name = s.getFirstName();
                studentName.setText("Student: " + teacher_name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studRef.child("lessons").child(lessonID).removeValue().addOnCompleteListener(new  OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(removeLesson.this, "lesson removed from student", Toast.LENGTH_LONG);
                    }
                });
                teacherRef.child("lessons").child(lessonID).removeValue().addOnCompleteListener(new  OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(removeLesson.this, "lesson removed from teacher", Toast.LENGTH_LONG);
                    }
                });
                lessonRef.removeValue().addOnCompleteListener(new  OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(removeLesson.this, "lesson removed from DB", Toast.LENGTH_LONG);
                    }
                });
                Intent intent = new Intent(removeLesson.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}