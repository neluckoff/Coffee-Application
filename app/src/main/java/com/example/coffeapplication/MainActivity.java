package com.example.coffeapplication;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {
    SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_area);

        /*mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);*/
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        LatLng CoofeOne = new LatLng(55.75772607568077, 37.62742044545359);
        googleMap.addMarker(new MarkerOptions().position(CoofeOne).title("Наша точка"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(CoofeOne));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(CoofeOne, 10f));
    }
}