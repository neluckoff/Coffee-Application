package com.example.coffeapplication.mvvm.viewModels;

import androidx.lifecycle.ViewModel;

import com.example.coffeapplication.mvvm.models.User;

public class SignUpViewModel extends ViewModel {

    User user;

    public void setUserName(String name) {
        user.setName(name);
    }

    public void setPhoneNumber(String number) {
        user.setNumber(number);
    }

    public void setPassword(String pass) {
        user.setPassword(pass);
    }

    public void setUserLastname(String name) {
        user.setLastname(name);
    }

    public void setBirthday(String bd) {
        user.setBirthday(bd);
    }

    public void  setGender(String gender) {
        user.setGender(gender);
    }

    public void getAllInfo() {
        System.out.println(user.getName());
    }
}
