package com.example.noice_service;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyBookingExpand extends AppCompatActivity {

    String id;
    String name;
    String time_slot;
    String car_no;
    String phone_no;
    String booking_status;
    String details;
    String includes;
    String status;
    String sPrice;
    DatabaseReference bookingReference;
    DatabaseReference UserDatabaseReference;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_booking_expand);

        mAuth = FirebaseAuth.getInstance();
        UserDatabaseReference = FirebaseDatabase.getInstance().getReference().child("User");

        TextView tvname = findViewById(R.id.mbooking_name);
        TextView tvtime_slot = findViewById(R.id.time_slot);
        TextView tvcar_no = findViewById(R.id.car_no);
        TextView tvphone_no = findViewById(R.id.phone_no);
        TextView etdetails = findViewById(R.id.details);
        TextView etincludes = findViewById(R.id.tv_day);
        TextView etstatus = findViewById(R.id.status);

        id = getIntent().getStringExtra("booking_id");
        booking_status = getIntent().getStringExtra("booking_status");
        name = getIntent().getStringExtra("booking_name");
        time_slot = getIntent().getStringExtra("time_slot");
        car_no = getIntent().getStringExtra("car_no");
        phone_no = getIntent().getStringExtra("phone_no");
        details = getIntent().getStringExtra("details");
        includes = getIntent().getStringExtra("includes");
        status = getIntent().getStringExtra("booking_status");
        sPrice = getIntent().getStringExtra("s_price");

        tvname.setText(name);
        tvtime_slot.setText(time_slot);
        tvcar_no.setText(car_no);
        tvphone_no.setText(phone_no);
        etdetails.setText(details);
        etincludes.setText(includes);
        etstatus.setText(status);

    }

    public void changeDetails(View view){
        if (booking_status.equals("Booking Successful")){
            Intent intent=new Intent(MyBookingExpand.this,BookingUpdateForm.class);
            intent.putExtra("booking_id",id);
            intent.putExtra("booking_name",name);
            intent.putExtra("time_slot",time_slot);
            intent.putExtra("car_no",car_no);
            intent.putExtra("phone_no",phone_no);
            startActivity(intent);
        } else {
            Toast.makeText(MyBookingExpand.this, "Service has been already finished!",Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteBooking(View view){
        if (booking_status.equals("Finished")){
            bookingReference = FirebaseDatabase.getInstance().getReference("Bookings");
            bookingReference.child(id).removeValue();
            Toast.makeText(MyBookingExpand.this, "Record deleted!",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(MyBookingExpand.this,MyBookingsActivity.class);
            startActivity(intent);

        } else {
            Toast.makeText(MyBookingExpand.this, "Service is not finished yet!",Toast.LENGTH_SHORT).show();
        }
    }

    //Place Delivery Request
    public void placeRequest(View view){
        if (booking_status.equals("Booking Successful")){
            Intent intent=new Intent(MyBookingExpand.this,NewDeliverRequest.class);
            intent.putExtra("booking_id",id);
            intent.putExtra("s_price",sPrice);
            startActivity(intent);
        } else {
            Toast.makeText(MyBookingExpand.this, "Service has been already finished!",Toast.LENGTH_SHORT).show();
        }
    }

}