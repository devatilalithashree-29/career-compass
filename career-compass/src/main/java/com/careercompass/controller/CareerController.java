package com.careercompass.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.careercompass.ds.RecommendationEngine.RankedCareer;
import com.careercompass.model.Career;
import com.careercompass.model.College;
import com.careercompass.model.StudentProfile;
import com.careercompass.service.CareerService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Allows the frontend laptop to make API requests without CORS blocks
public class CareerController {

    private final CareerService careerService;

    public CareerController(CareerService careerService) {
        this.careerService = careerService;
    }

    // 1. Get all available careers
    @GetMapping("/careers")
    public List<Career> getAllCareers() {
        return careerService.getAllCareers();
    }

    // 2. Recommend Careers (Uses Max-Heap Priority Queue)
    @PostMapping("/recommend")
    public List<RankedCareer> recommendCareers(@RequestBody StudentProfile profile) {
        return careerService.getRecommendations(profile);
    }

    // 3. Get Career Pathway (Uses Graph BFS Traversal)
    @GetMapping("/pathway/{careerId}")
    public Map<String, Object> getPathway(@PathVariable String careerId) {
        return careerService.getPathwayForCareer(careerId);
    }

    // 4. Search Colleges within Budget (Uses AVL Balanced Tree)
    @GetMapping("/colleges/budget")
    public List<College> getCollegesByBudget(@RequestParam double maxBudget) {
        return careerService.getCollegesWithinBudget(maxBudget);
    }
}