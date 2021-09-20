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

public class services_list extends AppCompatActivity implements ServiceListClickListener{

    DatabaseReference bookingsDatabase;
    List<ServicesModel> servicesModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_list);
        RecyclerView serviceList = findViewById(R.id.my_service_list);

        bookingsDatabase = FirebaseDatabase.getInstance().getReference().child("Service_Admin");

        bookingsDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){
                    for(DataSnapshot ds : snapshot.getChildren()){
                        String s_price=ds.child("s_price").getValue(String.class);
                        String s_title=ds.child("s_title").getValue(String.class);
                        String s_description=ds.child("s_description").getValue(String.class);
                        String tv_day=ds.child("tv_day").getValue(String.class);

                        servicesModels.add(new ServicesModel(s_title,s_price,tv_day,s_description));
                    }

                    ServicesListRecyclerView servicesListRecyclerView = new ServicesListRecyclerView(servicesModels, services_list.this);
                    serviceList.setLayoutManager(new LinearLayoutManager(services_list.this));
                    serviceList.setAdapter(servicesListRecyclerView);

                } else {

                }
            }

            @Override
            public void onCancelled( DatabaseError error) {

            }
        });

    }


    @Override
    public void onClickItem(ServicesModel servicesModel) {
        Intent intent=new Intent(services_list.this,BookingFrom.class);
        intent.putExtra("s_title",servicesModel.getS_title());
        intent.putExtra("s_price",servicesModel.getS_price());
        intent.putExtra("s_description",servicesModel.getS_description());
        intent.putExtra("tv_day",servicesModel.getTv_day());
        startActivity(intent);
    }
}