package com.example.gurpartap.skip_and_buy.Model;

/**
 * Created by OWNER on 10/7/2016.
 */

public class UserAccount {
    @com.google.gson.annotations.SerializedName("id")
    private String id;
    @com.google.gson.annotations.SerializedName("email")
    private String email;
    @com.google.gson.annotations.SerializedName("password")
    private String password;

    public UserAccount() {
    }

    public UserAccount(String id, String email, String password) {
        this.id=id;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
