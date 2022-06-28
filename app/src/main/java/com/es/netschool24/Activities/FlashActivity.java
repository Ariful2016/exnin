package com.es.netschool24.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;
import android.widget.TextView;

import com.es.netschool24.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;

public class FlashActivity extends AppCompatActivity {

    ImageView flash_img;
    TextView flash_txt;
    CountDownTimer countDownTimer;

    //FirebaseAuth firebaseAuth;
    //FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);

        flash_img = findViewById(R.id.flash_img);


        //firebaseAuth = FirebaseAuth.getInstance();
        //firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        countDownTimer = new CountDownTimer(300,200) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
/*
                if(firebaseUser!= null){
                    startActivity(new Intent(FlashActivity.this, DashboardActivity.class));
                    finish();
                }else {
                    startActivity(new Intent(FlashActivity.this, StartActivity.class));
                    finish();
                }*/

                startActivity(new Intent(FlashActivity.this, DashboardActivity.class));
                finish();

            }
        }.start();
    }
}