package com.skillgapanalyzer.dto;

import java.util.List;

/**
 * DTO for learning roadmap response
 */
public class RoadmapResponse {
    
    private String roleName;
    private List<RoadmapItem> beginner;
    private List<RoadmapItem> intermediate;
    private List<RoadmapItem> advanced;
    
    // Constructors
    public RoadmapResponse() {
    }
    
    public RoadmapResponse(String roleName, List<RoadmapItem> beginner, 
                          List<RoadmapItem> intermediate, List<RoadmapItem> advanced) {
        this.roleName = roleName;
        this.beginner = beginner;
        this.intermediate = intermediate;
        this.advanced = advanced;
    }
    
    // Getters and Setters
    public String getRoleName() {
        return roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    public List<RoadmapItem> getBeginner() {
        return beginner;
    }
    
    public void setBeginner(List<RoadmapItem> beginner) {
        this.beginner = beginner;
    }
    
    public List<RoadmapItem> getIntermediate() {
        return intermediate;
    }
    
    public void setIntermediate(List<RoadmapItem> intermediate) {
        this.intermediate = intermediate;
    }
    
    public List<RoadmapItem> getAdvanced() {
        return advanced;
    }
    
    public void setAdvanced(List<RoadmapItem> advanced) {
        this.advanced = advanced;
    }
}
