package com.example.umbralight;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class InstansiAdapter extends RecyclerView.Adapter<InstansiAdapter.ViewHolder> {
    private List<Instansi> list;

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
        final Instansi instansi = list.get(position);
        holder.textKabupaten.setText(instansi.getKabupaten());
        holder.textNama.setText(instansi.getNama());
        holder.textAlamat.setText(instansi.getAlamat());

        holder.cardInstansi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "berhasil di klik mang " + instansi.getNama());
                Context context = v.getContext();
                Intent i =new Intent(context, NavigationActivity.class);
                i.putExtra("latitude", instansi.getLatitude());
                i.putExtra("longitude", instansi.getLongitude());
                i.putExtra("nama", instansi.getNama());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textKabupaten, textNama, textAlamat;
        private CardView cardInstansi;

        public ViewHolder(View itemView) {
            super(itemView);
            textKabupaten = itemView.findViewById(R.id.nama_kabupaten);
            textNama = itemView.findViewById(R.id.nama_instansi);
            textAlamat = itemView.findViewById(R.id.alamat_instansi);
            cardInstansi = itemView.findViewById(R.id.card_instansi);
        }

    }
}
