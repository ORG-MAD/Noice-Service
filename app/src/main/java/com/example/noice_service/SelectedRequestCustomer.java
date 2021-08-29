package com.example.noice_service;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SelectedRequestCustomer extends AppCompatActivity {
    TextView tvSreqCustomer;
    TextView textView;
    EditText et_customerID;
    EditText et_contactNumber;
    EditText et_vehicleName;
    EditText et_locationDetails;
    Button btn_edit;
    Button btn_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_request_customer);

        //Noice Text Heading
        TextView textView = (TextView) findViewById(R.id.tv_heading);
        String text = "NOICE SERVICE";
        SpannableString ss = new SpannableString(text);
        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        ss.setSpan(boldSpan, 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);

        //Initialize the Intent Varibles
        tvSreqCustomer = findViewById(R.id.tv_customerName);

        et_customerID = findViewById(R.id.et_customerID);
        et_contactNumber = findViewById(R.id.et_contactNumber);
        et_vehicleName = findViewById(R.id.et_vehicleName);
        et_locationDetails = findViewById(R.id.et_locationDetails);
        btn_edit = findViewById(R.id.btn_edit);
        btn_delete = findViewById(R.id.btn_delete);

        Intent intent = getIntent();

        if(intent.getExtras() != null){
            ReqModelCustomer reqModelCustomer = (ReqModelCustomer) intent.getSerializableExtra("data");

            tvSreqCustomer.setText(reqModelCustomer.getRequestTitle());
        }
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

    //Navigate to Edit Request Avtivity
    public void onClickEdit(View e){
        Intent intent = new Intent(this, EditSelectedRequest.class);//this keyword defines the current activity.
        startActivity(intent);

        Context context = getApplicationContext(); //The context to use. Usually your Application or Activity object
        CharSequence message = "Update Page";
        //Display string
        int duration = Toast.LENGTH_SHORT; //How long the toast message will lasts
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

    //Navigate to Delete Activity
    public void onClickDelete(View d){
        Intent intent = new Intent(this, DeleteSelectedRequest.class);//this keyword defines the current activity.
        startActivity(intent);

        Context context = getApplicationContext(); //The context to use. Usually your Application or Activity object
        CharSequence message = "Delete Page";
        //Display string
        int duration = Toast.LENGTH_SHORT; //How long the toast message will lasts
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }
}