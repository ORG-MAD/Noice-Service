package com.example.noice_service;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class ViewBookings_Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bookings_admin);

        TabLayout tabLayout = findViewById(R.id.tabview);
        ViewPager viewPager = findViewById(R.id.pager);

        tabLayout.addTab(tabLayout.newTab().setText("BOOKING 1"));
        tabLayout.addTab(tabLayout.newTab().setText("BOOKING 2"));
        tabLayout.addTab(tabLayout.newTab().setText("BOOKING 3"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        PagerAdapter pagerAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }
}




