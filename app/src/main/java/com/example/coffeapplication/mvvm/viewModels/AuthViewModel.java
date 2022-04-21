package com.example.coffeapplication.mvvm.viewModels;

import android.app.Application;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.coffeapplication.mvvm.models.AuthModel;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class AuthViewModel extends AndroidViewModel {

    private AuthModel authModel;
    private MutableLiveData<FirebaseUser> userData;
    private MutableLiveData<Boolean> loggedStatus;
    GoogleSignInClient gsc;

    public MutableLiveData<FirebaseUser> getUserData() {
        return userData;
    }

    public MutableLiveData<Boolean> getLoggedStatus() {
        return loggedStatus;
    }

    public AuthViewModel(@NonNull Application application) {
        super(application);
        authModel = new AuthModel(application);
        userData = authModel.getFirebaseUserMutableLiveData();
        loggedStatus = authModel.getUserLoggedMutableLiveData();
    }

    public void register(String email , String pass){
        authModel.register(email, pass);
    }
    public void signIn(String email , String pass){
        authModel.login(email, pass);
    }
    public void signOut(){
        authModel.signOut();
    }


}
