package com.example.carder;

public class AdminUser extends User {
    AdminUser(){
        super.adminFlag=true;
        super.username="root";
        super.password="pass123";
    }
    String getUsername(){
        return super.username;
    }
    String getPassword(){
        return super.password;
    }
}
