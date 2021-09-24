package com.example.noice_service;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Arrays;
import java.util.List;

public class NewDeliverRequest extends AppCompatActivity {
    EditText locationDetails,customerName, customerEmail, contactNo, vehicleName;
    Button subBtn;
    String status = "Pending";
    DAORequest dao = new DAORequest();
    int PLACE_PICKER_REQUEST = 1;
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_deliver_request);

        //Noice Text Heading
        TextView textView = (TextView) findViewById(R.id.tv_heading);
        String text = "NOICE SERVICE";
        SpannableString ss = new SpannableString(text);
        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        ss.setSpan(boldSpan, 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);

        //Assign Variables
        customerName = findViewById(R.id.et_customerName);
        customerEmail = findViewById(R.id.et_customerEmail);
        contactNo = findViewById(R.id.et_contactNumber);
        vehicleName = findViewById(R.id.et_vehicleName);
        locationDetails = findViewById(R.id.et_locationDetails);
        subBtn = findViewById(R.id.btn_newReq);


        //Initialize Validation style
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        //Validating name field
        awesomeValidation.addValidation(this, R.id.et_customerName, RegexTemplate.NOT_EMPTY, R.string.invalid_nameD);
        //Validating email field
        awesomeValidation.addValidation(this, R.id.et_customerEmail, Patterns.EMAIL_ADDRESS, R.string.invalid_emailD);
        //Validating contact no field
        awesomeValidation.addValidation(this, R.id.et_contactNumber, "[0-9]{10}$", R.string.invalid_numberD);
        //Validating Vehicle field
        awesomeValidation.addValidation(this, R.id.et_vehicleName, RegexTemplate.NOT_EMPTY, R.string.invalid_vehicleNameD);


        //Setting the location
        Places.initialize(getApplicationContext(), "AIzaSyDqANwoI65CAbZY8vurHA-OxsL_TzQGZdk");
        locationDetails.setFocusable(false);
        locationDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Initialize place field list
                List<Place.Field> fieldList = Arrays.asList(Place.Field.ADDRESS,Place.Field.LAT_LNG, Place.Field.NAME);
                //Create intent
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fieldList).build(NewDeliverRequest.this);
                //Start activity result
                startActivityForResult(intent, 100);
            }
        });

        subBtn.setOnClickListener(v->{
            if (awesomeValidation.validate()) {
                ReqModel req = new ReqModel(
                        customerName.getText().toString(),
                        customerEmail.getText().toString(),
                        contactNo.getText().toString(),
                        vehicleName.getText().toString(),
                        locationDetails.getText().toString(),
                        status
                );
                dao.add(req).addOnSuccessListener(sucess->{
                    Toast.makeText(this,"Record is Inserted",Toast.LENGTH_SHORT).show();
                }).addOnFailureListener((er->{
                    Toast.makeText(this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
                }));
                //On Success
                Toast.makeText(getApplicationContext(), "Validated Successfully", Toast.LENGTH_SHORT).show();
                //Navigate back to the Home Screen
                Intent intent;
                intent = new Intent(NewDeliverRequest.this,HomeCustomer.class);
                startActivity(intent);
            }else{
                Toast.makeText(getApplicationContext(), "Validation Failed", Toast.LENGTH_SHORT).show();
            }
        });

//-------------------------------------------------------Bottom App BAR FUNCTION------------------------------------------------------------
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
//-------------------------------------------------------Bottom App BAR FUNCTION------------------------------------------------------
    }
    //Location Picker
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 && resultCode == RESULT_OK){
            Place place = Autocomplete.getPlaceFromIntent(data);
            locationDetails.setText(place.getAddress());
        }else if(resultCode == AutocompleteActivity.RESULT_ERROR){
            Status status = Autocomplete.getStatusFromIntent(data);
            Toast.makeText(getApplicationContext(),status.getStatusMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
