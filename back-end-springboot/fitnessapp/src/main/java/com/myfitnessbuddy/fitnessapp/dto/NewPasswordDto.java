package com.myfitnessbuddy.fitnessapp.dto;

public class NewPasswordDto {
    private String password;
    
    private String confirmPassword;
    
    public NewPasswordDto(){}
    public NewPasswordDto(String p, String cp) {
        this.password = p;
        this.confirmPassword = cp;
    }
    
    public String getConfirmPassword() {
        return confirmPassword;
    }
    
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}
