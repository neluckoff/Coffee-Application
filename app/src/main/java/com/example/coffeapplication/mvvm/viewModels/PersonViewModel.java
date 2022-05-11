package com.example.coffeapplication.mvvm.viewModels;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.coffeapplication.R;
import com.example.coffeapplication.mvvm.models.User;
import com.example.coffeapplication.mvvm.repositories.PersonRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

import java.util.Objects;

public class PersonViewModel extends ViewModel {
    PersonRepository personRepository = new PersonRepository();

    public void getNameAndMailFromBD(TextView name, TextView mail) {
        personRepository.getMyRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    User user = ds.getValue(User.class);
                    if (user.mail.equals(Objects.requireNonNull(personRepository.getmAuth().getCurrentUser()).getEmail())) {
                        name.setText(user.name + " " + user.secName);
                        mail.setText(user.getMail());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void generatingQRCode(String data, ImageView qrcode) {
        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            BitMatrix matrix = writer.encode(data, BarcodeFormat.QR_CODE, 1000, 1000);
            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bitmap = encoder.createBitmap(matrix);
            qrcode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    public void setEditInfo(EditText name, EditText mail, EditText lastname) {
        personRepository.getMyRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    User user = ds.getValue(User.class);
                    if (user.mail.equals(Objects.requireNonNull(personRepository.getmAuth().getCurrentUser()).getEmail())) {
                        name.setText(user.name);
                        mail.setText(user.getBirthday());
                        lastname.setText(user.secName);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void setName(TextView name) {
        personRepository.getMyRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    User user = ds.getValue(User.class);
                    if (user.mail.equals(Objects.requireNonNull(personRepository.getmAuth().getCurrentUser()).getEmail())) {
                        name.setText(user.name);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
