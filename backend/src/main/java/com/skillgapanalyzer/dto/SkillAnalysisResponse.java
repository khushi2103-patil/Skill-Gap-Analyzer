package com.skillgapanalyzer.dto;

import java.util.List;

/**
 * DTO for skill gap analysis response
 */
public class SkillAnalysisResponse {
    
    private String roleName;
    private List<String> matchedSkills;
    private List<String> missingSkills;
    private double matchPercentage;
    private int totalRequiredSkills;
    private int matchedCount;
    
    // Constructors
    public SkillAnalysisResponse() {
    }
    
    public SkillAnalysisResponse(String roleName, List<String> matchedSkills, 
                                List<String> missingSkills, double matchPercentage,
                                int totalRequiredSkills, int matchedCount) {
        this.roleName = roleName;
        this.matchedSkills = matchedSkills;
        this.missingSkills = missingSkills;
        this.matchPercentage = matchPercentage;
        this.totalRequiredSkills = totalRequiredSkills;
        this.matchedCount = matchedCount;
    }
    
    // Getters and Setters
    public String getRoleName() {
        return roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    public List<String> getMatchedSkills() {
        return matchedSkills;
    }
    
    public void setMatchedSkills(List<String> matchedSkills) {
        this.matchedSkills = matchedSkills;
    }
    
    public List<String> getMissingSkills() {
        return missingSkills;
    }
    
    public void setMissingSkills(List<String> missingSkills) {
        this.missingSkills = missingSkills;
    }
    
    public double getMatchPercentage() {
        return matchPercentage;
    }
    
    public void setMatchPercentage(double matchPercentage) {
        this.matchPercentage = matchPercentage;
    }
    
    public int getTotalRequiredSkills() {
        return totalRequiredSkills;
    }
    
    public void setTotalRequiredSkills(int totalRequiredSkills) {
        this.totalRequiredSkills = totalRequiredSkills;
    }
    
    public int getMatchedCount() {
        return matchedCount;
    }
    
    public void setMatchedCount(int matchedCount) {
        this.matchedCount = matchedCount;
    }
}
