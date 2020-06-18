package com.example.cloudcomputingproject;

public class ModelUser {
    private String uid;
    private String email;

    public ModelUser(String uid, String email) {
        this.setUid(uid);
        this.setEmail(email);
    }

    public ModelUser() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
