package com.example.umbralight;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MeaningAdapter extends RecyclerView.Adapter<MeaningAdapter.ViewHolder> {
    private ArrayList<String> key, definition, example;

    public MeaningAdapter(ArrayList<String> key, ArrayList<String> definition, ArrayList<String> example) {
        this.key = key;
        this.definition = definition;
        this.example = example;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.meaning_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return key.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView key, definition, example;

        public ViewHolder(View itemView) {
            super(itemView);
            key = itemView.findViewById(R.id.key);
            definition = itemView.findViewById(R.id.definition);
            example = itemView.findViewById(R.id.example);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final String definition_item = definition.get(position);
        final String example_item = example.get(position);
        final String key_item = key.get(position);

        holder.key.setText(key_item);
        holder.definition.setText(definition_item);
        holder.example.setText(example_item);
    }
}
