package com.example.noice_service;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;

public class Register_Customer extends AppCompatActivity {
    TextView textView;
    TextView tv_bday;
    EditText et_date;
    ToggleButton toggleButton;
    ImageView imageView;

    DatePickerDialog.OnDateSetListener setListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_customer);

        textView = (TextView) findViewById(R.id.tv_heading);
        String text = "NOICE SERVICE";
        SpannableString ss = new SpannableString(text);
        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        ss.setSpan(boldSpan, 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);

        tv_bday = findViewById(R.id.tv_bday);
        et_date = findViewById(R.id.et_date);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        tv_bday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Register_Customer.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,setListener,year,month,day
                );
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = day+"/"+month+"/"+year;
                tv_bday.setText(date);
            }
        };

        et_date.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Register_Customer.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            month = month+1;
                            String date = day+"/"+month+"/"+year;
                            et_date.setText(date);
                        }
                    },year, month, day);
                    datePickerDialog.show();

            }
        });

        toggleButton = findViewById(R.id.toggle);
        imageView = findViewById(R.id.imageView);

        imageView.setImageDrawable(getResources().getDrawable(R.drawable.male));

        toggleButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(toggleButton.isChecked()){
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.female));
                }else{
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.male));
                }
            }
        });
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