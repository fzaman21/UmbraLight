package com.example.umbralight;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class InstansiAdapter extends RecyclerView.Adapter<InstansiAdapter.ViewHolder> {
//    private Context context;
    private List<Instansi> list;
    public static String latitude, longitude;

    //construktor InstansiAdapter
    public InstansiAdapter(List<Instansi> list) {
//        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate view yang akan digunakan yaitu layout instansi_item.xml
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.instansi_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Instansi instansi = list.get(position);

        holder.textJenis.setText(instansi.getJenis());
        holder.textNama.setText(instansi.getNama());
        holder.textAlamat.setText(instansi.getAlamat());
        latitude = instansi.getLatitude();
        longitude = instansi.getLongitude();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textJenis, textNama, textAlamat;

        public ViewHolder(View itemView) {
            super(itemView);
            textJenis = itemView.findViewById(R.id.jenis_instansi);
            textNama = itemView.findViewById(R.id.nama_instansi);
            textAlamat = itemView.findViewById(R.id.alamat_instansi);
        }
    }
}
