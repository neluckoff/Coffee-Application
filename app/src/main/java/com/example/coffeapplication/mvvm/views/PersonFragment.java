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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.coffeapplication.MainActivity;
import com.example.coffeapplication.R;
import com.example.coffeapplication.mvvm.models.User;
import com.example.coffeapplication.mvvm.repositories.PersonRepository;
import com.example.coffeapplication.mvvm.viewModels.AuthViewModel;
import com.example.coffeapplication.mvvm.viewModels.PersonViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PersonFragment extends Fragment {
    Button loyaltyProgram, exit, historyOrders;
    Dialog loyaltyDialog, ordersDialog, editDialog, editInfoDialog;
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

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editDialog.setContentView(R.layout.edit_profile);
                editDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                editDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

                personViewModel.setEditInfo((editDialog.findViewById(R.id.editName)),
                        (editDialog.findViewById(R.id.editMail)), (editDialog.findViewById(R.id.editLastname)));

                (editDialog.findViewById(R.id.closeEdit)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EditText nameText = (editDialog.findViewById(R.id.editName));
                        EditText firstnameText = (editDialog.findViewById(R.id.editLastname));
                        EditText mailText = (editDialog.findViewById(R.id.editMail));
                        String name = nameText.getText().toString();
                        String firstname = firstnameText.getText().toString();
                        String mail = mailText.getText().toString();

                        //DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("Users");
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                        PersonRepository personRepository = new PersonRepository();

                        if (!name.isEmpty() && !firstname.isEmpty() && !mail.isEmpty()) {
                            databaseReference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                        User user = ds.getValue(User.class);
                                        if (user.mail.equals(Objects.requireNonNull(personRepository.getmAuth().getCurrentUser()).getEmail())) {
                                            String id = ds.getKey();
                                            databaseReference.child(id).child("name").setValue(name);
                                            databaseReference.child(id).child("secName").setValue(firstname);
                                            databaseReference.child(id).child("birthdat").setValue(mail);
                                            return;
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }
                        //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_news, new PersonFragment()).commit();
                        editDialog.dismiss();
                        editProfile.setVisibility(View.GONE);
                    }
                });

                editDialog.show();
            }
        });

        name = view.findViewById(R.id.pName);
        mail = view.findViewById(R.id.tNumber);
        personViewModel.getNameAndMailFromBD(name, mail);
    }

}
