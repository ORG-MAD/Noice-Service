package com.example.noice_service;



import java.io.Serializable;

public class ReqModelCustomer implements Serializable {
    private String requestTitle;

    public ReqModelCustomer(String requestTitle) {
        this.requestTitle = requestTitle;
    }

    public String getRequestTitle() {
        return requestTitle;
    }

    public void setRequestTitle(String requestTitle) {
        this.requestTitle = requestTitle;
    }
}