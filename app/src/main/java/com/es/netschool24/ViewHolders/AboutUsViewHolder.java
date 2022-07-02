package com.es.netschool24.ViewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.es.netschool24.R;

public class AboutUsViewHolder extends RecyclerView.ViewHolder {
    public TextView txt_head_1, about_txt;
    public ImageView about_us_img;
    public AboutUsViewHolder(@NonNull View itemView) {
        super(itemView);

        txt_head_1 = itemView.findViewById(R.id.txt_head_1);
        about_txt = itemView.findViewById(R.id.about_txt);
        about_us_img = itemView.findViewById(R.id.about_us_img);
    }
}
