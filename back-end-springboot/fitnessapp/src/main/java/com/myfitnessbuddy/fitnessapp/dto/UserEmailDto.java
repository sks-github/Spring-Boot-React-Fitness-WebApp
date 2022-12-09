package com.myfitnessbuddy.fitnessapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserEmailDto {
    private String email;

    //@JsonProperty("fieldname") can be avoided if no-args constructor is present
    public UserEmailDto(){}
    public UserEmailDto(@JsonProperty("email") String mail){
        this.email = mail;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }  
     
}