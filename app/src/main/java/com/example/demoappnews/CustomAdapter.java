package com.example.demoappnews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demoappnews.Models.NewHeadlines;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    private Context context;
    private List<NewHeadlines> healdings;
    private SelectListener listener;

    public CustomAdapter(Context context, List<NewHeadlines> healdings, SelectListener listener) {
        this.context = context;
        this.healdings = healdings;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.headline_list_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        holder.tvTitle.setText(healdings.get(position).getTitle());
        holder.tvSource.setText(healdings.get(position).getSource().getName());
        if(healdings.get(position).getUrlToImage() != null){
            Picasso.get().load(healdings.get(position).getUrlToImage()).into(holder.ivHeadline);
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnNewsClicked(healdings.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return healdings.size();
    }
}
