package com.es.netschool24.ViewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.es.netschool24.R;

public class PopularCourseViewHolder extends RecyclerView.ViewHolder {
    public ImageView popular_course_img;
    public TextView popular_course_title;
    public PopularCourseViewHolder(@NonNull View itemView) {
        super(itemView);

        popular_course_img = itemView.findViewById(R.id.popular_course_img);
        popular_course_title = itemView.findViewById(R.id.popular_course_title);
    }
}
