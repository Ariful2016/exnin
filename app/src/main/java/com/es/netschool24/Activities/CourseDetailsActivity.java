package com.es.netschool24.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.es.netschool24.Models.CourseDay;
import com.es.netschool24.Models.LoginResponse;
import com.es.netschool24.MyApi;
import com.es.netschool24.MyRetrofit;
import com.es.netschool24.R;
import com.es.netschool24.storage.SharedPrefManager;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseDetailsActivity extends AppCompatActivity {


    Toolbar kidToolbar;
    AppCompatButton enroll_btn;

    CircleImageView course_img, int_img;

    TextView course_title_txt, overviewDescription_txt, kid_subject_txt, kid_subject_bem_txt,
            duration_month_txt, total_class_txt, total_class_time_txt, taka_txt, usd_txt, first_installment_txt;

    Intent intent;

    String img;
    String title, overview, slug, duration, totalClass, classInfo, fee, usdeuro, installment, courseId;

    Dialog dialog;


    TimePickerDialog timePickerDialog;
    TimePicker timePicker;

    List<CourseDay> courseDayList;

    String[] selectDay;

    @SuppressLint ("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);


        intent = getIntent();

        kidToolbar = findViewById(R.id.kidsToolbar);
        enroll_btn = findViewById(R.id.enroll_btn);

        setSupportActionBar(kidToolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        courseDayList = new ArrayList<>();


        if (intent.hasExtra("img")) {
            img = intent.getStringExtra("img");
            courseId = intent.getStringExtra("id");
            title = intent.getStringExtra("title");
            overview = intent.getStringExtra("overview");
            slug = intent.getStringExtra("slug");
            duration = intent.getStringExtra("duration");
            totalClass = intent.getStringExtra("totalClass");
            classInfo = intent.getStringExtra("classInfo");
            fee = intent.getStringExtra("fee");
            usdeuro = intent.getStringExtra("usdeuro");
            //installment = intent.getStringExtra("installment");
        }

        course_img = findViewById(R.id.course_img);
        course_title_txt = findViewById(R.id.course_title_txt);
        overviewDescription_txt = findViewById(R.id.overviewDescription_txt);
        kid_subject_txt = findViewById(R.id.kid_subject_txt);
        kid_subject_bem_txt = findViewById(R.id.kid_subject_bem_txt);
        duration_month_txt = findViewById(R.id.duration_month_txt);
        total_class_txt = findViewById(R.id.total_class_txt);
        total_class_time_txt = findViewById(R.id.total_class_time_txt);
        taka_txt = findViewById(R.id.taka_txt);
        usd_txt = findViewById(R.id.usd_txt);
        first_installment_txt = findViewById(R.id.first_installment_txt);


        //course_img.setImageURI(Uri.parse(img));
        Picasso.get().load(img).placeholder(R.drawable.kidlearning).into(course_img);

        course_title_txt.setText(title);
        overviewDescription_txt.setText(overview);
        kid_subject_txt.setText(slug);
        kid_subject_bem_txt.setText(slug);
        duration_month_txt.setText("Duration: " + duration + " months");
        total_class_txt.setText("Total Class: " + totalClass);
        total_class_time_txt.setText("(" + classInfo + ")");
        taka_txt.setText(fee + " BDT");
        usd_txt.setText(usdeuro);
        //first_installment_txt.setText("1st installment = "+installment);


        MyApi myApi = MyRetrofit.getRetrofit().create(MyApi.class);
        Call<List<CourseDay>> cDay = myApi.getCourseDay();


        timePicker = new TimePicker(this);
        int currentHour = timePicker.getCurrentHour();
        int currentMin = timePicker.getCurrentMinute();

        AlertDialog.Builder builder = new AlertDialog.Builder(CourseDetailsActivity.this);
        builder.setTitle("Select your Day and Time");

        View view = getLayoutInflater().inflate(R.layout.custom_dialog, null);
        AutoCompleteTextView day;
        TextInputEditText time;
        AppCompatButton addCart;
        TextView cancel;
        day = view.findViewById(R.id.select_day);
        time = view.findViewById(R.id.select_time);
        addCart = view.findViewById(R.id.addCart_btn);
        cancel = view.findViewById(R.id.cancel_txt);

        cDay.enqueue(new Callback<List<CourseDay>>() {
            @Override
            public void onResponse(Call<List<CourseDay>> call, Response<List<CourseDay>> response) {
                courseDayList = response.body();
                assert courseDayList != null;
                selectDay = new String[courseDayList.size()];

                for (int i = 0; i < courseDayList.size(); i++) {

                    selectDay[i] = courseDayList.get(i).getName();
                }

                ArrayAdapter<String> dayAdapter = new ArrayAdapter<String>(CourseDetailsActivity.this, android.R.layout.simple_list_item_1, selectDay);
                day.setAdapter(dayAdapter);

            }

            @Override
            public void onFailure(Call<List<CourseDay>> call, Throwable t) {

            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog = new TimePickerDialog(CourseDetailsActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                time.setText(hourOfDay + ":" + minute);
                            }
                        }, currentHour, currentMin, false);
                timePickerDialog.show();

            }
        });

        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences(SharedPrefManager.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                int uId = sharedPreferences.getInt("userId", 0);
                String userID = String.valueOf(uId);

                String token = sharedPreferences.getString("token", "");

                Log.i("TAG", "onClick: " + token);

                String day_str = day.getText().toString();
                String time_str = time.getText().toString();

                if (SharedPrefManager.getInstance(CourseDetailsActivity.this).isLoggedIn()) {

                    myApi.courseEnroll("Bearer " + token, day_str, time_str, userID, courseId).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                            Log.i("Enroll", "onResponse: " + response);
                            dialog.dismiss();
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                            Log.i("Enroll", "onFailure: " + t.getMessage());
                            dialog.dismiss();

                        }
                    });


                    /*                Intent intent = new Intent(CourseDetailsActivity.this, CartActivity.class);
                    intent.putExtra("day", day_str);
                    intent.putExtra("time", time_str);
                    //intent.putExtra("img", img);
                    //intent.putExtra("title", title);
                    //intent.putExtra("fee", fee);
                    startActivity(intent);*/
                } else {
                    startActivity(new Intent(CourseDetailsActivity.this, SignInActivity.class));
                }


            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        builder.setView(view);

        dialog = builder.create();

        enroll_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });


    }
}