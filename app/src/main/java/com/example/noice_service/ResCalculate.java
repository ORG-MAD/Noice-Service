package com.example.noice_service;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class ResCalculate extends AppCompatActivity
{
    private TextView mTotal;
    private DatabaseReference mDatabase;

    RecyclerView recview;
    Resource_Calculation adapter;
    FloatingActionButton fb;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_calculate);

        setTitle("Search here..");

        //total price
        mTotal = (TextView)findViewById(R.id.totalAmtID);
        mDatabase= FirebaseDatabase.getInstance().getReference().child("services");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int sum=0;
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Map<String, Object> map = (Map<String,Object>) ds.getValue();
                    Object price = map.get("price");
                    int pValue = Integer.parseInt(String.valueOf(price));
                    sum = calcSumValue(sum, pValue);

                    mTotal.setText(String.valueOf(sum));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //view service details
        recview=(RecyclerView)findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<SVCmodel> options =
                new FirebaseRecyclerOptions.Builder<SVCmodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("services"), SVCmodel.class)
                        .build();

        adapter=new Resource_Calculation(options);
        recview.setAdapter(adapter);

//-------------------------------------------------------Bottom App BAR FUNCTION---------------------------------------------
        //Initialize variables and assign them
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

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
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.searchmenu,menu);

        MenuItem item=menu.findItem(R.id.search);

        SearchView searchView=(SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String s) {

                processsearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processsearch(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void processsearch(String s)
    {
        FirebaseRecyclerOptions<SVCmodel> options =
                new FirebaseRecyclerOptions.Builder<SVCmodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("services").orderByChild("title").startAt(s).endAt(s+"\uf8ff"), SVCmodel.class)
                        .build();

        adapter=new Resource_Calculation(options);
        adapter.startListening();
        recview.setAdapter(adapter);

    }

    int calcSumValue(int a, int b){
        return a + b;
    }

}