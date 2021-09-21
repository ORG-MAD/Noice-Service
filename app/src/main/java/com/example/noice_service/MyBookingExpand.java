package com.example.noice_service;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MyBookingExpand extends AppCompatActivity {

    String id;
    String name;
    String time_slot;
    String car_no;
    String phone_no;
    String booking_status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_booking_expand);

        TextView tvname = findViewById(R.id.mbooking_name);
        TextView tvtime_slot = findViewById(R.id.time_slot);
        TextView tvcar_no = findViewById(R.id.car_no);
        TextView tvphone_no = findViewById(R.id.phone_no);

        id = getIntent().getStringExtra("booking_id");
        booking_status = getIntent().getStringExtra("booking_status");
        name = getIntent().getStringExtra("booking_name");
        time_slot = getIntent().getStringExtra("time_slot");
        car_no = getIntent().getStringExtra("car_no");
        phone_no = getIntent().getStringExtra("phone_no");

        tvname.setText(name);
        tvtime_slot.setText(time_slot);
        tvcar_no.setText(car_no);
        tvphone_no.setText(phone_no);

    }

    public void changeDetails(View view){
        Toast.makeText(MyBookingExpand.this, "halooo",Toast.LENGTH_SHORT).show();
        if (booking_status.equals("Booking Successful")){
            Intent intent=new Intent(MyBookingExpand.this,BookingUpdateForm.class);
            intent.putExtra("booking_id",id);
            intent.putExtra("booking_name",name);
            intent.putExtra("time_slot",time_slot);
            intent.putExtra("car_no",car_no);
            intent.putExtra("phone_no",phone_no);
            startActivity(intent);
        } else {
            Toast.makeText(MyBookingExpand.this, "Service has been already finished",Toast.LENGTH_SHORT).show();
        }
    }
}