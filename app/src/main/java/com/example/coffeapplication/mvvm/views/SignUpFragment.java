package com.example.coffeapplication.mvvm.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.coffeapplication.R;
import com.example.coffeapplication.mvvm.viewModels.AuthViewModel;
import com.google.firebase.auth.FirebaseUser;

public class SignUpFragment extends Fragment {

    private EditText phoneEdit, passEdit;
    private Button signUpBtn;
    private AuthViewModel viewModel;
    private NavController navController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this , ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())).get(AuthViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_registration, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        phoneEdit = view.findViewById(R.id.loginUp);
        passEdit = view.findViewById(R.id.passUp);
        TextView signInText = view.findViewById(R.id.sign_inUp2);
        signUpBtn = view.findViewById(R.id.sign_buttonUp);

        navController = Navigation.findNavController(view);

        signInText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_signUpFragment_to_signInFragment);
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = phoneEdit.getText().toString();
                String pass = passEdit.getText().toString();

                if (!phone.isEmpty() && !pass.isEmpty()){
                    viewModel.register(phone , pass);
                    navController.navigate(R.id.action_signUpFragment_to_signInFragment);
                }
            }
        });
    }
}