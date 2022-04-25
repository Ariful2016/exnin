package com.es.netschool24.Activities;

import static android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.es.netschool24.R;

public class AboutUsActivity extends AppCompatActivity {

    TextView txt_head_1, txt_head_2, txt_head_3, txt_1, txt_2, txt_3;
    Toolbar aboutUsToolbar;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        txt_head_1 = findViewById(R.id.txt_head_1);
        txt_head_2 = findViewById(R.id.txt_head_2);
        txt_head_3 = findViewById(R.id.txt_head_3);
        txt_1 = findViewById(R.id.txt_1);
        txt_2 = findViewById(R.id.txt_2);
        txt_3 = findViewById(R.id.txt_3);
        aboutUsToolbar = findViewById(R.id.aboutUsToolbar);

        setSupportActionBar(aboutUsToolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            txt_1.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
        }*/
    }
}