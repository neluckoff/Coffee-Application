package com.example.coffeapplication.mvvm.repositories;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeapplication.R;
import com.example.coffeapplication.mvvm.models.Cart;
import com.example.coffeapplication.mvvm.models.MenuItem;
import com.example.coffeapplication.mvvm.models.News;
import com.example.coffeapplication.mvvm.models.Orders;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class MenusRepository {
    ArrayList<MenuItem> holder = new ArrayList<>();
    ArrayList<MenuItem> standartHolder = new ArrayList<>();
    ArrayList<MenuItem> seasonHolder = new ArrayList<>();
    ArrayList<MenuItem> bakeHolder = new ArrayList<>();

    public MenusRepository() {
        holder = new ArrayList<>();
        standartHolder = new ArrayList<>();
        seasonHolder = new ArrayList<>();
        bakeHolder = new ArrayList<>();
        createMenu();

        MenuItem item;
        for (int i=0; i < holder.size(); i++) {
            item = holder.get(i);
            if (item.getId().equals("standart")) {
                standartHolder.add(item);
            } else if (item.getId().equals("bake")) {
                bakeHolder.add(item);
            }
            if (item.isSeason()) {
                seasonHolder.add(item);
            }
        }
    }

    private void createMenu() {
        MenuItem m1 = new MenuItem("Капучино", "100p", "standart", R.drawable.mug_coffee, false);
        holder.add(m1);

        MenuItem m2 = new MenuItem("Раф", "100p", "standart", R.drawable.mug_coffee, false);
        holder.add(m2);

        MenuItem m3 = new MenuItem("Круасан", "100p", "bake", R.drawable.croissant, false);
        holder.add(m3);

        MenuItem m4 = new MenuItem("Круасан \"Дикий\"", "150p", "season-bake", R.drawable.croissant, true);
        holder.add(m4);
    }

    public ArrayList<MenuItem> getStandartHolder() {
        return standartHolder;
    }

    public ArrayList<MenuItem> getSeasonHolder() {
        return seasonHolder;
    }

    public ArrayList<MenuItem> getBakeHolder() {
        return bakeHolder;
    }

    public ArrayList<MenuItem> getHolder() {
        return holder;
    }
}
