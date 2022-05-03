package com.example.coffeapplication.mvvm.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import com.example.coffeapplication.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MapFragment extends Fragment {
    BottomNavigationView bottomNavigationView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_map, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

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

        return view;
    }

}
