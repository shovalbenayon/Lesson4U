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
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    FirebaseAuth auth = FirebaseAuth.getInstance();
    SharedPreferences sp;
    EditText mailb;
    EditText passb;
    Button loginb;
    RadioGroup radioGroup;
    String type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mailb = findViewById(R.id.emailLogin);
        passb = findViewById(R.id.editTextTextPassword2);
        loginb = findViewById(R.id.loginb3);
        radioGroup = findViewById(R.id.radiogroup);

        loginb.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if(checkedId == R.id.radioButtonStudent) {
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("user_details", 0);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("type", "student");
                    editor.apply();
                    type = "student";
                    Log.d("REGISTRATION", type);
                } else if(checkedId == R.id.radioButtonTeacher) {
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("user_details", 0);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("type", "teacher");
                    editor.apply();
                    type = "teacher";
                    Log.d("REGISTRATION", type);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == loginb) {
            Log.d("REGISTRATION", "login clicked");
            if(auth.getUid() == null) {

                String password = passb.getText().toString().trim();
                String email = mailb.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mailb.setError("Please enter your Email");
                }
                else if(TextUtils.isEmpty(password) || password.length()<6){
                    passb.setError("Enter at least 6 characters");
                }
                else if(type.equals("")){  // Make sure teacher/student is selected
                    Toast.makeText(RegisterActivity.this, "Select user type (Teacher/Student)", Toast.LENGTH_LONG).show();

                }else{

                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        public void onComplete(Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Successfully Registered", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(RegisterActivity.this, userRegistration.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }

            }
        }
    }
}