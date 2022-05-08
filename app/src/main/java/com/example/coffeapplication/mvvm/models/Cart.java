package com.example.coffeapplication.mvvm.models;

public class Cart {
    String name, syrop, cost, count;

    public Cart(String name, String syrop, String cost, String count) {
        this.name = name;
        this.syrop = syrop;
        this.cost = cost;
        this.count = count;
    }

    public Cart() {}

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

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
