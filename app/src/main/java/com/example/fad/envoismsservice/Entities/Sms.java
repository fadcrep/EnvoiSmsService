package com.example.fad.envoismsservice.Entities;

/**
 * Created by Crepin Hugues FADJO (f@dcrepin) on 23/08/2016.
 */
public class Sms {
    int id,user_id;
    String receiver,content;

    public Sms() {
    }

    public Sms(int id, int user_id, String receiver, String content) {
        this.id = id;
        this.user_id = user_id;
        this.receiver = receiver;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
