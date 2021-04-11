package com.example.lesson4u;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class AddLessonActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener  {
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private String currentUser;
    private Button bAddLesson, date;
    private String chosenDate = "";
    private Spinner eTime;
//    private EditText eDate;
    private Spinner spLessonSubjects;
    private Spinner spLevel;
    private EditText price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lesson);
        currentUser = auth.getInstance().getCurrentUser().getUid();
        bAddLesson = findViewById(R.id.addLessonBtn);
        eTime = findViewById(R.id.chooseTime);
        eTime.setPrompt("Choose time");
//        eDate = findViewById(R.id.LessonDate);
        date = findViewById(R.id.date);
        date.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Calendar systemCalendar = Calendar.getInstance();
                int year = systemCalendar.get(Calendar.YEAR);
                int month = systemCalendar.get(Calendar.MONTH);
                int day = systemCalendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePiker = new DatePickerDialog(AddLessonActivity.this, AddLessonActivity.this, year, month, day);
                datePiker.show();
            }
        });
        spLessonSubjects = findViewById(R.id.chooseLessonSubSpinner);
        spLevel = findViewById(R.id.chooseLevelSpinner);
        price = findViewById(R.id.LessonPrice);

        bAddLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("lessons");

                LessonObj newLesson = new LessonObj(currentUser, spLessonSubjects.getSelectedItem().toString(), spLevel.getSelectedItem().toString(), Integer.parseInt(price.getText().toString()), chosenDate, eTime.getSelectedItem().toString());

                myRef.push().setValue(newLesson).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(AddLessonActivity.this, "Successfully Added Lesson", Toast.LENGTH_SHORT).show();
//                            eDate.getText().clear();
                            price.getText().clear();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(AddLessonActivity.this, "WHOOPS! Lesson adding failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        chosenDate = "";
        chosenDate = chosenDate + dayOfMonth + "/" + (month+1) + "/" + year;
//        Toast.makeText(AddLessonActivity.this, "You Selected "+chosenDate, Toast.LENGTH_LONG).show();
        date.setText(chosenDate);
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
