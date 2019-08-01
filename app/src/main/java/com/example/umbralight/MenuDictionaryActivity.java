package com.example.umbralight;
//10116050
//Fikih Zaman
//AKB-2
//07-07-2019
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import static android.support.constraint.Constraints.TAG;

public class MenuDictionaryActivity extends Fragment {
    public MenuDictionaryActivity(){}
    public static JSONArray responseJSON;

    LinearLayout view;
    EditText cari_kata;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = (LinearLayout) inflater.inflate(R.layout.activity_menu_encyclopedy, container, false);
        cari_kata = view.findViewById(R.id.cari_kata);
        Button cari = view.findViewById(R.id.cari);
        recyclerView = view.findViewById(R.id.recycler_hasil);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        AndroidNetworking.initialize(getActivity());
        getActivity().setTitle("Dictionary");

        cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getKata(cari_kata.getText().toString());
            }
        });

        return view;
    }

    private void getKata(String define) {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        AndroidNetworking.get("https://googledictionaryapi.eu-gb.mybluemix.net")
                .addQueryParameter("define", define)
                .addQueryParameter("lang", "en")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        responseJSON = response;
                        Log.d(TAG, "berhasil mang semua data " + response);
                        ArrayList<String> word = new ArrayList<>();
                        ArrayList<String> phonetic = new ArrayList<>();
                        ArrayList<String> origin = new ArrayList<>();

                        try {
                            for(int i = 0; i < response.length(); i++) {
                                word.add(response.getJSONObject(i).getString("word"));
                                phonetic.add(response.getJSONObject(i).getString("phonetic"));
                                origin.add(response.getJSONObject(i).getString("origin"));
                            }
                            Log.d(TAG, "berhasil mang word " + word);
                            HasilAdapter adapter = new HasilAdapter(word, phonetic, origin);
                            recyclerView.setAdapter(adapter);
                            progressDialog.dismiss();

//                            try {
//                                JSONObject meaning = array0.getJSONObject("meaning");
//                                Log.d(TAG, "berhasil mang meaning " + meaning);
//
//                                Iterator<String> keys = meaning.keys();
//                                while (keys.hasNext()) {
//                                    String key = keys.next();
//                                    Log.d(TAG, "berhasil mang meaning " + key + " isinya " + meaning.optString(key));
//                                }
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                                Log.d(TAG, "error mang meaning " + e);
//                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d(TAG, "error mang ambil response ");
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        Log.d(TAG, "error mang hasil " + error);
                    }
                });
    }
}
