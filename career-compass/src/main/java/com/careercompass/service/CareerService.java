package com.careercompass.service;

import com.careercompass.ds.CareerPathwayGraph;
import com.careercompass.ds.CollegeAVLTree;
import com.careercompass.ds.RecommendationEngine;
import com.careercompass.model.Career;
import com.careercompass.model.College;
import com.careercompass.model.Course;
import com.careercompass.model.Exam;
import com.careercompass.model.StudentProfile;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CareerService {

    private final List<Career> careers = new ArrayList<>();
    private final List<College> colleges = new ArrayList<>();
    private final List<Course> courses = new ArrayList<>();
    private final List<Exam> exams = new ArrayList<>();

    private final CareerPathwayGraph pathwayGraph = new CareerPathwayGraph();
    private final CollegeAVLTree collegeAVLTree = new CollegeAVLTree();
    private final RecommendationEngine recommendationEngine = new RecommendationEngine();

    public static class RankedCareer {
        private final Career career;
        private final double score;

        public RankedCareer(Career career, double score) {
            this.career = career;
            this.score = score;
        }

        public Career getCareer() {
            return career;
        }

        public double getScore() {
            return score;
        }
    }

    public CareerService() {
        initData();
    }

    private void initData() {
        // Sample Careers
        Career c1 = new Career("c1", "Software Engineer", "MPC", Arrays.asList("Coding", "Math", "Logic"), 800000.0, "Designing and developing scalable backend architectures and web systems.");
        Career c2 = new Career("c2", "AI & Data Scientist", "MPC", Arrays.asList("Math", "Algorithms", "ML"), 950000.0, "Building neural models, predictive systems, and large language solutions.");
        Career c3 = new Career("c3", "Medical Doctor (MBBS)", "BiPC", Arrays.asList("Biology", "Patient Care", "Healthcare"), 1000000.0, "Diagnosing clinical conditions, patient treatment, and medical therapy.");
        Career c4 = new Career("c4", "Chartered Accountant", "MEC", Arrays.asList("Finance", "Audit", "Tax"), 850000.0, "Corporate taxation, statutory audits, and capital risk management.");
        Career c5 = new Career("c5", "Corporate Lawyer", "CEC", Arrays.asList("Law", "Policy", "Debate"), 750000.0, "Corporate transactions, litigation, regulatory affairs, and rights protection.");

        careers.addAll(Arrays.asList(c1, c2, c3, c4, c5));

        // Sample Colleges
        College col1 = new College("col1", "Shri Vishnu Engineering College for Women (SVECW)", "Bhimavaram", 110000.0, "Autonomous (JNTUK)");
        College col2 = new College("col2", "IIT Hyderabad", "Sangareddy", 225000.0, "Institute of National Importance");
        College col3 = new College("col3", "NIT Warangal", "Warangal", 145000.0, "National Institute of Tech");
        College col4 = new College("col4", "AIIMS Mangalagiri", "Mangalagiri", 15000.0, "National Medical Institute");

        colleges.addAll(Arrays.asList(col1, col2, col3, col4));

        for (College col : colleges) {
            collegeAVLTree.insert(col);
        }

        // Sample Courses & Exams
        courses.add(new Course("crs1", "B.Tech Computer Science", "4 Years", "MPC"));
        courses.add(new Course("crs2", "MBBS", "5.5 Years", "BiPC"));
        exams.add(new Exam("ex1", "AP EAPCET", "Engineering & Agriculture"));
        exams.add(new Exam("ex2", "JEE Main", "Engineering Admissions"));
        exams.add(new Exam("ex3", "NEET UG", "National Medical Admissions"));
    }

    public List<Career> getAllCareers() {
        return careers;
    }

    public List<College> getAllColleges() {
        return colleges;
    }

    public List<RankedCareer> getRecommendations(StudentProfile profile) {
        List<RankedCareer> list = new ArrayList<>();
        for (Career c : careers) {
            double score = 0.5;
            if (profile != null && profile.getStream() != null && profile.getStream().equalsIgnoreCase(c.getStream())) {
                score += 0.4;
            }
            list.add(new RankedCareer(c, score));
        }
        list.sort((a, b) -> Double.compare(b.getScore(), a.getScore()));
        return list;
    }

    public Map<String, Object> getPathwayBFS(String careerId) {
        Career found = careers.stream().filter(c -> c.getId().equalsIgnoreCase(careerId)).findFirst().orElse(null);
        if (found == null && !careers.isEmpty()) {
            found = careers.get(0);
        }

        Map<String, Object> res = new HashMap<>();
        res.put("career", found);
        res.put("courses", courses);
        res.put("exams", exams);
        res.put("colleges", colleges);
        return res;
    }

    public List<College> getCollegesByMaxFee(double maxFee) {
        List<College> filtered = new ArrayList<>();
        for (College c : colleges) {
            if (c.getAnnualFee() <= maxFee) {
                filtered.add(c);
            }
        }
        return filtered;
    }
}