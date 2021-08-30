package com.example.noice_service;

public class PaymentModel {
    String name;
    String totalIncome;

    public PaymentModel(String name, String totalIncome){
        this.name=name;
        this.totalIncome=totalIncome;

    }

    public String getName() {
        return name;
    }

    public String getTotalIncome() {
        return totalIncome;
    }
}
