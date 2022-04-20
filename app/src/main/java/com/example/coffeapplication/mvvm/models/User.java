package com.example.coffeapplication.mvvm.models;

public class User {
    String name;
    String lastname;
    String tNumber;
    String password;
    String gender;
    String birthday;

    public User(String name, String lastname, String tNumber, String password, String gender, String birthday) {
        this.name = name;
        this.lastname = lastname;
        this.tNumber = tNumber;
        this.password = password;
        this.birthday = birthday;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getNumber(String number) {
        return tNumber;
    }

    public void setNumber(String tNumber) {
        this.tNumber = tNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
