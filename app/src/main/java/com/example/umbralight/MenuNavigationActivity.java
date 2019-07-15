package com.example.umbralight;
//10116050
//Fikih Zaman
//AKB-2
//07-07-2019

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class MenuNavigationActivity extends Fragment {
    public MenuNavigationActivity(){}
    private View v;
    FragmentManager fragmentManager;
    Fragment fragment = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Navigation");

        v = inflater.inflate(R.layout.activity_menu_navigation, container, false);
        btnInstansi();
        return v;
    }

    private void btnInstansi() {
        ImageButton police = v.findViewById(R.id.police);
        ImageButton fire = v.findViewById(R.id.fire);
        ImageButton hospital = v.findViewById(R.id.hospital);

        police.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callFragment("Polisi");
            }
        });
        fire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callFragment("Pemadam");
            }
        });
        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callFragment("Rumkit");
            }
        });
    }

    private void callFragment(String instansi) {
        Bundle bundle = new Bundle();
        bundle.putString("instansi", instansi);
        fragment = new InstansiActivity();
        fragment.setArguments(bundle);
        fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frame_container, fragment)
                .addToBackStack(null)
                .commit();
        DrawerLayout drawer = getActivity().findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
}
