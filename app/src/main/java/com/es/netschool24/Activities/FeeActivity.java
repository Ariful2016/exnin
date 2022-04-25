package com.es.netschool24.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.es.netschool24.Adapters.FeeAdapter;
import com.es.netschool24.Models.Tutionfee;
import com.es.netschool24.R;

import java.util.ArrayList;
import java.util.List;

public class FeeActivity extends AppCompatActivity {


    RecyclerView recyclerView;

    List<Tutionfee> tutionfeeList;
    FeeAdapter feeAdapter;

    Toolbar toolbar;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fee);

        tutionfeeList = new ArrayList<>();

        toolbar = findViewById(R.id.fee_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        Tutionfee tutionfee = new Tutionfee("8 days","2 days","50 min","$ 40");
        Tutionfee tutionfee2 = new Tutionfee("12 days","3 days","50 min","$ 55");
        Tutionfee tutionfee3 = new Tutionfee("16 days","4 days","50 min","$ 75");
        Tutionfee tutionfee4 = new Tutionfee("20 days","5 days","50 min","$ 90");

        tutionfeeList.add(tutionfee);
        tutionfeeList.add(tutionfee2);
        tutionfeeList.add(tutionfee3);
        tutionfeeList.add(tutionfee4);


        recyclerView = findViewById(R.id.feeRecycler);

        feeAdapter = new FeeAdapter(FeeActivity.this,tutionfeeList);
        recyclerView.setAdapter(feeAdapter);


    }
}