package com.example.noice_service;

public class MyBookings {

    private String booking_id;
    private String booking_name;
    private String booking_time;
    private String booking_status;
    private String car_no;
    private String details;
    private String phone_no;
    private String s_price;
    private String time_slot;
    private String tv_day;

    public MyBookings(String booking_id, String booking_name, String booking_status, String booking_time, String car_no, String details, String phone_no, String s_price, String time_slot, String tv_day) {
        this.booking_id = booking_id;
        this.booking_name = booking_name;
        this.booking_time = booking_time;
        this.booking_status = booking_status;
        this.car_no = car_no;
        this.details = details;
        this.phone_no = phone_no;
        this.s_price = s_price;
        this.time_slot = time_slot;
        this.tv_day = tv_day;
    }

    public String getBooking_id() {
        return booking_id;
    }

    public String getBooking_name() {
        return booking_name;
    }

    public String getBooking_status() {
        return booking_status;
    }

    public String getBooking_time() {
        return booking_time;
    }

    public String getCar_no() {
        return car_no;
    }

    public String getDetails() {
        return details;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public String getS_price() {
        return s_price;
    }

    public String getTime_slot() {
        return time_slot;
    }

    public String getTv_day() {
        return tv_day;
    }
}