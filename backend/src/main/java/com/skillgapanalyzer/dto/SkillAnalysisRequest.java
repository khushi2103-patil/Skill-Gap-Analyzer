package com.skillgapanalyzer.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * DTO for skill gap analysis request
 */
public class SkillAnalysisRequest {
    
    @NotNull
    private Long roleId;
    
    private List<Long> userSkillIds;
    
    // Constructors
    public SkillAnalysisRequest() {
    }
    
    public SkillAnalysisRequest(Long roleId, List<Long> userSkillIds) {
        this.roleId = roleId;
        this.userSkillIds = userSkillIds;
    }
    
    // Getters and Setters
    public Long getRoleId() {
        return roleId;
    }
    
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    
    public List<Long> getUserSkillIds() {
        return userSkillIds;
    }
    
    public void setUserSkillIds(List<Long> userSkillIds) {
        this.userSkillIds = userSkillIds;
    }
}
