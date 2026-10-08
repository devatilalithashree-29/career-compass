document.addEventListener("DOMContentLoaded", () => {
    console.log("EduGuide JS Loaded Successfully");

    /* 1. STREAMS */
    const streamData = {
        "mpc": {
            name: "Mathematics, Physics, Chemistry (MPC)",
            fields: [
                { name: "Computer Science & Engineering", courses: ["B.Tech Computer Science (CSE)", "B.Tech AI & Data Science", "BCA", "B.Sc Computer Science"] },
                { name: "Core Engineering", courses: ["B.Tech Electronics & Comm (ECE)", "B.Tech Electrical & Electronics (EEE)", "B.Tech Mechanical", "B.Tech Civil"] },
                { name: "Architecture & Defense", courses: ["Bachelor of Architecture (B.Arch)", "Commercial Pilot License (CPL)", "National Defence Academy (NDA)"] }
            ]
        },
        "bipc": {
            name: "Biology, Physics, Chemistry (BiPC)",
            fields: [
                { name: "Medicine & Clinical Healthcare", courses: ["MBBS (General Medicine)", "BDS (Dental Surgery)", "BAMS (Ayurvedic Medicine)", "BHMS (Homeopathy)"] },
                { name: "Allied Health Sciences & Pharma", courses: ["Bachelor of Pharmacy (B.Pharm)", "Doctor of Pharmacy (Pharm.D)", "B.Sc Nursing", "Bachelor of Physiotherapy (BPT)"] },
                { name: "Agriculture & Bioscience", courses: ["B.Sc (Hons) Agriculture", "Bachelor of Veterinary Science (B.V.Sc)", "B.Sc Biotechnology"] }
            ]
        },
        "mec": {
            name: "Mathematics, Economics, Commerce (MEC)",
            fields: [
                { name: "Professional Finance & Accounting", courses: ["Chartered Accountancy (CA)", "CMA India", "ACCA Global", "B.Com Honors (Finance)"] },
                { name: "Business Management & Analytics", courses: ["BBA (Business Analytics)", "Integrated MBA (IIM IPM)", "B.Sc Financial Economics"] }
            ]
        },
        "cec": {
            name: "Civics, Economics, Commerce (CEC)",
            fields: [
                { name: "Legal Studies & Judiciary", courses: ["Integrated B.A. LL.B. (5 Years)", "Integrated B.Com LL.B.", "Corporate Legal Practice"] },
                { name: "Public Administration & Management", courses: ["B.A. Public Policy & Governance", "BBA (Human Resource Management)", "Civil Services Track"] }
            ]
        },
        "arts": {
            name: "Arts & Humanities",
            fields: [
                { name: "Design, Visual Arts & Media", courses: ["B.Des UI/UX & Communication Design", "B.A. Journalism & Mass Communication", "Animation & VFX"] },
                { name: "Humanities & Social Sciences", courses: ["B.A. Clinical Psychology", "Bachelor of Social Work (BSW)", "B.A. English Literature"] }
            ]
        },
        "vocational": {
            name: "Vocational & Applied Tracks",
            fields: [
                { name: "Software Development & IT", courses: ["B.Voc Software Development", "Diploma in Full Stack Web Development", "Digital Marketing"] }
            ]
        }
    };

    const streamGrid = document.getElementById("streamGrid");
    const streamSearch = document.getElementById("streamSearch");
    if (streamGrid) {
        if (streamSearch) {
            streamSearch.addEventListener("input", () => {
                const q = streamSearch.value.trim().toLowerCase();
                streamGrid.querySelectorAll(".stream-card").forEach(c => {
                    c.style.display = c.textContent.toLowerCase().includes(q) ? "" : "none";
                });
            });
        }
        streamGrid.addEventListener("click", (e) => {
            const card = e.target.closest(".stream-card");
            if (!card) return;
            const text = (card.dataset.stream || card.textContent).toLowerCase();
            let key = "mpc";
            if (text.includes("bipc")) key = "bipc";
            else if (text.includes("mec")) key = "mec";
            else if (text.includes("cec")) key = "cec";
            else if (text.includes("art")) key = "arts";
            else if (text.includes("voc")) key = "vocational";

            const data = streamData[key];
            const selName = document.getElementById("selectedStreamName");
            const fieldGrid = document.getElementById("fieldGrid");
            if (selName && data) selName.textContent = data.name;
            if (fieldGrid && data) {
                fieldGrid.innerHTML = data.fields.map(f => `
                    <div class="field-card" style="background:#fff; border:1px solid #e2e8f0; border-radius:12px; padding:20px; box-shadow:0 4px 6px -1px rgba(0,0,0,0.05); margin-bottom:15px;">
                        <h3 style="color:#1e293b; margin-bottom:8px; font-size:1.15rem;">${escapeHTML(f.name)}</h3>
                        <p style="font-weight:700; color:#6366f1; font-size:0.8rem; margin-bottom:8px;">POPULAR COURSES</p>
                        <ul style="list-style:none; padding:0; margin:0;">
                            ${f.courses.map(c => `<li style="padding:4px 0; color:#475569; border-bottom:1px solid #f8fafc;">• ${escapeHTML(c)}</li>`).join("")}
                        </ul>
                    </div>
                `).join("");
                smoothScroll("streamDetails");
            }
        });
    }

    /* 2. COURSES */
    const courseList = [
        { id: 1, name: "B.Tech in Computer Science & Engineering", duration: "4 Years", stream: "MPC", fee: "₹35,000 - ₹1,40,000/yr", desc: "Data structures, algorithms, operating systems, cloud systems, and AI engineering.", elig: "10+2 MPC with 50%+ and JEE / AP-TS EAPCET rank." },
        { id: 2, name: "B.Tech in AI & Data Science", duration: "4 Years", stream: "MPC", fee: "₹70,000 - ₹1,45,000/yr", desc: "Specialization in machine learning, deep neural nets, big data analysis, and predictive models.", elig: "10+2 MPC with qualifying entrance rank." },
        { id: 3, name: "MBBS (Bachelor of Medicine & Surgery)", duration: "5.5 Years", stream: "BiPC", fee: "₹15,000 - ₹1,20,000/yr", desc: "Clinical training including anatomy, physiology, surgery, pathology, and internship.", elig: "10+2 BiPC with qualifying NEET UG rank." },
        { id: 4, name: "Bachelor of Pharmacy (B.Pharm)", duration: "4 Years", stream: "BiPC / MPC", fee: "₹45,000 - ₹1,10,000/yr", desc: "Drug development, medicinal chemistry, formulation analytics, and industrial pharmacology.", elig: "10+2 with Chemistry + EAPCET score." },
        { id: 5, name: "Chartered Accountancy (CA)", duration: "3-4.5 Years", stream: "MEC / CEC", fee: "₹30,000 - ₹75,000 total", desc: "Corporate accounting, statutory audit, corporate taxation, and business laws.", elig: "10+2 clearance + ICAI Foundation registration." },
        { id: 6, name: "Integrated B.A. LL.B. (Honours)", duration: "5 Years", stream: "Any Stream (CEC/Arts)", fee: "₹50,000 - ₹2,60,000/yr", desc: "Comprehensive legal education covering criminal jurisprudence, constitutional law, and corporate litigation.", elig: "10+2 clearance + CLAT / LAWCET." },
        { id: 7, name: "B.Tech in Civil & Infrastructure", duration: "4 Years", stream: "MPC", fee: "₹35,000 - ₹1,10,000/yr", desc: "Structural analysis, transport engineering, geotechnical foundations, and modern CAD planning.", elig: "10+2 MPC + JEE / EAPCET." },
        { id: 8, name: "B.Sc (Hons) in Agriculture", duration: "4 Years", stream: "BiPC", fee: "₹25,000 - ₹60,000/yr", desc: "Agronomy, plant breeding, farm robotics, soil science, and agro-business analytics.", elig: "10+2 BiPC + State Agri CET / ICAR rank." }
    ];

    const courseGrid = document.getElementById("courseGrid");
    const courseSearch = document.getElementById("courseSearch");
    if (courseGrid) {
        function renderCourses(arr) {
            courseGrid.innerHTML = arr.map((crs, i) => `
                <div class="course-card" data-id="${crs.id}">
                    <div class="course-card-top">
                        <div class="course-icon">◇</div>
                        <span class="course-code">${String(i + 1).padStart(2, '0')}</span>
                    </div>
                    <h3>${escapeHTML(crs.name)}</h3>
                    <p class="course-card-description">${escapeHTML(crs.desc)}</p>
                    <div class="course-meta">
                        <span>${escapeHTML(crs.duration)}</span>
                        <span>${escapeHTML(crs.stream)}</span>
                    </div>
                    <button class="course-view-btn" type="button" style="cursor:pointer;">View details →</button>
                </div>
            `).join("");
            const cnt = document.getElementById("courseCount");
            if (cnt) cnt.textContent = `${arr.length} courses available`;
        }
        renderCourses(courseList);

        if (courseSearch) {
            courseSearch.addEventListener("input", () => {
                const q = courseSearch.value.trim().toLowerCase();
                renderCourses(courseList.filter(c => c.name.toLowerCase().includes(q) || c.stream.toLowerCase().includes(q) || c.desc.toLowerCase().includes(q)));
            });
        }

        courseGrid.addEventListener("click", (e) => {
            const card = e.target.closest(".course-card");
            if (!card) return;
            const id = Number(card.dataset.id);
            const crs = courseList.find(c => c.id === id);
            if (!crs) return;

            const nameEl = document.getElementById("selectedCourseName");
            const detEl = document.getElementById("courseDetailContent");
            if (nameEl) nameEl.textContent = crs.name;
            if (detEl) {
                detEl.innerHTML = `
                    <div style="display:grid; grid-template-columns:repeat(auto-fit, minmax(200px, 1fr)); gap:15px; margin-bottom:20px;">
                        <div style="background:#f8fafc; padding:15px; border-radius:10px; border-left:4px solid #6366f1;"><span>DURATION</span><br><strong>${escapeHTML(crs.duration)}</strong></div>
                        <div style="background:#f8fafc; padding:15px; border-radius:10px; border-left:4px solid #10b981;"><span>STREAM</span><br><strong>${escapeHTML(crs.stream)}</strong></div>
                        <div style="background:#f8fafc; padding:15px; border-radius:10px; border-left:4px solid #3b82f6;"><span>TYPICAL FEES</span><br><strong>${escapeHTML(crs.fee)}</strong></div>
                    </div>
                    <div style="background:#fff; padding:20px; border-radius:12px; border:1px solid #e2e8f0;">
                        <h3 style="margin-top:0;">Eligibility Criteria</h3><p style="color:#475569;">${escapeHTML(crs.elig)}</p>
                        <h3>Curriculum & Scope</h3><p style="color:#475569;">${escapeHTML(crs.desc)}</p>
                    </div>
                `;
                smoothScroll("courseDetails");
            }
        });
    }

    /* 3. EXAMS */
    const examData = {
        "JEE Main": { purpose: "Engineering Admissions (NITs/IIITs)", stream: "MPC", prep: "Physics, Chem, Math concepts & PYQs", next: "JoSAA / CSAB counselling." },
        "NEET UG": { purpose: "Medical Admissions (MBBS/BDS)", stream: "BiPC", prep: "NCERT Biology mastery & timed full-length mocks", next: "MCC 15% AIQ & State Quota counselling." },
        "AP EAPCET": { purpose: "AP Engineering, Pharmacy & Agriculture", stream: "MPC / BiPC", prep: "Board syllabus formula speed & accuracy", next: "APSCHE web counselling option entry." },
        "CUET UG": { purpose: "Central Universities (DU/BHU/HCU)", stream: "All Streams", prep: "Class 12 domain subjects & General Test", next: "University specific seat allocation portals." },
        "CLAT": { purpose: "National Law Universities (NLUs)", stream: "All Streams", prep: "Reading comprehension, legal aptitude & current affairs", next: "Consortium of NLUs centralized admissions." },
        "NATA": { purpose: "Bachelor of Architecture (B.Arch)", stream: "MPC", prep: "Drawing perspective, aesthetic sensitivity & Math", next: "State B.Arch counselling using NATA + 10+2 scores." }
    };

    const examGrid = document.getElementById("examGrid");
    const examSearch = document.getElementById("examSearch");
    if (examGrid) {
        if (examSearch) {
            examSearch.addEventListener("input", () => {
                const q = examSearch.value.trim().toLowerCase();
                examGrid.querySelectorAll(".exam-card").forEach(c => {
                    c.style.display = c.textContent.toLowerCase().includes(q) ? "" : "none";
                });
            });
        }
        examGrid.addEventListener("click", (e) => {
            const card = e.target.closest(".exam-card");
            if (!card) return;
            const name = card.querySelector("h3")?.textContent.trim();
            const data = examData[name];
            const nameEl = document.getElementById("selectedExamName");
            const detEl = document.getElementById("examDetailContent");
            if (nameEl) nameEl.textContent = name;
            if (detEl && data) {
                detEl.innerHTML = `
                    <div style="display:grid; grid-template-columns:repeat(auto-fit, minmax(200px, 1fr)); gap:15px; margin-bottom:20px;">
                        <div style="background:#f8fafc; padding:15px; border-radius:10px; border-left:4px solid #6366f1;"><span>PURPOSE</span><br><strong>${escapeHTML(data.purpose)}</strong></div>
                        <div style="background:#f8fafc; padding:15px; border-radius:10px; border-left:4px solid #10b981;"><span>STREAM</span><br><strong>${escapeHTML(data.stream)}</strong></div>
                    </div>
                    <div style="background:#fff; padding:20px; border-radius:12px; border:1px solid #e2e8f0;">
                        <h3 style="margin-top:0;">Preparation Strategy</h3><p style="color:#475569;">${escapeHTML(data.prep)}</p>
                        <h3>Next Steps & Counselling</h3><p style="color:#475569;">${escapeHTML(data.next)}</p>
                    </div>
                `;
                smoothScroll("examDetails");
            }
        });
    }

    /* 4. COLLEGES */
    const collegeList = [
        { name: "Shri Vishnu Engineering College for Women (SVECW)", loc: "Bhimavaram, AP", fee: "₹1,10,000 / yr", type: "Autonomous (JNTUK)", desc: "Premier women's engineering college known for product placements (Amazon, Adobe) and hackathon culture.", adm: "AP EAPCET Convener Quota & Category-B Management Quota." },
        { name: "IIT Hyderabad", loc: "Sangareddy, TS", fee: "₹2,25,000 / yr", type: "Institute of National Importance", desc: "Top IIT renowned for AI, computer science, and microelectronics research.", adm: "JEE Advanced rank via JoSAA." },
        { name: "NIT Warangal", loc: "Warangal, TS", fee: "₹1,45,000 / yr", type: "National Institute of Tech", desc: "Historic engineering institution with exceptional campus recruitment records.", adm: "JEE Main rank via JoSAA/CSAB." },
        { name: "CBIT Hyderabad", loc: "Gandipet, Hyderabad, TS", fee: "₹1,40,000 / yr", type: "Autonomous (OU)", desc: "Leading engineering college in Hyderabad with high placement consistency.", adm: "TS EAPCET rank & Category-B." },
        { name: "VNR VJIET", loc: "Bachupally, Hyderabad, TS", fee: "₹1,35,000 / yr", type: "Autonomous (JNTUH)", desc: "NAAC A++ college with strong project culture and tech recruitment drives.", adm: "TS EAPCET rank through web-counselling." },
        { name: "GVP College of Engineering (GVPCE)", loc: "Visakhapatnam, AP", fee: "₹1,05,000 / yr", type: "Autonomous (JNTUK)", desc: "Top autonomous college in coastal AP with strong technical branch placements.", adm: "AP EAPCET ranking via APSCHE." },
        { name: "VR Siddhartha Engineering College", loc: "Vijayawada, AP", fee: "₹1,05,000 / yr", type: "Autonomous (Deemed)", desc: "Oldest private engineering college in AP with stellar industry tie-ups.", adm: "AP EAPCET / Siddhartha Academy admissions." },
        { name: "GMR Institute of Technology (GMRIT)", loc: "Rajam, Srikakulam, AP", fee: "₹77,800 / yr", type: "Autonomous (JNTUK)", desc: "Modern residential campus backed by GMR Group with top-tier training facilities.", adm: "AP EAPCET state counselling." },
        { name: "JNTU College of Engineering Kakinada", loc: "Kakinada, AP", fee: "₹35,000 / yr", type: "Government University Campus", desc: "Affordable government technical university with prestigious alumni base.", adm: "Top AP EAPCET state ranks." },
        { name: "Andhra University College of Engineering", loc: "Visakhapatnam, AP", fee: "₹40,000 / yr", type: "Government University Campus", desc: "Prestigious university campus with beach-front heritage and PSU selections.", adm: "AP EAPCET top convener ranks." },
        { name: "AIIMS Mangalagiri", loc: "Mangalagiri, AP", fee: "₹15,000 total", type: "National Medical Institute", desc: "Apex central medical campus with world-class hospital and surgery training.", adm: "NEET UG All-India rank via MCC." },
        { name: "NALSAR University of Law", loc: "Hyderabad, TS", fee: "₹2,60,000 / yr", type: "National Law University", desc: "Top 3 Law school in India known for Supreme Court clerkships and Tier-1 firm hires.", adm: "CLAT rank via Consortium." }
    ];

    const collegeGrid = document.getElementById("collegeGrid");
    const collegeSearch = document.getElementById("collegeSearch");
    if (collegeGrid) {
        function renderColleges(arr) {
            collegeGrid.innerHTML = arr.map((col, i) => `
                <div class="college-card">
                    <div class="college-card-top">
                        <div class="college-icon">🏛️</div>
                        <span class="college-code">${String(i + 1).padStart(2, '0')}</span>
                    </div>
                    <h3>${escapeHTML(col.name)}</h3>
                    <p class="college-card-description">${escapeHTML(col.desc)}</p>
                    <div class="college-tags" style="margin:8px 0;">
                        <span style="background:#e0e7ff; color:#3730a3; padding:2px 8px; border-radius:4px; font-size:0.8rem; font-weight:700;">${escapeHTML(col.fee)}</span>
                        <span style="background:#f1f5f9; color:#475569; padding:2px 8px; border-radius:4px; font-size:0.8rem;">${escapeHTML(col.loc)}</span>
                    </div>
                    <button class="college-view-btn" type="button" style="cursor:pointer;">View details →</button>
                </div>
            `).join("");
            const cnt = document.getElementById("collegeCount");
            if (cnt) cnt.textContent = `${arr.length} colleges available`;
        }
        renderColleges(collegeList);

        if (collegeSearch) {
            collegeSearch.addEventListener("input", () => {
                const q = collegeSearch.value.trim().toLowerCase();
                renderColleges(collegeList.filter(c => c.name.toLowerCase().includes(q) || c.loc.toLowerCase().includes(q) || c.desc.toLowerCase().includes(q)));
            });
        }

        collegeGrid.addEventListener("click", (e) => {
            const card = e.target.closest(".college-card");
            if (!card) return;
            const name = card.querySelector("h3")?.textContent.trim();
            const data = collegeList.find(c => c.name === name);
            const nameEl = document.getElementById("selectedCollegeName");
            const detEl = document.getElementById("collegeDetailContent");
            if (nameEl) nameEl.textContent = name;
            if (detEl && data) {
                detEl.innerHTML = `
                    <div style="display:grid; grid-template-columns:repeat(auto-fit, minmax(200px, 1fr)); gap:15px; margin-bottom:20px;">
                        <div style="background:#f8fafc; padding:15px; border-radius:10px; border-left:4px solid #6366f1;"><span>LOCATION</span><br><strong>${escapeHTML(data.loc)}</strong></div>
                        <div style="background:#f8fafc; padding:15px; border-radius:10px; border-left:4px solid #10b981;"><span>ANNUAL TUITION FEE</span><br><strong style="color:#059669; font-size:1.1rem;">${escapeHTML(data.fee)}</strong></div>
                        <div style="background:#f8fafc; padding:15px; border-radius:10px; border-left:4px solid #3b82f6;"><span>TYPE</span><br><strong>${escapeHTML(data.type)}</strong></div>
                    </div>
                    <div style="background:#fff; padding:20px; border-radius:12px; border:1px solid #e2e8f0;">
                        <h3 style="margin-top:0;">Institution Profile</h3><p style="color:#475569;">${escapeHTML(data.desc)}</p>
                        <h3>Admissions Procedure</h3><p style="color:#475569;">${escapeHTML(data.adm)}</p>
                    </div>
                `;
                smoothScroll("collegeDetails");
            }
        });
    }

    /* 5. SCHOLARSHIPS */
    const scholarshipData = {
        "National Scholarship Scheme": { cat: "Central Govt", level: "Undergraduate", elig: "Top 80th percentile in 10+2 with income under ₹4.5 LPA", sup: "Up to ₹20,000/yr directly via DBT" },
        "Post-Matric Scholarship": { cat: "State Welfare", level: "Higher Education", elig: "SC/ST/BC/EBC students enrolled in degree colleges", sup: "Full tuition reimbursement (e.g. Jagananna Vidya Deevena)" },
        "Merit Scholarship": { cat: "Merit-Based", level: "Technical / Degree", elig: "Top 1% rankers in state/national entrance tests", sup: "Tuition waiver and academic book allowances" },
        "Scholarship for Girl Students": { cat: "Women STEM", level: "B.Tech / MBBS", elig: "Female students pursuing technical degrees", sup: "₹50,000/yr grant (AICTE Pragati Scheme)" },
        "Engineering Scholarships": { cat: "Corporate / CSR", level: "B.Tech", elig: "Deserving engineering students (Foundation for Excellence)", sup: "Tuition support + laptop assistance" },
        "Minority Student Scholarships": { cat: "Minority Welfare", level: "Professional", elig: "Designated minority communities with 50%+ marks", sup: "Maintenance allowance + course fee subsidies" }
    };

    const scholarshipGrid = document.getElementById("scholarshipGrid");
    const scholarshipSearch = document.getElementById("scholarshipSearch");
    if (scholarshipGrid) {
        if (scholarshipSearch) {
            scholarshipSearch.addEventListener("input", () => {
                const q = scholarshipSearch.value.trim().toLowerCase();
                scholarshipGrid.querySelectorAll(".scholarship-card").forEach(c => {
                    c.style.display = c.textContent.toLowerCase().includes(q) ? "" : "none";
                });
            });
        }
        scholarshipGrid.addEventListener("click", (e) => {
            const card = e.target.closest(".scholarship-card");
            if (!card) return;
            const name = card.querySelector("h3")?.textContent.trim();
            const data = scholarshipData[name];
            const nameEl = document.getElementById("selectedScholarshipName");
            const detEl = document.getElementById("scholarshipDetailContent");
            if (nameEl) nameEl.textContent = name;
            if (detEl && data) {
                detEl.innerHTML = `
                    <div style="display:grid; grid-template-columns:repeat(auto-fit, minmax(200px, 1fr)); gap:15px; margin-bottom:20px;">
                        <div style="background:#f8fafc; padding:15px; border-radius:10px; border-left:4px solid #6366f1;"><span>CATEGORY</span><br><strong>${escapeHTML(data.cat)}</strong></div>
                        <div style="background:#f8fafc; padding:15px; border-radius:10px; border-left:4px solid #10b981;"><span>SUPPORT BENEFIT</span><br><strong>${escapeHTML(data.sup)}</strong></div>
                    </div>
                    <div style="background:#fff; padding:20px; border-radius:12px; border:1px solid #e2e8f0;">
                        <h3 style="margin-top:0;">Eligibility Requirements</h3><p style="color:#475569;">${escapeHTML(data.elig)}</p>
                    </div>
                `;
                smoothScroll("scholarshipDetails");
            }
        });
    }

    /* 6. PATHWAYS */
    const pathwayData = {
        "MPC → CSE → Software Engineer": { steps: [["1", "10+2 MPC Stream"], ["2", "B.Tech Computer Science"], ["3", "Data Structures & Modern Stack"], ["4", "Software Engineer"]], desc: "Pathway from mathematics fundamentals to building scalable backend and cloud systems." },
        "BiPC → MBBS → Doctor": { steps: [["1", "10+2 BiPC Stream"], ["2", "NEET UG Entrance"], ["3", "MBBS Coursework & Internship"], ["4", "Doctor / Clinical Specialist"]], desc: "Healthcare pathway focusing on human pathology, clinical diagnosis, and patient care." },
        "BiPC → B.Pharm → Pharmacist": { steps: [["1", "10+2 BiPC Stream"], ["2", "B.Pharm Degree"], ["3", "Formulation Research / Licensing"], ["4", "Licensed Pharmacist"]], desc: "Pharmaceutical formulation, therapeutic discovery, and clinical dispensing." },
        "MEC → B.Com → Business Analyst": { steps: [["1", "10+2 MEC Stream"], ["2", "B.Com / BBA Analytics"], ["3", "Data Tools (SQL, Power BI)"], ["4", "Business Analyst"]], desc: "Finance understanding merged with analytics tools to drive corporate growth decisions." },
        "CEC → Law → Legal Professional": { steps: [["1", "10+2 CEC Stream"], ["2", "CLAT Entrance Exam"], ["3", "Integrated B.A. LL.B."], ["4", "Advocate / Corporate Legal Counsel"]], desc: "Law track focusing on corporate compliance, litigation, and constitutional rights." },
        "Arts → Social Sciences → Research": { steps: [["1", "10+2 Arts / Humanities"], ["2", "B.A. Social Sciences"], ["3", "Postgraduate Research Fellowships"], ["4", "Policy Analyst / Researcher"]], desc: "Social sciences path directing public policy, community development, and research." }
    };

    const pathwayGrid = document.getElementById("pathwayGrid");
    const pathwaySearch = document.getElementById("pathwaySearch");
    if (pathwayGrid) {
        if (pathwaySearch) {
            pathwaySearch.addEventListener("input", () => {
                const q = pathwaySearch.value.trim().toLowerCase();
                pathwayGrid.querySelectorAll(".pathway-card").forEach(c => {
                    c.style.display = c.textContent.toLowerCase().includes(q) ? "" : "none";
                });
            });
        }
        pathwayGrid.addEventListener("click", (e) => {
            const card = e.target.closest(".pathway-card");
            if (!card) return;
            const name = card.querySelector("h3")?.textContent.trim();
            const data = pathwayData[name];
            const nameEl = document.getElementById("selectedPathwayName");
            const detEl = document.getElementById("pathwayDetailContent");
            if (nameEl) nameEl.textContent = name;
            if (detEl && data) {
                detEl.innerHTML = `
                    <div style="display:grid; grid-template-columns:repeat(auto-fit, minmax(200px, 1fr)); gap:15px; margin-bottom:20px;">
                        ${data.steps.map(s => `<div style="background:#f8fafc; padding:15px; border-radius:10px; border-left:4px solid #6366f1;"><strong>Step ${s[0]}:${escapeHTML(s[1])}</strong></div>`).join("")}
                    </div>
                    <div style="background:#fff; padding:20px; border-radius:12px; border:1px solid #e2e8f0;">
                        <h3 style="margin-top:0;">About This Pathway</h3><p style="color:#475569;">${escapeHTML(data.desc)}</p>
                    </div>
                `;
                smoothScroll("pathwayDetails");
            }
        });
    }

    /* 7. DYNAMIC CAREERS & GRAPH ROADMAP */
    const careerGrid = document.getElementById("careerGrid");
    const careerSearch = document.getElementById("careerSearch");
    if (careerGrid) {
        let allCareers = [];
        async function fetchCareers() {
            try {
                const res = await fetch(`${window.location.origin}/api/careers`);
                if (!res.ok) throw new Error("API Error");
                allCareers = await res.json();
                renderCareers(allCareers);
            } catch (err) {
                console.error("Failed to load backend careers:", err);
            }
        }

        function renderCareers(list) {
            careerGrid.innerHTML = list.map(c => `
                <div class="career-card" data-id="${c.id}">
                    <div class="career-card-top">
                        <span class="course-code">${escapeHTML(c.stream)}</span>
                    </div>
                    <h3>${escapeHTML(c.title)}</h3>
                    <p class="career-card-description">${escapeHTML(c.description || "")}</p>
                    <div class="career-tags">
                        ${(c.primaryInterests || []).map(t => `<span>${escapeHTML(t)}</span>`).join(" ")}
                    </div>
                    <div class="course-meta" style="margin-top:10px;">
                        <span>Avg Salary: ₹${c.averageStartingSalary ? c.averageStartingSalary.toLocaleString() : 'N/A'}</span>
                    </div>
                    <button class="career-view-btn" type="button" data-id="${c.id}" style="cursor:pointer; margin-top:12px;">
                        View Roadmap (BFS) →
                    </button>
                </div>
            `).join("");
            const cnt = document.getElementById("careerCount");
            if (cnt) cnt.textContent = `${list.length} careers available`;
        }

        if (careerSearch) {
            careerSearch.addEventListener("input", () => {
                const q = careerSearch.value.trim().toLowerCase();
                renderCareers(allCareers.filter(c => c.title.toLowerCase().includes(q) || (c.stream && c.stream.toLowerCase().includes(q)) || (c.description && c.description.toLowerCase().includes(q))));
            });
        }

        document.addEventListener("click", async (e) => {
            const btn = e.target.closest(".career-view-btn");
            if (!btn) return;
            const card = btn.closest(".career-card");
            const careerId = btn.dataset.id || card?.dataset.id;
            if (!careerId) return;

            const nameEl = document.getElementById("selectedCareerName");
            const detEl = document.getElementById("careerDetailContent");

            try {
                const res = await fetch(`${window.location.origin}/api/pathway/${careerId}`);
                if (!res.ok) throw new Error("Pathway Error");
                const data = await res.json();
                if (nameEl) nameEl.textContent = data.career.title;
                if (detEl) {
                    detEl.innerHTML = `
                        <div style="display:grid; grid-template-columns:repeat(auto-fit, minmax(200px, 1fr)); gap:15px; margin-bottom:20px;">
                            <div style="background:#f8fafc; padding:15px; border-radius:10px; border-left:4px solid #6366f1;"><span>STREAM</span><br><strong>${escapeHTML(data.career.stream)}</strong></div>
                            <div style="background:#f8fafc; padding:15px; border-radius:10px; border-left:4px solid #3b82f6;"><span>COURSES (GRAPH BFS)</span><br><strong>${(data.courses || []).map(c => escapeHTML(c.name)).join(", ") || "None"}</strong></div>
                            <div style="background:#f8fafc; padding:15px; border-radius:10px; border-left:4px solid #10b981;"><span>ENTRANCE EXAMS</span><br><strong>${(data.exams || []).map(ex => escapeHTML(ex.name)).join(", ") || "None"}</strong></div>
                        </div>
                        <div style="background:#fff; padding:20px; border-radius:12px; border:1px solid #e2e8f0;">
                            <h3 style="margin-top:0;">Prerequisite Colleges in AP & TS (Traversed Graph Nodes)</h3>
                            <p style="line-height:1.9; color:#334155;">${(data.colleges || []).map(c => `• <strong>${escapeHTML(c.name)}</strong> (${escapeHTML(c.city)}) — Tuition: <strong>₹${c.annualFee.toLocaleString()}/yr</strong>`).join("<br>") || "No linked colleges"}</p>
                        </div>
                    `;
                    smoothScroll("careerDetails");
                }
            } catch (err) {
                console.error("Pathway fetch failed:", err);
            }
        });

        fetchCareers();
    }

    function escapeHTML(str) {
        return String(str || "").replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/"/g, "&quot;").replace(/'/g, "&#039;");
    }

    function smoothScroll(id) {
        const el = document.getElementById(id);
        if (el) el.scrollIntoView({ behavior: "smooth", block: "start" });
    }
});