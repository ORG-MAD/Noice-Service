package com.example.noice_service;


import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class ReqModel implements Serializable {

    @Exclude
    private String key;
    private String customerName;
    private String customerEmail;
    private String contactNumber;
    private String vehicleName;
    private String location;
    private String status;
    private String s_price;
    private String booking_ID;

    //Constructors
    public ReqModel(){};

    public ReqModel(String customerName, String customerEmail, String contactNumber, String vehicleName, String location, String status, String s_price, String booking_ID) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.contactNumber = contactNumber;
        this.vehicleName = vehicleName;
        this.location = location;
        this.status = status;
        this.s_price = s_price;
        this.booking_ID = booking_ID;
    }

    //Getters and Setters for the private variables
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getS_price() { return s_price; }

    public void setS_price(String s_price) { this.s_price = s_price; }

    public String getBooking_ID() { return booking_ID; }

    public void setBooking_ID(String booking_ID) { this.booking_ID = booking_ID; }
}