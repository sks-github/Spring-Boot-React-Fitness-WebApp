package com.myfitnessbuddy.fitnessapp.services;

import java.util.List;

import com.myfitnessbuddy.fitnessapp.entities.Authority;
import com.myfitnessbuddy.fitnessapp.entities.Exercise;
import com.myfitnessbuddy.fitnessapp.entities.User;

public interface UserService {
    public User getUserByUsername(String username);
    public User addUser(User userReq);
    public Authority addAuthority(Authority authorityReq);
    public Authority getAuthority(String authority);
    public User updateUser(User userReq);
    public void deleteUserByUsername(String username);
    public void addAuthorityToUser(User user, Authority authority);
    public void addExerciseToFav(User user, Exercise exercise);
    public User getUserByEmail(String email);
    public User getUserByPasswordResetToken(String token);
    public List<User> getAllUsers();
}
