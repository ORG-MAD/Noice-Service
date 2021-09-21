package com.example.noice_service;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;

public class EditSelectedRequest extends AppCompatActivity {
    TextView viewID, viewName, viewContactNo, viewVehicleName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_selected_request);

        //Noice Text Heading
        TextView textView = (TextView) findViewById(R.id.tv_heading);
        String text = "NOICE SERVICE";
        SpannableString ss = new SpannableString(text);
        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        ss.setSpan(boldSpan, 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);

        Button subBtn = findViewById(R.id.btn_edit);
        Button delBtn = findViewById(R.id.btn_delete);

        final EditText customerName = findViewById(R.id.et_customerName);
        final EditText customerID = findViewById(R.id.et_customerID);
        final EditText contactNo = findViewById(R.id.et_contactNumber);
        final EditText vehicleName = findViewById(R.id.et_vehicleName);
        final EditText location = findViewById(R.id.et_locationDetails);

        DAORequest dao = new DAORequest();

        viewID = findViewById(R.id.tv_customerIDtext);
        viewName = findViewById(R.id.tv_customerName);
        viewContactNo = findViewById(R.id.tv_contactNumberText);
        viewVehicleName = findViewById(R.id.tv_vehicleNameText);

        ReqModel req = (ReqModel) getIntent().getSerializableExtra("UPDATE");
        if(req != null) {
            viewID.setText(req.getCustomerID());
            viewName.setText(req.getCustomerName());
            viewContactNo.setText(req.getContactNumber());
            viewVehicleName.setText(req.getVehicleName());
        }
        subBtn.setOnClickListener(v-> {
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("location", location.getText().toString());
                    dao.update(req.getKey(), hashMap).addOnSuccessListener(sucess -> {
                        Toast.makeText(this, "Record is Updated", Toast.LENGTH_SHORT).show();
                    }).addOnFailureListener((er -> {
                        Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                    }));
        });

        delBtn.setOnClickListener(v-> {
            dao.remove(req.getKey()).addOnSuccessListener(sucess -> {
                Toast.makeText(this, "Record is Removed", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener((er -> {
                Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
            }));
        });

        //Initialize Intent Variables
//        et_customerID = findViewById(R.id.et_customerID);
//        et_contactNumber = findViewById(R.id.et_contactNumber);
//        et_vehicleName = findViewById(R.id.et_vehicleName);
//        et_locationDetails = findViewById(R.id.et_locationDetails);
//
//        tvSreqCustomer = findViewById(R.id.tv_customerName);
//
//        Intent intent = getIntent();
//
//        intent.getStringExtra("cID");
//        intent.getStringExtra("contactNo");
//        intent.getStringExtra("vehicleName");
//        intent.getStringExtra("locationDetails");
//
//        if(intent.getExtras() != null){
//            ReqModelCustomer reqModelCustomer = (ReqModelCustomer) intent.getSerializableExtra("data");
//
//            tvSreqCustomer.setText(reqModelCustomer.getRequestTitle());
//        }

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