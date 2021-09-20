package com.example.noice_service;

public class MyBookings {
    private String booking_id;
    private String booking_name;
    private String details;
    private String booking_time;
    private String car_no;
    private String phone_no;
    private String time_slot;

    public MyBookings() {
    }

    public String getTime_slot() {
        return time_slot;
    }

    public void setTime_slot(String time_slot) {
        this.time_slot = time_slot;
    }

    public MyBookings(String booking_id, String booking_name, String details, String booking_time, String car_no, String phone_no, String time_slot) {
        this.booking_id = booking_id;
        this.booking_name = booking_name;
        this.details = details;
        this.booking_time = booking_time;
        this.car_no = car_no;
        this.phone_no = phone_no;
        this.time_slot = time_slot;
    }

    public String getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(String booking_id) {
        this.booking_id = booking_id;
    }

    public String getBooking_name() {
        return booking_name;
    }

    public void setBooking_name(String booking_name) {
        this.booking_name = booking_name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getBooking_time() {
        return booking_time;
    }

    public void setBooking_time(String booking_time) {
        this.booking_time = booking_time;
    }

    public String getCar_no() {
        return car_no;
    }

    public void setCar_no(String car_no) {
        this.car_no = car_no;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }
}
