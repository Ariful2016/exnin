package com.es.netschool24.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.es.netschool24.Adapters.SocialAdapter;
import com.es.netschool24.Models.Options;
import com.es.netschool24.Models.SocialLink;
import com.es.netschool24.MyApi;
import com.es.netschool24.MyRetrofit;
import com.es.netschool24.R;
import com.google.android.material.textfield.TextInputEditText;

import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactActivity extends AppCompatActivity {

    Toolbar toolbar;

    List<SocialLink> socialLinkList;
    List<Options> optionsList;
    List<Options> phoneList;

   // ConstraintLayout whatsapp_link,facebook_link,email_link,linkedin_link,pinterest_link,instagram_link,youtube_link;

    RecyclerView socialRecycler;
    SocialAdapter socialAdapter;

    TextInputEditText full_name,mobile_num,e_mail,message;
    AppCompatButton submit_btn;

    TextView phone_txt1,phone_txt2,email_txt1,email_txt2,web_txt;

    ProgressDialog progressDialog;


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        toolbar = findViewById(R.id.contact_toolbar);
        socialRecycler = findViewById(R.id.socialRecycler);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        socialLinkList = new ArrayList<>();
        optionsList = new ArrayList<>();
        phoneList = new ArrayList<>();

        full_name = findViewById(R.id.full_name);
        mobile_num = findViewById(R.id.mobile_num);
        e_mail = findViewById(R.id.e_mail);
        message = findViewById(R.id.message);
        submit_btn = findViewById(R.id.submit_btn);

        phone_txt1 = findViewById(R.id.phone_txt1);
        phone_txt2 = findViewById(R.id.phone_txt2);
        email_txt1 = findViewById(R.id.email_txt1);
        email_txt2 = findViewById(R.id.email_txt2);
        web_txt = findViewById(R.id.web_txt);


        // initialise progressDialog
        progressDialog = new ProgressDialog(ContactActivity.this);
        progressDialog.setTitle("Please Wait !");
        progressDialog.setMessage("Sending your message.....!");


        MyApi myApi = MyRetrofit.getRetrofit().create(MyApi.class);


        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_str = Objects.requireNonNull(full_name.getText()).toString();
                String mobile_str = Objects.requireNonNull(mobile_num.getText()).toString();
                String mail_str = Objects.requireNonNull(e_mail.getText()).toString();
                String message_str = Objects.requireNonNull(message.getText()).toString();

                if (name_str.isEmpty()){
                    ShowError("Name field is required!");
                }else if(mobile_str.isEmpty()){
                    ShowError("Mobile field is required!");
                }else if(mail_str.isEmpty()){
                    ShowError("E-Mail field is required!");
                }else if(message_str.isEmpty()){
                    ShowError("Message field is required!");
                }else {
                    progressDialog.show();
                    Call<ResponseBody> sendSms = myApi.sendMessage(name_str,mobile_str,mail_str,message_str);
                    sendSms.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            progressDialog.dismiss();
                            if (response.isSuccessful()){
                                Log.i("Message", "onResponse: "+ response.body());
                                Toast.makeText(ContactActivity.this, "Message sends successfully", Toast.LENGTH_SHORT).show();
                                full_name.setText("");
                                mobile_num.setText("");
                                e_mail.setText("");
                                message.setText("");
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            progressDialog.dismiss();
                            Toast.makeText(ContactActivity.this, "Message does not send, please try again!", Toast.LENGTH_SHORT).show();
                            Log.i("Message", "onError: "+ t.getLocalizedMessage().toString());
                        }
                    });
                }

            }
        });

        Call<List<SocialLink>> call = myApi.getSocialLink();
        call.enqueue(new Callback<List<SocialLink>>() {
            @Override
            public void onResponse(Call<List<SocialLink>> call, Response<List<SocialLink>> response) {
                socialLinkList = response.body();

                if (socialLinkList.size()>0){
                    for (SocialLink socialLink: socialLinkList){
                        Log.i("Link", "onResponse: "+socialLink.getLink());
                        socialAdapter = new SocialAdapter(ContactActivity.this, socialLinkList);
                        socialRecycler.setAdapter(socialAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<SocialLink>> call, Throwable t) {

            }
        });

        Call<List<Options>> option = myApi.getOptions();

        option.enqueue(new Callback<List<Options>>() {
            @Override
            public void onResponse(Call<List<Options>> call, Response<List<Options>> response) {

                optionsList = response.body();
                assert optionsList != null;
                if (optionsList.size()>0){
                    for (Options options: optionsList){
                        Log.i("Footer", "onResponse: "+ options.getFooterSiteLink());
                        String web = Jsoup.parse(options.getFooterSiteLink()).text();
                        web_txt.setText(web);

                        String phoneList = Jsoup.parse(options.getFooterNumber()).text();
                        //Log.i("phone", "onResponse: "+phoneList);
                        String phone1 = phoneList.substring(0,15);
                        String phone2 = phoneList.substring(16,31);
                        //Log.i("phone1", "onResponse: "+phone1+" "+ phone2);

                        phone_txt1.setText(phone1);
                        phone_txt2.setText(phone2);

                        String emailList = Jsoup.parse(options.getFooterEmail()).text();
                        //Log.i("email", "onResponse: "+emailList);
                        String email1 = emailList.substring(0,17);
                        String email2 = emailList.substring(18,38);
                        //Log.i("email", "onResponse: "+email1 +" "+email2 );

                        email_txt1.setText(email1);
                        email_txt2.setText(email2);

                    }
                }

            }

            @Override
            public void onFailure(Call<List<Options>> call, Throwable t) {

            }
        });






    }

    private void LordUrl(String s) {

        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));

    }


    private void ShowError(String msg) {


        AlertDialog.Builder builder = new AlertDialog.Builder(ContactActivity.this);

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