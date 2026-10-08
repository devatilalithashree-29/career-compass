package com.careercompass.model;

public class College {
    private String id;
    private String name;
    private String city;
    private double annualFee;
    private double placementRating;
    private double cutoffPercentage;
    private double distanceKm;

    public College() {}

    public College(String id, String name, String city, double annualFee, double placementRating, double cutoffPercentage, double distanceKm) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.annualFee = annualFee;
        this.placementRating = placementRating;
        this.cutoffPercentage = cutoffPercentage;
        this.distanceKm = distanceKm;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getCity() { return city; }
    public double getAnnualFee() { return annualFee; }
    public double getPlacementRating() { return placementRating; }
    public double getCutoffPercentage() { return cutoffPercentage; }
    public double getDistanceKm() { return distanceKm; }
}