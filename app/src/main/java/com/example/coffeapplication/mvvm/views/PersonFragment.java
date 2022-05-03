package com.example.coffeapplication.mvvm.views;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.coffeapplication.MainActivity;
import com.example.coffeapplication.R;
import com.example.coffeapplication.mvvm.viewModels.AuthViewModel;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class PersonFragment extends Fragment {
    Button loyaltyProgram, exit, editProfile, historyOrders;
    Dialog loyaltyDialog, ordersDialog;
    ImageView qrcode;
    AuthViewModel viewModel;

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
        String data = "Name Firstname Sale";
        qrcode = view.findViewById(R.id.QRcode);
        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            BitMatrix matrix = writer.encode(data, BarcodeFormat.QR_CODE, 1000, 1000);
            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bitmap = encoder.createBitmap(matrix);
            qrcode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }

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
                ordersDialog.setContentView(R.layout.orders_history);
                ordersDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                ordersDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

                (ordersDialog.findViewById(R.id.closeHistoryBtn)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ordersDialog.dismiss();
                    }
                });

                ordersDialog.show();
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
    }

}
