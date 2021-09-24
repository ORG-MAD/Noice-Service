package com.example.noice_service;

public class ServiceReportModel {
    private String name;
    private String price;
    private int count;

    public ServiceReportModel(String name, String price) {
        this.name = name;
        this.price = price;
        count = 1;
    }

    public void increaseCount(){
        count = count + 1;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }
}
