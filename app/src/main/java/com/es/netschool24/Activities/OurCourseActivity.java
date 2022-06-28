package com.es.netschool24.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.es.netschool24.Adapters.CourseAdapter;
import com.es.netschool24.Models.AllCourse;
import com.es.netschool24.Models.AllCourseParent;
import com.es.netschool24.Models.CourseName;
import com.es.netschool24.MyApi;
import com.es.netschool24.MyRetrofit;
import com.es.netschool24.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OurCourseActivity extends AppCompatActivity {

    List<AllCourse> allCourseList ;

    RecyclerView recyclerView;

    CourseAdapter courseAdapter;

    Toolbar toolbar;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_course);


        toolbar = findViewById(R.id.all_course_toolbar);
        recyclerView = findViewById(R.id.recycler);

        allCourseList = new ArrayList<>();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MyApi myApi = MyRetrofit.getRetrofit().create(MyApi.class);
        Call<AllCourseParent> allCourse = myApi.getAllCourse();

        allCourse.enqueue(new Callback<AllCourseParent>() {
            @Override
            public void onResponse(@NonNull Call<AllCourseParent> call, @NonNull Response<AllCourseParent> response) {

                assert response.body() != null;
                allCourseList = response.body().getCourse();

                if (allCourseList.size()>0){
                    courseAdapter = new CourseAdapter(OurCourseActivity.this,allCourseList);
                    recyclerView.setAdapter(courseAdapter);
                }

            }

            @Override
            public void onFailure(@NonNull Call<AllCourseParent> call, Throwable t) {

            }
        });




    }
}