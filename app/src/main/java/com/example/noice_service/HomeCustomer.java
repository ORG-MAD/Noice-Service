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

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

public class HomeCustomer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_customer);

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

        builder.normalImageRes(R.drawable.noice_logo).normalText("My Profile")
                .normalColorRes(R.color.colorYellowD)
                .normalTextColorRes(R.color.black)
                .textSize(15);
        boomMenuButton.addBuilder(builder);

        //My Profile Activity
        builder.listener(new OnBMClickListener() {
            @Override
            public void onBoomButtonClick(int index) {
                index = 0;
                Intent intent;
                intent = new Intent(HomeCustomer.this, MyAccount.class);//My Profile Activity
                startActivity(intent);
            }
        });

        //Delivery Request Activity
        builder1.normalImageRes(R.drawable.noice_logo).normalText("Delivery Requests")
                .normalColorRes(R.color.colorYellowD)
                .normalTextColorRes(R.color.black);
        boomMenuButton.addBuilder(builder1);
        builder1.listener(new OnBMClickListener() {
            @Override
            public void onBoomButtonClick(int index) {
                index = 1;
                Intent intent;
                intent = new Intent(HomeCustomer.this,NewDeliverRequest.class);//Delivery Request Activity
                startActivity(intent);
            }
        });

        //Place Booking Activity
        builder2.normalImageRes(R.drawable.noice_logo).normalText("Place Booking")
                .normalColorRes(R.color.colorYellowD)
                .normalTextColorRes(R.color.black);
        boomMenuButton.addBuilder(builder2);
        builder2.listener(new OnBMClickListener() {
            @Override
            public void onBoomButtonClick(int index) {
                index = 2;
                Intent intent;
                intent = new Intent(HomeCustomer.this,services_list.class);//Place Booking Activity
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