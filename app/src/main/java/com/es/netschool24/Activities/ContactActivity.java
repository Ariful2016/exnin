package com.es.netschool24.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.es.netschool24.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactActivity extends AppCompatActivity {

    Toolbar toolbar;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        toolbar = findViewById(R.id.contact_toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



/*
        facebook_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LordUrl("https://www.facebook.com/");
            }
        });
        twitter_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LordUrl("https://www.twitter.com/");
            }
        });

        instagram_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LordUrl("https://www.instagram.com/");
            }
        });*/
    }

    private void LordUrl(String s) {

        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));

    }
}