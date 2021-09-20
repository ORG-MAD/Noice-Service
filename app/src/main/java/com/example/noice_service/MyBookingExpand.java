package com.example.noice_service;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MyBookingExpand extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_booking_expand);

        TextView tvname = findViewById(R.id.mbooking_name);
        TextView tvtime_slot = findViewById(R.id.time_slot);
        TextView tvcar_no = findViewById(R.id.car_no);
        TextView tvphone_no = findViewById(R.id.phone_no);

        String id = getIntent().getStringExtra("booking_id");
        String name = getIntent().getStringExtra("booking_name");
        String time_slot = getIntent().getStringExtra("time_slot");
        String car_no = getIntent().getStringExtra("car_no");
        String phone_no = getIntent().getStringExtra("phone_no");

        tvname.setText(name);
        tvtime_slot.setText(time_slot);
        tvcar_no.setText(car_no);
        tvphone_no.setText(phone_no);

    }
}