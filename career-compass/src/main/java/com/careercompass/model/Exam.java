package com.careercompass.model;

public class Exam {
    private String id;
    private String name;
    private String registrationDeadline;
    private double difficultyRating;

    public Exam() {}

    public Exam(String id, String name, String registrationDeadline, double difficultyRating) {
        this.id = id;
        this.name = name;
        this.registrationDeadline = registrationDeadline;
        this.difficultyRating = difficultyRating;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getRegistrationDeadline() { return registrationDeadline; }
    public double getDifficultyRating() { return difficultyRating; }
}