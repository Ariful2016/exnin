package com.es.netschool24.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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

import com.es.netschool24.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class TeacherRegisterActivity extends AppCompatActivity {

    TextInputEditText full_nameEdit,father_nameEdit,date_of_birthEdit,mobileEdit,emailEdit, present_addressEdit,permanent_addressEdit,educationEdit,versity_nameEdit,resultEdit,passwordEdit;
    ImageView nid_birth_img,photo_img,certificate_img;
    AppCompatButton register_btn;
    TextView login_txt;

    AutoCompleteTextView gender,name_of_course_recycler;

    String [] Gender = {"Male", "Female"};
    String[] course_names = {"Bangla", "Math", "English", "Koran shikkha", "English spoken", "Foreign language ", "Basic computer",
            "Official computer", "Graphic design", "Web design", "Digital marketing", "Others"};

    DatePickerDialog datePickerDialog;
    DatePicker datePicker;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    StorageReference storageReference;

    ProgressDialog progressDialog;

    Uri nidUri,photoUri,certificateUri;
    String nidStr, photoStr, certificateStr;

    Toolbar toolbar;

    ImageView back_img;


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_register);

        // find all input text, button.
        full_nameEdit = findViewById(R.id.full_name);
        father_nameEdit = findViewById(R.id.father_name);
        date_of_birthEdit = findViewById(R.id.date_of_birth);
        emailEdit = findViewById(R.id.email);
        mobileEdit = findViewById(R.id.mobile);
        gender = findViewById(R.id.gender);
        name_of_course_recycler = findViewById(R.id.name_of_course);
        present_addressEdit = findViewById(R.id.present_address);
        permanent_addressEdit = findViewById(R.id.permanent_address);
        educationEdit = findViewById(R.id.education);
        versity_nameEdit = findViewById(R.id.versity_name);
        resultEdit = findViewById(R.id.result);
        passwordEdit = findViewById(R.id.password);
        nid_birth_img = findViewById(R.id.nid_birth);
        photo_img = findViewById(R.id.photo);
        certificate_img = findViewById(R.id.certificate);
        register_btn = findViewById(R.id.register_btn);
        login_txt = findViewById(R.id.login_txt);




       toolbar = findViewById(R.id.teacher_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // clickable nid_birth imageview to take all nid img from user phone
        nid_birth_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
<<<<<<< Updated upstream
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,101);
=======
                ImagePicker.with(TeacherRegisterActivity.this)
                        .crop()                    //Crop image(Optional), Check Customization for more option
                        .compress(512)            //Final image size will be less than 1 MB(Optional)
                        .maxResultSize(300, 300)    //Final image resolution will be less than 1080 x 1080(Optional)
                        .start(101);
>>>>>>> Stashed changes
            }
        });

        // clickable photo imageview to take all nid img from user phone
        photo_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,102);
            }
        });

        // clickable certificate imageview to take all nid img from user phone
        certificate_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
<<<<<<< Updated upstream
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,103);
=======
                ImagePicker.with(TeacherRegisterActivity.this)
                        .crop()                    //Crop image(Optional), Check Customization for more option
                        .compress(512)            //Final image size will be less than 1 MB(Optional)
                        .maxResultSize(300, 300)    //Final image resolution will be less than 1080 x 1080(Optional)
                        .start(103);
>>>>>>> Stashed changes
            }
        });

        //initialise firebaseAuth,databaseReference & storageReference
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("User").child("Teacher");
        storageReference = FirebaseStorage.getInstance().getReference("Upload").child("Teacher_img_info");


        // initialise progressDialog
        progressDialog = new ProgressDialog(TeacherRegisterActivity.this);
        progressDialog.setTitle("Please Wait !");
        progressDialog.setMessage("Creating Your Account.....!");

        // Add gender into spinner
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(TeacherRegisterActivity.this, android.R.layout.simple_list_item_1,Gender);
        gender.setAdapter(genderAdapter);

        // Add course into spinner
        ArrayAdapter<String> courseNameAdapter = new ArrayAdapter<String>(TeacherRegisterActivity.this, android.R.layout.simple_list_item_1,course_names);
        name_of_course_recycler.setAdapter(courseNameAdapter);

        gender.addTextChangedListener(new TextWatcher() {
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
        // show all course using recycler
        name_of_course_recycler.addTextChangedListener(new TextWatcher() {
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


        // initialise date picker to set date on date_of_birth editText
        datePicker = new DatePicker(this);
        int currentDay = datePicker.getDayOfMonth();
        int currentMonth = (datePicker.getMonth())+1;
        int currentYear = datePicker.getYear();

        date_of_birthEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog = new DatePickerDialog(TeacherRegisterActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                                date_of_birthEdit.setText(day + "/" + (month+1) +"/"+ year);
                            }
                        },currentYear,currentMonth,currentDay);

                datePickerDialog.show();

            }
        });

        // if had account
        login_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),TeacherLoginActivity.class));
            }
        });

        // if have no account
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // convert user input into string
                String t_name = full_nameEdit.getText().toString().trim();
                String t_father = father_nameEdit.getText().toString().trim();
                String t_birth = date_of_birthEdit.getText().toString().trim();
                String t_email = emailEdit.getText().toString().trim();
                String t_mobile = mobileEdit.getText().toString().trim();
                String t_gender = gender.getText().toString().trim();
                String t_course = name_of_course_recycler.getText().toString().trim();
                String t_present_address = present_addressEdit.getText().toString().trim();
                String t_permanent_address = permanent_addressEdit.getText().toString().trim();
                String t_education = educationEdit.getText().toString().trim();
                String t_versity_name = versity_nameEdit.getText().toString().trim();
                String t_result = resultEdit.getText().toString().trim();
                String t_password = passwordEdit.getText().toString().trim();
               // String t_nid_birth = nid_birth.getText.toString().trim();
                //String t_photo = photo.getText().toString().trim();
                //String t_certificate = certificate.getText().toString().trim();

                // check validation user input
                if(t_name.equals("")){
                    ShowError("Name field can't be empty!");
                }else if(t_father.equals("")){
                    ShowError("Father's name field can't be empty!");
                }else if(t_birth.equals("")){
                    ShowError("Birth field can't be empty!");
                }else if(t_mobile.equals("")){
                    ShowError("Mobile field can't be empty!");
                }else if(t_gender.equals("")){
                    ShowError("Gender field can't be empty!");
                }else if(t_email.equals("")){
                    ShowError("Email field can't be empty!");
                }else if(t_course.equals("")){
                    ShowError("Course field can't be empty!");
                }else if(t_present_address.equals("")){
                    ShowError("Present Address field can't be empty!");
                }else if(t_permanent_address.equals("")){
                    ShowError("Permanent Address field can't be empty!");
                }else if(t_education.equals("")){
                    ShowError("Education field can't be empty!");
                }else if(t_versity_name.equals("")){
                    ShowError("University field can't be empty!");
                }else if(t_result.equals("")){
                    ShowError("Result field can't be empty!");
                }else if(t_password.equals("")){
                    ShowError("Password field can't be empty!");
                }else if(t_password.length()<6){
                    ShowError("Password must be more than 6!");
<<<<<<< Updated upstream
                }else {
                    // create account with email & password
                    firebaseAuth.createUserWithEmailAndPassword(t_email,t_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){

                                firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

                                if (firebaseUser != null) {

                                    String currentTeacherId = firebaseUser.getUid();


                                    // create nid storageReference
                                    StorageReference nidRef = storageReference.child(currentTeacherId).child("NID_"+ System.currentTimeMillis());
                                    nidRef.putFile(nidUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                            if (task.isSuccessful()) {


                                                nidRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                    @Override
                                                    public void onSuccess(Uri uri) {

                                                      nidStr = String.valueOf(uri);

                                                        // create photo storageReference
                                                        StorageReference photoRef = storageReference.child(currentTeacherId).child("Photo_"+ System.currentTimeMillis());
                                                        photoRef.putFile(photoUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                                                                if (task.isSuccessful()){

                                                                    photoRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                                        @Override
                                                                        public void onSuccess(Uri uri) {

                                                                            photoStr= String.valueOf(uri);

                                                                            // create certificate storageReference
                                                                            StorageReference certificateRef = storageReference.child(currentTeacherId).child("Certificate_"+ System.currentTimeMillis());
                                                                            certificateRef.putFile(certificateUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                                                                @Override
                                                                                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                                                                                    if(task.isSuccessful()){

                                                                                        certificateRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                                                            @Override
                                                                                            public void onSuccess(Uri uri) {
                                                                                                certificateStr = String.valueOf(uri);


                                                                                                HashMap<String, Object> TeacherMap = new HashMap<>();
                                                                                                TeacherMap.put("fullName",t_name);
                                                                                                TeacherMap.put("fatherName",t_father);
                                                                                                TeacherMap.put("dateOfBirth",t_birth);
                                                                                                TeacherMap.put("email",t_email);
                                                                                                TeacherMap.put("mobile",t_mobile);
                                                                                                TeacherMap.put("gender",t_gender);
                                                                                                TeacherMap.put("nameOfCourse",t_course);
                                                                                                TeacherMap.put("PresentAddress",t_present_address);
                                                                                                TeacherMap.put("permanentAddress",t_permanent_address);
                                                                                                TeacherMap.put("education",t_education);
                                                                                                TeacherMap.put("versityName",t_versity_name);
                                                                                                TeacherMap.put("result",t_result);
                                                                                                TeacherMap.put("password",t_password);
                                                                                                TeacherMap.put("nidImg",nidStr);
                                                                                                TeacherMap.put("photo",photoStr);
                                                                                                TeacherMap.put("certificate",certificateStr);
                                                                                                TeacherMap.put("TeacherId",currentTeacherId);

                                                                                                // send input data to database
                                                                                                databaseReference.child(currentTeacherId).setValue(TeacherMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                    @Override
                                                                                                    public void onComplete(@NonNull Task<Void> task) {

                                                                                                        if (task.isSuccessful()) {

                                                                                                            startActivity(new Intent(TeacherRegisterActivity.this, TeacherLoginActivity.class));
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
                                                                                        });
                                                                                    }
                                                                                }
                                                                            });
                                                                        }
                                                                    });
                                                                }
                                                            }
                                                        });
                                                    }
                                                });
                                            }
                                        }
                                    });
                                }

                            }else {
=======
                } else {
                    progressDialog.show();

                    MyApi myApi = MyRetrofit.getRetrofit().create(MyApi.class);
                    if (encodedNidImage != null && encodedPhotoImage != null && encodedCertificateImage != null) {
                        Log.i("TAG", "onClick: " + encodedNidImage + encodedPhotoImage + encodedCertificateImage);

                        Call<ResponseBody> tRequest = myApi.tRegister(
                                t_name,
                                t_email,
                                t_password,
                                t_birth,
                                t_mobile,
                                t_present_address,
                                t_national,
                                t_education,
                                t_father,
                                t_gender,
                                t_permanent_address,
                                t_course,
                                t_versity_name,
                                encodedNidImage,
                                encodedPhotoImage,
                                encodedCertificateImage
                        );

                        tRequest.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                                Log.i("tRegister", "onSuccess: " + response.body()+" "+ response.code());
                                progressDialog.dismiss();
                                finish();

>>>>>>> Stashed changes

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

    // check nid_birth,photo,certificate works properly or not with method override
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 101){
            if(resultCode == RESULT_OK){
                if(data!= null){

                    nidUri = data.getData();
                    nid_birth_img.setImageURI(nidUri);



                }
            }
        }else if(requestCode ==102){
            if(resultCode == RESULT_OK){
                if (data!= null ){
                    photoUri = data.getData();
                    photo_img.setImageURI(photoUri);
                }
            }
        }else if(requestCode ==103){
            if(resultCode == RESULT_OK){
                if (data!= null ){
                    certificateUri = data.getData();
                    certificate_img.setImageURI(certificateUri);
                }
            }
        }
    }

    // create method to show error
    private void ShowError(String msg) {


        AlertDialog.Builder builder = new AlertDialog.Builder(TeacherRegisterActivity.this);

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