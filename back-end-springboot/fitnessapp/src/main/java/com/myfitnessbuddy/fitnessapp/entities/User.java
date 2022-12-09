package com.myfitnessbuddy.fitnessapp.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="users")
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    
    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "password_reset_token")
    private String passwordResetToken;

    @Column(name = "password_changed_at")
    private Date passwordChangedAt;

    @Column(name = "reset_token_expires_at")
    private Date resetTokenExpiresAt;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "authority_id"))
    List<Authority> authorities = new ArrayList<>();  

    @ManyToMany()
    @JoinTable(name = "user_fav_exercise",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "exercise_id"))
    Set<Exercise> favExercises = new HashSet<>();

    //constructors
    public User(){}
    public User(String username, String email, String password, String name){
        this.username = username;
        this.email = email;
        this.password =  password;
        this.name = name;
        this.passwordResetToken = null;
        this.passwordChangedAt = null;
        this.resetTokenExpiresAt = null;

    }
    //helpers
    public void addExercise(Exercise exercise){
        this.favExercises.add(exercise);
    }
    public void addAuthority(Authority authority){
        this.authorities.add(authority);
    }

    //getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Override
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPasswordResetToken() {
        return passwordResetToken;
    }
    public void setPasswordResetToken(String passwordResetToken) {
        this.passwordResetToken = passwordResetToken;
    }
    public Date getPasswordChangedAt() {
        return passwordChangedAt;
    }
    public void setPasswordChangedAt(Date passwordChangedAt) {
        this.passwordChangedAt = passwordChangedAt;
    }
    public Date getResetTokenExpiresAt() {
        return resetTokenExpiresAt;
    }
    public void setResetTokenExpiresAt(Date resetTokenExpiresAt) {
        this.resetTokenExpiresAt = resetTokenExpiresAt;
    }
    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }
    @Override
    public List<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    public Set<Exercise> getFavExercises() {
        return favExercises;
    }
    public void setFavExercises(Set<Exercise> favExercises) {
        this.favExercises = favExercises;
    }
}
