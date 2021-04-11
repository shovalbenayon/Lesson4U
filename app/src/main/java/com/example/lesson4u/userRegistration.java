package com.example.lesson4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class userRegistration extends AppCompatActivity implements View.OnClickListener{
    final String TAG = "userRegistration";
    FirebaseAuth auth = FirebaseAuth.getInstance();
    SharedPreferences sp;
    EditText First_name;
    EditText Last_name;
   // EditText email; // not used
    EditText city;
    EditText Phone_number;
    Button registrationB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        First_name = findViewById(R.id.First_name);
        Last_name = findViewById(R.id.Last_name);
        //email = findViewById(R.id.emailLogin);  //not used
        city = findViewById(R.id.city);
        Phone_number = findViewById(R.id.Phone_number);
        registrationB = findViewById(R.id.registration_button);
        registrationB.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String fName = First_name.getText().toString();
        String lName = Last_name.getText().toString();
        String _city = city.getText().toString();
        String phoneNumber = Phone_number.getText().toString();

        if(TextUtils.isEmpty(fName)){
            First_name.setError("Enter your first name");
        }
        else if(TextUtils.isEmpty(lName)){
            Last_name.setError("Enter your last name");
        }
        else if(TextUtils.isEmpty(_city)){
            city.setError("Enter city");
        }
        else if(TextUtils.isEmpty(phoneNumber)){
            Phone_number.setError("Enter your phone number");
        }
        else if(v == registrationB) {
            sp = getSharedPreferences("user_details", 0);
            String type = sp.getString("type", null);
            Log.d(TAG, "type is " + type);
            addInfoToTheSharedPreferncesFile("fname", First_name.getText().toString());
            if (type.equals("student")) {
                FirebaseStudent.writeNewStudent(auth.getUid(), auth.getCurrentUser().getEmail(), phoneNumber, fName,
                        lName, _city);
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            } else if (type.equals("teacher")) {

                FirebaseTeacher.writeNewTeacher(auth.getUid(), auth.getCurrentUser().getEmail(), phoneNumber, fName,
                        lName, _city);
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

            }
        }

        }


    private void addInfoToTheSharedPreferncesFile(String key, String value){
        sp = getApplicationContext().getSharedPreferences("user_details", 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }
}