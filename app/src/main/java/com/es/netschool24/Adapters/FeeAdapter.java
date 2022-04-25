package com.es.netschool24.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.es.netschool24.Models.Tutionfee;
import com.es.netschool24.R;
import com.es.netschool24.ViewHolders.FeeViewHolder;

import java.util.List;

public class FeeAdapter extends RecyclerView.Adapter<FeeViewHolder> {

    Context context;
    List<Tutionfee> tutionfeeList;

    public FeeAdapter(Context context, List<Tutionfee> tutionfeeList) {
        this.context = context;
        this.tutionfeeList = tutionfeeList;
    }

    @NonNull
    @Override
    public FeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_fee,parent,false);
        return new FeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeeViewHolder holder, int position) {

        Tutionfee tutionfee = tutionfeeList.get(position);
        holder.monthly.setText(tutionfee.getMonthly());
        holder.weekly.setText(tutionfee.getWeekly());
        holder.time.setText(tutionfee.getTime());
        holder.fee.setText(tutionfee.getFee());

    }

    @Override
    public int getItemCount() {
        return tutionfeeList.size();
    }
}
