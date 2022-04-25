package com.es.netschool24.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.es.netschool24.R;
import com.google.android.material.textfield.TextInputEditText;

public class CartActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextInputEditText time;
    AutoCompleteTextView name_of_course,day;

    String[] course_names = {"Kid's Learning", "Kid's English", "Spoken English", "Arabic Shikkha", "Quran Shikkha", "Bangla Language ", "Foreign Language",
            "General knowledge", "Basic Computer", "Official Computer", "Video Editing", "Digital Marketing", "Graphics Design", "Web Design", "App Development", "Freelancing", "Others"};

    String [] days = {"SAT - TUE - FRI", "SUN - TUE - THU", "MON - WED - FRI", "SAT - TUE - FRI", "TUE - THU - SAT", "WED - FRI - SUN", "THU - SAT - MON", "FRI - SUN - TUE"};

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        toolbar = findViewById(R.id.enroll_toolbar);

        name_of_course = findViewById(R.id.name_of_course);
        day = findViewById(R.id.day);
        time = findViewById(R.id.s_time);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayAdapter<String> courseNameAdapter = new ArrayAdapter<String>(CartActivity.this, android.R.layout.simple_list_item_1,course_names);
        name_of_course.setAdapter(courseNameAdapter);

        // Add days into spinner
        ArrayAdapter<String> daysAdapter = new ArrayAdapter<String>(CartActivity.this, android.R.layout.simple_list_item_1,days);
        day.setAdapter(daysAdapter);

        name_of_course.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        day.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        /*enroll_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CourseDetailsActivity.this,CartActivity.class));
            }
        });*/
    }
}