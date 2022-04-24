package com.example.coffeapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.coffeapplication.mvvm.viewModels.NewsViewModel;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


//extends FragmentActivity implements OnMapReadyCallback
public class MainActivity extends AppCompatActivity {
    SupportMapFragment mapFragment;
    RecyclerView newsRecycler;
    NewsViewModel newsAdapter;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*bottomNavigationView = findViewById(R.id.bottomNavigationView);
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
            });*/


        /*List<News> newsList = new ArrayList<>();
        newsList.add(new News(1, "ic_news_photo", "Новый латте", "21.03.2022"));
        newsList.add(new News(2, "ic_news_photo", "Новый капучино", "01.04.2022"));
        newsList.add(new News(3, "ic_news_photo", "Новый американо", "01.04.2022"));
        newsList.add(new News(4, "ic_news_photo", "Новый фрапучино", "02.04.2022"));

        setNewsRecycler(newsList);*/

        /*mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);*/
    }
}