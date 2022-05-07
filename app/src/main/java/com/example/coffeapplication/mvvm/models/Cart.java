package com.example.coffeapplication.mvvm.models;

public class Cart {
    String name, syrop, cost;
    int count;

    public Cart(String name, String syrop, String cost, int count) {
        this.name = name;
        this.syrop = syrop;
        this.cost = cost;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSyrop() {
        return syrop;
    }

    public void setSyrop(String syrop) {
        this.syrop = syrop;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
