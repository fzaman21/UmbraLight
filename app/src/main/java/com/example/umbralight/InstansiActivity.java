package com.example.umbralight;
//10116050
//Fikih Zaman
//AKB-2
//07-07-2019
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class InstansiActivity extends Fragment {
    public InstansiActivity(){}
    RelativeLayout view;
    private List<Instansi> instansiList;
    private RecyclerView mList;
    private String instansi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = (RelativeLayout) inflater.inflate(R.layout.activity_instansi, container, false);
        mList = view.findViewById(R.id.main_list);
        mList.setHasFixedSize(true);
        mList.setLayoutManager(new LinearLayoutManager(getActivity()));
        instansiList = new ArrayList<>();
        AndroidNetworking.initialize(getActivity());
        final Bundle bundle = this.getArguments();
        instansi = bundle.getString("instansi");
        if (instansi.equals("Pemadam")) {
            getActivity().setTitle("Pemadam Kebakaran");
        } else if (instansi.equals("Rumkit")) {
            getActivity().setTitle("Rumah Sakit");
        } else {
            getActivity().setTitle("Polisi");
        }

        getInstansi();

        return view;
    }

    public void getInstansi() {

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

                                if (data.getString("jenis_instansi").equals(instansi)) {
                                    instansiList.add(new Instansi(
                                            data.getString("nama_kabupaten"),
                                            data.getString("nama_instansi"),
                                            data.getString("alamat_instansi"),
                                            data.getString("lat_"),
                                            data.getString("long_")
                                    ));
                                }
                            }
                            InstansiAdapter adapter = new InstansiAdapter(instansiList);
                            mList.setAdapter(adapter);
                            progressDialog.dismiss();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        progressDialog.dismiss();
                    }
                });
    }

}
