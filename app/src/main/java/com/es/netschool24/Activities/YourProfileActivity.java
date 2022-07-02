package com.es.netschool24.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.es.netschool24.Adapters.ProfileFragmentAdapter;
import com.es.netschool24.Adapters.TeacherProfileFragmentAdapter;
import com.es.netschool24.R;
import com.google.android.material.tabs.TabLayout;

public class YourProfileActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    ProfileFragmentAdapter adapter;
    TeacherProfileFragmentAdapter teacherProfileFragmentAdapter;
    FragmentManager fragmentManager;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_profile);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        fragmentManager =getSupportFragmentManager();
        adapter = new ProfileFragmentAdapter(fragmentManager);
        teacherProfileFragmentAdapter = new TeacherProfileFragmentAdapter(fragmentManager);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(YourProfileActivity.this,DashboardActivity.class);
                startActivity(intent);
            }
        });
    }
}