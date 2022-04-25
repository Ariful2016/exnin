package com.es.netschool24.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.es.netschool24.Activities.CourseDetailsActivity;
import com.es.netschool24.Models.CourseName;
import com.es.netschool24.R;
import com.es.netschool24.ViewHolders.CourseViewHolder;
import com.es.netschool24.ViewHolders.SliderViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseViewHolder> {
    Context context;
    List<CourseName> courseNameList;

    public CourseAdapter(Context context, List<CourseName> courseNameList) {
        this.context = context;
        this.courseNameList = courseNameList;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.iteam_course, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        CourseName courseName = courseNameList.get(position);
        holder.courseName.setText(courseName.getCourseName());
        Picasso.get().load(courseName.getImgUrl()).placeholder(R.drawable.kid_learning_all_courses).into(holder.course_img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CourseDetailsActivity.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return courseNameList.size();
    }
}
