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

    //Constructors
    public ReqModel(){};

    public ReqModel(String customerName, String customerEmail, String contactNumber, String vehicleName, String location, String status) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.contactNumber = contactNumber;
        this.vehicleName = vehicleName;
        this.location = location;
        this.status = status;
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
}