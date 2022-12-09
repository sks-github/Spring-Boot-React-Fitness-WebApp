package com.myfitnessbuddy.fitnessapp.controllers;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myfitnessbuddy.fitnessapp.dto.LoginCredentialsDto;
import com.myfitnessbuddy.fitnessapp.dto.MessageResponseDto;
import com.myfitnessbuddy.fitnessapp.dto.NewPasswordDto;
import com.myfitnessbuddy.fitnessapp.dto.UserEmailDto;
import com.myfitnessbuddy.fitnessapp.dto.UserSignUpDto;
import com.myfitnessbuddy.fitnessapp.entities.User;
import com.myfitnessbuddy.fitnessapp.services.AuthService;
import com.myfitnessbuddy.fitnessapp.services.EmailSenderService;
import com.myfitnessbuddy.fitnessapp.services.UserService;
import com.myfitnessbuddy.fitnessapp.utils.JwtUtils;

@CrossOrigin(origins = "http://localhost:3000", exposedHeaders = {"authorization", "username"})
@RestController
public class AuthController {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;
    @Autowired
    private EmailSenderService mailService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public ResponseEntity<?> logInUser(@RequestBody LoginCredentialsDto loginReq, HttpServletResponse httpServletResponse){
        try {
            Authentication authenticate = authenticationManager
                .authenticate(
                    new UsernamePasswordAuthenticationToken(
                        loginReq.getUsername(), loginReq.getPassword()
                    )
                );

            User user = (User) authenticate.getPrincipal();
            user = userService.getUserByUsername(user.getUsername());
            // System.out.println("user: " + user.getUsername() + " " + user.getEmail());
            Map<String, String> map = new HashMap<>();
            map.put("username", user.getUsername());
            map.put("name", user.getName());

            return ResponseEntity.ok()
                .header(
                    HttpHeaders.AUTHORIZATION,
                    jwtUtils.generateJwt(user)
                )
                .body(map);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponseDto("incorrect username or password"));
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUpUser(@RequestBody UserSignUpDto userSignUpDto){
        User userPrev = userService.getUserByUsername(userSignUpDto.getUsername());
        if(userPrev != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponseDto("username unavailable"));
        }
        userPrev = userService.getUserByEmail(userSignUpDto.getEmail());
        if(userPrev != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponseDto("email already linked to existing user"));
        }
        User user = userService.addUser(new User(userSignUpDto.getUsername(), userSignUpDto.getEmail(), passwordEncoder.encode(userSignUpDto.getPassword()), userSignUpDto.getName()));

        String jwt = jwtUtils.generateJwt(user);
        return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.AUTHORIZATION, jwt).header("username", user.getUsername()).build();
    }

    @PostMapping("/user/forgottenPassword")
    public ResponseEntity<?> forgottenPassword(@RequestBody UserEmailDto userEmail){
        String email = userEmail.getEmail();
        User user = userService.getUserByEmail(email);
        if(user == null) {
            return ResponseEntity.badRequest().body(new MessageResponseDto("No user exists with this email"));            
        }else{
            String reset_token = authService.createPasswordResetTokenForUser(user);
            String mailBody = "this password reset token is valid only for 10 minutes.\nClick on the following link to reset your password: " + reset_token;
            mailService.sendSimpleEmail(email, "sumansahu417@gmail.com", "Password Reset Token", mailBody);
            return ResponseEntity.ok().body(new MessageResponseDto("password reset token has been successfully sent to " + email));
        }
    }

    @PostMapping(value="/user/resetPassword/{token}")
    public ResponseEntity<?> resetPassword(@PathVariable String token, @RequestBody NewPasswordDto passwordDto) {
        User user = userService.getUserByPasswordResetToken(token);
        if(user == null){
            return ResponseEntity.badRequest().body(new MessageResponseDto("invalid reset token"));
        }
        Date currentDate = new Date(System.currentTimeMillis());
        if(currentDate.before(user.getResetTokenExpiresAt())){
            authService.resetPassword(user, passwordDto);
            //redirect to login
            return ResponseEntity.ok().body(new MessageResponseDto("password successfully changed"));
        }else{
            //redirect to login
            return ResponseEntity.badRequest().body(new MessageResponseDto("reset token has expired"));
        }
    }    

}
