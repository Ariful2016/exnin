package com.es.netschool24.ViewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.es.netschool24.R;

public class CartViewHolder extends RecyclerView.ViewHolder {
    public ImageView cart_course_img,cart_course_delete;
    public TextView cart_course_title,cart_price_txt,cart_installment_txt1,cart_installment_txt2,cart_installment_txt3,cart_pay_txt;
    public CartViewHolder(@NonNull View itemView) {
        super(itemView);

        cart_course_img = itemView.findViewById(R.id.cart_course_img);
        cart_course_delete = itemView.findViewById(R.id.cart_course_delete);
        cart_course_title = itemView.findViewById(R.id.cart_course_title);
        cart_price_txt = itemView.findViewById(R.id.cart_price_txt);
        cart_installment_txt1 = itemView.findViewById(R.id.cart_installment_txt1);
        cart_installment_txt2 = itemView.findViewById(R.id.cart_installment_txt2);
        cart_installment_txt3 = itemView.findViewById(R.id.cart_installment_txt3);
        cart_pay_txt = itemView.findViewById(R.id.cart_pay_txt);
    }
}
