package com.example.noice_service;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;

public class CalcReceiptTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    @Test
    public void calculationsTest1(){
        CalculateReciept calculateReciept = new CalculateReciept();
        int result = calculateReciept.calcSum(1500, 2000);
        assertEquals(3500, result);
    }

}
