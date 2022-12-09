package com.myfitnessbuddy.fitnessapp.dto;

public class MessageResponseDto {
    private String message;
    public MessageResponseDto(){}
    public MessageResponseDto(String msg){
        this.message = msg;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}