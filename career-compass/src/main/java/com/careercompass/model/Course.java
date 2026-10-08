package com.careercompass.model;

public class Course {
    private String id;
    private String name;
    private int durationYears;
    private double typicalFees;

    public Course() {}

    public Course(String id, String name, int durationYears, double typicalFees) {
        this.id = id;
        this.name = name;
        this.durationYears = durationYears;
        this.typicalFees = typicalFees;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getDurationYears() { return durationYears; }
    public double getTypicalFees() { return typicalFees; }
}