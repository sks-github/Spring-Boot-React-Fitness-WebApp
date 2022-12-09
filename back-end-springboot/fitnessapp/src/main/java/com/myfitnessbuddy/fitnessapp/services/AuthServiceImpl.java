package com.myfitnessbuddy.fitnessapp.services;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myfitnessbuddy.fitnessapp.dto.NewPasswordDto;
import com.myfitnessbuddy.fitnessapp.entities.User;
import com.myfitnessbuddy.fitnessapp.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String createPasswordResetTokenForUser(User user) {
        String reset_token = UUID.randomUUID().toString();
        user.setPasswordResetToken(reset_token);
        user.setResetTokenExpiresAt(new Date(System.currentTimeMillis() + (10*60*1000)));
        userRepository.save(user);
        return reset_token;
    }

    @Override
    public void resetPassword(User user, NewPasswordDto passwordDto) {
        user.setPassword(passwordEncoder.encode(passwordDto.getPassword()));
        user.setPasswordResetToken(null);
        user.setResetTokenExpiresAt(null);
        user.setPasswordChangedAt(new Date(System.currentTimeMillis()));
        userRepository.save(user);
    }
    
}
