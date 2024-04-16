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

public class AkunAdapter extends RecyclerView.Adapter<AkunAdapter.ViewHolder> {
    private final ArrayList<Post>akun;

    public AkunAdapter(ArrayList<Post> akun) {
        this.akun = akun;
    }

    @NonNull
    @Override
    public AkunAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item, parent, false);
        return new AkunAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AkunAdapter.ViewHolder holder, int position) {

        Post acount = akun.get(position);

        holder.profilAkun.setImageResource(acount.getProfil());
        holder.postingan.setImageResource(acount.getFotopost());
        holder.namaAccount.setText(acount.getNamaAkun());
        holder.follower.setText(acount.getFolower());
        holder.following.setText(acount.getFolowing());


        holder.namaAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), MainActivity3.class);
                intent.putExtra(MainActivity3.ACT3, acount);
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return akun.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView profilAkun,postingan;
        TextView namaAccount,follower,following;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            profilAkun = itemView.findViewById(R.id.profilAccount);
            postingan = itemView.findViewById(R.id.postinganAccount);
            namaAccount = itemView.findViewById(R.id.namaAccount);
            follower = itemView.findViewById(R.id.folower);
            following = itemView.findViewById(R.id.folowing);


        }
    }

}
