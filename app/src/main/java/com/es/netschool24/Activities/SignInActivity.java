package com.es.netschool24.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.es.netschool24.Models.AllCourseParent;
import com.es.netschool24.Models.LoginResponse;
import com.es.netschool24.MyApi;
import com.es.netschool24.MyRetrofit;
import com.es.netschool24.R;
import com.es.netschool24.storage.SharedPrefManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {

    TextInputEditText email, pass;
    AppCompatButton login_btn;


    ProgressDialog progressDialog;

    TextView txtSignup, txtErrMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        login_btn = findViewById(R.id.login);
        txtSignup = findViewById(R.id.txtSignup);
        txtErrMsg = findViewById(R.id.txtErrMsg);


        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait...");
        progressDialog.setMessage("Sign in with email...");

        txtSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_str = email.getText().toString().trim();
                String pass_str = pass.getText().toString().trim();

                if (email_str.equals("")) {

                    ShowError("Email Field can't be empty !");


                } else if (pass_str.equals("")) {

                    ShowError("Password Field can't be empty !");


                } else if (pass_str.length() < 8) {

                    ShowError("Password should be more than 6 character !");

                } else {

                    progressDialog.show();

                    MyApi myApi = MyRetrofit.getRetrofit().create(MyApi.class);
                    Call<LoginResponse> call = myApi.userLogin(email_str, pass_str);

                    call.enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            progressDialog.dismiss();
                            LoginResponse loginResponse = response.body();
                            assert loginResponse != null;
                            if (!loginResponse.getError()
                                    && email_str.equals(loginResponse.getUser().getEmail())) {
                                //startActivity(new Intent(SignInActivity.this, DashboardActivity.class));
                                Log.i("Log", "onResponse: " + loginResponse.getUser().getEmail());
                                Log.i("Log", "onResponse: " + loginResponse.getUser().getId());

                                SharedPrefManager.saveUserID(loginResponse.getUser().getId(), SignInActivity.this);

                                SharedPrefManager.getInstance(SignInActivity.this).saveUser(loginResponse);
                                //startActivity(new Intent(SignInActivity.this, DashboardActivity.class));
                                finish();
                                /*for (int i=0; i<loginResponse.getUser().getRoles().size(); i++){
                                    String role = loginResponse.getUser().getRoles().get(i).getName();
                                    if (Objects.equals(role, "student")){
                                        startActivity(new Intent(SignInActivity.this, DashboardActivity.class));
                                    }else {

                                    }
                                }*/
                            } else {
                                txtErrMsg.setText("Email or Password doesn't match, please try again!");


                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            progressDialog.dismiss();
                            Log.i("Log", "onResponse: failed" + t.getLocalizedMessage().toString());

                        }
                    });


                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (SharedPrefManager.getInstance(SignInActivity.this).isLoggedIn()) {
            startActivity(new Intent(SignInActivity.this, DashboardActivity.class));
        }
    }

    private void ShowError(String msg) {


        AlertDialog.Builder builder = new AlertDialog.Builder(SignInActivity.this);

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