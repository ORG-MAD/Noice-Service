package com.example.noice_service;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
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
    RecyclerView adminbookinglist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings_admin);
        adminbookinglist = findViewById(R.id.admin_booking_list);

        bookingsDatabase = FirebaseDatabase.getInstance().getReference().child("Bookings");

        loadData();

        //-------------------------------------------------------Bottom App BAR FUNCTION---------------------------------------------
        //Initialize variables and assign them
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home Selected


        //Perform Item Selected Event Listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext(), Dashboard.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.about:
                        startActivity(new Intent(getApplicationContext(), About.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
//-------------------------------------------------------Bottom App BAR FUNCTION---------------------------------------------

    }

    public void loadData(){
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
        intent.putExtra("price",myBooking.getS_price());
        intent.putExtra("bookingTime",myBooking.getBooking_time());
        startActivity(intent);
    }

    public void seeReport(View view){
        Intent intent = new Intent(BookingsAdmin.this,AdminBookingReport.class);
        startActivity(intent);
    }

}