package com.example.noice_service;

import static org.junit.Assert.assertEquals;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Rule;
import org.junit.Test;

public class ResCalculateTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    @Test
    public void calculationsTest1(){
        ResCalculate resCalculate = new ResCalculate();
        int result = resCalculate.calcSumValue(1000, 2000);
        assertEquals(3000, result);
    }

    @Test
    public void calculationsTest2(){
        ResCalculate resCalculate = new ResCalculate();
        int result = resCalculate.calcSumValue(0, 1000);
        assertEquals(1000, result);
    }
    @Test
    public void calculationsTest3(){
        ResCalculate resCalculate = new ResCalculate();
        int result = resCalculate.calcSumValue(1000, 0);
        assertEquals(1000, result);
    }
}
