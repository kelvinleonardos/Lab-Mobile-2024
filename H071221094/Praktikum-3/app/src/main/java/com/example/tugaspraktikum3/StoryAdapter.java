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

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder> {

    private final ArrayList<Post> story;

    public StoryAdapter(ArrayList<Post> story) {
        this.story = story;
    }


    @NonNull
    @Override
    public StoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item, parent, false);
        return new StoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryAdapter.ViewHolder holder, int position) {

        Post stories = story.get(position);

        holder.fotoProfil.setImageResource(stories.getProfil());


        holder.fotoProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), MainActivity2.class);
                intent.putExtra(MainActivity2.ACT2, stories);
                holder.itemView.getContext().startActivity(intent);
            }
        });

//        holder.namaAkun.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent inten = new Intent(holder.itemView.getContext(), MainActivity3.class);
//                inten.putExtra(MainActivity3.ACT3,stories);
//                holder.itemView.getContext().startActivity(inten);
//            }
//        });



    }

    @Override
    public int getItemCount() {
        return story.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        ImageView fotoProfil;
        TextView namaAkun;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            fotoProfil = itemView.findViewById(R.id.iv_profilAccount);
        }
    }
}
