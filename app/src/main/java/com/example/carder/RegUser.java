package com.example.carder;

public class RegUser extends User {
    RegUser(){
        super.adminFlag=false;
        super.username="";
        super.password="";
    }
    String getUsername(){
        return super.username;
    }
    String getPassword(){
        return super.password;
    }
}
