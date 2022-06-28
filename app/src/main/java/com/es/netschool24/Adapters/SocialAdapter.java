package com.es.netschool24.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.es.netschool24.Models.SocialLink;
import com.es.netschool24.R;
import com.es.netschool24.ViewHolders.SocialViewHolder;

import java.util.List;

public class SocialAdapter extends RecyclerView.Adapter<SocialViewHolder> {
    Context context;
    List<SocialLink> socialLinkList;

    public SocialAdapter(Context context, List<SocialLink> socialLinkList) {
        this.context = context;
        this.socialLinkList = socialLinkList;
    }

    @NonNull
    @Override
    public SocialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.social_item,parent,false);
        return new SocialViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SocialViewHolder holder, int position) {
        SocialLink socialLink = socialLinkList.get(position);
        holder.social_txt.setText(socialLink.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LordUrl(socialLink.getLink());
            }
        });
    }

    @Override
    public int getItemCount() {
        return socialLinkList.size();
    }

    private void LordUrl(String s) {

        Uri uri = Uri.parse(s);
        context.startActivity(new Intent(Intent.ACTION_VIEW,uri));

    }
}
