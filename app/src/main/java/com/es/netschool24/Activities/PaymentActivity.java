package com.es.netschool24.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.es.netschool24.R;
import com.google.android.material.textfield.TextInputEditText;

public class PaymentActivity extends AppCompatActivity {

    TextInputEditText full_name,course_name,mobile,email,country_name,
            amount,Pay_for;
    AppCompatButton payment;

    Toolbar toolbar;



    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        full_name = findViewById(R.id.full_name);
        course_name = findViewById(R.id.course_name);
        mobile = findViewById(R.id.mobile);
        email = findViewById(R.id.email);
        country_name = findViewById(R.id.country_name);
        amount = findViewById(R.id.amount);
        Pay_for = findViewById(R.id.Pay_for);
        payment = findViewById(R.id.payment);


        toolbar = findViewById(R.id.paymentToolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);





    }
}