package com.example.noice_service;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class Navigation_Drawer extends AppCompatActivity {

    //Initialize variables
    TextView textView;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        textView = (TextView) findViewById(R.id.tv_heading);
        String text = "NOICE SERVICE";
        SpannableString ss = new SpannableString(text);
        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        ss.setSpan(boldSpan, 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);

        drawerLayout = findViewById(R.id.drawer_layout);

    }

    public void ClickMenu(View view){
        //Open drawer
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        //Open drawer layout
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view){
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        //Closer drawer layout
        //Check condition
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            //When drawer is open
            //Close drawer
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void ClickProfile(View view){
        //Recreate activity
        recreate();
    }

    public void ClickBookings(View view){
        //Redirect activity to My Bookings
        redirectActivity(this,test.class);
    }

    public void ClickSettings(View view){
        //Redirect activity to settings
        redirectActivity(this,test.class);
    }

    public void ClickChangePassword(View view){
        //Redirect activity to change password
        redirectActivity(this,test.class);
    }

    public void ClickRemoveAccount(View view){
        //Redirect activity to remove account
        redirectActivity(this, test.class);
    }

    public void ClickLogout(View view){
        //Close app
        logout(this);
    }

    public static void logout(Activity activity) {
        //Initialize alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        //Set Title
        builder.setTitle("Logout");

        //Set message
        builder.setMessage("Are you sure you want to logout?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Finish activity
                activity.finishAffinity();

                //Exit app
                System.exit(0);
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        //Show dialog
        builder.show();
    }

    public static void redirectActivity(Activity activity, Class aclass) {
        //Initialize intent
        Intent intent = new Intent(activity, aclass);

        //Set flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        //Start activity
        activity.startActivity(intent);

    }

    protected void onPause(){
        super.onPause();
        
        //Close drawer
        closeDrawer(drawerLayout);
    }

}