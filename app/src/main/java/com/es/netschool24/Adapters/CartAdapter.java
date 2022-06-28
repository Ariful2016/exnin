package com.es.netschool24.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.es.netschool24.AppConstants.AppConstants;
import com.es.netschool24.Models.CartCourse;
import com.es.netschool24.R;
import com.es.netschool24.ViewHolders.CartViewHolder;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {
    Context context;
    List<CartCourse> cartCourseList;

    public CartAdapter(Context context, List<CartCourse> cartCourseList) {
        this.context = context;
        this.cartCourseList = cartCourseList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, @SuppressLint("RecyclerView") int position) {


        CartCourse cartCourse = cartCourseList.get(position);

        Glide.with(context).load(AppConstants.course_image_path + cartCourse.getBannerImage()).into(holder.cart_course_img);
        holder.cart_course_title.setText(cartCourse.getName());
        holder.cart_price_txt.setText(cartCourse.getCourseFee() + " tk");
        holder.cart_pay_txt.setText("1st inst." + cartCourse.getInstallments().get(0).getBdt());

        /*if(cartId.getCourse().getInstallments().size()>0){
            switch (cartId.getCourse().getInstallments().size()){

                case 1:
                    holder.cart_installment_txt1.setText("1st inst." + cartId.getCourse().getInstallments().get(0).getBdt());

                    break;
                case 2:
                    holder.cart_installment_txt1.setText("1st inst." + cartId.getCourse().getInstallments().get(0).getBdt());
                    holder.cart_installment_txt2.setText("2nd inst." + cartId.getCourse().getInstallments().get(1).getBdt());
                    break;
                case 3:
                    holder.cart_installment_txt1.setText("1st inst." + cartId.getCourse().getInstallments().get(0).getBdt());
                    holder.cart_installment_txt2.setText("2nd inst." + cartId.getCourse().getInstallments().get(1).getBdt());
                    holder.cart_installment_txt3.setText("3rd inst." + cartId.getCourse().getInstallments().get(2).getBdt());
                    break;

            }
        }else {
            holder.cart_pay_txt.setText("");
            holder.cart_installment_txt1.setText("");
            holder.cart_installment_txt2.setText("");
            holder.cart_installment_txt3.setText("");
        }*/

        holder.cart_course_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             cartCourseList.remove(position);
            }
        });





    }

    @Override
    public int getItemCount() {
        return cartCourseList.size();
    }
}
