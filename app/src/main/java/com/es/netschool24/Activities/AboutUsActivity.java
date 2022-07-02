package com.es.netschool24.Activities;



import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;

import android.os.Bundle;
import android.util.Log;


import com.es.netschool24.Adapters.AboutUsAdapter;
import com.es.netschool24.Models.AboutUs;
import com.es.netschool24.MyApi;
import com.es.netschool24.MyRetrofit;
import com.es.netschool24.R;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AboutUsActivity extends AppCompatActivity {


    Toolbar aboutUsToolbar;

    List<AboutUs> aboutUsList;

    RecyclerView recyclerView;
    AboutUsAdapter aboutUsAdapter;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        aboutUsToolbar = findViewById(R.id.aboutUsToolbar);

        setSupportActionBar(aboutUsToolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        aboutUsList = new ArrayList<>();

        recyclerView = findViewById(R.id.about_us_recycler);

        MyApi myApi = MyRetrofit.getRetrofit().create(MyApi.class);


        Call<List<AboutUs>> aboutUs = myApi.getAboutUs();

        aboutUs.enqueue(new Callback<List<AboutUs>>() {
            @Override
            public void onResponse(Call<List<AboutUs>> call, Response<List<AboutUs>> response) {
                aboutUsList = response.body();
                if(aboutUsList.size()>0){
                    for (AboutUs a: aboutUsList){
                        Log.i("TAG", "onResponse: "+ a.getSectionTitle()+ "desc "+ a.getAboutUs() + "img "+ a.getAboutBanner());
                    }

                    aboutUsAdapter = new AboutUsAdapter(AboutUsActivity.this, aboutUsList);

                    recyclerView.setAdapter(aboutUsAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<AboutUs>> call, Throwable t) {

            }
        });







    }
}