package com.example.coffeapplication.mvvm.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.coffeapplication.R;
import com.example.coffeapplication.mvvm.repositories.AuthRepository;
import com.example.coffeapplication.mvvm.viewModels.AuthViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInFragment extends Fragment {

    private EditText phoneEdit, passEdit;
    private TextView signUpText, forgetPass;
    private Button signInBtn;
    private AuthViewModel viewModel;
    private NavController navController;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this , ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())).get(AuthViewModel.class);
        auth = FirebaseAuth.getInstance();

        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            startActivity(new Intent(getActivity(), AppActivity.class));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        phoneEdit = view.findViewById(R.id.loginIn);
        passEdit = view.findViewById(R.id.passIn);
        signUpText = view.findViewById(R.id.sign_upIn);
        signInBtn = view.findViewById(R.id.sign_buttonIn);
        forgetPass = view.findViewById(R.id.forget);

        navController = Navigation.findNavController(view);

        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_signInFragment_to_signUpFragment);
            }
        });

        forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_signInFragment_to_forgetPasswordFragment);
            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = phoneEdit.getText().toString();
                String pass = passEdit.getText().toString();

                if (!mail.isEmpty() && !pass.isEmpty()) {
                    login(mail, pass);
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Пропущено поле", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void login(String email , String pass){
        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    //firebaseUserMutableLiveData.postValue(auth.getCurrentUser());
                    startActivity(new Intent(getActivity(), AppActivity.class));
                    Toast.makeText(getActivity().getApplication(), "Успешный вход", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity().getApplication(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}