package com.es.netschool24.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.es.netschool24.Activities.OurCourseActivity;
import com.es.netschool24.Activities.StudentRegisterActivity;
import com.es.netschool24.Adapters.AllCourseAdapter;
import com.es.netschool24.Adapters.PopularCourseAdapter;
import com.es.netschool24.Adapters.SliderAdapter;
import com.es.netschool24.AppConstants.AppConstants;
import com.es.netschool24.Models.AllCourse;
import com.es.netschool24.Models.AllCourseParent;
import com.es.netschool24.Models.Banners;
import com.es.netschool24.MyApi;
import com.es.netschool24.Models.SliderItems;
import com.es.netschool24.MyRetrofit;
import com.es.netschool24.R;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    SliderView sliderView;

    //List<SliderItems> sliderItemList;

    ImageView logo;
    AppCompatButton ZoomMeetingId, registration_btn;

    FrameLayout cont;

    TextView viewAll_txt;

    LinearLayout kid_details;

    List<AllCourse> allCourseList;
    List<AllCourse> allCourseLimit;
    List<AllCourse> allCoursePopular;
    RecyclerView all_course_recycler;
    AllCourseAdapter allCourseAdapter;

    RecyclerView popular_course_recycler;
    PopularCourseAdapter popularCourseAdapter;

    List<Banners> bannersList;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        allCourseList = new ArrayList<>();
        allCourseLimit = new ArrayList<>();
        allCoursePopular = new ArrayList<>();
        bannersList = new ArrayList<>();


        sliderView = view.findViewById(R.id.imageSlider);
        logo = view.findViewById(R.id.logo);
        // ZoomMeetingId = view.findViewById(R.id.ZoomMeetingId);
        cont = view.findViewById(R.id.container);
        registration_btn = view.findViewById(R.id.registration_btn);
        //sliderItemList = new ArrayList<>();

        viewAll_txt = view.findViewById(R.id.viewall_txt);

        all_course_recycler = view.findViewById(R.id.all_course_recycler);
        popular_course_recycler = view.findViewById(R.id.popular_course_recycler);


        MyApi myApi = MyRetrofit.getRetrofit().create(MyApi.class);
        Call<AllCourseParent> allCourse = myApi.getAllCourse();

        Call<List<Banners>> allBanners = myApi.getBanners();


        allCourse.enqueue(new Callback<AllCourseParent>() {
            @Override
            public void onResponse(@NonNull Call<AllCourseParent> call, @NonNull Response<AllCourseParent> response) {


                assert response.body() != null;
                Log.i("TAG", "onResponse: total course " + response.body().getCourse().size());


                allCourseList = response.body().getCourse();

                if (allCourseList.size() > 0) {
                    for (int i = 0; i < 8; i++) {
                        allCourseLimit.add(allCourseList.get(i));
                    }

                    // Log.i("TAG", "onResponse: "+a.getName());
                    allCourseAdapter = new AllCourseAdapter(getActivity(), allCourseLimit);
                    all_course_recycler.setAdapter(allCourseAdapter);
                }

                if (allCourseList.size()>0){
                    for (int i =0; i<6; i++){
                        allCoursePopular.add(allCourseList.get(i));
                    }
                    // Log.i("TAG", "onResponse: "+a.getName());
                    popularCourseAdapter = new PopularCourseAdapter(getActivity(), allCoursePopular);
                    popular_course_recycler.setAdapter(popularCourseAdapter);
                }



            }


            @Override
            public void onFailure(Call<AllCourseParent> call, Throwable t) {
                Log.i("TAG", "error : " + t.getLocalizedMessage());
            }
        });

        /*allCourse.enqueue(new Callback<AllCourseParent>() {
            @Override
            public void onResponse(Call<AllCourseParent> call, Response<AllCourseParent> response) {
                assert response.body() != null;
                allCourseList = response.body().getCourse();
                if (allCourseList.size()>0){
                    for (int i =0; i<6; i++){
                        allCourseLimit.add(allCourseList.get(i));
                    }
                    // Log.i("TAG", "onResponse: "+a.getName());
                    popularCourseAdapter = new PopularCourseAdapter(getActivity(), allCourseLimit);
                    popular_course_recycler.setAdapter(popularCourseAdapter);
                }


            }

            @Override
            public void onFailure(Call<AllCourseParent> call, Throwable t) {

            }
        });*/


        allBanners.enqueue(new Callback<List<Banners>>() {
            @Override
            public void onResponse(Call<List<Banners>> call, Response<List<Banners>> response) {
                bannersList = response.body();
                //Log.i("TAG", "onResponse: "+ bannersList);
                assert bannersList != null;
                if (bannersList.size() > 0) {
                     /*for (Banners i: bannersList){
                         Log.i("TAG", "title: "+ i.getBannerTitle()+ "img: "+i.getBannerImage());
                     }*/
                    setUpSlider();
                }
            }

            @Override
            public void onFailure(Call<List<Banners>> call, Throwable t) {
                // Log.i("TAG", "error: "+ t.getLocalizedMessage());
            }
        });


        viewAll_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), OurCourseActivity.class));
            }
        });


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


        SliderAdapter adapter = new SliderAdapter(getContext(), bannersList);
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.COLOR); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.CUBEINDEPTHTRANSFORMATION);
        sliderView.setIndicatorEnabled(false);

        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();
    }
}