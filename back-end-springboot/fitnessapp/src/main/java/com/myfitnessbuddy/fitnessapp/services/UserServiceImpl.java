package com.myfitnessbuddy.fitnessapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myfitnessbuddy.fitnessapp.entities.Authority;
import com.myfitnessbuddy.fitnessapp.entities.Exercise;
import com.myfitnessbuddy.fitnessapp.entities.User;
import com.myfitnessbuddy.fitnessapp.repositories.AuthorityRepository;
import com.myfitnessbuddy.fitnessapp.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public User getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user;
    }

    @Override
    public User addUser(User userReq) {
        User user = new User(userReq.getUsername(), userReq.getEmail(), userReq.getPassword(), userReq.getName());
        Authority authority = getAuthority("USER");
        if(authority == null){
            authority = addAuthority(new Authority("USER"));
        }
        user.addAuthority(authority);
        user = userRepository.save(user);
        return user;
    }

    @Override
    public Authority addAuthority(Authority authorityReq) {
        Authority authority = new Authority(authorityReq.getAuthority());
        authority = authorityRepository.save(authority);
        return authority;
    }

    @Override
    public Authority getAuthority(String authority) {
        Authority role = authorityRepository.findAuthorityByAuthority(authority).orElse(null);
        return role;
    }

    @Override
    public User updateUser(User userReq) {
        User user = userRepository.findByUsername(userReq.getUsername());
        if(userReq.getName() != null){
            user.setName(userReq.getName());
        }
        user = userRepository.save(user);
        return user;
    }

    @Override
    public void deleteUserByUsername(String username) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void addAuthorityToUser(User user, Authority authority) {
        user.addAuthority(authority);
        userRepository.save(user);
    }

    @Override
    public void addExerciseToFav(User user, Exercise exercise) {
        user.addExercise(exercise);
        userRepository.save(user);
    }
    
    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getUserByPasswordResetToken(String token) {
        return userRepository.findByPasswordResetToken(token);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


}