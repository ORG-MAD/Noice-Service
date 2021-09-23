package com.example.noice_service;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class BookingFrom extends AppCompatActivity {

    DatabaseReference bookingsDatabase;
    ProgressBar loadingProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_from);

        String s_title = getIntent().getStringExtra("s_title");
        String s_price = getIntent().getStringExtra("s_price");
        String s_description = getIntent().getStringExtra("s_description");
        String tv_day = getIntent().getStringExtra("tv_day");


        bookingsDatabase = FirebaseDatabase.getInstance().getReference().child("Bookings");

        final EditText phoneNumberEditText = findViewById(R.id.phone_number);
        final EditText carNumberEditText = findViewById(R.id.car_number);
        final Button bookConfirm = findViewById(R.id.book_confirm);
        loadingProgressBar = findViewById(R.id.loading);
        TextView heading = findViewById(R.id.heading_);
        heading.setText(s_title);

        Spinner dropdown = findViewById(R.id.time_slot);
        String[] items = new String[]{"Select time", "08:00 AM","09:00 AM", "10:00 AM", "11:00 PM", "12:00 PM", "01:00 PM", "02:00 PM", "03:00 PM" };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);

        dropdown.setAdapter(adapter);


        bookConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phoneNo = phoneNumberEditText.getText().toString();
                String carNo = carNumberEditText.getText().toString();
                String time_slot = dropdown.getSelectedItem().toString();

                if(phoneNo.isEmpty()){
                    phoneNumberEditText.setError("Please enter Contact Number");
                    phoneNumberEditText.requestFocus();
                } else if( !(phoneNo.length() == 10) || !(phoneNo.charAt(0) == '0')) {
                    phoneNumberEditText.setError("Please enter valid Contact Number");
                    phoneNumberEditText.requestFocus();
                }else if(carNo.isEmpty()){
                    carNumberEditText.setError("Please enter Car Number");
                    carNumberEditText.requestFocus();
                } else if(time_slot.equals("Select time")) {
                    ((TextView)dropdown.getSelectedView()).setError("Please Select time");
                    dropdown.requestFocus();
                } else {
                    loadingProgressBar.setVisibility(View.VISIBLE);
                    ProcessBook(phoneNo, carNo, time_slot,s_title,s_description, s_price, tv_day);
                }
            }
        });
    }

    private void ProcessBook(String pNo, String cNo, String timeSlot,String s_title,String s_description, String s_price, String tv_day){

        Date date = new Date();
        String id = bookingsDatabase.push().getKey();

        bookingsDatabase.child(id).child("booking_id").setValue(id);
        bookingsDatabase.child(id).child("booking_name").setValue(s_title);
        bookingsDatabase.child(id).child("booking_status").setValue("Booking Successful");
        bookingsDatabase.child(id).child("booking_time").setValue(date.toString());
        bookingsDatabase.child(id).child("car_no").setValue(cNo);
        bookingsDatabase.child(id).child("details").setValue(s_description);
        bookingsDatabase.child(id).child("phone_no").setValue(pNo);
        bookingsDatabase.child(id).child("s_price").setValue(s_price);
        bookingsDatabase.child(id).child("time_slot").setValue(timeSlot);
        bookingsDatabase.child(id).child("tv_day").setValue(tv_day);

        loadingProgressBar.setVisibility(View.GONE);
        Toast.makeText(this,"Booking success",Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, services_list.class);
        startActivity(intent);
    }
}