package com.es.netschool24.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.es.netschool24.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    TextInputEditText email,pass;
    AppCompatButton signup_btn;

    FirebaseAuth firebaseAuth;

    ProgressDialog progressDialog;

    TextView txtSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        signup_btn = findViewById(R.id.login);
        txtSignup = findViewById(R.id.txtSignup);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait...");
        progressDialog.setMessage("Sign in with email...");


        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_str =email.getText().toString().trim();
                String pass_str = pass.getText().toString().trim();

                if (email_str.equals("")) {

                    ShowError("Email Field can't be empty !");


                } else if (pass_str.equals("")) {

                    ShowError("Password Field can't be empty !");


                } else if (pass_str.length() < 6) {

                    ShowError("Password should be more than 6 character !");

                } else {

                    progressDialog.show();
                    firebaseAuth.createUserWithEmailAndPassword(email_str, pass_str).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {


                            if (task.isSuccessful()) {
                                progressDialog.dismiss();
                                startActivity(new Intent(getApplicationContext(), SignInActivity.class));

                                finish();
                            }

                        }
                    });

                }
            }
        });
    }

        private void ShowError(String msg) {


            AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);

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