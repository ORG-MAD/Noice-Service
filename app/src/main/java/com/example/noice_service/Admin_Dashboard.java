package com.example.noice_service;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Admin_Dashboard extends AppCompatActivity {

    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_dashboard);

//-------------------------------------------------------Bottom App BAR FUNCTION---------------------------------------------
        //Initialize variables and assign them
        BottomNavigationView bottomNavigationView = findViewById(R.id.adminbottom_navigation);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.admin_dashboard);

        //Perform Item Selected Event Listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.admin_dashboard:
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

    //Navigates to Services
    public void services(View view) {
        Intent intent = new Intent(Admin_Dashboard.this, ServiceHome.class);
        startActivity(intent);
    }

    //Navigates to Customer Requests
    public void custReq(View view) {
        Intent intent = new Intent(Admin_Dashboard.this, DeliverRequestCustomer.class);
        startActivity(intent);
    }

    //Navigates to Monthly Report
    public void monthlyReport(View view) {
        Intent intent = new Intent(Admin_Dashboard.this, ResCalculate.class);
        startActivity(intent);
    }

    //Navigates to Total Income
    public void totalIncome(View view) {
        Intent intent = new Intent(Admin_Dashboard.this, ViewBookings_Admin.class);
        startActivity(intent);
    }

    public void feedback(View view) {
        Intent intent = new Intent(Admin_Dashboard.this, jviewFeedbacks.class);
        startActivity(intent);
    }
}

