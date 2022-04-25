package com.es.netschool24.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;

import com.es.netschool24.R;
import com.google.android.material.textfield.TextInputEditText;

public class ComplainActivity extends AppCompatActivity {

    TextInputEditText full_name,email,teacherName,complain;

    AutoCompleteTextView name_of_course;

    AppCompatButton submit_btn;

    Toolbar toolbar;


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain);

        full_name = findViewById(R.id.full_name);
        email = findViewById(R.id.email);
        teacherName = findViewById(R.id.teacherName);
        complain = findViewById(R.id.complain);
        name_of_course = findViewById(R.id.name_of_course);
        submit_btn = findViewById(R.id.submit);


        toolbar = findViewById(R.id.complain_toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
}