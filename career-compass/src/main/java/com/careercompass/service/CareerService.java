package com.careercompass.service;

import com.careercompass.ds.CareerPathwayGraph;
import com.careercompass.ds.CollegeAVLTree;
import com.careercompass.ds.RecommendationEngine;
import com.careercompass.model.*;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CareerService {

    private final Map<String, Career> careerDatabase = new HashMap<>();
    private final Map<String, Course> courseDatabase = new HashMap<>();
    private final Map<String, Exam> examDatabase = new HashMap<>();
    private final Map<String, College> collegeDatabase = new HashMap<>();

    private final CollegeAVLTree collegeAVLTree = new CollegeAVLTree();
    private final CareerPathwayGraph pathwayGraph = new CareerPathwayGraph();
    private final RecommendationEngine recommendationEngine = new RecommendationEngine();

    @PostConstruct
    public void initData() {
        // --- 1. CAREERS ---
        Career c1 = new Career("c_swe", "Software Engineering", "MPC",
                List.of("Programming", "Technology", "Mathematics"),
                List.of("Problem Solving", "Coding", "Algorithms", "System Design"),
                "Architect, design, and maintain large-scale software platforms, cloud infrastructure, and mobile applications.", 950000.0);

        Career c2 = new Career("c_ds", "Data Science & Artificial Intelligence", "MPC",
                List.of("Mathematics", "Statistics", "Machine Learning", "Technology"),
                List.of("Python", "Analytical Thinking", "Data Modeling", "Deep Learning"),
                "Extract intelligence from big data, design neural networks, and deploy automated predictive AI agents.", 1050000.0);

        Career c3 = new Career("c_med", "General Medicine & Surgery", "BiPC",
                List.of("Biology", "Healthcare", "Patient Care", "Science"),
                List.of("Clinical Diagnosis", "Empathy", "Surgical Basics", "Critical Care"),
                "Diagnose pathologies, conduct medical interventions, and oversee patient recovery protocols.", 1000000.0);

        Career c4 = new Career("c_pharm", "Pharmaceutical Research & Formulation", "BiPC",
                List.of("Chemistry", "Biology", "Pharmacology", "Research"),
                List.of("Analytical Chemistry", "Drug Formulation", "Quality Control", "Regulatory Affairs"),
                "Synthesize novel therapeutic drugs, test clinical efficacy, and supervise pharmacovigilance pipelines.", 650000.0);

        Career c5 = new Career("c_fin", "Investment Banking & Quantitative Finance", "MEC",
                List.of("Economics", "Finance", "Mathematics", "Analytics"),
                List.of("Financial Modeling", "Valuation", "Risk Analysis", "Spreadsheets"),
                "Advise on multi-million corporate mergers, manage equity portfolios, and formulate capital raising strategies.", 1200000.0);

        Career c6 = new Career("c_ca", "Chartered Accountancy & Audit", "MEC",
                List.of("Accounting", "Taxation", "Law", "Finance"),
                List.of("Auditing", "Tax Compliance", "Financial Reporting", "Corporate Law"),
                "Oversee statutory corporate audits, design tax avoidance frameworks, and direct financial strategy.", 900000.0);

        Career c7 = new Career("c_law", "Corporate Law & Litigation", "CEC",
                List.of("Law", "Legal Reasoning", "Public Policy", "Debate"),
                List.of("Contract Drafting", "Legal Analysis", "Advocacy", "Negotiation"),
                "Counsel corporations on mergers and statutory regulations or represent clients in High Courts.", 850000.0);

        Career c8 = new Career("c_civil", "Civil & Structural Engineering", "MPC",
                List.of("Mathematics", "Physics", "Construction", "Architecture"),
                List.of("Structural Analysis", "AutoCAD", "Project Management", "Site Planning"),
                "Engineer seismic-resistant bridges, transit tunnels, urban transport networks, and commercial high-rises.", 650000.0);

        Career c9 = new Career("c_cyber", "Cybersecurity & Ethical Hacking", "MPC",
                List.of("Technology", "Networking", "Cryptography", "Security"),
                List.of("Penetration Testing", "Network Protocols", "SIEM", "Incident Response"),
                "Defend mission-critical systems against intrusions, conduct red-team attacks, and preserve data integrity.", 900000.0);

        Career c10 = new Career("c_biotech", "Biotechnology & Genetic Engineering", "BiPC",
                List.of("Biology", "Genetics", "Microbiology", "Research"),
                List.of("DNA Sequencing", "CRISPR", "Bioinformatics", "Microbial Culture"),
                "Develop targeted gene therapies, recombinant vaccines, and drought-resistant agricultural cultivars.", 700000.0);

        List.of(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10).forEach(c -> careerDatabase.put(c.getId(), c));

        // --- 2. COURSES ---
        Course crs1 = new Course("crs_cse", "B.Tech in Computer Science & Engineering", 4, 120000.0);
        Course crs2 = new Course("crs_aiml", "B.Tech in Artificial Intelligence & Data Science", 4, 130000.0);
        Course crs3 = new Course("crs_mbbs", "Bachelor of Medicine & Bachelor of Surgery (MBBS)", 5, 25000.0);
        Course crs4 = new Course("crs_bpharm", "Bachelor of Pharmacy (B.Pharm)", 4, 80000.0);
        Course crs5 = new Course("crs_bcom", "B.Com in Corporate Accounting & Finance", 3, 50000.0);
        Course crs6 = new Course("crs_ca_found", "ICAI Chartered Accountancy Foundation", 3, 60000.0);
        Course crs7 = new Course("crs_ba_llb", "Integrated B.A. LL.B. (Hons)", 5, 180000.0);
        Course crs8 = new Course("crs_civil", "B.Tech in Civil & Infrastructure Engineering", 4, 100000.0);
        Course crs9 = new Course("crs_biotech", "B.Tech in Biotechnology", 4, 110000.0);

        List.of(crs1, crs2, crs3, crs4, crs5, crs6, crs7, crs8, crs9).forEach(cr -> courseDatabase.put(cr.getId(), cr));

        // --- 3. EXAMS ---
        Exam ex1 = new Exam("ex_jee", "JEE Main & Advanced", "31-March-2027", 4.8);
        Exam ex2 = new Exam("ex_eamcet", "AP / TS EAPCET", "15-May-2027", 3.8);
        Exam ex3 = new Exam("ex_neet", "NEET UG", "05-May-2027", 4.7);
        Exam ex4 = new Exam("ex_clat", "Common Law Admission Test (CLAT)", "01-December-2026", 4.2);
        Exam ex5 = new Exam("ex_icai", "ICAI Foundation Examination", "15-June-2027", 4.0);
        Exam ex6 = new Exam("ex_cuet", "CUET UG (Central Universities)", "20-May-2027", 3.5);

        List.of(ex1, ex2, ex3, ex4, ex5, ex6).forEach(ex -> examDatabase.put(ex.getId(), ex));

        // --- 4. COLLEGES (AP & TELANGANA WITH REALISTIC ANNUAL FEES) ---
        List<College> collegeList = List.of(
            new College("col_svecw", "Shri Vishnu Engineering College for Women (SVECW)", "Bhimavaram, AP", 110000.0, 4.8, 88.0, 20.0),
            new College("col_iith", "IIT Hyderabad", "Kandi, Sangareddy, TS", 225000.0, 4.9, 98.0, 390.0),
            new College("col_nitw", "NIT Warangal", "Warangal, TS", 145000.0, 4.8, 95.0, 350.0),
            new College("col_cbit", "Chaitanya Bharathi Institute of Technology (CBIT)", "Gandipet, Hyderabad, TS", 140000.0, 4.5, 92.0, 370.0),
            new College("col_vnr", "VNR Vignana Jyothi Institute of Engineering & Tech (VNR VJIET)", "Bachupally, Hyderabad, TS", 135000.0, 4.6, 94.0, 360.0),
            new College("col_gvp", "Gayatri Vidya Parishad College of Engineering (GVPCE)", "Visakhapatnam, AP", 105000.0, 4.5, 89.0, 250.0),
            new College("col_vrsec", "Velagapudi Ramakrishna Siddhartha Engineering College (VRSEC)", "Vijayawada, AP", 105000.0, 4.4, 87.0, 115.0),
            new College("col_gmrit", "GMR Institute of Technology (GMRIT)", "Rajam, AP", 77800.0, 4.3, 85.0, 190.0),
            new College("col_jntuk", "JNTU College of Engineering Kakinada", "Kakinada, AP", 35000.0, 4.5, 86.0, 110.0),
            new College("col_au", "Andhra University College of Engineering", "Visakhapatnam, AP", 40000.0, 4.6, 89.0, 255.0),
            new College("col_ou", "University College of Engineering, Osmania University", "Hyderabad, TS", 35000.0, 4.5, 88.0, 365.0),
            new College("col_aiims", "AIIMS Mangalagiri", "Mangalagiri, AP", 15000.0, 4.9, 99.0, 110.0),
            new College("col_amc", "Andhra Medical College", "Visakhapatnam, AP", 25000.0, 4.8, 97.0, 260.0),
            new College("col_gmc", "Guntur Medical College", "Guntur, AP", 25000.0, 4.7, 95.0, 120.0),
            new College("col_osmania_med", "Osmania Medical College", "Koti, Hyderabad, TS", 30000.0, 4.8, 96.0, 375.0),
            new College("col_nalsar", "NALSAR University of Law", "Shamirpet, Hyderabad, TS", 260000.0, 4.9, 97.0, 395.0),
            new College("col_loyola", "Loyola Academy Degree & PG College", "Alwal, Secunderabad, TS", 65000.0, 4.3, 82.0, 360.0)
        );

        collegeList.forEach(col -> {
            collegeDatabase.put(col.getId(), col);
            collegeAVLTree.insert(col); // Indexed into Self-Balancing AVL Tree
        });

        // --- 5. BUILD GRAPH ADJACENCY (BFS PATHWAYS) ---
        // Software Engineering & AI pathways
        pathwayGraph.addEdge("c_swe", "crs_cse");
        pathwayGraph.addEdge("c_swe", "crs_aiml");
        pathwayGraph.addEdge("c_ds", "crs_aiml");
        pathwayGraph.addEdge("c_ds", "crs_cse");
        pathwayGraph.addEdge("c_cyber", "crs_cse");

        pathwayGraph.addEdge("crs_cse", "ex_jee");
        pathwayGraph.addEdge("crs_cse", "ex_eamcet");
        pathwayGraph.addEdge("crs_aiml", "ex_jee");
        pathwayGraph.addEdge("crs_aiml", "ex_eamcet");

        pathwayGraph.addEdge("ex_jee", "col_iith");
        pathwayGraph.addEdge("ex_jee", "col_nitw");
        pathwayGraph.addEdge("ex_eamcet", "col_svecw");
        pathwayGraph.addEdge("ex_eamcet", "col_cbit");
        pathwayGraph.addEdge("ex_eamcet", "col_vnr");
        pathwayGraph.addEdge("ex_eamcet", "col_gvp");
        pathwayGraph.addEdge("ex_eamcet", "col_vrsec");
        pathwayGraph.addEdge("ex_eamcet", "col_gmrit");
        pathwayGraph.addEdge("ex_eamcet", "col_jntuk");
        pathwayGraph.addEdge("ex_eamcet", "col_au");
        pathwayGraph.addEdge("ex_eamcet", "col_ou");

        // Civil Engineering pathway
        pathwayGraph.addEdge("c_civil", "crs_civil");
        pathwayGraph.addEdge("crs_civil", "ex_jee");
        pathwayGraph.addEdge("crs_civil", "ex_eamcet");
        pathwayGraph.addEdge("ex_eamcet", "col_au");
        pathwayGraph.addEdge("ex_eamcet", "col_jntuk");
        pathwayGraph.addEdge("ex_eamcet", "col_vrsec");

        // Medicine & Biotech pathways
        pathwayGraph.addEdge("c_med", "crs_mbbs");
        pathwayGraph.addEdge("crs_mbbs", "ex_neet");
        pathwayGraph.addEdge("ex_neet", "col_aiims");
        pathwayGraph.addEdge("ex_neet", "col_amc");
        pathwayGraph.addEdge("ex_neet", "col_gmc");
        pathwayGraph.addEdge("ex_neet", "col_osmania_med");

        pathwayGraph.addEdge("c_pharm", "crs_bpharm");
        pathwayGraph.addEdge("crs_bpharm", "ex_eamcet");
        pathwayGraph.addEdge("crs_bpharm", "ex_neet");
        pathwayGraph.addEdge("ex_eamcet", "col_au");

        pathwayGraph.addEdge("c_biotech", "crs_biotech");
        pathwayGraph.addEdge("crs_biotech", "ex_jee");
        pathwayGraph.addEdge("crs_biotech", "ex_eamcet");
        pathwayGraph.addEdge("ex_eamcet", "col_cbit");

        // Finance, CA & Law pathways
        pathwayGraph.addEdge("c_fin", "crs_bcom");
        pathwayGraph.addEdge("crs_bcom", "ex_cuet");
        pathwayGraph.addEdge("ex_cuet", "col_loyola");

        pathwayGraph.addEdge("c_ca", "crs_ca_found");
        pathwayGraph.addEdge("crs_ca_found", "ex_icai");
        pathwayGraph.addEdge("ex_icai", "col_loyola");

        pathwayGraph.addEdge("c_law", "crs_ba_llb");
        pathwayGraph.addEdge("crs_ba_llb", "ex_clat");
        pathwayGraph.addEdge("ex_clat", "col_nalsar");
    }

    public List<Career> getAllCareers() {
        return new ArrayList<>(careerDatabase.values());
    }

    public List<RankedCareer> getRecommendations(StudentProfile profile) {
        return recommendationEngine.getTopRecommendations(profile, new ArrayList<>(careerDatabase.values()), 5);
    }

    public Map<String, Object> getPathwayForCareer(String careerId) {
        List<String> visitedNodeIds = pathwayGraph.traversePathwayBFS(careerId);
        Map<String, Object> pathwayData = new HashMap<>();

        Career career = careerDatabase.get(careerId);
        List<Course> courses = new ArrayList<>();
        List<Exam> exams = new ArrayList<>();
        List<College> colleges = new ArrayList<>();

        for (String id : visitedNodeIds) {
            if (courseDatabase.containsKey(id)) courses.add(courseDatabase.get(id));
            else if (examDatabase.containsKey(id)) exams.add(examDatabase.get(id));
            else if (collegeDatabase.containsKey(id)) colleges.add(collegeDatabase.get(id));
        }

        pathwayData.put("career", career);
        pathwayData.put("courses", courses);
        pathwayData.put("exams", exams);
        pathwayData.put("colleges", colleges);

        return pathwayData;
    }

    public List<College> getCollegesWithinBudget(double maxBudget) {
        return collegeAVLTree.getCollegesWithinBudget(maxBudget);
    }
}