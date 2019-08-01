package com.example.umbralight;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.ArrayList;

public class HasilAdapter extends RecyclerView.Adapter<HasilAdapter.ViewHolder> {
    private ArrayList<String> word, phonetic, origin;

    public HasilAdapter(ArrayList<String> word, ArrayList<String> phonetic, ArrayList<String> origin) {
        this.word = word;
        this.phonetic = phonetic;
        this.origin = origin;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.hasil_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return word.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView word, phonetic, origin;

        public ViewHolder(View itemView) {
            super(itemView);
            word = itemView.findViewById(R.id.word);
            phonetic = itemView.findViewById(R.id.phonetic);
            origin = itemView.findViewById(R.id.origin);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final String word_item = word.get(position);
        final String phonetic_item = phonetic.get(position);
        final String origin_item = origin.get(position);

        holder.word.setText("Word: " + word_item);
        holder.phonetic.setText("Phonetic: " + phonetic_item);
        holder.origin.setText("Origin: " + origin_item);
    }
}
