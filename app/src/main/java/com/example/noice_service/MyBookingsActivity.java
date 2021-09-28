package com.example.noice_service;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyBookingsActivity extends AppCompatActivity implements MyBookingClickListner{

    DatabaseReference bookingsDatabase;
    List<MyBookings> retreivedBooking = new ArrayList<>();
    DatabaseReference UserDatabaseReference;
    FirebaseAuth mAuth;
    String user_email;
    RecyclerView mybookinglist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bookings);
        mybookinglist = findViewById(R.id.my_booking_list);

        bookingsDatabase = FirebaseDatabase.getInstance().getReference().child("Bookings");
        mAuth = FirebaseAuth.getInstance();
        UserDatabaseReference = FirebaseDatabase.getInstance().getReference().child("User");

        getUserEmail();
        loadData();

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
                        String email=ds.child("user_email").getValue(String.class);

                        if (user_email.equals(email)){
                            retreivedBooking.add(new MyBookings(booking_id,booking_name,booking_status,booking_time,car_no,details,phone_no,s_price,time_slot,tv_day));
                        }
                    }

                    MyBookingsRecyclerView myBookingsRecyclerView = new MyBookingsRecyclerView(retreivedBooking, MyBookingsActivity.this);
                    mybookinglist.setLayoutManager(new LinearLayoutManager(MyBookingsActivity.this));
                    mybookinglist.setAdapter(myBookingsRecyclerView);

                } else {

                }
            }

            @Override
            public void onCancelled( DatabaseError error) {

            }
        });
    }

    public void onClickItem(MyBookings myBooking) {
        Intent intent=new Intent(MyBookingsActivity.this,MyBookingExpand.class);
        intent.putExtra("booking_id",myBooking.getBooking_id());
        intent.putExtra("booking_name",myBooking.getBooking_name());
        intent.putExtra("booking_status",myBooking.getBooking_status());
        intent.putExtra("time_slot",myBooking.getTime_slot());
        intent.putExtra("car_no",myBooking.getCar_no());
        intent.putExtra("phone_no",myBooking.getPhone_no());
        intent.putExtra("details",myBooking.getDetails());
        intent.putExtra("includes",myBooking.getTv_day());
        intent.putExtra("s_price",myBooking.getS_price());
        intent.putExtra("user_email",user_email);
        startActivity(intent);
    }

    private void getUserEmail() {
        UserDatabaseReference.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists() && snapshot.getChildrenCount() > 0) {
                    user_email = snapshot.child("email").getValue().toString();
                } else {
                    Toast.makeText(MyBookingsActivity.this,"Please login",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MyBookingsActivity.this, jLogin_Customer.class);
                    startActivity(intent);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void goBackServiceList(View view){
        Intent intent = new Intent(MyBookingsActivity.this, services_list.class);
        startActivity(intent);
    }

}