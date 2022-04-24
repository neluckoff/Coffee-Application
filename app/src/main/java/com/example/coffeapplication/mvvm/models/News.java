package com.example.coffeapplication.mvvm.models;

public class News {
    int image;
    String text;
    String date;

    public News(int image, String text, String date) {
        this.image = image;
        this.text = text;
        this.date = date;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
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
