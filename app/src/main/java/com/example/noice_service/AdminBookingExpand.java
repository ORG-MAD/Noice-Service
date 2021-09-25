package com.example.noice_service;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminBookingExpand extends AppCompatActivity {

    DatabaseReference bookingReference;
    String id;
    String name;
    String time_slot;
    String car_no;
    String phone_no;
    String price;
    String bookingTime;
    TextView tvname;
    TextView tvtime_slot;
    TextView tvcar_no;
    TextView tvphone_no;
    TextView book_date;
    TextView book_fee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_booking_expand);

        tvname = findViewById(R.id.mbooking_name);
        tvtime_slot = findViewById(R.id.time_slot);
        tvcar_no = findViewById(R.id.car_no);
        tvphone_no = findViewById(R.id.phone_no);
        book_date = findViewById(R.id.booking_date);
        book_fee = findViewById(R.id.price);

        id = getIntent().getStringExtra("booking_id");
        name = getIntent().getStringExtra("booking_name");
        time_slot = getIntent().getStringExtra("time_slot");
        car_no = getIntent().getStringExtra("car_no");
        phone_no = getIntent().getStringExtra("phone_no");
        price = getIntent().getStringExtra("price");
        bookingTime = getIntent().getStringExtra("bookingTime");

        tvname.setText(name);
        tvtime_slot.setText(time_slot);
        tvcar_no.setText(car_no);
        tvphone_no.setText(phone_no);
        book_date.setText(getDate(bookingTime));
        book_fee.setText(price);

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

    public void finishBooking(View view){
        bookingReference = FirebaseDatabase.getInstance().getReference("Bookings");
        bookingReference.child(id).child("booking_status").setValue("Finished");
        saveFinishedBookings();
        Toast.makeText(AdminBookingExpand.this, "Booking marked as Finish", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(AdminBookingExpand.this,BookingsAdmin.class);
        startActivity(intent);
    }

    public void saveFinishedBookings(){
        bookingReference = FirebaseDatabase.getInstance().getReference("Finished_Bookings");
        bookingReference.child(id).child("booking_name").setValue(name);
        bookingReference.child(id).child("s_price").setValue(price);
        bookingReference.child(id).child("booking_time").setValue(bookingTime);
    }

    public void goBackAdminBookings(View view){
        Intent intent=new Intent(AdminBookingExpand.this, BookingsAdmin.class);
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