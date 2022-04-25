package com.es.netschool24.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.es.netschool24.Adapters.CourseAdapter;
import com.es.netschool24.AllApiMethod;
import com.es.netschool24.Models.CourseName;
import com.es.netschool24.MyRetrofit;
import com.es.netschool24.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OurCourseActivity extends AppCompatActivity {

    List<CourseName> courseNameList ;

    RecyclerView recyclerView;

    CourseAdapter courseAdapter;

    Toolbar toolbar;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_course);

        courseNameList = new ArrayList<>();

        CourseName courseName = new CourseName("Kid's Learning","https://firebasestorage.googleapis.com/v0/b/netschool24-arif.appspot.com/o/All_Courses%2Fkid's%20learning-%20all%20courses.jpg?alt=media&token=7078ea89-da61-44a2-9c9d-2862ce3e3358");
        CourseName courseName1 = new CourseName("Basic Computer","https://firebasestorage.googleapis.com/v0/b/netschool24-arif.appspot.com/o/All_Courses%2F2.Basic%20computer.png?alt=media&token=dc04e1d7-5415-42f6-b81c-c2ccde6ebc6c");
        CourseName courseName2 = new CourseName("Digital Marketing","https://firebasestorage.googleapis.com/v0/b/netschool24-arif.appspot.com/o/All_Courses%2F3.%20digital%20marketing.png?alt=media&token=0d494bf1-7137-499a-ba7a-32ab3afd1c9e");
        CourseName courseName3 = new CourseName("Web Design","https://firebasestorage.googleapis.com/v0/b/netschool24-arif.appspot.com/o/All_Courses%2F4.%20web%20design.png?alt=media&token=ade28a76-d0b7-466f-b992-50305cc63614");
        CourseName courseName4 = new CourseName("Official Computer","https://firebasestorage.googleapis.com/v0/b/netschool24-arif.appspot.com/o/All_Courses%2F5.%20Official%20computer.png?alt=media&token=8dec5f11-1024-438f-acb1-cbbd36d1eaa8");
        CourseName courseName6 = new CourseName("Video Editing","https://firebasestorage.googleapis.com/v0/b/netschool24-arif.appspot.com/o/All_Courses%2F6.%20video%20editing.png?alt=media&token=a28a7342-b658-4c02-bdb5-47ecbecbe237");
        CourseName courseName7 = new CourseName("App Development","https://firebasestorage.googleapis.com/v0/b/netschool24-arif.appspot.com/o/All_Courses%2F7.App%20Development.png?alt=media&token=91e8710a-06b7-4775-ba79-5c207f3c4320");
        CourseName courseName8 = new CourseName("Freelancing","https://firebasestorage.googleapis.com/v0/b/netschool24-arif.appspot.com/o/All_Courses%2F8.%20Freelancing.png?alt=media&token=d6ff33e5-6a1c-417b-bfaf-7e842d1d6b2a");
        CourseName courseName9 = new CourseName("Arabic Shikkha","https://firebasestorage.googleapis.com/v0/b/netschool24-arif.appspot.com/o/All_Courses%2F9.Arabic%20Shikkha.jpg?alt=media&token=c3493ad4-c3d6-42b1-8388-6ba79eff8b39");
        CourseName courseName10 = new CourseName("Bangla Language","https://firebasestorage.googleapis.com/v0/b/netschool24-arif.appspot.com/o/All_Courses%2F10.%20Bangla%20language.png?alt=media&token=4894f15b-318f-48b1-b8ed-1c1928d8b790");
        CourseName courseName11 = new CourseName("General Knowledge","https://firebasestorage.googleapis.com/v0/b/netschool24-arif.appspot.com/o/All_Courses%2F11.General%20knowledge.png?alt=media&token=616acbe8-a52b-4bcf-8e90-6ab96695237a");
        CourseName courseName12 = new CourseName("Foreign Language","https://firebasestorage.googleapis.com/v0/b/netschool24-arif.appspot.com/o/All_Courses%2F12.Foreign-language.jpg?alt=media&token=5714eade-21b4-4d20-9125-86075577ccd3");
        CourseName courseName13 = new CourseName("Spoken English","https://firebasestorage.googleapis.com/v0/b/netschool24-arif.appspot.com/o/All_Courses%2F14.Spoken%20english.jpg?alt=media&token=1cd9bb1f-615c-4e6e-8a60-2a3c6b9d6041");
        CourseName courseName14 = new CourseName("Graphic Design","https://firebasestorage.googleapis.com/v0/b/netschool24-arif.appspot.com/o/All_Courses%2F16.Graphic%20design.jpg?alt=media&token=92dd4a5e-a263-486a-9585-c7d69ea33bce");



        courseNameList.add(courseName);
        courseNameList.add(courseName1);
        courseNameList.add(courseName2);
        courseNameList.add(courseName3);
        courseNameList.add(courseName4);
        courseNameList.add(courseName6);
        courseNameList.add(courseName7);
        courseNameList.add(courseName8);
        courseNameList.add(courseName9);
        courseNameList.add(courseName10);
        courseNameList.add(courseName11);
        courseNameList.add(courseName12);
        courseNameList.add(courseName13);
        courseNameList.add(courseName14);




        toolbar = findViewById(R.id.all_course_toolbar);
        recyclerView = findViewById(R.id.recycler);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*AllApiMethod apiMethod = MyRetrofit.getRetrofit().create(AllApiMethod.class);
        Call<List<CourseName>> allCourse = apiMethod.getAllCourse();

        allCourse.enqueue(new Callback<List<CourseName>>() {
            @Override
            public void onResponse(Call<List<CourseName>> call, Response<List<CourseName>> response) {
                courseNameList = response.body();
            }

            @Override
            public void onFailure(Call<List<CourseName>> call, Throwable t) {

            }
        });
*/


        courseAdapter = new CourseAdapter(OurCourseActivity.this,courseNameList);
        recyclerView.setAdapter(courseAdapter);

    }
}