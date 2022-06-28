package com.es.netschool24.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.es.netschool24.Models.AllCourseParent;
import com.es.netschool24.MyApi;
import com.es.netschool24.MyRetrofit;
import com.es.netschool24.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentRegisterActivity extends AppCompatActivity {

    TextInputEditText full_nameEdit,father_nameEdit,date_of_birthEdit,whatsapp_noEdit,emailEdit,gender,nationalityEdit,addressEdit,guardian_nameEdit,
            guardian_phoneEdit,passwordEdit;

    AppCompatButton register_btn;
    TextView login_txt;

    //AutoCompleteTextView name_of_course,day;

    /*String[] course_names = {"Kid's Learning", "Kid's English", "Spoken English", "Arabic Shikkha", "Quran Shikkha", "Bangla Language ", "Foreign Language",
                             "General knowledge", "Basic Computer", "Official Computer", "Video Editing", "Digital Marketing", "Graphics Design", "Web Design", "App Development", "Freelancing", "Others"};

    String [] days = {"SAT - TUE - FRI", "SUN - TUE - THU", "MON - WED - FRI", "SAT - TUE - FRI", "TUE - THU - SAT", "WED - FRI - SUN", "THU - SAT - MON", "FRI - SUN - TUE"};

    String [] times = { "06:00 pm" , "06:00 pm" , "06:00 pm" , "06:00 pm" , "06:00 pm" };*/

    DatePickerDialog datePickerDialog;
    DatePicker datePicker;


    //FirebaseAuth firebaseAuth;
    //FirebaseUser firebaseUser;
    //DatabaseReference databaseReference;

    ProgressDialog progressDialog;

    Toolbar toolbar;





    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);

        // find all input text, button.
        full_nameEdit = findViewById(R.id.full_name);
        father_nameEdit = findViewById(R.id.father_name);
        date_of_birthEdit = findViewById(R.id.date_of_birth);
        whatsapp_noEdit = findViewById(R.id.whatsapp_no);
        emailEdit = findViewById(R.id.email);
        nationalityEdit = findViewById(R.id.nationality);
        gender = findViewById(R.id.gender);
        addressEdit = findViewById(R.id.address);
        guardian_nameEdit = findViewById(R.id.guardian_name);
        guardian_phoneEdit = findViewById(R.id.guardian_phone);
        passwordEdit = findViewById(R.id.password);
        register_btn = findViewById(R.id.submit);
        login_txt = findViewById(R.id.login_txt);



        toolbar = findViewById(R.id.student_reg_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // initialise firebaseAuth & databaseReference
        //firebaseAuth = FirebaseAuth.getInstance();
        //databaseReference = FirebaseDatabase.getInstance().getReference("User").child("Student");

        // initialise progressDialog
        progressDialog = new ProgressDialog(StudentRegisterActivity.this);
        progressDialog.setTitle("Please Wait !");
        progressDialog.setMessage("Creating Your Account.....!");





        // initialise date picker to set date on date_of_birth editText
        datePicker = new DatePicker(this);
        int currentDay = datePicker.getDayOfMonth();
        int currentMonth = (datePicker.getMonth())+1;
        int currentYear = datePicker.getYear();

       date_of_birthEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog = new DatePickerDialog(StudentRegisterActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                                date_of_birthEdit.setText(day + "/" + (month+1) +"/"+ year);
                            }
                        },currentYear,currentMonth,currentDay);

                datePickerDialog.show();

            }
        });

        // login_txt if had account
        login_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(getApplicationContext(),StudentLoginActivity.class));
            }
        });

        // if not have account
       register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // convert user input into string
                String s_name = full_nameEdit.getText().toString().trim();
                String f_name = father_nameEdit.getText().toString().trim();
                String d_of_birth = date_of_birthEdit.getText().toString().trim();
                String whats_app = whatsapp_noEdit.getText().toString().trim();
                String s_email = emailEdit.getText().toString().trim();
                String s_nationality = nationalityEdit.getText().toString().trim();
                String s_gender = gender.getText().toString().trim();
                String s_address = addressEdit.getText().toString().trim();
                String s_guardian_name = guardian_nameEdit.getText().toString().trim();
                String s_guardian_phone = guardian_phoneEdit.getText().toString().trim();
                String s_password = passwordEdit.getText().toString().trim();

                // check validation user input
                if(s_name.equals("")){
                    ShowError("Name field can't be empty!");
                }else if(d_of_birth.equals("")){
                    ShowError("Date of birth field can't be empty!");
                }else if(s_email.equals("")){
                    ShowError("Email field can't be empty!");
                }else if(s_nationality.equals("")){
                    ShowError("Nationality field can't be empty!");
                }else if(s_gender.equals("")){
                    ShowError("Gender field can't be empty!");
                } else if(s_address.equals("")){
                    ShowError("Address field can't be empty!");
                }else if(s_password.equals("")){
                    ShowError("Password field can't be empty!");
                }else if(s_password.length()<6){
                    ShowError("Password must be more than 8 character!");
                }else {

                    progressDialog.show();

                    MyApi myApi = MyRetrofit.getRetrofit().create(MyApi.class);
                    Call<ResponseBody> sRegister = myApi.studentRegister(s_name,f_name,s_email,s_password,d_of_birth,whats_app,
                            s_nationality,s_gender,s_address,s_guardian_name,s_guardian_phone);

                    sRegister.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            progressDialog.dismiss();

                            if(response.isSuccessful()){
                                assert response.body() != null;
                                Log.i("SR", "Success: "+ response.body().toString()+" success");
                                Toast.makeText(StudentRegisterActivity.this, "Registration Successfully! ", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(StudentRegisterActivity.this, DashboardActivity.class));
                            }else {
                                assert response.errorBody() != null;
                                Log.i("SR", "error1: "+ response.errorBody().toString());
                            }

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            progressDialog.dismiss();
                            Log.i("SR", "error3: "+t.getLocalizedMessage() );
                        }
                    });
                }




            }
        });



    }

    // create method to show error
    private void ShowError(String msg) {


        AlertDialog.Builder builder = new AlertDialog.Builder(StudentRegisterActivity.this);

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