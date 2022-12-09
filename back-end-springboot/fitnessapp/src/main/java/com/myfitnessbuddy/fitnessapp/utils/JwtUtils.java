package com.myfitnessbuddy.fitnessapp.utils;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import com.myfitnessbuddy.fitnessapp.entities.Authority;
import com.myfitnessbuddy.fitnessapp.entities.User;
import com.myfitnessbuddy.fitnessapp.services.UserService;

@Component
public class JwtUtils implements Serializable{
    private static final long serialVersionUID = 1L;
    public static final long JWT_EXPIRY = 24*60*60*1000;//in millisec

    @Autowired
    private UserService userService;

    @Value("${jwt_secret}")
    private String jwt_secret;

    public String generateJwt(User user){
        String username = user.getUsername();
         Algorithm algorithm = Algorithm.HMAC256(jwt_secret);
         String jwt = JWT.create()
                            .withSubject(username)
                            .withExpiresAt(new Date(System.currentTimeMillis() + JWT_EXPIRY))
                            .withClaim("authorities", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                            .sign(algorithm);
        return jwt;
    }

    public User decodeJwt(String token){
        Algorithm algorithm = Algorithm.HMAC256(jwt_secret);
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        String username = decodedJWT.getSubject();
        String[] roles = decodedJWT.getClaim("authorities").asArray(String.class);
        List<Authority> authorities = new ArrayList<>();
        Arrays.stream(roles).forEach(role -> {
            authorities.add(new Authority(role));
        });
        User user = new User();
        user.setUsername(username);
        user.setAuthorities(authorities);
        return user;
    }

    public DecodedJWT getDecodedJwt(String token){
        Algorithm algorithm = Algorithm.HMAC256(jwt_secret);
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        return decodedJWT;
    }

    public Boolean isJwtValid(String token){
        DecodedJWT decodedJWT = getDecodedJwt(token);
        User user = userService.getUserByUsername(decodedJWT.getSubject());
        if(user == null) return false;
        Date currentDate = new Date(System.currentTimeMillis());
        if(currentDate.before(decodedJWT.getExpiresAt()) == false) return false;
        return true;
    }

    public String getUsernameFromToken(String token){
        DecodedJWT decodedJWT = getDecodedJwt(token);
        return decodedJWT.getSubject();
    }
}
