package com.skillgapanalyzer.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

/**
 * Skill entity representing technical skills
 */
@Entity
@Table(name = "skills")
public class Skill {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;
    
    @Column(length = 500)
    private String description;
    
    @ManyToMany(mappedBy = "skills")
    private Set<User> users = new HashSet<>();
    
    @ManyToMany(mappedBy = "requiredSkills")
    private Set<CareerRole> careerRoles = new HashSet<>();
    
    // Constructors
    public Skill() {
    }
    
    public Skill(String name) {
        this.name = name;
    }
    
    public Skill(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Set<User> getUsers() {
        return users;
    }
    
    public void setUsers(Set<User> users) {
        this.users = users;
    }
    
    public Set<CareerRole> getCareerRoles() {
        return careerRoles;
    }
    
    public void setCareerRoles(Set<CareerRole> careerRoles) {
        this.careerRoles = careerRoles;
    }
}
