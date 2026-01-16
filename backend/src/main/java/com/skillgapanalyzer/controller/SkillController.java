package com.skillgapanalyzer.controller;

import com.skillgapanalyzer.dto.RoadmapResponse;
import com.skillgapanalyzer.dto.SkillAnalysisRequest;
import com.skillgapanalyzer.dto.SkillAnalysisResponse;
import com.skillgapanalyzer.model.Skill;
import com.skillgapanalyzer.service.SkillService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for skill analysis endpoints
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/skills")
public class SkillController {
    
    @Autowired
    SkillService skillService;
    
    /**
     * Analyze skill gap
     */
    @PostMapping("/analyze")
    public ResponseEntity<?> analyzeSkillGap(@Valid @RequestBody SkillAnalysisRequest request) {
        try {
            SkillAnalysisResponse response = skillService.analyzeSkillGap(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    
    /**
     * Get learning roadmap for a role
     */
    @GetMapping("/roadmap/{roleId}")
    public ResponseEntity<?> getRoadmap(@PathVariable Long roleId) {
        try {
            RoadmapResponse roadmap = skillService.generateRoadmap(roleId);
            return ResponseEntity.ok(roadmap);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    
    /**
     * Get all available skills
     */
    @GetMapping
    public ResponseEntity<List<Skill>> getAllSkills() {
        List<Skill> skills = skillService.getAllSkills();
        return ResponseEntity.ok(skills);
    }
}
