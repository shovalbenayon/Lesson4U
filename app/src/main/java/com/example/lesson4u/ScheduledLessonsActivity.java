package com.example.lesson4u;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class ScheduledLessonsActivity extends AppCompatActivity {

    private FirebaseAuth auth = FirebaseAuth.getInstance();
    final String TAG = "schedLessons";
    ArrayList<LessonObj> lessons;
    ArrayList<String> lessonIds;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduled_lessons);

        recyclerView = findViewById(R.id.recyclerView);

        Intent intent = getIntent();
        lessons =  intent.getExtras().getParcelableArrayList("lessons");
        lessonIds = intent.getExtras().getStringArrayList("lessonIds");
        Log.d(TAG, "array size " + lessons.size());



        MyAdapterForScheduledLessons myAdapter = new MyAdapterForScheduledLessons(this, lessons, lessonIds);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.MainPage:
                Toast.makeText(this, "Go to main page", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.Logout:
                Toast.makeText(this, "Disconnecting", Toast.LENGTH_SHORT).show();
                auth.signOut();
                Intent intent1 = new Intent(getApplicationContext(), LoginOrRegister.class);
                startActivity(intent1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}