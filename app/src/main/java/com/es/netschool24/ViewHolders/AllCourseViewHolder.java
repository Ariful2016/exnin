package com.es.netschool24.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.es.netschool24.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class AllCourseViewHolder extends RecyclerView.ViewHolder {
   public CircleImageView course_img;
   public TextView course_name;

    public AllCourseViewHolder(@NonNull View itemView) {
        super(itemView);

        course_img = itemView.findViewById(R.id.course_img);
        course_name = itemView.findViewById(R.id.course_name);
    }
}
