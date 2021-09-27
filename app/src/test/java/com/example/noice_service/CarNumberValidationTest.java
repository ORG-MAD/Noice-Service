package com.example.noice_service;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;

public class CarNumberValidationTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    @Test
    public void CarNumberValidateTest1(){

        BookingFrom bookingFrom = new BookingFrom();
        boolean result = bookingFrom.checkCarNo("CAR 1000");
        assertTrue(result);
    }

    @Test
    public void CarNumberValidateTest2(){

        BookingFrom bookingFrom = new BookingFrom();
        boolean result = bookingFrom.checkCarNo("KV 1000");
        assertTrue(result);
    }

    @Test
    public void CarNumberValidateTest3(){

        BookingFrom bookingFrom = new BookingFrom();
        boolean result = bookingFrom.checkCarNo("KV00000000");
        assertFalse(result);
    }
}
