package com.myfitnessbuddy.fitnessapp.services;

import com.myfitnessbuddy.fitnessapp.dto.NewPasswordDto;
import com.myfitnessbuddy.fitnessapp.entities.User;

public interface AuthService {
    public String createPasswordResetTokenForUser(User user);

    public void resetPassword(User user, NewPasswordDto passwordDto);
}
