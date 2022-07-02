package com.es.netschool24.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.es.netschool24.Models.AllCourse;
import com.es.netschool24.Models.AllCourseParent;
import com.es.netschool24.MyApi;
import com.es.netschool24.MyRetrofit;
import com.es.netschool24.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinFreeLearningActivity extends AppCompatActivity {

    TextInputEditText name,mobile,email,address;
    AutoCompleteTextView name_of_course;
    AppCompatButton submit;

    List<AllCourse> allCourseList;

    String[] course_names;
    int [] id;

    int a =0 ;

    ProgressDialog progressDialog;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_free_learning);

        Toolbar toolbar;

        toolbar = findViewById(R.id.join_free_learning);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        name = findViewById(R.id.name);
        mobile = findViewById(R.id.mobile);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        name_of_course = findViewById(R.id.course);
        submit = findViewById(R.id.submit_btn);

        allCourseList = new ArrayList<>();

        progressDialog = new ProgressDialog(JoinFreeLearningActivity.this);
        progressDialog.setTitle("Please Wait...");
        progressDialog.setMessage("Sends your interest successfully!");

        MyApi myApi = MyRetrofit.getRetrofit().create(MyApi.class);
        Call<AllCourseParent> allCourse = myApi.getAllCourse();



        allCourse.enqueue(new Callback<AllCourseParent>() {
            @Override
            public void onResponse(Call<AllCourseParent> call, Response<AllCourseParent> response) {
                allCourseList = response.body().getCourse();
                course_names = new String[allCourseList.size()];
                id = new int[allCourseList.size()];
                for (int i =0; i<allCourseList.size(); i++){
                    course_names[i] = allCourseList.get(i).getName();
                    id[i] = allCourseList.get(i).getId();
                }
                ArrayAdapter<String> courseNameAdapter = new ArrayAdapter<String>(JoinFreeLearningActivity.this, android.R.layout.simple_list_item_1,course_names);
                name_of_course.setAdapter(courseNameAdapter);
            }

            @Override
            public void onFailure(Call<AllCourseParent> call, Throwable t) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_str = name.getText().toString();
                String mobile_str = mobile.getText().toString();
                String email_str = email.getText().toString();
                String address_str = address.getText().toString();
                String course_str = name_of_course.getText().toString();


                if ((name_str.isEmpty())){
                    ShowError("Name field can't be empty!");
                }else if ((mobile_str.isEmpty())){
                    ShowError("Mobile field can't be empty!");
                }else if ((email_str.isEmpty())){
                    ShowError("Email field can't be empty!");
                }else if ((course_str.isEmpty())){
                    ShowError("Course field can't be empty!");
                }else {
                    progressDialog.show();
                    Call<ResponseBody> join = myApi.getJoinFree(name_str,mobile_str,email_str,address_str,1);

                    join.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            progressDialog.dismiss();
                            if (response.isSuccessful()){
                                name.setText("");
                                mobile.setText("");
                                email.setText("");
                                address.setText("");
                                name_of_course.setText("");
                                Log.i("join", "onResponse: "+response.body().toString());
                                Toast.makeText(JoinFreeLearningActivity.this, "Sends your interest successfully!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.i("join", "onError: "+t.getLocalizedMessage().toString());
                            progressDialog.dismiss();
                        }
                    });
                }




            }
        });

    }

    private void ShowError(String msg) {


        AlertDialog.Builder builder = new AlertDialog.Builder(JoinFreeLearningActivity.this);

        builder.setIcon(R.drawable.ic_error);
        builder.setTitle("Error");
        builder.setMessage(msg);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        });
        AlertDialog alertDialog = builder.create();

        alertDialog.show();
    }
}