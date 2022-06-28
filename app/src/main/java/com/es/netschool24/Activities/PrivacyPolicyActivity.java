package com.es.netschool24.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.es.netschool24.Adapters.PrivacyPolicyAdapter;
import com.es.netschool24.Models.AllCourseParent;
import com.es.netschool24.Models.PrivacyPolicy;
import com.es.netschool24.MyApi;
import com.es.netschool24.MyRetrofit;
import com.es.netschool24.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrivacyPolicyActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    PrivacyPolicyAdapter adapter;
    List<PrivacyPolicy> privacyPolicyList;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

        toolbar = findViewById(R.id.privacy_toolbar);
        recyclerView = findViewById(R.id.privacy_policy_recycler);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        privacyPolicyList = new ArrayList<>();

        MyApi myApi = MyRetrofit.getRetrofit().create(MyApi.class);
        Call<List<PrivacyPolicy>> privacyPolicyCall = myApi.getPrivacyPolicy();

        privacyPolicyCall.enqueue(new Callback<List<PrivacyPolicy>>() {
            @Override
            public void onResponse(Call<List<PrivacyPolicy>> call, Response<List<PrivacyPolicy>> response) {
                privacyPolicyList = response.body();
                Log.i("POLICY", "onResponse: "+ privacyPolicyList.size());

                adapter = new PrivacyPolicyAdapter(PrivacyPolicyActivity.this,privacyPolicyList);
                recyclerView.setAdapter(adapter);
                
            }

            @Override
            public void onFailure(Call<List<PrivacyPolicy>> call, Throwable t) {

            }
        });
    }
}