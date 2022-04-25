package com.es.netschool24.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.es.netschool24.Activities.CourseDetailsActivity;
import com.es.netschool24.Activities.DashboardActivity;
import com.es.netschool24.Activities.OurCourseActivity;
import com.es.netschool24.Activities.StudentRegisterActivity;
import com.es.netschool24.Adapters.SliderAdapter;
import com.es.netschool24.Models.SliderItems;
import com.es.netschool24.R;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    SliderView sliderView;

    List<SliderItems> sliderItemList;

    ImageView logo;
    AppCompatButton ZoomMeetingId,registration_btn;

    FrameLayout cont;

    TextView viewAll_txt;

    LinearLayout kid_details;



    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);



        sliderView = view.findViewById(R.id.imageSlider);
        logo = view.findViewById(R.id.logo);
       // ZoomMeetingId = view.findViewById(R.id.ZoomMeetingId);
        cont = view.findViewById(R.id.container);
        registration_btn = view.findViewById(R.id.registration_btn);
        sliderItemList = new ArrayList<>();

        viewAll_txt = view.findViewById(R.id.viewall_txt);
        kid_details = view.findViewById(R.id.kid_details);

        kid_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CourseDetailsActivity.class));
            }
        });

        viewAll_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), OurCourseActivity.class));
            }
        });

        setUpSlider();

       /* ZoomMeetingId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent meetingId = new Intent(Intent.ACTION_VIEW, Uri.parse("https://zoom.us/"));
                startActivity(meetingId);
            }
        });*/

        registration_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StudentRegisterActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void setUpSlider() {


        SliderItems sliderItem = new SliderItems("desciption 1 ", "https://firebasestorage.googleapis.com/v0/b/netschool24-arif.appspot.com/o/slide1.jpg?alt=media&token=6ad0a9df-f762-4c1b-a62b-ebeea3acda6a");
        SliderItems sliderItem2 = new SliderItems("desciption 2 ", "https://firebasestorage.googleapis.com/v0/b/netschool24-arif.appspot.com/o/silde2.jpg?alt=media&token=7b0dbc15-7a99-416b-9435-ee3b5c9d558e");
        SliderItems sliderItem4 = new SliderItems("desciption 4 ", "https://firebasestorage.googleapis.com/v0/b/netschool24-arif.appspot.com/o/slide3.jpg?alt=media&token=b440ddbd-6df6-4fae-962c-3ad8e1f9c017");
        sliderItemList.add(sliderItem);
        sliderItemList.add(sliderItem2);
        sliderItemList.add(sliderItem4);


        SliderAdapter adapter = new SliderAdapter(getContext(), sliderItemList);
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.COLOR); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.CUBEINDEPTHTRANSFORMATION);
        sliderView.setIndicatorEnabled(false);

        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();
    }
}