package com.example.coffeapplication.mvvm.repositories;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.coffeapplication.R;
import com.example.coffeapplication.mvvm.models.Cart;
import com.example.coffeapplication.mvvm.models.Orders;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class OrderRepository {
    ArrayList<Orders> holder;

    public OrderRepository() {
        holder = new ArrayList<>();
    }

    public ArrayList<Orders> getFromDB(View view) {
        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = mFirebaseDatabase.getReference("Orders");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int count = 0;
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Orders orders = ds.getValue(Orders.class);
                    holder.add(orders);
                    count += 1;
                }
                TextView sumText = view.findViewById(R.id.counterHistory);
                sumText.setText(String.valueOf(count));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Collections.reverse(holder);
        return holder;
    }
}
