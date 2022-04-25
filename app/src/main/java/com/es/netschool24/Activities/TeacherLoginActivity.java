package com.es.netschool24.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.es.netschool24.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class TeacherLoginActivity extends AppCompatActivity {

    AppCompatButton login_btn;
    TextInputEditText emailEdit,passwordEdit;
    TextView create_account_txt;


    FirebaseAuth firebaseAuth;

    ProgressDialog progressDialog;

    Toolbar toolbar;


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);

        // find all input text, button.
        create_account_txt = findViewById(R.id.create_account);
        login_btn = findViewById(R.id.login);
        emailEdit = findViewById(R.id.email);
        passwordEdit = findViewById(R.id.password);


        toolbar = findViewById(R.id.teacher_login_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // initialise firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();

        // initialise progressDialog
        progressDialog = new ProgressDialog(TeacherLoginActivity.this);
        progressDialog.setTitle("Please Wait !");
        progressDialog.setMessage("Checking Your Account.....!");

        // if have no account
        create_account_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeacherLoginActivity.this,TeacherRegisterActivity.class);
                startActivity(intent);
            }
        });

        // if had accont
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // convert user input  to string
                String email = emailEdit.getText().toString().trim();
                String password = passwordEdit.getText().toString().trim();


                // check input validation
                if (email.equals("")) {

                    ShowError("Email Field can't be empty !");


                } else if (password.equals("")) {

                    ShowError("Password Field can't be empty !");


                } else if (password.length() < 6) {

                    ShowError("Password should be more than 6 digit !");

                } else {

                    progressDialog.show();
                    // create account with email & password
                    firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                startActivity(new Intent(TeacherLoginActivity.this, DashboardActivity.class));
                                progressDialog.dismiss();
                                finish();


                            } else {
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


        AlertDialog.Builder builder = new AlertDialog.Builder(TeacherLoginActivity.this);

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