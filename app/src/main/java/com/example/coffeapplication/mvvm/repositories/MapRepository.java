package com.example.coffeapplication.mvvm.repositories;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.example.coffeapplication.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapRepository extends FragmentActivity implements OnMapReadyCallback  {

    public MapRepository() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        LatLng CoffeeOne = new LatLng(55.75772607568077, 37.62742044545359);
        googleMap.addMarker(new MarkerOptions().position(CoffeeOne).title("Наша точка"));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(CoffeeOne, 10f));
    }

}
