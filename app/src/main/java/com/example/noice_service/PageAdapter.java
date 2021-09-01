package com.example.noice_service;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PageAdapter extends FragmentStatePagerAdapter {

    int mTabs;

    public PageAdapter(@NonNull FragmentManager fm, int Tabs) {
        super(fm);
        this.mTabs = Tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                BookingFragment1 Tab1 = new BookingFragment1();
                return Tab1;

            case 1:
                BookingFragment2 Tab2 = new BookingFragment2();
                return Tab2;

            case 2:
                BookingFragment3 Tab3 = new BookingFragment3();
                return Tab3;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mTabs;
    }
}
