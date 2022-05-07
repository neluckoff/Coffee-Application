package com.example.coffeapplication.mvvm.repositories;

import com.example.coffeapplication.mvvm.models.Cart;

import java.util.ArrayList;

public class CartRepository {
    ArrayList<Cart> holder;

    public CartRepository() {
        holder = new ArrayList<>();
    }

    public void addToHolder(String name, String syrop, String cost, int count) {
        Cart cart = new Cart(name, syrop, cost, count);
        holder.add(cart);
    }

    public ArrayList<Cart> getHolder() {
        return holder;
    }
}
