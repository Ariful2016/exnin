package com.es.netschool24.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.es.netschool24.Models.PrivacyPolicy;
import com.es.netschool24.R;
import com.es.netschool24.ViewHolders.PrivacyPolicyViewHolder;

import org.jsoup.Jsoup;

import java.util.List;

public class PrivacyPolicyAdapter extends RecyclerView.Adapter<PrivacyPolicyViewHolder> {
    Context context;
    List<PrivacyPolicy> privacyPolicyList;

    public PrivacyPolicyAdapter(Context context, List<PrivacyPolicy> privacyPolicyList) {
        this.context = context;
        this.privacyPolicyList = privacyPolicyList;
    }

    @NonNull
    @Override
    public PrivacyPolicyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.privacy_policy_item,parent, false);
        return new PrivacyPolicyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PrivacyPolicyViewHolder holder, int position) {

        PrivacyPolicy privacyPolicy = privacyPolicyList.get(position);
        String title_str = Jsoup.parse(privacyPolicy.getTitle()).text();
        String body_str = Jsoup.parse(privacyPolicy.getPrivacyPolicy()).text();
        holder.privacy_title_txt.setText(title_str);
        holder.privacy_body_txt.setText(body_str);

    }

    @Override
    public int getItemCount() {
        return privacyPolicyList.size();
    }
}
