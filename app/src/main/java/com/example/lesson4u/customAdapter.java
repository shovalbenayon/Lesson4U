package com.example.lesson4u;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.concurrent.CountDownLatch;

public class customAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<String> list = new ArrayList<String>();
    private boolean isQueryingFinished = false;
    private ArrayList<LessonObj> lessons = new ArrayList<LessonObj>();
    final String Tag = "customAdapter";
//    private boolean con = false;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("lessons");
    LessonObj tempLesson;
    private Context context;

    public customAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
        getLessonsFromDB();

        Log.d(Tag, "1lesson.size : "+lessons.size() + ", list.size = "+list.size() );

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
        //just return 0 if your list items do not have an Id variable.
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_layout, null);
        }


        //Handle TextView and display string from your list
        TextView tvContact= (TextView)view.findViewById(R.id.tvContact);
        Log.d(Tag, "2lesson.size : "+lessons.size() + ", list.size = "+list.size() );
        if(lessons.size()==list.size()){
            LessonObj templ = lessons.get(position);
            tvContact.setText("          | time: "+templ.getTime()+":00 | price: "+templ.getPrice());
        }else{
            tvContact.setText(list.get(position));
        }



        //Handle buttons and add onClickListeners
        Button callbtn= (Button)view.findViewById(R.id.btn);
        callbtn.setText("<");

        callbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something

            }
        });

        tvContact.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something

            }
        });

        return view;
    }

    public void getLessonsFromDB(){

        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(lessons.size()==0){
                    for(int i =0; i<list.size(); i++){
                        LessonObj t = dataSnapshot.child(list.get(i)).getValue(LessonObj.class);
                        lessons.add(t);

                    }
                }


            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        });

    }
    public boolean isRefreshed(){
        getLessonsFromDB();
        if(list.size()==lessons.size()){
            return true;
        }
        else{
            return false;
        }
    }

}