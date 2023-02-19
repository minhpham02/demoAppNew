package com.example.demoappnews;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CustomViewHolder extends RecyclerView.ViewHolder {

    TextView tvSource, tvTitle;
    ImageView ivHeadline;
    CardView cardView;

    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);

        tvSource = itemView.findViewById(R.id.tvSource);
        tvTitle = itemView.findViewById(R.id.tvTitle);
        ivHeadline = itemView.findViewById(R.id.ivHeadline);
        cardView = itemView.findViewById(R.id.card_view);

    }
}
