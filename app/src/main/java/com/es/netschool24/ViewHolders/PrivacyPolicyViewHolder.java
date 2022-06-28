package com.es.netschool24.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.es.netschool24.R;

public class PrivacyPolicyViewHolder extends RecyclerView.ViewHolder {
    public TextView privacy_title_txt,privacy_body_txt;
    public PrivacyPolicyViewHolder(@NonNull View itemView) {
        super(itemView);

        privacy_title_txt = itemView.findViewById(R.id.privacy_title_txt);
        privacy_body_txt = itemView.findViewById(R.id.privacy_body_txt);
    }
}
