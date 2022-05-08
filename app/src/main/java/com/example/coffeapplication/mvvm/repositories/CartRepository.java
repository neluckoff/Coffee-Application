package com.example.coffeapplication.mvvm.repositories;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.coffeapplication.R;
import com.example.coffeapplication.mvvm.models.Cart;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CartRepository {
    ArrayList<Cart> holder;
    int result;

    public CartRepository() {
        holder = new ArrayList<>();
    }

    public ArrayList<Cart> getHolder() {
        //System.out.println(holder);
        return holder;
    }

    public void setSum(int sum) {
        this.result = sum;
    }

    public int getSum() {
        return result;
    }

    public ArrayList<Cart> getFromDB(View view) {
        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = mFirebaseDatabase.getReference("Cart");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int sum = 0;
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Cart cart = ds.getValue(Cart.class);
                    holder.add(cart);
                    System.out.println(cart.getName());

                    String a = (cart.getCost()
                            .substring(0, cart.getCost().length()-1));
                    sum += (Integer.parseInt(a) * Integer.parseInt(cart.getCount()));
                }
                TextView sumText = view.findViewById(R.id.resultSum);
                sumText.setText(sum + "p");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return holder;
    }
}
