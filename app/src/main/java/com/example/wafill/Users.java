package com.example.wafill;

public class Users {
    public String userId,username, emailAddress,address,number,password;

    public Users(){

    }

    public Users(String userId, String username, String emailAddress, String address, String number, String password) {
        this.userId = userId;
        this.username = username;
        this.emailAddress = emailAddress;
        this.address = address;
        this.number = number;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getAddress() {
        return address;
    }

    public String getNumber() {
        return number;
    }

    public String getPassword() {
        return password;
    }
}
