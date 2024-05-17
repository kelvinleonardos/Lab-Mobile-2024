package com.example.notes.recyclerview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.DetailActivity;
import com.example.notes.R;
import com.example.notes.models.Note;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private ArrayList<Note> notes;
    private ArrayList<Note> originalNotes;

    public NoteAdapter(ArrayList<Note> notes) {
        this.notes = notes;
        this.originalNotes = new ArrayList<>(notes);
    }


    @NonNull
    @Override
    public NoteAdapter.NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_card, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.NoteViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.time.setText(note.getUpdatedAt()==null ? "Created at : " + note.getCreatedAt() : "Updated at : " + note.getUpdatedAt());
        holder.title.setText(note.getTitle());
        holder.content.setText(note.getContent());
        holder.layout.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
            intent.putExtra("writeMode", "edit");
            intent.putExtra("id", note.getId());
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView time;
        TextView title;
        TextView content;
        LinearLayout layout;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.tv_time);
            title = itemView.findViewById(R.id.tv_title);
            content = itemView.findViewById(R.id.tv_note);
            layout = itemView.findViewById(R.id.clickable);
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void filter(String text) {
        notes.clear();
        if(text.isEmpty()){
            notes.addAll(originalNotes);
        } else{
            text = text.toLowerCase();
            for(Note note: originalNotes){
                if(note.getTitle().toLowerCase().contains(text) || note.getContent().toLowerCase().contains(text)){
                    notes.add(note);
                }
            }
        }
        notifyDataSetChanged();
    }
}
