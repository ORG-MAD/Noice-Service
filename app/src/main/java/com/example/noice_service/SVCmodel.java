package com.example.noice_service;

public class SVCmodel
{
  String title,description,price,totalTime,equ;
    SVCmodel()
    {

    }
    public SVCmodel(String title, String description, String price, String equ, String totalTime) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.equ=equ;
        this.totalTime = totalTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getEqu() {
        return equ;
    }

    public void setEqu(String equ) {
        this.equ = equ;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }
}
