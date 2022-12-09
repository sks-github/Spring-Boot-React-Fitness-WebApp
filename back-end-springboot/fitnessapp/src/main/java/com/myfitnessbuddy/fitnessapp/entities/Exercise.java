package com.myfitnessbuddy.fitnessapp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "exercises")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exercise_id")
    private Long id;

    @Column(name = "apiexercise_id")
    private Long apiExerciseId;
    
    @Column(name = "name")
    private String name;

    public Exercise(){}
    public Exercise(Long apiExerciseId, String name){
        this.apiExerciseId = apiExerciseId;
        this.name = name;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((apiExerciseId == null) ? 0 : apiExerciseId.hashCode());
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
        Exercise other = (Exercise) obj;
        if (apiExerciseId == null) {
            if (other.apiExerciseId != null)
                return false;
        } else if (!apiExerciseId.equals(other.apiExerciseId))
            return false;
        return true;
    }
    public Long getApiExerciseId() {
        return apiExerciseId;
    }
    public void setApiExerciseId(Long apiExerciseId) {
        this.apiExerciseId = apiExerciseId;
    }
    @Override
    public String toString() {
        return "Exercise [apiExerciseId=" + apiExerciseId + ", id=" + id + ", name=" + name + "]";
    }

    
}
