package com.example.noice_service;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdminBookingReport extends AppCompatActivity {
    DatabaseReference bookingsDatabase;
    List<ServiceReportModel> services;
    RecyclerView adminbookinglist;
    TextView tv_total;

    int total_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_booking_report);
        adminbookinglist = findViewById(R.id.admin_booking_report_list);
        tv_total = findViewById(R.id.total_);

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
                        startActivity(new Intent(getApplicationContext(), Admin_Dashboard.class));
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

    @Override
    protected void onStart() {
        super.onStart();

        Date date = new Date();
        String today = getDate(date.toString());

        total_ = 0;
        bookingsDatabase = FirebaseDatabase.getInstance().getReference().child("Finished_Bookings");
        services = new ArrayList<>();

        bookingsDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){
                    for(DataSnapshot ds : snapshot.getChildren()){

                        String booking_name=ds.child("booking_name").getValue(String.class);
                        String booking_time=ds.child("booking_time").getValue(String.class);
                        String s_price=ds.child("s_price").getValue(String.class);

                        if(today.equals(getDate(booking_time))){

                            boolean serviceExists = false;

                            for(ServiceReportModel service : services)
                            {
                                if (booking_name.equals(service.getName())){
                                    service.increaseCount();
                                    total_ = total_ + Integer.parseInt(service.getPrice());
                                    serviceExists = true;
                                }
                            }
                            if(serviceExists == false){
                                services.add(new ServiceReportModel(booking_name, s_price));
                                total_ = total_ + Integer.parseInt(s_price);
                            }
                        }

                    }
                    tv_total.setText(Integer.toString(total_) + ".00");
                    AdminBookingReportRecyclerView adminBookingsReportRecyclerView = new AdminBookingReportRecyclerView(services);
                    adminbookinglist.setLayoutManager(new LinearLayoutManager(AdminBookingReport.this));
                    adminbookinglist.setAdapter(adminBookingsReportRecyclerView);

                } else {

                }
            }

            @Override
            public void onCancelled( DatabaseError error) {

            }
        });
    }

    public void goBackAdminBookings(View view){
        Intent intent=new Intent(AdminBookingReport.this, BookingsAdmin.class);
        startActivity(intent);
    }

    public static String getDate(String str){
        int index = str.indexOf(' ');
        index = str.indexOf(' ', index + 1);
        index = str.indexOf(' ', index + 1);

        String result = str.substring(0, index);
        return result;
    }
}