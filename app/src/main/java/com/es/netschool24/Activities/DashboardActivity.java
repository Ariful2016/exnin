package com.es.netschool24.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ShareCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Intent;
import android.database.DatabaseErrorHandler;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.es.netschool24.Adapters.SliderAdapter;

import com.es.netschool24.AppConstants.AppConstants;
import com.es.netschool24.Fragment.HomeFragment;
import com.es.netschool24.Fragment.MessageFragment;
import com.es.netschool24.Models.Options;
import com.es.netschool24.Models.SliderItems;
import com.es.netschool24.MyApi;
import com.es.netschool24.MyRetrofit;
import com.es.netschool24.R;
import com.es.netschool24.storage.SharedPrefManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import nl.psdcompany.duonavigationdrawer.views.DuoDrawerLayout;
import nl.psdcompany.duonavigationdrawer.widgets.DuoDrawerToggle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DashboardActivity extends AppCompatActivity {


    Toolbar toolbar;

    CircleImageView profile_image;
    ImageView logo;

    TextView profileName, profileEmail, aboutUs, yourProfile, course, studentRegistration,teacherRegistration,freeLearning,visitWebsite, payment, notification, complain, contact;


    FragmentManager fragmentManager;

    List<Options> optionsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // find toolbar & setup


        toolbar = findViewById(R.id.toolbar);
        // find all text view
        profile_image = findViewById(R.id.profile_image);
        profileName = findViewById(R.id.profileName);
        profileEmail = findViewById(R.id.profileEmail);
        aboutUs = findViewById(R.id.aboutUs);
        yourProfile = findViewById(R.id.yourProfile);
        course = findViewById(R.id.course);
        studentRegistration = findViewById(R.id.studentRegistration);
        teacherRegistration = findViewById(R.id.teacherRegistration);
        freeLearning = findViewById(R.id.freeLearning);
        visitWebsite = findViewById(R.id.visitWebsite);
        payment = findViewById(R.id.payment);
        notification = findViewById(R.id.notification);
        complain = findViewById(R.id.complain);
        contact = findViewById(R.id.contact);
        logo = findViewById(R.id.logo);

        optionsList = new ArrayList<>();

        MyApi myApi = MyRetrofit.getRetrofit().create(MyApi.class);
        Call<List<Options>> options = myApi.getOptions();

        options.enqueue(new Callback<List<Options>>() {
            @Override
            public void onResponse(Call<List<Options>> call, Response<List<Options>> response) {
                optionsList = response.body();
                assert optionsList != null;
                if (optionsList.size()>0){
                    for (Options i : optionsList){
                        Log.i("Logo", "onResponse: "+ AppConstants.logo_image_path+i.getLogo()+ " "+AppConstants.logo_icon_image_path+i.getLogoIcon());

                        toolbar.setLogoDescription(AppConstants.logo_image_path+i.getLogo());


                        //logo.setImageURI(Uri.parse(AppConstants.logo_icon_image_path+i.getLogoIcon()));
                        Glide.with(DashboardActivity.this).load(AppConstants.logo_icon_image_path+i.getLogoIcon()).into(logo);
                    }
                }

            }

            @Override
            public void onFailure(Call<List<Options>> call, Throwable t) {

            }
        });

        setSupportActionBar(toolbar);


        // initialise fragmentmanager
        fragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            fragmentManager.beginTransaction().add(R.id.container, new HomeFragment(), null).commit();
        }


        DuoDrawerLayout drawerLayout = (DuoDrawerLayout) findViewById(R.id.drawer);
        DuoDrawerToggle drawerToggle = new DuoDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);


        drawerToggle.setDrawerIndicatorEnabled(false);
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_hamburger, getApplicationContext().getTheme());
        drawerToggle.setHomeAsUpIndicator(drawable);
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

        drawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);

                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });


        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent whatsapp = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.whatsapp.com/"));
                //startActivity(whatsapp);
                startActivity(new Intent(DashboardActivity.this,DashboardActivity.class));

            }
        });




        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId()) {
                    case R.id.home:
                        fragmentManager.beginTransaction().replace(R.id.container, new HomeFragment(), null).commit();

                        Toast.makeText(DashboardActivity.this, "home is clicked", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.share:
                        Toast.makeText(DashboardActivity.this, "share is clicked", Toast.LENGTH_SHORT).show();
                        // PopupMenu popupMenu = new PopupMenu(DashboardActivity.this,R.id.share);

                        ShareCompat.IntentBuilder
                                .from(DashboardActivity.this)
                                .setType("text/plain")
                                .setChooserTitle("Share URL")
                                .setText("Hey check out my app at :  https://play.google.com/store/apps/details?id="+getPackageName())
                                .startChooser();
                        break;

                    case R.id.whatsapp:

                        break;
                    case R.id.message:

                        fragmentManager.beginTransaction().replace(R.id.container, new MessageFragment(), null).commit();

                        Toast.makeText(DashboardActivity.this, "Message is clicked", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.profile:
                        Intent intent = new Intent(DashboardActivity.this, YourProfileActivity.class);
                        startActivity(intent);
                        break;

                }


                return true;
            }
        });

        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DashboardActivity.this, AboutUsActivity.class);
                startActivity(intent);
            }
        });

        yourProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DashboardActivity.this, YourProfileActivity.class);
                startActivity(intent);
            }
        });

        course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DashboardActivity.this, OurCourseActivity.class);
                startActivity(intent);
            }
        });

        studentRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DashboardActivity.this, StudentRegisterActivity.class);
                startActivity(intent);
            }
        });

        teacherRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DashboardActivity.this, TeacherRegisterActivity.class);
                startActivity(intent);
            }
        });

        freeLearning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DashboardActivity.this, JoinFreeLearningActivity.class);
                startActivity(intent);
            }
        });

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DashboardActivity.this, PaymentActivity.class);
                startActivity(intent);
            }
        });

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DashboardActivity.this, NotificationActivity.class);
                startActivity(intent);
            }
        });

        complain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DashboardActivity.this, ComplainActivity.class);
                startActivity(intent);
            }
        });

        visitWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DashboardActivity.this, WebViewActivity.class);
                startActivity(intent);
            }
        });

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DashboardActivity.this, ContactActivity.class);
                startActivity(intent);
            }
        });

        UserPermission();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.top_menu,menu);

        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.privacy_policy){

            //Toast.makeText(DashboardActivity.this, "Privacy and Policy Clicked", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(DashboardActivity.this, PrivacyPolicyActivity.class));

        }
        if(item.getItemId() == R.id.changePassword){

            Toast.makeText(DashboardActivity.this, "Change Password Clicked", Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId() == R.id.logout){
            SharedPrefManager.getInstance(DashboardActivity.this).LoggedOut();
            startActivity(new Intent(DashboardActivity.this, SignInActivity.class));
            finish();
        }



        return super.onOptionsItemSelected(item);
    }

    private void UserPermission() {

        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)

                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            // do you work now
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // permission is denied permenantly, navigate user to app settings
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                })
                .onSameThread()
                .check();

    }


}