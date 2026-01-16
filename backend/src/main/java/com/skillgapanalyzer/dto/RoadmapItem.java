package com.skillgapanalyzer.dto;

import java.util.List;

/**
 * DTO for roadmap item
 */
public class RoadmapItem {
    
    private String skill;
    private String stage; // BEGINNER, INTERMEDIATE, ADVANCED
    private String description;
    private List<String> resources;
    
    // Constructors
    public RoadmapItem() {
    }
    
    public RoadmapItem(String skill, String stage, String description, List<String> resources) {
        this.skill = skill;
        this.stage = stage;
        this.description = description;
        this.resources = resources;
    }
    
    // Getters and Setters
    public String getSkill() {
        return skill;
    }
    
    public void setSkill(String skill) {
        this.skill = skill;
    }
    
    public String getStage() {
        return stage;
    }
    
    public void setStage(String stage) {
        this.stage = stage;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public List<String> getResources() {
        return resources;
    }
    
    public void setResources(List<String> resources) {
        this.resources = resources;
    }
}
