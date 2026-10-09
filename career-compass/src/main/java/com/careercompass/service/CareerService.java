package com.careercompass.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.careercompass.ds.CareerPathwayGraph;
import com.careercompass.ds.CollegeAVLTree;
import com.careercompass.ds.RecommendationEngine;
import com.careercompass.ds.RecommendationEngine.RankedCareer;
import com.careercompass.model.Career;
import com.careercompass.model.College;
import com.careercompass.model.Course;
import com.careercompass.model.Exam;
import com.careercompass.model.StudentProfile;

@Service
public class CareerService {

    private final List<Career> careers = new ArrayList<>();
    private final List<College> colleges = new ArrayList<>();
    private final List<Course> courses = new ArrayList<>();
    private final List<Exam> exams = new ArrayList<>();

    private final CareerPathwayGraph pathwayGraph = new CareerPathwayGraph();
    private final CollegeAVLTree collegeAVLTree = new CollegeAVLTree();
    private final RecommendationEngine recommendationEngine = new RecommendationEngine();

    public CareerService() {
        initData();
    }

    private void initData() {
        // Career: (id, title, stream, skills, prerequisites, description, avgSalary)
        Career c1 = new Career("c1", "Software Engineer", "MPC",
                Arrays.asList("Coding", "Math"), Arrays.asList("Math", "CS"),
                "Designing and developing software systems.", 800000.0);
        Career c2 = new Career("c2", "AI & Data Scientist", "MPC",
                Arrays.asList("Python", "Math"), Arrays.asList("Math", "Statistics"),
                "Building predictive and machine learning models.", 950000.0);
        Career c3 = new Career("c3", "Medical Doctor", "BiPC",
                Arrays.asList("Biology", "Care"), Arrays.asList("Biology", "Chemistry"),
                "Diagnosing and treating patient health conditions.", 1000000.0);
        Career c4 = new Career("c4", "Chartered Accountant", "MEC",
                Arrays.asList("Finance", "Tax"), Arrays.asList("Math", "Commerce"),
                "Auditing, tax planning, and corporate financial advisory.", 850000.0);
        Career c5 = new Career("c5", "Corporate Lawyer", "CEC",
                Arrays.asList("Law", "Debate"), Arrays.asList("Civics", "Economics"),
                "Corporate contracts and business litigation.", 750000.0);

        careers.addAll(Arrays.asList(c1, c2, c3, c4, c5));

        // College: (id, name, location, annualFee, cutOffRank, placementRate, rating)
        College col1 = new College("col1", "SVECW", "Bhimavaram", 110000.0, 15000.0, 92.0, 4.5);
        College col2 = new College("col2", "IIT Hyderabad", "Sangareddy", 225000.0, 2500.0, 98.0, 4.9);
        College col3 = new College("col3", "NIT Warangal", "Warangal", 145000.0, 5000.0, 95.0, 4.8);
        College col4 = new College("col4", "AIIMS Mangalagiri", "Mangalagiri", 15000.0, 1200.0, 99.0, 4.9);

        colleges.addAll(Arrays.asList(col1, col2, col3, col4));

        for (College col : colleges) {
            collegeAVLTree.insert(col);
        }

        // Course: (id, name, durationYears as double, stream)
       courses.add(new Course("crs1", "B.Tech Computer Science", 4, 120000.0));
        courses.add(new Course("crs2", "MBBS", 5, 250000.0));

        // Exam: (id, name, registrationDeadline, difficultyRating)
        exams.add(new Exam("ex1", "AP EAPCET", "2026-04-15", 4.2));
        exams.add(new Exam("ex2", "JEE Main", "2026-03-30", 4.8));
        exams.add(new Exam("ex3", "NEET UG", "2026-04-10", 4.9));
    }

    public List<Career> getAllCareers() {
        return careers;
    }

    public List<College> getAllColleges() {
        return colleges;
    }

    public List<Exam> getAllExams() {
        return exams;
    }

    public List<Course> getAllCourses() {
        return courses;
    }

    public List<RankedCareer> getRecommendations(StudentProfile profile) {
        return recommendationEngine.recommendCareers(profile, new ArrayList<>(careers));
    }

    public Map<String, Object> getPathwayForCareer(String careerId) {
        Career found = careers.stream()
                .filter(c -> c.getId().equalsIgnoreCase(careerId))
                .findFirst()
                .orElse(careers.isEmpty() ? null : careers.get(0));

        Map<String, Object> res = new HashMap<>();
        res.put("career", found);
        res.put("courses", courses);
        res.put("exams", exams);
        res.put("colleges", colleges);
        return res;
    }

    public List<College> getCollegesWithinBudget(double maxFee) {
        List<College> result = new ArrayList<>();
        for (College col : colleges) {
            if (col.getAnnualFee() <= maxFee) {
                result.add(col);
            }
        }
        return result;
    }
}