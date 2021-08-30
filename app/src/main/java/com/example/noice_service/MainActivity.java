package com.example.noice_service;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Noice Text Heading
        TextView textView = (TextView) findViewById(R.id.tv_heading);
        String text = "NOICE SERVICE";
        SpannableString ss = new SpannableString(text);
        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        ss.setSpan(boldSpan, 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);

        //Boom Menu
        BoomMenuButton boomMenuButton = findViewById(R.id.boommenu);
        boomMenuButton.setButtonEnum(ButtonEnum.Ham);
        boomMenuButton.setPiecePlaceEnum(PiecePlaceEnum.HAM_3);
        boomMenuButton.setButtonPlaceEnum(ButtonPlaceEnum.HAM_3);
        HamButton.Builder builder=new HamButton.Builder();
        HamButton.Builder builder1=new HamButton.Builder();
        HamButton.Builder builder2=new HamButton.Builder();

        //Service Management Activity
        builder.normalImageRes(R.drawable.noice_logo).normalText("Service Management")
                .normalColorRes(R.color.colorYellowD)
                .normalTextColorRes(R.color.black);
        boomMenuButton.addBuilder(builder);

        builder.listener(new OnBMClickListener() {
            @Override
            public void onBoomButtonClick(int index) {
                index = 0;
                Intent intent;
                intent = new Intent(MainActivity.this, ServiceHome.class);//Service Management Activity
                startActivity(intent);
            }
        });

        //Delivery Request Handling Activity
        builder1.normalImageRes(R.drawable.noice_logo).normalText("Delivery Request Handling")
                .normalColorRes(R.color.colorYellowD)
                .normalTextColorRes(R.color.black);
        boomMenuButton.addBuilder(builder1);
        builder1.listener(new OnBMClickListener() {
            @Override
            public void onBoomButtonClick(int index) {
                index = 1;
                Intent intent;
                intent = new Intent(MainActivity.this,DeliveryRequestHandling.class);//Delivery Request Handling Activity
                startActivity(intent);
            }
        });

        //Booking Management Activity
        builder2.normalImageRes(R.drawable.noice_logo).normalText("Booking Management")
                .normalColorRes(R.color.colorYellowD)
                .normalTextColorRes(R.color.black);
        boomMenuButton.addBuilder(builder2);
        builder2.listener(new OnBMClickListener() {
            @Override
            public void onBoomButtonClick(int index) {
                index = 2;
                Intent intent;
                intent = new Intent(MainActivity.this,HomeCustomer.class);
                startActivity(intent);
            }
        });
//-------------------------------------------------------Bottom App BAR FUNCTION---------------------------------------------
        //Initialize variables and assign them
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.home);

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