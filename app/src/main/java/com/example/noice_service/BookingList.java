package com.example.noice_service;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;

public class BookingList extends AppCompatActivity {

    //initialize variables
    ExpandableListView expandableListView;
    ArrayList<String> listGroup = new ArrayList<>();
    HashMap<String, ArrayList<String>> listChild = new HashMap<>();
    MainAdapter adapter;
    Button btn_tabview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_list);

        expandableListView = findViewById(R.id.exp_list_view);

        //Use for loop

        //Add values in group list

        for(int g = 0; g <= 5; g++) {
            listGroup.add("Date " + g);

            //Initialize array list
            ArrayList<String> arrayList = new ArrayList<>();

            for(int c = 0; c <= 4; c++) {
                //Add values in array list
                arrayList.add("Service " + c + ": Service_Name, Time");
            }
            listChild.put(listGroup.get(g), arrayList);
        }
        //Initialize adapter
        adapter = new MainAdapter(listGroup, listChild);

        //Set adapter
        expandableListView.setAdapter(adapter);

        btn_tabview = (Button)findViewById(R.id.btn_tabview);
        btn_tabview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton();
            }
        });
        //-------------------------------------------------------Bottom App BAR FUNCTION---------------------------------------------
        //Initialize variables and assign them
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

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

    public void clickButton()
    {
        Intent intent = new Intent(this,ViewBookings_Admin.class);
        startActivity(intent);
    }

}