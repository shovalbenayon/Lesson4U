package com.example.lesson4u;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final String TAG = "ProfileActivity";
    SharedPreferences sp;
    TextView fname;
    TextView lname;
    TextView city;
    TextView mail;
    TextView phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        fname = findViewById(R.id.prof1);
        lname = findViewById(R.id.prof2);
        city = findViewById(R.id.prof3);
        mail = findViewById(R.id.prof4);
        phone = findViewById(R.id.prof5);
        sp = getSharedPreferences("user_details", 0);
        String type = sp.getString("type", null);
        if(type.equals("student")){
            DatabaseReference studentsRef = database.getReference("students");
            studentsRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    StudentObj s = dataSnapshot.child(auth.getUid()).getValue(StudentObj.class);
                    fname.setText("First name: "+s.getFirstName());
                    lname.setText("Last name: "+s.getLastName());
                    city.setText("City: "+s.getCity());
                    mail.setText("Mail: "+s.getEmail());
                    phone.setText("Phone: "+s.getPhoneNum());

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } else if(type.equals("teacher")){
            DatabaseReference teachersRef = database.getReference("teachers");
            teachersRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    TeacherObj t = dataSnapshot.child(auth.getUid()).getValue(TeacherObj.class);
                    fname.setText("First name: "+t.getFirstName());
                    lname.setText("Last name: "+t.getLastName());
                    city.setText("City: "+t.getCity());
                    mail.setText("Mail: "+t.getEmail());
                    phone.setText("Phone: "+t.getPhoneNum());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }


        //TODO: make a nice profile page for the user
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