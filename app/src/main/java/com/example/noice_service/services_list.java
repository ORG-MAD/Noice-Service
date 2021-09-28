package com.example.noice_service;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
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
    RecyclerView serviceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_list);
        serviceList = findViewById(R.id.my_service_list);

        bookingsDatabase = FirebaseDatabase.getInstance().getReference().child("services");

        loadData();


//-------------------------------------------------------Bottom App BAR FUNCTION---------------------------------------------
        //Initialize variables and assign them
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        //Perform Item Selected Event Listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext(), jMainInterface_Customer.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), HomeCustomer.class));
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
                        String s_description=ds.child("description").getValue(String.class);
                        String tv_day=ds.child("equ").getValue(String.class);
                        String totalTime=ds.child("totalTime").getValue(String.class);
                        String s_price=ds.child("price").getValue(String.class);
                        String s_title=ds.child("title").getValue(String.class);

                        ServicesModel servicesModel = new ServicesModel(s_title,s_price,tv_day,s_description);
                        servicesModel.setTotalTime(totalTime);
                        servicesModels.add(servicesModel);
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
        intent.putExtra("totalTime",servicesModel.getTotalTime());
        intent.putExtra("tv_day",servicesModel.getTv_day());
        startActivity(intent);
    }

    public void seeMyBookings(View view){
        Intent intent=new Intent(services_list.this,MyBookingsActivity.class);
        startActivity(intent);
    }
}