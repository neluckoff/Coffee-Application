package com.example.coffeapplication.mvvm.views;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.coffeapplication.MainActivity;
import com.example.coffeapplication.R;
import com.example.coffeapplication.mvvm.viewModels.AuthViewModel;
import com.example.coffeapplication.mvvm.viewModels.PersonViewModel;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class PersonFragment extends Fragment {
    Button loyaltyProgram, exit, historyOrders;
    Dialog loyaltyDialog, ordersDialog, editDialog;
    ImageView qrcode;
    AuthViewModel viewModel;
    PersonViewModel personViewModel;
    TextView name, mail;
    ImageButton editProfile;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this , ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())).get(AuthViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person, container, false);
        personViewModel = new ViewModelProvider(this).get(PersonViewModel.class);

        name = view.findViewById(R.id.pName);
        mail = view.findViewById(R.id.tNumber);
        personViewModel.getNameAndMailFromBD(name, mail);

        String data = "Mama krasotul\'ka";
        qrcode = view.findViewById(R.id.QRcode);
        personViewModel.generatingQRCode(data, qrcode);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loyaltyDialog = new Dialog(getContext());

        loyaltyProgram = view.findViewById(R.id.loyalityButton);

        loyaltyProgram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loyaltyDialog.setContentView(R.layout.loyalty_program);
                loyaltyDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                loyaltyDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

                (loyaltyDialog.findViewById(R.id.materialCloseButton)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        loyaltyDialog.dismiss();
                    }
                });

                loyaltyDialog.show();
            }
        });

        ordersDialog = new Dialog(getContext());
        historyOrders = view.findViewById(R.id.listOrderButton);

        historyOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_news, new OrderFragment()).commit();
            }
        });

        exit = view.findViewById(R.id.exitButton);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.signOut();
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });

        editProfile = view.findViewById(R.id.editProfile);
        editDialog = new Dialog(getContext());
        EditText nameEdit = view.findViewById(R.id.editTextTextPersonName);
        EditText firstnameEdit = view.findViewById(R.id.editTextFirstName);
        EditText mailEdit = view.findViewById(R.id.editTextMail);

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editDialog.setContentView(R.layout.edit_profile);
                editDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                editDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

                editDialog.show();
            }
        });
    }

}
