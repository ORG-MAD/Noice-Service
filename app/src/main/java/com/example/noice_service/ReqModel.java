package com.example.noice_service;


import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class ReqModel implements Serializable {

    @Exclude
    private String key;

    private String customerName;
    private String customerID;
    private String contactNumber;
    private String vehicleName;
    private String location;

    public ReqModel(){};

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
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

    public ReqModel(String customerName, String customerID, String contactNumber, String vehicleName, String location) {
        this.customerName = customerName;
        this.customerID = customerID;
        this.contactNumber = contactNumber;
        this.vehicleName = vehicleName;
        this.location = location;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

//    public ReqModel(String customerName) {
//        this.customerName = customerName;
//    }
//
//    public String getCustomerName() {
//        return customerName;
//    }
//
//    public void setCustomerName(String customerName) {
//        this.customerName = customerName;
//    }
}