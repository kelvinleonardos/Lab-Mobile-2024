package com.example.tugaspraktikum3;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder>{
    private final ArrayList<Post> posts;

    public PostAdapter(ArrayList<Post> posts) {
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);

        holder.namaAcount.setText(post.getNamaAkun());
        holder.deskrip.setText(post.getDeskrip());
        holder.profile.setImageResource(post.getProfil());
        holder.postfoto.setImageResource(post.getFotopost());

        holder.profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), MainActivity2.class);
                intent.putExtra(MainActivity2.ACT2, post);
                holder.itemView.getContext().startActivity(intent);
            }
        });

        holder.namaAcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION){
                    Intent intent = new Intent(holder.itemView.getContext(),MainActivity3.class);
                    intent.putExtra(MainActivity3.ACT3,post);
                    holder.itemView.getContext().startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView namaAcount, deskrip;
        ImageView profile,postfoto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            namaAcount = itemView.findViewById(R.id.tv_namaAccount);
            deskrip = itemView.findViewById(R.id.tv_deskrip);
            profile = itemView.findViewById(R.id.iv_profilAccount);
            postfoto = itemView.findViewById(R.id.iv_foto);
        }
    }
}
