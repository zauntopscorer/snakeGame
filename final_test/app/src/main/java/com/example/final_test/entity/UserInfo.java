package com.example.final_test.entity;

import androidx.annotation.NonNull;

public class UserInfo {
    private String userName;
    private String password;
    private String scorer;

    public String getUserName() {
        return userName;
    }
    public String getPassword() {
        return password;
    }
    public String getScorer() {
        return scorer;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setScorer(String scorer) {
        this.scorer = scorer;
    }

    //重写toString方法

    @NonNull
    @Override
    public String toString() {
        return userName+"-"+password+"-"+scorer;
    }
}
