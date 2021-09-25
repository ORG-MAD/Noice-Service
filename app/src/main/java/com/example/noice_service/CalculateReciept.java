package com.example.noice_service;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class CalculateReciept extends AppCompatActivity {
    TextView viewEmail, viewName, viewPrice, netAmount;
    EditText delFee;
    String email;
    String name;
    String price;
    String bID;
    Button btnSubmit;
    DatabaseReference bookingReference;
    DAORequest dao = new DAORequest();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_reciept);

        viewName = findViewById(R.id.tv_customerName);
        viewEmail = findViewById(R.id.tv_customerEmailtext);
        viewPrice = findViewById(R.id.tv_basePriceText);
        delFee = findViewById(R.id.et_delFee);
        netAmount = findViewById(R.id.tv_netAmount);

        name = getIntent().getStringExtra("cus_name");
        email = getIntent().getStringExtra("user_email");
        price = getIntent().getStringExtra("s_price");
        bID = getIntent().getStringExtra("booking_id");
        btnSubmit = findViewById(R.id.btn_emailR);

        viewName.setText(name);
        viewEmail.setText(email);
        viewPrice.setText(price);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!viewPrice.getText().toString().equals("") && !delFee.getText().toString().equals("")){
                    int temp1 = Integer.parseInt(viewPrice.getText().toString());
                    int temp2 = Integer.parseInt(delFee.getText().toString());
                    netAmount.setText(String.valueOf(temp1 + temp2));
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        viewPrice.addTextChangedListener(textWatcher);
        delFee.addTextChangedListener(textWatcher);
    }

    //Send email function
    public void sendmail(View view) {
        String[] TO_EMAILS = {"noice@gmail.com"};

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, TO_EMAILS);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Confirmation");
        intent.putExtra(Intent.EXTRA_TEXT, "Thank you for using Noice Service mobile app");

        startActivity(Intent.createChooser(intent, "Choose your email client"));
    }



}
