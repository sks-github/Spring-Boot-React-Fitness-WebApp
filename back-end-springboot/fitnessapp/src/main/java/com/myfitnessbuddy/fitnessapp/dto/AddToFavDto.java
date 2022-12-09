package com.myfitnessbuddy.fitnessapp.dto;

public class AddToFavDto {
    private Long exerciseId;
    private String exerciseName;
    private String username;

    public AddToFavDto(){}
    public AddToFavDto(Long exerciseId, String username){
        this.exerciseId = exerciseId;
        this.username = username;
    }
    public Long getExerciseId() {
        return exerciseId;
    }
    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getExerciseName() {
        return exerciseName;
    }
    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }
    @Override
    public String toString() {
        return "AddToFavDto [exerciseId=" + exerciseId + ", exerciseName=" + exerciseName + ", username=" + username
                + "]";
    }

    
}
