package com.example.coffeapplication.mvvm.models;

public class Orders {
    private String id;
    private String final_cost;
    private String names, cost;

    public Orders(String id, String final_cost, String names, String cost) {
        this.id = id;
        this.final_cost = final_cost;
        this.names = names;
        this.cost = cost;
    }

    public Orders() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFinal_cost() {
        return final_cost;
    }

    public void setFinal_cost(String final_cost) {
        this.final_cost = final_cost;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
