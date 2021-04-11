package com.example.lesson4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginOrRegister extends AppCompatActivity implements View.OnClickListener {
    Button login;
    Button register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_or_register);
        register = findViewById(R.id.button);
        login = findViewById(R.id.button2);

        login.setOnClickListener(this);
        register.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == login){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        } else if(v == register){
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        }
    }
}