package com.example.noice_service;


import java.io.Serializable;

public class ReqModel implements Serializable {
    private String customerName;

    public ReqModel(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}