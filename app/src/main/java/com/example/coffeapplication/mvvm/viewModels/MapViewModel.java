package com.example.coffeapplication.mvvm.viewModels;

import androidx.lifecycle.ViewModel;

import com.example.coffeapplication.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapViewModel extends ViewModel {

    public void getMap(SupportMapFragment mapFragment) {

        MarkerOptions markerOptions = new MarkerOptions();
        MarkerOptions markerOptions1 = new MarkerOptions();
        LatLng CoffeeOne = new LatLng(55.74100568878398, 37.57804483107259);
        LatLng CoffeeTwo = new LatLng(55.77046797310417, 37.62782662634571);
        LatLng Moscow = new LatLng(55.75115095448885, 37.6243933990855);
        markerOptions.position(CoffeeOne).title("Наша точка").snippet("Часы работы: 6:00 - 22:00");
        markerOptions1.position(CoffeeTwo).title("Наша точка").snippet("Часы работы: 7:30 - 23:30");

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                googleMap.addMarker(markerOptions);
                googleMap.addMarker(markerOptions1);
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Moscow,12));
            }
        });
    }
}
