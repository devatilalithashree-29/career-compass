package com.careercompass.ds;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import com.careercompass.model.Career;
import com.careercompass.model.StudentProfile;

public class RecommendationEngine {

    public static class RankedCareer implements Comparable<RankedCareer> {
        private final Career career;
        private final double matchPercentage;

        public RankedCareer(Career career, double matchPercentage) {
            this.career = career;
            this.matchPercentage = matchPercentage;
        }

        public Career getCareer() {
            return career;
        }

        public double getMatchPercentage() {
            return matchPercentage;
        }

        @Override
        public int compareTo(RankedCareer other) {
            // Max-Heap: highest percentage first
            return Double.compare(other.matchPercentage, this.matchPercentage);
        }
    }

    public static List<RankedCareer> recommendCareers(StudentProfile profile, List<Career> catalog) {
        PriorityQueue<RankedCareer> maxHeap = new PriorityQueue<>();

        for (Career career : catalog) {
            double interestScore = computeOverlap(profile.getInterests(), career.getPrimaryInterests());
            double skillScore = computeOverlap(profile.getSkills(), career.getRequiredSkills());
            double streamScore = (career.getStream() != null && profile.getStream() != null && 
                                  career.getStream().equalsIgnoreCase(profile.getStream())) ? 1.0 : 0.2;

            double compositeScore = (interestScore * 0.50) + (skillScore * 0.35) + (streamScore * 0.15);
            double percentage = Math.round(compositeScore * 100.0);

            if (percentage > 20.0) {
                maxHeap.offer(new RankedCareer(career, percentage));
            }
        }

        List<RankedCareer> rankedList = new ArrayList<>();
        while (!maxHeap.isEmpty()) {
            rankedList.add(maxHeap.poll());
        }
        return rankedList;
    }

    private static double computeOverlap(List<String> studentList, List<String> targetList) {
        if (studentList == null || targetList == null || targetList.isEmpty()) return 0.0;
        long matches = studentList.stream()
                .map(String::toLowerCase)
                .filter(s -> targetList.stream().anyMatch(t -> t.equalsIgnoreCase(s)))
                .count();
        return (double) matches / targetList.size();
    }
}