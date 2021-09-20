package com.example.noice_service;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class test extends AppCompatActivity {

    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        drawerLayout = findViewById(R.id.drawer_layout);
    }

    public void ClickMenu(View view){
        Navigation_Drawer.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view){
        Navigation_Drawer.closeDrawer(drawerLayout);
    }

    public void ClickProfile(View view){
        //Redirect activity to home
        Navigation_Drawer.redirectActivity(this,Navigation_Drawer.class);
    }

    public void ClickBookings(View view){
        recreate();
    }

    public void ClickSettings(View view){
        Navigation_Drawer.redirectActivity(this,test.class);
    }

    public void ClickLogout(View view){
        Navigation_Drawer.logout(this);
    }

    protected void onPause(){
        super.onPause();
        //Close drawer
        Navigation_Drawer.closeDrawer(drawerLayout);
    }
}