package com.skillgapanalyzer.service;

import com.skillgapanalyzer.dto.RoadmapItem;
import com.skillgapanalyzer.dto.RoadmapResponse;
import com.skillgapanalyzer.dto.SkillAnalysisRequest;
import com.skillgapanalyzer.dto.SkillAnalysisResponse;
import com.skillgapanalyzer.model.CareerRole;
import com.skillgapanalyzer.model.Skill;
import com.skillgapanalyzer.repository.CareerRoleRepository;
import com.skillgapanalyzer.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service for skill gap analysis and roadmap generation
 */
@Service
public class SkillService {
    
    @Autowired
    CareerRoleRepository careerRoleRepository;
    
    @Autowired
    SkillRepository skillRepository;
    
    /**
     * Analyze skill gap between user skills and required role skills
     */
    public SkillAnalysisResponse analyzeSkillGap(SkillAnalysisRequest request) {
        CareerRole careerRole = careerRoleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new RuntimeException("Career role not found with id: " + request.getRoleId()));
        
        Set<Skill> requiredSkills = careerRole.getRequiredSkills();
        List<Long> userSkillIds = request.getUserSkillIds() != null ? request.getUserSkillIds() : new ArrayList<>();
        
        List<Skill> userSkills = userSkillIds.isEmpty() 
            ? new ArrayList<>() 
            : skillRepository.findByIdIn(userSkillIds);
        
        // Find matched and missing skills
        List<String> matchedSkills = userSkills.stream()
                .filter(requiredSkills::contains)
                .map(Skill::getName)
                .collect(Collectors.toList());
        
        List<String> missingSkills = requiredSkills.stream()
                .filter(skill -> !userSkills.contains(skill))
                .map(Skill::getName)
                .collect(Collectors.toList());
        
        // Calculate match percentage
        int totalRequired = requiredSkills.size();
        int matchedCount = matchedSkills.size();
        double matchPercentage = totalRequired > 0 
            ? (double) matchedCount / totalRequired * 100 
            : 0.0;
        
        return new SkillAnalysisResponse(
            careerRole.getName(),
            matchedSkills,
            missingSkills,
            matchPercentage,
            totalRequired,
            matchedCount
        );
    }
    
    /**
     * Generate learning roadmap for a career role
     */
    public RoadmapResponse generateRoadmap(Long roleId) {
        CareerRole careerRole = careerRoleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Career role not found with id: " + roleId));
        
        Set<Skill> requiredSkills = careerRole.getRequiredSkills();
        
        // Divide skills into stages (simple logic - can be enhanced)
        List<RoadmapItem> beginner = new ArrayList<>();
        List<RoadmapItem> intermediate = new ArrayList<>();
        List<RoadmapItem> advanced = new ArrayList<>();
        
        int skillCount = requiredSkills.size();
        int index = 0;
        
        for (Skill skill : requiredSkills) {
            List<String> resources = generateResourcesForSkill(skill.getName());
            RoadmapItem item = new RoadmapItem(
                skill.getName(),
                "",
                skill.getDescription() != null ? skill.getDescription() : "Learn " + skill.getName(),
                resources
            );
            
            // Distribute skills across stages
            if (index < skillCount / 3) {
                item.setStage("BEGINNER");
                beginner.add(item);
            } else if (index < (skillCount * 2) / 3) {
                item.setStage("INTERMEDIATE");
                intermediate.add(item);
            } else {
                item.setStage("ADVANCED");
                advanced.add(item);
            }
            
            index++;
        }
        
        return new RoadmapResponse(careerRole.getName(), beginner, intermediate, advanced);
    }
    
    /**
     * Generate learning resources for a skill
     */
    private List<String> generateResourcesForSkill(String skillName) {
        List<String> resources = new ArrayList<>();
        
        // Generic resources - can be customized per skill
        resources.add("Official documentation: " + skillName);
        resources.add("Online course: " + skillName + " Fundamentals");
        resources.add("Practice projects: Build a " + skillName + " application");
        resources.add("Community forums: Join " + skillName + " developer community");
        
        return resources;
    }
    
    /**
     * Get all available skills
     */
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }
}
