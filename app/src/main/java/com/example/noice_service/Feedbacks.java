package com.example.noice_service;

public class Feedbacks {
    String type, subject, Message;

    public Feedbacks() {
    }

    public Feedbacks(String type, String subject, String message) {
        this.type = type;
        this.subject = subject;
        Message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
