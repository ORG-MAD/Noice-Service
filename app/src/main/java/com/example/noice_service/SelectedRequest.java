package com.example.noice_service;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SelectedRequest extends AppCompatActivity {
    TextView tvSreq , viewID, viewName, viewContactNo, viewVehicleName, viewLocation;

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_request);

        //Noice Text Heading
        TextView textView = (TextView) findViewById(R.id.tv_heading);
        String text = "NOICE SERVICE";
        SpannableString ss = new SpannableString(text);
        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        ss.setSpan(boldSpan, 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);

        final EditText customerName = findViewById(R.id.et_customerName);
        final EditText customerID = findViewById(R.id.et_customerID);
        final EditText contactNo = findViewById(R.id.et_contactNumber);
        final EditText vehicleName = findViewById(R.id.et_vehicleName);
        final EditText location = findViewById(R.id.et_locationDetails);

        DAORequest dao = new DAORequest();
//        tvSreq = findViewById(R.id.tv_customerName);
        viewID = findViewById(R.id.tv_customerIDtext);
        viewName = findViewById(R.id.tv_customerName);
        viewContactNo = findViewById(R.id.tv_contactNumberText);
        viewVehicleName = findViewById(R.id.tv_vehicleNameText);
        viewLocation  = findViewById(R.id.tv_locationDetailstext);

        ReqModel req = (ReqModel) getIntent().getSerializableExtra("VIEW");
        if(req != null) {
            viewID.setText(req.getCustomerID());
            viewName.setText(req.getCustomerName());
            viewContactNo.setText(req.getContactNumber());
            viewVehicleName.setText(req.getVehicleName());
            viewLocation.setText(req.getLocation());
        }

//        Intent intent = getIntent();
//
//        if(intent.getExtras() != null){
//            ReqModel viewReq = (ReqModel) getIntent().getSerializableExtra("VIEW");
//            tvSreq.setText(viewReq.getCustomerName());
//
//            if(viewReq != null){
//                viewID.setText(viewReq.getCustomerID());
//                viewName.setText(viewReq.getCustomerName());
//                viewContactNo.setText(viewReq.getContactNumber());
//                viewVehicleName.setText(viewReq.getVehicleName());
//                viewLocation.setText(viewReq.getLocation());
//            }

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

}