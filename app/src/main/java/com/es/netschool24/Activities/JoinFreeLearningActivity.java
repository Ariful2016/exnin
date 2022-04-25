package com.es.netschool24.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.es.netschool24.R;
import com.google.android.material.textfield.TextInputEditText;

public class JoinFreeLearningActivity extends AppCompatActivity {

    TextInputEditText name,mobile,email,address;
    AutoCompleteTextView name_of_course;
    AppCompatButton submit;


    String[] course_names = {"Kid's Learning", "Kid's English", "Spoken English", "Arabic Shikkha", "Quran Shikkha", "Bangla Language ", "Foreign Language",
            "General knowledge", "Basic Computer", "Official Computer", "Video Editing", "Digital Marketing", "Graphics Design", "Web Design", "App Development", "Freelancing", "Others"};

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_free_learning);

        Toolbar toolbar;

        toolbar = findViewById(R.id.join_free_learning);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        name = findViewById(R.id.name);
        mobile = findViewById(R.id.mobile);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        name_of_course = findViewById(R.id.course);


        ArrayAdapter<String> courseNameAdapter = new ArrayAdapter<String>(JoinFreeLearningActivity.this, android.R.layout.simple_list_item_1,course_names);
        name_of_course.setAdapter(courseNameAdapter);


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
    }
}