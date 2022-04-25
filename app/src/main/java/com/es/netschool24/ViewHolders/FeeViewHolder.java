package com.es.netschool24.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.es.netschool24.R;

public class FeeViewHolder extends RecyclerView.ViewHolder {

    public TextView monthly,weekly,time,fee;

    public FeeViewHolder(@NonNull View itemView) {
        super(itemView);

        monthly = itemView.findViewById(R.id.monthly);
        weekly = itemView.findViewById(R.id.weekly);
        time = itemView.findViewById(R.id.time);
        fee = itemView.findViewById(R.id.fee);


    }
}
