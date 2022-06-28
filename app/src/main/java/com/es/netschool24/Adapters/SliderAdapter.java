package com.es.netschool24.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.es.netschool24.AppConstants.AppConstants;
import com.es.netschool24.Models.Banners;
import com.es.netschool24.Models.SliderItems;
import com.es.netschool24.R;
import com.es.netschool24.ViewHolders.SliderViewHolder;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderViewHolder> {

    private Context context;
    private List<Banners> bannersList;


    public SliderAdapter(Context context, List<Banners> bannersList) {
        this.context = context;
        this.bannersList = bannersList;
    }

    @Override
    public SliderViewHolder onCreateViewHolder(ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slider, parent, false);
        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SliderViewHolder viewHolder, int position) {
        Banners banners = bannersList.get(position);

        //viewHolder.textViewDescription.setText(sliderItem.getDescription());
        //viewHolder.textViewDescription.setTextSize(16);
        //viewHolder.textViewDescription.setTextColor(Color.WHITE);
        Glide.with(viewHolder.itemView)
                .load(AppConstants.banner_image_path+banners.getBannerImage())
                .fitCenter()
                .into(viewHolder.imageViewBackground);
    }

    @Override
    public int getCount() {
        return bannersList.size();
    }
}
