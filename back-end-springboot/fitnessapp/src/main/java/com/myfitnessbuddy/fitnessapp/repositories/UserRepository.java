package com.myfitnessbuddy.fitnessapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myfitnessbuddy.fitnessapp.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);

    public User findByEmail(String email);

    public User findByPasswordResetToken(String token);
}
