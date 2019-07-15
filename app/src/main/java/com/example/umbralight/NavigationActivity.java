package com.example.umbralight;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class NavigationActivity extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    public void onMapReady(GoogleMap googleMap) {
        double latitude = Double.parseDouble(getIntent().getExtras().getString("latitude"));
        double longitude = Double.parseDouble(getIntent().getExtras().getString("longitude"));
        String nama = getIntent().getExtras().getString("nama");

        LatLng myloc = new LatLng(latitude, longitude);
        googleMap.addMarker(new MarkerOptions().position(myloc)
                .title(nama));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myloc, 20));
    }
}
