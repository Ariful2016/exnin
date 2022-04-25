package com.es.netschool24.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.es.netschool24.Activities.OurCourseActivity;
import com.es.netschool24.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class CourseFragment extends Fragment {

    FloatingActionButton floatingActionButton;

    public CourseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course, container, false);
        floatingActionButton = view.findViewById(R.id.float_btn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), OurCourseActivity.class));
            }
        });
        return view;
    }
}