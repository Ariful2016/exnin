package com.es.netschool24.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.es.netschool24.Adapters.CartAdapter;
import com.es.netschool24.Models.Cart;
import com.es.netschool24.Models.CartCourse;
import com.es.netschool24.Models.CartCourseMain;
import com.es.netschool24.MyApi;
import com.es.netschool24.MyRetrofit;
import com.es.netschool24.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {

    Toolbar toolbar;
    //TextView day, time, course_title_txt, fee_taka_txt, first_pay,second_pay, third_pay,total_taka_txt, total_taka;
    AppCompatButton check_btn;
    Intent intent;
    String d,t, img, title, fee;
    CircleImageView kids_img;

    List<Cart> cartList;
    RecyclerView cart_recycler;
    List<CartCourse> cartCourseList;

    CartAdapter cartAdapter;

    TextView total_taka;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        toolbar = findViewById(R.id.enroll_toolbar);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cartList = new ArrayList<>();
        cartCourseList = new ArrayList<>();

        cart_recycler = findViewById(R.id.cart_recycler);
        total_taka = findViewById(R.id.total_taka);

       // intent = getIntent();

        /*if (intent.hasExtra("day")){
           d =  intent.getStringExtra("day");
           t = intent.getStringExtra("time");
           //img = intent.getStringExtra("img");
           //title = intent.getStringExtra("title");
           //fee = intent.getStringExtra("fee");
        }
*/



        //Picasso.get().load(img).placeholder(R.drawable.kidlearning).into(kids_img);
        //course_title_txt.setText(title);
        //fee_taka_txt.setText(fee+" BDT");

       // day.setText(d);
       // time.setText(t);

        MyApi myApi = MyRetrofit.getRetrofit().create(MyApi.class);
        Call<CartCourseMain> callCart = myApi.getCart();

       callCart.enqueue(new Callback<CartCourseMain>() {
           @Override
           public void onResponse(Call<CartCourseMain> call, Response<CartCourseMain> response) {
               if (response.isSuccessful()){
                   assert response.body() != null;
                   //cartCourseList = response.body();
                   Log.i("cartId","success ") ;

                   cartAdapter = new CartAdapter(CartActivity.this, cartCourseList);
                   cart_recycler.setAdapter(cartAdapter);


                   int total_price = response.body().getPayTotal();
                   Log.i("cartId","success "+ total_price) ;
                   total_taka.setText(total_price);
               }
           }

           @Override
           public void onFailure(Call<CartCourseMain> call, Throwable t) {
               Log.i("cartId","fail "+ t.getLocalizedMessage().toString()) ;

           }
       });



    }
}