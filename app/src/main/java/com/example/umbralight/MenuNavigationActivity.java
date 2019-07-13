package com.example.umbralight;
//10116050
//Fikih Zaman
//AKB-2
//07-07-2019
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;

import static android.support.constraint.Constraints.TAG;

public class MenuNavigationActivity extends Fragment {
    public MenuNavigationActivity(){}
    RelativeLayout view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = (RelativeLayout) inflater.inflate(R.layout.activity_menu_navigation, container, false);

        getActivity().setTitle("Navigation");
        AndroidNetworking.initialize(getActivity());
        getProvinsi();

        return view;
    }

    private void getProvinsi() {
        Log.d(TAG, "masuk method");
        AndroidNetworking.get("http://dev.farizdotid.com/api/instansi/semuainstansi")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, "on response" + response);
                    }
                    @Override
                    public void onError(ANError error) {
                        Log.d(TAG, "on response" + error);
                    }
                });
    }
}
