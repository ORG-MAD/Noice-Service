package com.example.noice_service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BookingUpdateForm extends AppCompatActivity {

    String id;
    EditText phone_no;
    EditText car_no;
    DatabaseReference bookingReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_update_form);

        id = getIntent().getStringExtra("booking_id");

        phone_no = findViewById(R.id.phone_number);
        car_no = findViewById(R.id.car_number);

        phone_no.setText(getIntent().getStringExtra("phone_no"));
        car_no.setText(getIntent().getStringExtra("car_no"));
    }

    public void confirmUpdate(View view) {

        String phoneNo = phone_no.getText().toString();
        String carNo = car_no.getText().toString();

        if(phoneNo.isEmpty()){
            phone_no.setError("Please enter Contact Number");
            phone_no.requestFocus();

        } else if( !(phoneNo.length() == 10) || !(phoneNo.charAt(0) == '0')) {
            phone_no.setError("Please enter valid Contact Number");
            phone_no.requestFocus();

        }else if(carNo.isEmpty()){
            car_no.setError("Please enter Car Number");
            car_no.requestFocus();
        } else {
            bookingReference = FirebaseDatabase.getInstance().getReference("Bookings");
            bookingReference.child(id).child("car_no").setValue(carNo);
            bookingReference.child(id).child("phone_no").setValue(phoneNo);

            Toast.makeText(BookingUpdateForm.this,"Details Changed Successfully!",Toast.LENGTH_SHORT).show();

//                Intent intent=new Intent(BookingUpdateForm.this,MyBookingsActivity.class);
//                startActivity(intent);
            }

    }

}