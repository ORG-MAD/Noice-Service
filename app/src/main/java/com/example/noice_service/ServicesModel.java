package com.example.noice_service;

public class ServicesModel {
    String s_title;
    String s_price;
    String totalTime;
    String tv_day;
    String s_description;

    public ServicesModel(String s_title, String s_price, String tv_day, String s_description) {
        this.s_title = s_title;
        this.s_price = s_price;
        this.tv_day = tv_day;
        this.s_description = s_description;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public String getTotalTime() { return totalTime; }

    public String getS_title() {
        return s_title;
    }

    public String getS_price() {
        return s_price;
    }

    public String getTv_day() {
        return tv_day;
    }

    public String getS_description() {
        return s_description;
    }
}
