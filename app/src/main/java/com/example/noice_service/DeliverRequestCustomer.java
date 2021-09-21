package com.example.noice_service;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DeliverRequestCustomer extends AppCompatActivity{

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    RequestAdapterCustomer adapter;
    DAORequest dao;
    boolean isLoading = false;
    String key = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver_request_customer);

        //Noice Text Heading
        TextView textView = (TextView) findViewById(R.id.tv_heading);
        String text = "NOICE SERVICE";
        SpannableString ss = new SpannableString(text);
        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        ss.setSpan(boldSpan, 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);

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


        swipeRefreshLayout = findViewById(R.id.swipecust);
        recyclerView = findViewById(R.id.recyclerviewcustomer);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new RequestAdapterCustomer(this);
        recyclerView.setAdapter(adapter);
        dao = new DAORequest();
        loadData();

        recyclerView.addOnScrollListener((new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int totalItem = linearLayoutManager.getItemCount();
                int lastVisible = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                if (totalItem < lastVisible + 3) {
                    if (!isLoading) {
                        isLoading = true;
                        loadData();
                    }
                }
            }
        }));
    }

    private void loadData() {
        swipeRefreshLayout.setRefreshing(true);
        dao.get(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<ReqModel> reqs = new ArrayList<>();
                for (DataSnapshot data : snapshot.getChildren()) {
                    ReqModel req = data.getValue(ReqModel.class);
                    req.setKey(data.getKey());
                    reqs.add(req);
                    key = data.getKey();
                }
                adapter.setItems(reqs);
                adapter.notifyDataSetChanged();
                isLoading = false;
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
    //Navigate to NewDeliverReqest Method
    public void onClickNew(View n){
        Intent intent = new Intent(this, NewDeliverRequest.class);//this keyword defines the current activity.
        startActivity(intent);

        Context context = getApplicationContext(); //The context to use. Usually your Application or Activity object
        CharSequence message = "New Request Page";

        //Display string
        int duration = Toast.LENGTH_SHORT; //How long the toast message will lasts
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }
}

//    Toolbar toolbar;
//    RecyclerView recyclerView;
//    Button btn_newReq;
//
//    List<ReqModelCustomer> reqModelListCustomer = new ArrayList<>();
//
//    String[] names = {"Request 1", "Request 2", "Request 3", "Request 4"};
//    RequestAdapterCustomer requestAdapterCustomer;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_deliver_request_customer);
//
//        //Noice Text Heading
//        TextView textView = (TextView) findViewById(R.id.tv_heading);
//        String text = "NOICE SERVICE";
//        SpannableString ss = new SpannableString(text);
//        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
//        ss.setSpan(boldSpan, 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        textView.setText(ss);
//
////-------------------------------------------------------Bottom App BAR FUNCTION---------------------------------------------
//        //Initialize variables and assign them
//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
//
//        //Set Home Selected
//
//
//        //Perform Item Selected Event Listener
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                switch(menuItem.getItemId()){
//                    case R.id.dashboard:
//                        startActivity(new Intent(getApplicationContext(), Dashboard.class));
//                        overridePendingTransition(0, 0);
//                        return true;
//                    case R.id.home:
//                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                        overridePendingTransition(0, 0);
//                        return true;
//                    case R.id.about:
//                        startActivity(new Intent(getApplicationContext(), About.class));
//                        overridePendingTransition(0, 0);
//                        return true;
//                }
//                return false;
//            }
//        });
////-------------------------------------------------------Bottom App BAR FUNCTION---------------------------------------------
//
//        recyclerView = findViewById(R.id.recyclerviewcustomer);
//        toolbar = findViewById(R.id.toolbarcustomer);
//
//        this.setSupportActionBar(toolbar);
//        this.getSupportActionBar().setTitle("");
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
//
//        for (String s:names){
//            ReqModelCustomer reqModelCustomer = new ReqModelCustomer(s);
//
//            reqModelListCustomer.add(reqModelCustomer);
//        }
//        requestAdapterCustomer = new RequestAdapterCustomer(reqModelListCustomer, this);
//        recyclerView.setAdapter(requestAdapterCustomer);
//    }
//
//    @Override
//    public void selectedReqCustomer(ReqModelCustomer reqModelCustomer) {
//        startActivity(new Intent(DeliverRequestCustomer.this, SelectedRequestCustomer.class).putExtra("data", reqModelCustomer));
//    }
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu, menu);
//
//        MenuItem menuItem = menu.findItem(R.id.search_view);
//
//        SearchView searchView = (SearchView) menuItem.getActionView();
//        searchView.setMaxWidth(Integer.MAX_VALUE);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                requestAdapterCustomer.getFilter().filter(newText);
//                return true;
//            }
//        });
//
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//        if(id == R.id.search_view){
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    //Navigate to NewDeliverReqest Method
//    public void onClickNew(View n){
//        Intent intent = new Intent(this, NewDeliverRequest.class);//this keyword defines the current activity.
//        startActivity(intent);
//
//        Context context = getApplicationContext(); //The context to use. Usually your Application or Activity object
//        CharSequence message = "New Request Page";
//
//        //Display string
//        int duration = Toast.LENGTH_SHORT; //How long the toast message will lasts
//        Toast toast = Toast.makeText(context, message, duration);
//        toast.show();
//    }
//
//
//}
