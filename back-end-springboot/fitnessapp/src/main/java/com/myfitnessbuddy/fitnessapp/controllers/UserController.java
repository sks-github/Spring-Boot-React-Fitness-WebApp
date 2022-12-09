package com.myfitnessbuddy.fitnessapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myfitnessbuddy.fitnessapp.dto.AddToFavDto;
import com.myfitnessbuddy.fitnessapp.dto.AuthorityDto;
import com.myfitnessbuddy.fitnessapp.dto.MessageResponseDto;
import com.myfitnessbuddy.fitnessapp.entities.Authority;
import com.myfitnessbuddy.fitnessapp.entities.Exercise;
import com.myfitnessbuddy.fitnessapp.entities.User;
import com.myfitnessbuddy.fitnessapp.repositories.ExerciseRepository;
import com.myfitnessbuddy.fitnessapp.services.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ExerciseRepository exerciseRepository;

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<?> getUser(@PathVariable String username){
        User user = userService.getUserByUsername(username);
        if(user == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponseDto("user not found"));
        }else{
            User userRes = new User(username, user.getEmail(), null, user.getName());
            return ResponseEntity.ok(userRes);
        }
    }

    @GetMapping("/users/{username}/fav")
    public ResponseEntity<?> getFavExercises(@PathVariable String username){
        User user = userService.getUserByUsername(username);
        System.out.println("size" + user.getFavExercises().size());
        user.getFavExercises().forEach(ex -> {
            System.out.println(ex.getName());
        });
        return ResponseEntity.ok(user.getFavExercises());
    }

    @PostMapping("/exercise/addToFav")
    public ResponseEntity<?> addToFavExercises(@RequestBody AddToFavDto addToFavDto){
        User user = userService.getUserByUsername(addToFavDto.getUsername());

        Exercise exercise = exerciseRepository.findByApiExerciseId(addToFavDto.getExerciseId()).orElse(null);
        if(exercise == null){
            exercise = new Exercise(addToFavDto.getExerciseId(), addToFavDto.getExerciseName());
        }

        exercise = exerciseRepository.save(exercise);
        userService.addExerciseToFav(user, exercise);
        return ResponseEntity.ok(new MessageResponseDto("added to favourites"));
    }

    @PostMapping("/updateAuthority/{username}")
    public ResponseEntity<?> updateUserAuthority(@PathVariable String username, @RequestBody AuthorityDto authorityDto){
        User user = userService.getUserByUsername(username);
        Authority authority = userService.getAuthority(authorityDto.getAuthority());
        if(authority == null){
            authority = userService.addAuthority(new Authority(authorityDto.getAuthority()));
        }
        userService.addAuthorityToUser(user, authority);
        return ResponseEntity.ok().build();
    }
}
