package com.example.noice_service;

public class User {
    private String name, password, email, phone, DOB, Country, regDate;

    public User() {
    }

    public User(String name, String password, String email, String phone, String DOB, String Country, String regDate) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.DOB = DOB;
        this.Country = Country;
        this.regDate = regDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getCountry() { return Country; }

    public void setCountry(String country) {
        Country = country;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }
}
