package com.example.coffeapplication.mvvm.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffeapplication.R;
import com.example.coffeapplication.mvvm.models.User;
import com.example.coffeapplication.mvvm.viewModels.AuthViewModel;
import com.google.firebase.database.DatabaseReference;

public class SignUpFragment extends Fragment {

    private EditText mailEdit, passEdit, nameEdit, surnameEdit, dateEdit, genderEdit;
    RadioButton male, female;
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

        mailEdit = view.findViewById(R.id.loginUp);
        passEdit = view.findViewById(R.id.passUp);
        TextView signInText = view.findViewById(R.id.sign_inUp2);
        signUpBtn = view.findViewById(R.id.sign_buttonUp);
        nameEdit = view.findViewById(R.id.name);
        surnameEdit = view.findViewById(R.id.firstname);
        dateEdit = view.findViewById(R.id.date);
        male = view.findViewById(R.id.manBtn);
        female = view.findViewById(R.id.girlBtn);

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
                String email = mailEdit.getText().toString();
                String pass = passEdit.getText().toString();
                String name = nameEdit.getText().toString();
                String sec_name = surnameEdit.getText().toString();
                String birthday = dateEdit.getText().toString();
                String gender = null;

                if (male.isChecked()) {
                    gender = "male";
                } else if (female.isChecked()) {
                    gender = "female";
                }

                if (!email.isEmpty() && !pass.isEmpty()){
                    if (!name.isEmpty() && !sec_name.isEmpty() && !birthday.isEmpty() && !gender.isEmpty()) {
                        viewModel.register(email, pass, name, sec_name, birthday, gender);
                        navController.navigate(R.id.action_signUpFragment_to_signInFragment);
                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), "Заполните информацию", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Пропущено поле", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}