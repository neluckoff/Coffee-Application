package com.example.coffeapplication.mvvm.models;

public class MenuItem {
    private String name, cost, syrop, clas;
    private boolean favorite;
    private int image;

    public MenuItem(String name, String cost, String clas, boolean favorite, int image) {
        this.name = name;
        this.cost = cost;
        this.syrop = "";
        this.clas = clas;
        this.favorite = favorite;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getSyrop() {
        return syrop;
    }

    public void setSyrop(String syrop) {
        this.syrop = syrop;
    }

    public String getClas() {
        return clas;
    }

    public void setClas(String clas) {
        this.clas = clas;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
