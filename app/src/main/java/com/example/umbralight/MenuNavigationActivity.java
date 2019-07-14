package com.example.umbralight;
//10116050
//Fikih Zaman
//AKB-2
//07-07-2019
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class MenuNavigationActivity extends Fragment {
    public MenuNavigationActivity(){}
    RelativeLayout view;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView mList;

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<Instansi> instansiList;
    private RecyclerView.Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = (RelativeLayout) inflater.inflate(R.layout.activity_menu_navigation, container, false);

        getActivity().setTitle("Navigation");
        AndroidNetworking.initialize(getActivity());
        getInstansi();

//        mRecyclerView = (RecyclerView) drawer.findViewById(android.R.id.list);
        mList = view.findViewById(R.id.main_list);
//        mList = view.findViewById(android.R.id.list);

        instansiList = new ArrayList<>();
        adapter = new InstansiAdapter(getActivity().getApplicationContext(),instansiList);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(mList.getContext(), linearLayoutManager.getOrientation());

        mList.setHasFixedSize(true);
        mList.setLayoutManager(linearLayoutManager);
        mList.addItemDecoration(dividerItemDecoration);
        mList.setAdapter(adapter);

        return view;
    }
    private void getInstansi() {
        Log.d(TAG, "masuk method");
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        AndroidNetworking.get("http://dev.farizdotid.com/api/instansi/semuainstansi")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray dataInstansi = response.getJSONArray("instansi");
                            for (int i = 0; i < dataInstansi.length(); i++) {
                                JSONObject data = dataInstansi.getJSONObject(i);
                                Instansi instansi = new Instansi();
                                instansi.setJenis(data.getString("jenis_instansi"));
                                instansi.setNama(data.getString("nama_instansi"));
                                instansi.setAlamat(data.getString("alamat_instansi"));
                                instansi.setLatitude(data.getString("lat_"));
                                instansi.setLongitude(data.getString("long_"));
//                                instansiList.add(new Instansi(
//                                        data.getString("jenis_instansi"),
//                                        data.getString("nama_instansi"),
//                                        data.getString("alamat_instansi"),
//                                        data.getString("lat_"),
//                                        data.getString("long_")
//////                                ));
                                instansiList.add(instansi);
                            }
                            progressDialog.dismiss();

//                            Log.d(TAG, "berhasil mang" + instansi);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        Log.d(TAG, "error mang " + error);
                    }
                });
    }
}
