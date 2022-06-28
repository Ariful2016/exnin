package com.es.netschool24.ViewHolders;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.es.netschool24.R;

public class SocialViewHolder extends RecyclerView.ViewHolder {
   public ImageView social_logo;
   public TextView social_txt;
    public SocialViewHolder(@NonNull View itemView) {
        super(itemView);

        social_txt = itemView.findViewById(R.id.social_txt);
        social_logo = itemView.findViewById(R.id.social_logo);
    }
}
