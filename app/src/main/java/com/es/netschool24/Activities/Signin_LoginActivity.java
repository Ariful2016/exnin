package com.es.netschool24.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.es.netschool24.R;

public class Signin_LoginActivity extends AppCompatActivity {

    LinearLayout google_layout,facebook_layout,email_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_login);

        email_layout = findViewById(R.id.email_layout);
        google_layout = findViewById(R.id.google_layout);
        facebook_layout = findViewById(R.id.facebook_layout);

        google_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Signin_LoginActivity.this,GoogleSignInActivity.class);
                startActivity(intent);
            }
        });

        email_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signin_LoginActivity.this,SignInActivity.class);
                startActivity(intent);
            }
        });

        facebook_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signin_LoginActivity.this,FacebookSignInActivity.class);
                startActivity(intent);
            }
        });
    }
}