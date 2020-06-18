package com.example.cloudcomputingproject;

public class MessageChat {
    private String message;
    private String reciver;
    private String sender;
    private String time;
    private boolean isSend;

    public MessageChat(String message, String reciver, String sender, String time, boolean isSend) {
        this.setMessage(message);
        this.setReciver(reciver);
        this.setSender(sender);
        this.setTime(time);
        this.setSend(isSend);
    }

    public MessageChat() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReciver() {
        return reciver;
    }

    public void setReciver(String reciver) {
        this.reciver = reciver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isSend() {
        return isSend;
    }

    public void setSend(boolean send) {
        isSend = send;
    }
}
