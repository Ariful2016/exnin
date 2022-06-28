package com.es.netschool24.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.es.netschool24.AppConstants.AppConstants;
import com.es.netschool24.Models.AllCourse;
import com.es.netschool24.Models.AllCourseParent;
import com.es.netschool24.R;
import com.es.netschool24.ViewHolders.AllCourseViewHolder;
import com.squareup.picasso.Picasso;


import java.util.List;

public class AllCourseAdapter extends RecyclerView.Adapter<AllCourseViewHolder> {
    Context context;
    List<AllCourse> allCourseList;

   /* public AllCourseAdapter(Context context, List<AllCourse> allCourseList) {
        this.context = context;
        this.allCourseList = allCourseList;
    }*/

    public AllCourseAdapter(Context context, List<AllCourse> allCourseList) {
        this.context = context;
        this.allCourseList = allCourseList;
    }

    @NonNull
    @Override
    public AllCourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_all_course, parent, false);
        return new AllCourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllCourseViewHolder holder, int position) {

        //AllCourseParent data = allCourseList.get(position);

        AllCourse allCourse = allCourseList.get(position);

        holder.course_name.setText(allCourse.getName());
        Picasso.get().load(AppConstants.course_image_path+allCourse.getBannerImage()).placeholder(R.drawable.kidlearning).into(holder.course_img);

      //  Picasso.get().load(allCourse.getBannerImage()).into(holder.course_img);

    }

    @Override
    public int getItemCount() {
        return allCourseList.size();
    }
}
