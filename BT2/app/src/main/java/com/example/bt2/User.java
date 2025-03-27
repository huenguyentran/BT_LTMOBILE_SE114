package com.example.bt2;

public class User {
    public String UserName, Password, UserId;

    public User() {}
    public User (String ID, String userName, String password)
    {
        UserId = ID;
        UserName = userName;
        Password = password;
    }
}
