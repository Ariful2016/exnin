package com.es.netschool24.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.es.netschool24.Activities.CourseDetailsActivity;
import com.es.netschool24.AppConstants.AppConstants;
import com.es.netschool24.Models.AllCourse;
import com.es.netschool24.Models.CourseName;
import com.es.netschool24.R;
import com.es.netschool24.ViewHolders.CourseViewHolder;
import com.es.netschool24.ViewHolders.SliderViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseViewHolder> {
    Context context;
    List<AllCourse> allCourseList;

    public CourseAdapter(Context context, List<AllCourse> allCourseList) {
        this.context = context;
        this.allCourseList = allCourseList;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.iteam_course, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, @SuppressLint("RecyclerView") int position) {
        AllCourse allCourse = allCourseList.get(position);
        holder.courseName.setText(allCourse.getName());
        //holder.courseName.setText(allCourse.getInstallments().get(position).getBdt());
        Picasso.get().load(AppConstants.course_image_path + allCourse.getBannerImage()).placeholder(R.drawable.kid_learning_all_courses).into(holder.course_img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CourseDetailsActivity.class);
                intent.putExtra("img", AppConstants.course_image_path + allCourse.getBannerImage());
                intent.putExtra("title", allCourse.getName());
                intent.putExtra("id", allCourse.getId().toString());
                intent.putExtra("overview", allCourse.getOverview());
                intent.putExtra("slug", allCourse.getSlug());
                intent.putExtra("duration", allCourse.getDuration());
                intent.putExtra("totalClass", allCourse.getTotalClass());
                intent.putExtra("classInfo", allCourse.getClassInfo());
                intent.putExtra("fee", allCourse.getCourseFee());
                intent.putExtra("usdeuro", allCourse.getUsdeuro());
                //intent.putExtra("installment", allCourse.getInstallments().get(position).getBdt());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return allCourseList.size();
    }
}
