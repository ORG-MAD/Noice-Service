package com.example.noice_service;

public class SVCmodel
{
  String title,description,price,imgurl,equ;
    SVCmodel()
    {

    }
    public SVCmodel(String title, String description, String price, String equ, String imgurl) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.equ=equ;
        this.imgurl = imgurl;
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
//    public String getTv_day() {
//        return tv_day;
//    }
//
//    public void setTv_day(String tv_day) {
//        this.tv_day = tv_day;
//    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    //    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getCourse() {
//        return course;
//    }
//
//    public void setCourse(String course) {
//        this.course = course;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPurl() {
//        return purl;
//    }
//
//    public void setPurl(String purl) {
//        this.purl = purl;
//    }
}
