package com.careercompass.model;

import java.util.List;

public class StudentProfile {
    private String stream;
    private List<String> interests;
    private List<String> skills;
    private double marksPercentage;
    private double budgetLimit;
    private String preferredLocation;

    public StudentProfile() {}

    public StudentProfile(String stream, List<String> interests, List<String> skills, double marksPercentage, double budgetLimit, String preferredLocation) {
        this.stream = stream;
        this.interests = interests;
        this.skills = skills;
        this.marksPercentage = marksPercentage;
        this.budgetLimit = budgetLimit;
        this.preferredLocation = preferredLocation;
    }

    public String getStream() { return stream; }
    public void setStream(String stream) { this.stream = stream; }

    public List<String> getInterests() { return interests; }
    public void setInterests(List<String> interests) { this.interests = interests; }

    public List<String> getSkills() { return skills; }
    public void setSkills(List<String> skills) { this.skills = skills; }

    public double getMarksPercentage() { return marksPercentage; }
    public void setMarksPercentage(double marksPercentage) { this.marksPercentage = marksPercentage; }

    public double getBudgetLimit() { return budgetLimit; }
    public void setBudgetLimit(double budgetLimit) { this.budgetLimit = budgetLimit; }

    public String getPreferredLocation() { return preferredLocation; }
    public void setPreferredLocation(String preferredLocation) { this.preferredLocation = preferredLocation; }
}