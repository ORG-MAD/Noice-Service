package com.example.noice_service;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
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

    }

    @Override
    protected void onStart() {
        super.onStart();

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
}