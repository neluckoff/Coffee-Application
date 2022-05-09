package com.example.coffeapplication.mvvm.models;

public class MenuItem {
    private String name, cost, syrop, id;
    private boolean favorite, season;
    private int image;

    public MenuItem(String name, String cost, String id, int image, boolean season) {
        this.name = name;
        this.cost = cost;
        this.syrop = "";
        this.id = id;
        this.favorite = false;
        this.image = image;
        this.season = season;
    }

    public MenuItem() {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public boolean isSeason() {
        return season;
    }

    public void setSeason(boolean season) {
        this.season = season;
    }
}
