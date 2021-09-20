package com.example.noice_service;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Login_Customer extends AppCompatActivity {

    TextView textView;
    EditText et_username;
    EditText et_password;
    Button btn_login;
    TextView tv_no;

    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_customer);

        btn_login = (Button) findViewById(R.id.btn_login);
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        tv_no = (TextView) findViewById(R.id.tv_no);
        tv_no.setVisibility(View.GONE);
        btn_login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_username.getText().toString().equals("admin") && et_password.getText().toString().equals("admin")){
                    Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Incorrect Credentials", Toast.LENGTH_SHORT).show();
                    tv_no.setVisibility(View.VISIBLE);
                    counter--;
                    tv_no.setText(Integer.toString(counter));

                    if(counter == 0){
                        btn_login.setEnabled(false);
                    }
                }
            }
        });

        textView = (TextView) findViewById(R.id.tv_heading);
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
    }
}