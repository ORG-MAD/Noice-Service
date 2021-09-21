package com.example.noice_service;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BookingsAdmin extends AppCompatActivity implements MyBookingClickListner {

    DatabaseReference bookingsDatabase;
    List<MyBookings> retreivedBooking = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings_admin);
        RecyclerView adminbookinglist = findViewById(R.id.admin_booking_list);

        bookingsDatabase = FirebaseDatabase.getInstance().getReference().child("Bookings");

        bookingsDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){
                    for(DataSnapshot ds : snapshot.getChildren()){

                        String booking_id=ds.child("booking_id").getValue(String.class);
                        String booking_name=ds.child("booking_name").getValue(String.class);
                        String booking_status=ds.child("booking_status").getValue(String.class);
                        String booking_time=ds.child("booking_time").getValue(String.class);
                        String car_no=ds.child("car_no").getValue(String.class);
                        String details=ds.child("details").getValue(String.class);
                        String phone_no=ds.child("phone_no").getValue(String.class);
                        String s_price=ds.child("s_price").getValue(String.class);
                        String time_slot=ds.child("time_slot").getValue(String.class);
                        String tv_day=ds.child("tv_day").getValue(String.class);

                        if (booking_status.equals("Booking Successful")){
                            retreivedBooking.add(new MyBookings(booking_id,booking_name,booking_status,booking_time,car_no,details,phone_no,s_price,time_slot,tv_day));
                        }
                    }

                    AdminBookingsRecyclerView adminBookingsRecyclerView = new AdminBookingsRecyclerView(retreivedBooking, BookingsAdmin.this);
                    adminbookinglist.setLayoutManager(new LinearLayoutManager(BookingsAdmin.this));
                    adminbookinglist.setAdapter(adminBookingsRecyclerView);

                } else {

                }
            }

            @Override
            public void onCancelled( DatabaseError error) {

            }
        });

    }

    public void onClickItem(MyBookings myBooking) {
        Intent intent=new Intent(BookingsAdmin.this,AdminBookingExpand.class);
        intent.putExtra("booking_id",myBooking.getBooking_id());
        intent.putExtra("booking_name",myBooking.getBooking_name());
        intent.putExtra("time_slot",myBooking.getTime_slot());
        intent.putExtra("car_no",myBooking.getCar_no());
        intent.putExtra("phone_no",myBooking.getPhone_no());
        intent.putExtra("details",myBooking.getDetails());
        startActivity(intent);
    }

}