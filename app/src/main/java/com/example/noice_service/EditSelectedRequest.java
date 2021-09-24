package com.example.noice_service;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
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

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;

public class EditSelectedRequest extends AppCompatActivity {
    TextView viewEmail, viewName, viewContactNo, viewVehicleName, viewLocation, viewStatusD;;
    EditText status;
    Button subBtn, delBtn;
    DAORequest dao = new DAORequest();
    AwesomeValidation awesomeValidation;

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

        //Assign Variables
        viewEmail = findViewById(R.id.tv_customerEmailtext);
        viewName = findViewById(R.id.tv_customerName);
        viewContactNo = findViewById(R.id.tv_contactNumberText);
        viewVehicleName = findViewById(R.id.tv_vehicleNameText);
        viewLocation = findViewById(R.id.tv_locationDetailstext);
        viewStatusD = findViewById(R.id.tv_statusText);
        status = findViewById(R.id.et_statusText);
        subBtn = findViewById(R.id.btn_edit);
        delBtn = findViewById(R.id.btn_delete);

        //Initialize Validation style
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        //Validating status field
        awesomeValidation.addValidation(this, R.id.et_statusText, "[a-zA-Z]{0,10}$", R.string.invalid_statusD);

        //Get data from the Intent
        ReqModel req = (ReqModel) getIntent().getSerializableExtra("UPDATE");

        //Set the values obtained form the intent
        if(req != null) {
            viewEmail.setText(req.getCustomerEmail());
            viewName.setText(req.getCustomerName());
            viewContactNo.setText(req.getContactNumber());
            viewVehicleName.setText(req.getVehicleName());
            viewLocation.setText(req.getLocation());
            viewStatusD.setText(req.getStatus());
        }


        //Updates the record and navigates back to the delivery request screen
        subBtn.setOnClickListener(v-> {
            if (awesomeValidation.validate()) {
                //On Success
                Toast.makeText(getApplicationContext(), "Validated Successfully", Toast.LENGTH_SHORT).show();

                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("status", status.getText().toString());

                //Update Function
                dao.update(req.getKey(), hashMap).addOnSuccessListener(sucess -> {
                    Toast.makeText(this, "Record is Updated", Toast.LENGTH_SHORT).show();
                    Intent intent;
                    intent = new Intent(EditSelectedRequest.this, DeliverRequestCustomer.class);
                    startActivity(intent);
                }).addOnFailureListener((er -> {
                    Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                }));
            }else{
                Toast.makeText(getApplicationContext(), "Validation Failed", Toast.LENGTH_SHORT).show();
            }
        });


        //Deletes the record and navigates back to the delivery request screen
        delBtn.setOnClickListener(v-> {
                //Delete Function
                dao.remove(req.getKey()).addOnSuccessListener(sucess -> {
                    Toast.makeText(this, "Record is Removed", Toast.LENGTH_SHORT).show();
                    Intent intent;
                    intent = new Intent(EditSelectedRequest.this, DeliverRequestCustomer.class);
                    startActivity(intent);
                }).addOnFailureListener((er -> {
                    Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                }));
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

    //Send email function
    public void sendmail(View view) {
        String[] TO_EMAILS = {"noice@gmail.com"};

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, TO_EMAILS);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Confirmation");
        intent.putExtra(Intent.EXTRA_TEXT, "Thank you for using Noice Service mobile app");

        startActivity(Intent.createChooser(intent, "Choose your email client"));
    }
}

