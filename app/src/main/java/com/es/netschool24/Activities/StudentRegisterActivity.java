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

public class StudentRegisterActivity extends AppCompatActivity {

    TextInputEditText full_nameEdit,father_nameEdit,mother_nameEdit,date_of_birthEdit,whatsapp_noEdit,emailEdit,nationalityEdit,addressEdit,guardian_nameEdit,
            guardian_phoneEdit,relation_with_guardianEdit,time,present_addressEdit,permanent_addressEdit, educationEdit,versity_nameEdit,resultEdit,passwordEdit;
    ImageView nid_birth,photo,certificate;
    AppCompatButton register_btn;
    TextView login_txt;

    //AutoCompleteTextView name_of_course,day;

    /*String[] course_names = {"Kid's Learning", "Kid's English", "Spoken English", "Arabic Shikkha", "Quran Shikkha", "Bangla Language ", "Foreign Language",
                             "General knowledge", "Basic Computer", "Official Computer", "Video Editing", "Digital Marketing", "Graphics Design", "Web Design", "App Development", "Freelancing", "Others"};

    String [] days = {"SAT - TUE - FRI", "SUN - TUE - THU", "MON - WED - FRI", "SAT - TUE - FRI", "TUE - THU - SAT", "WED - FRI - SUN", "THU - SAT - MON", "FRI - SUN - TUE"};

    String [] times = { "06:00 pm" , "06:00 pm" , "06:00 pm" , "06:00 pm" , "06:00 pm" };*/

    DatePickerDialog datePickerDialog;
    DatePicker datePicker;


    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;

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
        mother_nameEdit = findViewById(R.id.mother_name);
        date_of_birthEdit = findViewById(R.id.date_of_birth);
        whatsapp_noEdit = findViewById(R.id.whatsapp_no);
        emailEdit = findViewById(R.id.email);
        nationalityEdit = findViewById(R.id.nationality);
        addressEdit = findViewById(R.id.address);
        //name_of_course = findViewById(R.id.name_of_course);
        //day = findViewById(R.id.day);
       // time = findViewById(R.id.s_time);
        guardian_nameEdit = findViewById(R.id.guardian_name);
        guardian_phoneEdit = findViewById(R.id.guardian_phone);
        relation_with_guardianEdit = findViewById(R.id.relation_with_guardian);
       // present_address = findViewById(R.id.present_address);
       // permanent_address = findViewById(R.id.permanent_address);
       // education = findViewById(R.id.education);
       // versity_name = findViewById(R.id.versity_name);
       // result = findViewById(R.id.result);
        passwordEdit = findViewById(R.id.password);
       // nid_birth = findViewById(R.id.nid_birth);
       // photo = findViewById(R.id.photo);
       // certificate = findViewById(R.id.certificate);
        register_btn = findViewById(R.id.submit);
        login_txt = findViewById(R.id.login_txt);



        toolbar = findViewById(R.id.student_reg_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // initialise firebaseAuth & databaseReference
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("User").child("Student");

        // initialise progressDialog
        progressDialog = new ProgressDialog(StudentRegisterActivity.this);
        progressDialog.setTitle("Please Wait !");
        progressDialog.setMessage("Creating Your Account.....!");


        // Add course into spinner
        //ArrayAdapter<String> courseNameAdapter = new ArrayAdapter<String>(StudentRegisterActivity.this, android.R.layout.simple_list_item_1,course_names);
        //name_of_course.setAdapter(courseNameAdapter);

        // Add days into spinner
       // ArrayAdapter<String> daysAdapter = new ArrayAdapter<String>(StudentRegisterActivity.this, android.R.layout.simple_list_item_1,days);
       // day.setAdapter(daysAdapter);

        // Add time into spinner
        /*ArrayAdapter<String> timeAdapter = new ArrayAdapter<String>(StudentRegisterActivity.this, android.R.layout.simple_list_item_1,times);
        time.setAdapter(timeAdapter);*/

        /*name_of_course.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        day.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });*/
       /* time.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });*/


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
                String m_name = mother_nameEdit.getText().toString().trim();
                String d_of_birth = date_of_birthEdit.getText().toString().trim();
                String whats_app = whatsapp_noEdit.getText().toString().trim();
                String s_email = emailEdit.getText().toString().trim();
                String s_nationality = nationalityEdit.getText().toString().trim();
                String s_address = addressEdit.getText().toString().trim();
               // String s_name_of_course = name_of_course.getText().toString().trim();
                //String s_day = day.getText().toString().trim();
                //String s_time = time.getText().toString().trim();
                String s_guardian_name = guardian_nameEdit.getText().toString().trim();
                String s_guardian_phone = guardian_phoneEdit.getText().toString().trim();
                String s_relation_with_guardian = relation_with_guardianEdit.getText().toString().trim();
                String s_password = passwordEdit.getText().toString().trim();

                // check validation user input
                if(s_name.equals("")){
                    ShowError("Name field can't be empty!");
                }else if(f_name.equals("")){
                    ShowError("Father's name field can't be empty!");
                }else if(m_name.equals("")){
                    ShowError("Mother's name field can't be empty!");
                }else if(d_of_birth.equals("")){
                    ShowError("Date of birth field can't be empty!");
                }else if(whats_app.equals("")){
                    ShowError("WhatsApp field can't be empty!");
                }else if(s_email.equals("")){
                    ShowError("Email field can't be empty!");
                }else if(s_nationality.equals("")){
                    ShowError("Nationality field can't be empty!");
                }else if(s_address.equals("")){
                    ShowError("Address field can't be empty!");
                }/*else if(s_name_of_course.equals("")){
                    ShowError("Course field can't be empty!");
                }else if(s_day.equals("")){
                    ShowError("Day field can't be empty!");
                }else if(s_time.equals("")){
                    ShowError("Time field can't be empty!");
                }*/else if(s_guardian_name.equals("")){
                    ShowError("Guardian's name field can't be empty!");
                }else if(s_guardian_phone.equals("")){
                    ShowError("Guardian's phone field can't be empty!");
                }else if(s_relation_with_guardian.equals("")){
                    ShowError("Relationship field can't be empty!");
                }else if(s_password.equals("")){
                    ShowError("Password field can't be empty!");
                }else if(s_password.length()<6){
                    ShowError("Password must be more than 6!");
                }else {
                    // create account with email & password
                    firebaseAuth.createUserWithEmailAndPassword(s_email,s_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){

                                firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

                                if (firebaseUser != null) {

                                    String currentStudentId = firebaseUser.getUid();

                                    HashMap<String, Object> studentMap = new HashMap<>();
                                    studentMap.put("fullName",s_name);
                                    studentMap.put("fatherName",f_name);
                                    studentMap.put("motherName",m_name);
                                    studentMap.put("dateOfBirth",d_of_birth);
                                    studentMap.put("whatsApp",whats_app);
                                    studentMap.put("email",s_email);
                                    studentMap.put("nationality",s_nationality);
                                    studentMap.put("address",s_address);
                                   // studentMap.put("nameOfCourse",s_name_of_course);
                                   // studentMap.put("day",s_day);
                                   // studentMap.put("time",s_time);
                                    studentMap.put("guardianName",s_guardian_name);
                                    studentMap.put("guardianPhone",s_guardian_phone);
                                    studentMap.put("relationWithGuardian",s_relation_with_guardian);
                                    studentMap.put("password",s_password);
                                    studentMap.put("studentUserId",currentStudentId);



                                    // send input data to database
                                    databaseReference.child(currentStudentId).setValue(studentMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if (task.isSuccessful()) {

                                                startActivity(new Intent(StudentRegisterActivity.this, StudentLoginActivity.class));
                                                progressDialog.dismiss();
                                                finish();
                                            }else {
                                                progressDialog.dismiss();
                                                Log.i("TAG", "Error is : " + task.getException().getMessage());
                                                ShowError(task.getException().getMessage());

                                            }

                                        }
                                    });
                                }

                            }else {

                                progressDialog.dismiss();
                                Log.i("TAG", "Error is : " + task.getException().getMessage());
                                ShowError(task.getException().getMessage());

                            }

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