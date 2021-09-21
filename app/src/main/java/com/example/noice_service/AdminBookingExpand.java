package com.example.noice_service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminBookingExpand extends AppCompatActivity {

    DatabaseReference bookingReference;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_booking_expand);

        TextView tvname = findViewById(R.id.mbooking_name);
        TextView tvtime_slot = findViewById(R.id.time_slot);
        TextView tvcar_no = findViewById(R.id.car_no);
        TextView tvphone_no = findViewById(R.id.phone_no);

        id = getIntent().getStringExtra("booking_id");
        String name = getIntent().getStringExtra("booking_name");
        String time_slot = getIntent().getStringExtra("time_slot");
        String car_no = getIntent().getStringExtra("car_no");
        String phone_no = getIntent().getStringExtra("phone_no");

        tvname.setText(name);
        tvtime_slot.setText(time_slot);
        tvcar_no.setText(car_no);
        tvphone_no.setText(phone_no);
    }

    public void finishBooking(View view){
        bookingReference = FirebaseDatabase.getInstance().getReference("Bookings");
        //bookingReference.child(id).removeValue();
        //bookingReference.child(id).child("booking_name").setValue("halooo");
        bookingReference.child(id).child("booking_status").setValue("Finished");
        Intent intent=new Intent(AdminBookingExpand.this,BookingsAdmin.class);
        startActivity(intent);
    }
}