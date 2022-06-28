package com.es.netschool24.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.es.netschool24.AppConstants.AppConstants;
import com.es.netschool24.Models.AllCourse;
import com.es.netschool24.R;
import com.es.netschool24.ViewHolders.PopularCourseViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PopularCourseAdapter extends RecyclerView.Adapter<PopularCourseViewHolder> {
    Context context;
    List<AllCourse> allCourseList;

    public PopularCourseAdapter(Context context, List<AllCourse> allCourseList) {
        this.context = context;
        this.allCourseList = allCourseList;
    }

    @NonNull
    @Override
    public PopularCourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_popular_course, parent,false);
        return new PopularCourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularCourseViewHolder holder, int position) {

        AllCourse allCourse = allCourseList.get(position);
        holder.popular_course_title.setText(allCourse.getName());
        Picasso.get().load(AppConstants.course_image_path+allCourse.getBannerImage()).placeholder(R.drawable.kidlearning).into(holder.popular_course_img);

    }

    @Override
    public int getItemCount() {
        return allCourseList.size();
    }
}
