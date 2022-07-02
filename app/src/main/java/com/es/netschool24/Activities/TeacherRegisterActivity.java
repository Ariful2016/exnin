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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.FileUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import com.es.netschool24.Models.AllCourse;
import com.es.netschool24.Models.AllCourseParent;
import com.es.netschool24.Models.TeachersEducation;
import com.es.netschool24.MyApi;
import com.es.netschool24.MyRetrofit;
import com.es.netschool24.R;
import com.github.dhaval2404.imagepicker.ImagePicker;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherRegisterActivity extends AppCompatActivity {

    TextInputEditText full_nameEdit, father_nameEdit, date_of_birthEdit, mobileEdit, emailEdit, nationalityEdit, present_addressEdit, permanent_addressEdit, versity_nameEdit, resultEdit, passwordEdit;
    ImageView nid_birth_img, photo_img, certificate_img;
    AppCompatButton register_btn;
    TextView login_txt;

    AutoCompleteTextView gender;
    MultiAutoCompleteTextView name_of_course_recycler, educationEdit;

    String[] Gender = {"Male", "Female"};
    String[] course_names;
    String[] teachers_educations;

    List<AllCourse> allCourseList;
    List<TeachersEducation> teachersEducationList;

    DatePickerDialog datePickerDialog;
    DatePicker datePicker;


    ProgressDialog progressDialog;

    Uri nidUri, photoUri, certificateUri;
    String nidStr, photoStr, certificateStr;

    Toolbar toolbar;

    ImageView back_img;

    String filePath = "";

    String encodedNidImage;
    String encodedPhotoImage;
    String encodedCertificateImage;


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
        nationalityEdit = findViewById(R.id.nationality);
        present_addressEdit = findViewById(R.id.present_address);
        permanent_addressEdit = findViewById(R.id.permanent_address);
        educationEdit = findViewById(R.id.education);
        versity_nameEdit = findViewById(R.id.versity_name);
        passwordEdit = findViewById(R.id.password);
        nid_birth_img = findViewById(R.id.nid_birth);
        photo_img = findViewById(R.id.photo);
        certificate_img = findViewById(R.id.certificate);
        register_btn = findViewById(R.id.register_btn);
        login_txt = findViewById(R.id.login_txt);


        allCourseList = new ArrayList<>();
        teachersEducationList = new ArrayList<>();


        toolbar = findViewById(R.id.teacher_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // clickable nid_birth imageview to take all nid img from user phone
        nid_birth_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 101);
            }
        });

        // clickable photo imageview to take all nid img from user phone
        photo_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImagePicker.with(TeacherRegisterActivity.this)
                        .crop()                    //Crop image(Optional), Check Customization for more option
                        .compress(512)            //Final image size will be less than 1 MB(Optional)
                        .maxResultSize(300, 300)    //Final image resolution will be less than 1080 x 1080(Optional)
                        .start(102);

                /*               Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 102);*/
            }
        });

        // clickable certificate imageview to take all nid img from user phone
        certificate_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 103);
            }
        });


        //initialise firebaseAuth,databaseReference & storageReference


        // initialise progressDialog
        progressDialog = new ProgressDialog(TeacherRegisterActivity.this);
        progressDialog.setTitle("Please Wait !");
        progressDialog.setMessage("Creating Your Account.....!");


        MyApi myApi = MyRetrofit.getRetrofit().create(MyApi.class);
        Call<AllCourseParent> allCourse = myApi.getAllCourse();
        Call<List<TeachersEducation>> teachersEducationCall = myApi.getTeachersEducation();

        teachersEducationCall.enqueue(new Callback<List<TeachersEducation>>() {
            @Override
            public void onResponse(Call<List<TeachersEducation>> call, Response<List<TeachersEducation>> response) {
                teachersEducationList = response.body();
                teachers_educations = new String[teachersEducationList.size()];
                for (int i = 0; i < teachersEducationList.size(); i++) {
                    teachers_educations[i] = teachersEducationList.get(i).getName();
                }
                ArrayAdapter<String> educationAdapter = new ArrayAdapter<String>(TeacherRegisterActivity.this, android.R.layout.simple_list_item_1, teachers_educations);
                educationEdit.setAdapter(educationAdapter);
            }

            @Override
            public void onFailure(Call<List<TeachersEducation>> call, Throwable t) {

            }
        });

        allCourse.enqueue(new Callback<AllCourseParent>() {
            @Override
            public void onResponse(Call<AllCourseParent> call, Response<AllCourseParent> response) {
                allCourseList = response.body().getCourse();
                course_names = new String[allCourseList.size()];

                for (int i = 0; i < allCourseList.size(); i++) {

                    course_names[i] = allCourseList.get(i).getName();
                }

                ArrayAdapter<String> courseNameAdapter = new ArrayAdapter<String>(TeacherRegisterActivity.this, android.R.layout.simple_list_item_1, course_names);
                name_of_course_recycler.setAdapter(courseNameAdapter);
            }

            @Override
            public void onFailure(Call<AllCourseParent> call, Throwable t) {

            }
        });

        // Add gender into spinner
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(TeacherRegisterActivity.this, android.R.layout.simple_list_item_1, Gender);
        gender.setAdapter(genderAdapter);

        // Add course into spinner
        /*ArrayAdapter<String> courseNameAdapter = new ArrayAdapter<String>(TeacherRegisterActivity.this, android.R.layout.simple_list_item_1,course_names);
        name_of_course_recycler.setAdapter(courseNameAdapter);*/

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
        name_of_course_recycler.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        educationEdit.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());


        // initialise date picker to set date on date_of_birth editText
        datePicker = new DatePicker(this);
        int currentDay = datePicker.getDayOfMonth();
        int currentMonth = (datePicker.getMonth()) + 1;
        int currentYear = datePicker.getYear();

        date_of_birthEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog = new DatePickerDialog(TeacherRegisterActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                                date_of_birthEdit.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, currentYear, currentMonth, currentDay);

                datePickerDialog.show();

            }
        });

        // if had account
        login_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), TeacherLoginActivity.class));
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
                String t_national = nationalityEdit.getText().toString().trim();
                String t_course = name_of_course_recycler.getText().toString().trim();
                String t_present_address = present_addressEdit.getText().toString().trim();
                String t_permanent_address = permanent_addressEdit.getText().toString().trim();
                String t_education = educationEdit.getText().toString().trim();
                String t_versity_name = versity_nameEdit.getText().toString().trim();
                String t_password = passwordEdit.getText().toString().trim();
                // check validation user input
                if (t_name.equals("")) {
                    ShowError("Name field can't be empty!");
                } else if (t_father.equals("")) {
                    ShowError("Father's name field can't be empty!");
                } else if (t_mobile.equals("")) {
                    ShowError("Mobile field can't be empty!");
                } else if (t_gender.equals("")) {
                    ShowError("Gender field can't be empty!");
                } else if (t_email.equals("")) {
                    ShowError("Email field can't be empty!");
                } else if (t_course.equals("")) {
                    ShowError("Course field can't be empty!");
                } else if (t_present_address.equals("")) {
                    ShowError("Present Address field can't be empty!");
                } else if (t_permanent_address.equals("")) {
                    ShowError("Permanent Address field can't be empty!");
                } else if (t_education.equals("")) {
                    ShowError("Education field can't be empty!");
                } else if (t_versity_name.equals("")) {
                    ShowError("University field can't be empty!");
                } else if (encodedNidImage.equals("")) {
                    ShowError("NID field can't be empty!");
                } else if (t_birth.equals("")) {
                    ShowError("Birth field can't be empty!");
                } else if (encodedCertificateImage.equals("")) {
                    ShowError("Certificate field can't be empty!");
                } else if (encodedPhotoImage.equals("")) {
                    ShowError("Certificate field can't be empty!");
                } else if (t_password.equals("")) {
                    ShowError("Password field can't be empty!");
                } else if (t_password.length() < 6) {
                    ShowError("Password must be more than 6!");
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

                                Log.i("tRegister", "onSuccess: " + response.body());
                                progressDialog.dismiss();
                                finish();


                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {

                                Log.i("tRegister", "errorResponse: " + t.getMessage());
                                progressDialog.dismiss();

                            }
                        });


                    }


                }

            }
        });

    }
    private String encodeImage(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encImage;
    }

    // check nid_birth,photo,certificate works properly or not with method override
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    nidUri = data.getData();
                    final InputStream imageStream;
                    try {
                        imageStream = getContentResolver().openInputStream(nidUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        encodedNidImage = encodeImage(selectedImage);

                        Log.i("TAG", "onActivityResult: " + encodedNidImage);

                        nid_birth_img.setImageBitmap(selectedImage);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }


                }
            }
        }
        if (requestCode == 102) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    photoUri = data.getData();
                    final InputStream imageStream;
                    try {
                        imageStream = getContentResolver().openInputStream(photoUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        encodedPhotoImage = encodeImage(selectedImage);

                        Log.i("TAG", "onActivityResult: " + encodedPhotoImage);

                        photo_img.setImageBitmap(selectedImage);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }


                }
            }
        }
        if (requestCode == 103) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    certificateUri = data.getData();
                    final InputStream imageStream;
                    try {
                        imageStream = getContentResolver().openInputStream(certificateUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        encodedCertificateImage = encodeImage(selectedImage);

                        Log.i("TAG", "onActivityResult: " + encodedCertificateImage);

                        certificate_img.setImageBitmap(selectedImage);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
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

