package com.example.noice_service;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Register_Customer3 extends AppCompatActivity {

    TextView textView;
    ImageView iv_image;
    int SELECT_PHOTO = 1;
    Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_customer3);

        textView = (TextView) findViewById(R.id.tv_heading);
        String text = "NOICE SERVICE";
        SpannableString ss = new SpannableString(text);
        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        ss.setSpan(boldSpan, 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);

        Button btn_choose = findViewById(R.id.btn_choose);
        iv_image = findViewById(R.id.iv_image);

        btn_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,SELECT_PHOTO);
            }
        });

        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
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

    private void showAlertDialog() {
        Dialog dialog = new Dialog(Register_Customer3.this);
        dialog.setContentView(R.layout.alert_dialog);

        CheckBox checkBox = dialog.findViewById(R.id.check_box);
        Button btCancel = dialog.findViewById(R.id.bt_cancel);
        Button btOk = dialog.findViewById(R.id.bt_ok);

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        btOk.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        checkBox.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()){
                    btOk.setEnabled(true);
                    btOk.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
                }else{
                    btOk.setEnabled(false);
                    btOk.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                }
            }
        });
        dialog.show();
    }

}