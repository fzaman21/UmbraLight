package com.example.umbralight;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import static android.support.constraint.Constraints.TAG;

public class HasilAdapter extends RecyclerView.Adapter<HasilAdapter.ViewHolder> {
    private ArrayList<String> word, phonetic, origin, key, definition, example;
    Context context;

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
        RecyclerView recyclerView;

        public ViewHolder(View itemView) {
            super(itemView);
            word = itemView.findViewById(R.id.word);
            phonetic = itemView.findViewById(R.id.phonetic);
            origin = itemView.findViewById(R.id.origin);
            recyclerView = itemView.findViewById(R.id.recycler_meaning);
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

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        holder.recyclerView.setLayoutManager(mLayoutManager);
        holder.recyclerView.setHasFixedSize(true);

        JSONArray response = MenuDictionaryActivity.responseJSON;
        Log.d(TAG, "berhasil mang respon dari adapter " + response );

        try {
            key = new ArrayList<String>();
            definition = new ArrayList<String>();
            example = new ArrayList<String>();
            JSONObject meaning = response.getJSONObject(position).getJSONObject("meaning");
            Iterator<String> keys = meaning.keys();
            while (keys.hasNext()) {
                String key_item = keys.next();
                key.add(key_item);
                JSONArray meaning_item = meaning.getJSONArray(key_item);
                JSONObject meaning_obj = meaning_item.getJSONObject(0);
                definition.add(meaning_obj.optString("definition"));
                example.add(meaning_obj.optString("example"));
                Log.d(TAG, "berhasil mang definisi obj " + meaning_obj.optString("definition"));

                Log.d(TAG, "berhasil mang looping keyitem " + meaning.optString(key_item));
            }
            Log.d(TAG, "berhasil mang isi meaning " + meaning );
            Log.d(TAG, "berhasil mang key " + key );

            MeaningAdapter adapter = new MeaningAdapter(key, definition, example);
            holder.recyclerView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
