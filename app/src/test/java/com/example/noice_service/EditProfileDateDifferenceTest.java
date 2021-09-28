package com.example.noice_service;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;

public class EditProfileDateDifferenceTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    @Test
    public void checkCalcDatesDifference1(){
        jEditProfile_Customer jEditProfile_customer = new jEditProfile_Customer();
        long result = jEditProfile_customer.calcDifferenceDates(86400000);
        assertEquals(1,result);
    }
    @Test
    public void checkCalcDatesDifference2(){
        jEditProfile_Customer jEditProfile_customer = new jEditProfile_Customer();
        long result = jEditProfile_customer.calcDifferenceDates(172800000);
        assertEquals(2,result);
    }
}

