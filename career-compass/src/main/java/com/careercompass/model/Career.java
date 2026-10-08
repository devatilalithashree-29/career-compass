package com.careercompass.model;

import java.util.List;

public class Career {
    private String id;
    private String title;
    private String stream;
    private List<String> primaryInterests;
    private List<String> requiredSkills;
    private String description;
    private double averageStartingSalary;

    public Career() {}

    public Career(String id, String title, String stream, List<String> primaryInterests, List<String> requiredSkills, String description, double averageStartingSalary) {
        this.id = id;
        this.title = title;
        this.stream = stream;
        this.primaryInterests = primaryInterests;
        this.requiredSkills = requiredSkills;
        this.description = description;
        this.averageStartingSalary = averageStartingSalary;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getStream() { return stream; }
    public List<String> getPrimaryInterests() { return primaryInterests; }
    public List<String> getRequiredSkills() { return requiredSkills; }
    public String getDescription() { return description; }
    public double getAverageStartingSalary() { return averageStartingSalary; }
}