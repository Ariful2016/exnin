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
import com.google.firebase.auth.FirebaseUser;

public class StudentLoginActivity extends AppCompatActivity {

    AppCompatButton login_btn;
    TextInputEditText emailEdit,passwordEdit;
    TextView create_account_txt;



    ProgressDialog progressDialog;



    Toolbar toolbar;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        // find all input text, button.
        create_account_txt = findViewById(R.id.create_account);
        login_btn = findViewById(R.id.login);
        emailEdit = findViewById(R.id.email);
        passwordEdit = findViewById(R.id.password);






        toolbar = findViewById(R.id.student_log_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // initialise progressDialog

        progressDialog = new ProgressDialog(StudentLoginActivity.this);
        progressDialog.setTitle("Please Wait !");
        progressDialog.setMessage("Checking Your Account.....!");

        // login_txt if had no account
        create_account_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentLoginActivity.this,StartActivity.class);
                startActivity(intent);
            }
        });

        // login_btn if had account
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // convert user input into string
                String email = emailEdit.getText().toString().trim();
                String password = passwordEdit.getText().toString().trim();


                // check validation user input
                if (email.equals("")) {

                    ShowError("Email Field can't be empty !");


                } else if (password.equals("")) {

                    ShowError("Password Field can't be empty !");


                } else if (password.length() < 6) {

                    ShowError("Password should be more than 6 digit !");

                } else {
                    progressDialog.show();


                }

            }
        });

    }

    // create method to show error
    private void ShowError(String msg) {


        AlertDialog.Builder builder = new AlertDialog.Builder(StudentLoginActivity.this);

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