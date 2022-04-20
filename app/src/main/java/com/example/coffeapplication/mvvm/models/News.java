package com.example.coffeapplication.mvvm.models;

public class News {
    int id;
    String image;
    String text;
    String date;

    public News(int id, String image, String text, String date) {
        this.id = id;
        this.image = image;
        this.text = text;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
