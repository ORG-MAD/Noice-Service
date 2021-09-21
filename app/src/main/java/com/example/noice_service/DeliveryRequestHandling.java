package com.example.noice_service;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class DeliveryRequestHandling extends AppCompatActivity {
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    RequestAdapter adapter;
    DAORequest dao;
    boolean isLoading = false;
    String key = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_request_handling);

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


        swipeRefreshLayout = findViewById(R.id.swipe);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new RequestAdapter(this);
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
                ArrayList<ReqModel> emps = new ArrayList<>();
                for (DataSnapshot data : snapshot.getChildren()) {
                    ReqModel emp = data.getValue(ReqModel.class);
                    emps.add(emp);
                    key = data.getKey();
                }
                adapter.setItems(emps);
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
}


//public class DeliveryRequestHandling extends AppCompatActivity implements RequestAdapter.SelectedReq {
//    Toolbar toolbar;
//    RecyclerView recyclerView;
//    DAORequest dao;
//    ArrayList<ReqModel> reqModelList = new ArrayList<>();
//    SwipeRefreshLayout swipeRefreshLayout;
//    RequestAdapter reqAdapter;
//    boolean isLoading = false;
//    String key = null;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_delivery_request_handling);
//
//        //Noice Text Heading
//        TextView textView = (TextView) findViewById(R.id.tv_heading);
//        String text = "NOICE SERVICE";
//        SpannableString ss = new SpannableString(text);
//        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
//        ss.setSpan(boldSpan, 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        textView.setText(ss);

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
////        recyclerView = findViewById(R.id.recyclerview);
////        toolbar = findViewById(R.id.toolbar);
////
////        this.setSupportActionBar(toolbar);
////        this.getSupportActionBar().setTitle("");
////
////        recyclerView.setLayoutManager(new LinearLayoutManager(this));
////        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
////
////        for (String s:names){
////            ReqModel reqModel = new ReqModel();
////
////            reqModelList.add(reqModel);
////        }
////        reqAdapter = new RequestAdapter(reqModelList, this);
////        recyclerView.setAdapter(reqAdapter);
//
//        swipeRefreshLayout = findViewById(R.id.swipe);
//        recyclerView = findViewById(R.id.recyclerview);
//        recyclerView.setHasFixedSize(true);
//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(manager);
//        reqAdapter = new RequestAdapter(this);
//        recyclerView.setAdapter(reqAdapter);
//        dao = new DAORequest();
//        loadData();
//
//        recyclerView.addOnScrollListener((new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
//                int totalItem = linearLayoutManager.getItemCount();
//                int lastVisible = linearLayoutManager.findLastCompletelyVisibleItemPosition();
//                if(totalItem < lastVisible+3){
//                    if(!isLoading){
//                        isLoading=true;
//                        loadData();
//                    }
//                }
//            }
//        }));
//    }
//    private void loadData() {
//        swipeRefreshLayout.setRefreshing(true);
//        dao.get(key).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                ArrayList<ReqModel> reqs = new ArrayList<>();
//                for(DataSnapshot data: snapshot.getChildren()){
//                    ReqModel req = data.getValue(ReqModel.class);
//                    reqs.add(req);
//                    key = data.getKey();
//                }
//                reqAdapter.setItems(reqs);
//                reqAdapter.notifyDataSetChanged();
//                isLoading=false;
//                swipeRefreshLayout.setRefreshing(false);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                swipeRefreshLayout.setRefreshing(false);
//            }
//        });
//    }
//
//    @Override
//    public void selectedReq(ReqModel reqModel) {
//        startActivity(new Intent(DeliveryRequestHandling.this, SelectedRequestCustomer.class).putExtra("data", reqModel));
//    }
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
//                reqAdapter.getFilter().filter(newText);
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
//}