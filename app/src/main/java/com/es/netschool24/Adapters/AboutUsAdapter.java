package com.es.netschool24.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.es.netschool24.AppConstants.AppConstants;
import com.es.netschool24.Models.AboutUs;
import com.es.netschool24.R;
import com.es.netschool24.ViewHolders.AboutUsViewHolder;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;

import java.util.List;

public class AboutUsAdapter extends RecyclerView.Adapter<AboutUsViewHolder> {

    Context context;
    List<AboutUs> aboutUsList;

    public AboutUsAdapter(Context context, List<AboutUs> aboutUsList) {
        this.context = context;
        this.aboutUsList = aboutUsList;
    }

    @NonNull
    @Override
    public AboutUsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_about_us, parent, false);
        return new AboutUsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AboutUsViewHolder holder, int position) {
        AboutUs aboutUs = aboutUsList.get(position);
        String about_str = Jsoup.parse(aboutUs.getSectionTitle()).text();
        //String text = Jsoup.parse(htmlStr).text();
        String txt_head_str = Jsoup.parse(aboutUs.getAboutUs()).text();
        holder.about_txt.setText(about_str);
        holder.txt_head_1.setText(txt_head_str);
        Picasso.get().load(AppConstants.about_us_image_path+aboutUs.getAboutBanner()).into(holder.about_us_img);
    }

    @Override
    public int getItemCount() {
        return aboutUsList.size();
    }
}
