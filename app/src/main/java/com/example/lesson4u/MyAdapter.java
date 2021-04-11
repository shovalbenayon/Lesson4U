package com.example.lesson4u;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


    ArrayList<LessonObj> MatchedLessons;
    Context context;
    ArrayList<String> lessonIDs;

    public MyAdapter(Context ct, ArrayList<LessonObj> mLessons, ArrayList<String> ids){
        context = ct;
        MatchedLessons = mLessons;
        lessonIDs = ids;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.time.setText("Starts at: " + MatchedLessons.get(position).getTime()+":00 ");
        holder.price.setText("Price: "+MatchedLessons.get(position).getPrice()+"₪");

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LessonDetailsActivity.class);
                LessonObj temp = MatchedLessons.get(position);
                intent.putExtra("lesson", temp);
                intent.putExtra("id", lessonIDs.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return MatchedLessons.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView time, price;
        ConstraintLayout mainLayout;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.tvHour);
            price = itemView.findViewById(R.id.tvPrice);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
