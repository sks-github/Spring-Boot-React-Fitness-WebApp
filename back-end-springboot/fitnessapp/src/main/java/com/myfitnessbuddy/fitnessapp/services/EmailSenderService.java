package com.myfitnessbuddy.fitnessapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(String toEmail, String from, String subject, String body) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setSubject(subject);
        mailMessage.setTo(toEmail);
        mailMessage.setFrom(from);
        mailMessage.setText(body);
        
        mailSender.send(mailMessage);
    }
}
