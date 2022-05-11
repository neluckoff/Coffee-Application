package com.example.coffeapplication.mvvm.repositories;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeapplication.R;
import com.example.coffeapplication.mvvm.models.MenuItem;
import com.example.coffeapplication.mvvm.views.MenuFavoriteFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MenuFavoriteRepository {
    ArrayList<MenuItem> holder;

    public MenuFavoriteRepository() {
        holder = new ArrayList<>();
    }

    public ArrayList<MenuItem> getFavoriteFromDB(View view) {
        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = mFirebaseDatabase.getReference("Favorite");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    MenuItem menuItem = ds.getValue(MenuItem.class);
                    holder.add(menuItem);
                }
                RecyclerView rcv = view.findViewById(R.id.favoriteRecycler);
                rcv.getAdapter().notifyItemChanged(holder.size() - 1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return holder;
    }

    public ArrayList<MenuItem> getHolder() {
        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = mFirebaseDatabase.getReference("Favorite");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    MenuItem menuItem = ds.getValue(MenuItem.class);
                    holder.add(menuItem);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return holder;
    }
}
