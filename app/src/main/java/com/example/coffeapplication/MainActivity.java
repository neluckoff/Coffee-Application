package com.example.coffeapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.coffeapplication.adapter.NewsAdapter;
import com.example.coffeapplication.fragments.MapFragment;
import com.example.coffeapplication.fragments.MenuFragment;
import com.example.coffeapplication.fragments.NewsFragment;
import com.example.coffeapplication.fragments.PersonFragment;
import com.example.coffeapplication.model.News;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;


//extends FragmentActivity implements OnMapReadyCallback
public class MainActivity extends AppCompatActivity {
    SupportMapFragment mapFragment;
    RecyclerView newsRecycler;
    NewsAdapter newsAdapter;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_news);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        //bottomNavigationView.setOnNavigationItemSelectedListener(navListner);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_news, new NewsFragment()).commit();

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.nav_menu:
                        selectedFragment = new MenuFragment();
                        break;
                    case R.id.nav_news:
                        selectedFragment = new NewsFragment();
                        break;
                    case R.id.nav_map:
                        selectedFragment = new MapFragment();
                        break;
                    case R.id.nav_person:
                        selectedFragment = new PersonFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_news, selectedFragment).commit();
                return true;
            });


        /*List<News> newsList = new ArrayList<>();
        newsList.add(new News(1, "ic_news_photo", "Новый латте", "21.03.2022"));
        newsList.add(new News(2, "ic_news_photo", "Новый капучино", "01.04.2022"));
        newsList.add(new News(3, "ic_news_photo", "Новый американо", "01.04.2022"));
        newsList.add(new News(4, "ic_news_photo", "Новый фрапучино", "02.04.2022"));

        setNewsRecycler(newsList);*/

        /*mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);*/
    }

    /*private BottomNavigationView.OnNavigationItemSelectedListener navListner = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.nav_menu:
                    selectedFragment = new MenuFragment();
                    return true;
                case R.id.nav_news:
                    selectedFragment = new NewsFragment();
                    return true;
                case R.id.nav_map:
                    selectedFragment = new MapFragment();
                    return true;
                case R.id.nav_person:
                    selectedFragment = new PersonFragment();
                    return true;
            }
            assert false;
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container, selectedFragment).commit();
            return false;
        }
    };*/

    private void setNewsRecycler(List<News> newsList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        newsRecycler = findViewById(R.id.newsRecycler);
        newsRecycler.setLayoutManager(layoutManager);

        newsAdapter = new NewsAdapter(this, newsList);
        newsRecycler.setAdapter(newsAdapter);
    }

    /*@Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        LatLng CoofeOne = new LatLng(55.75772607568077, 37.62742044545359);
        googleMap.addMarker(new MarkerOptions().position(CoofeOne).title("Наша точка"));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(CoofeOne, 10f));
    }*/
}