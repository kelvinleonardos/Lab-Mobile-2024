package com.example.praktikum3;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

    private ArrayList<Main> main;

    public FeedAdapter(ArrayList<Main> main){this.main = main;}

    @NonNull
    @Override
    public FeedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed, parent, false);
        return new FeedAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedAdapter.ViewHolder holder, int position) {

        Main mains = main.get(position);

        holder.tv_namaProfile.setText(mains.getNama());
        holder.ivProfile.setImageResource(mains.getImageprofile());
        holder.ivFeed.setImageResource(mains.getImagefeed());
        holder.tv_caption.setText(mains.getCaption());

        holder.ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(holder.context, MainActivity2.class);

                intent.putExtra("main", mains);
                holder.context.startActivity(intent);
            }
        });

        holder.tv_namaProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(holder.context, MainActivity3.class);
                intent.putExtra("main", mains);

                holder.context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return main.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivFeed, ivProfile;
        TextView tv_caption, tv_namaProfile;
        Context context;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivFeed = itemView.findViewById(R.id.iv_feed);
            ivProfile = itemView.findViewById(R.id.iv_profile);
            tv_namaProfile = itemView.findViewById(R.id.tv_namaprofile);
            tv_caption = itemView.findViewById(R.id.tv_caption);
            context = itemView.getContext();

        }
    }
}