package com.myfitnessbuddy.fitnessapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myfitnessbuddy.fitnessapp.entities.Exercise;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long>{
    public Optional<Exercise> findByApiExerciseId(Long exerciseId);
}
