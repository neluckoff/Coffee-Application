package com.example.coffeapplication.mvvm.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.coffeapplication.R;
import com.example.coffeapplication.mvvm.viewModels.SignUpViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class SignUpFragment extends Fragment {

    SignUpViewModel signUpViewModel = new SignUpViewModel();

    TextInputEditText login;
    TextInputEditText password;
    TextInputEditText name;
    TextInputEditText lastname;
    TextInputEditText birthday;
    RadioButton gender;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.registration, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button registration = view.findViewById(R.id.sign_button);
        login = view.findViewById(R.id.login);
        password = view.findViewById(R.id.pass);
        name = view.findViewById(R.id.name);
        lastname = view.findViewById(R.id.firstname);
        birthday = view.findViewById(R.id.date);
        gender = view.findViewById(R.id.gender);

        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpViewModel.setPhoneNumber(login.getText().toString());
                signUpViewModel.setPassword(password.getText().toString());
                signUpViewModel.setUserName(password.getText().toString());
                signUpViewModel.setUserLastname(lastname.getText().toString());
                signUpViewModel.setBirthday(birthday.getText().toString());
                signUpViewModel.setGender(gender.getText().toString());

                signUpViewModel.getAllInfo();
            }
        });
    }

}
