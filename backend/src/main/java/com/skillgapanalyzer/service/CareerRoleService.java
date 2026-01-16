package com.skillgapanalyzer.service;

import com.skillgapanalyzer.model.CareerRole;
import com.skillgapanalyzer.repository.CareerRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for career role operations
 */
@Service
public class CareerRoleService {
    
    @Autowired
    CareerRoleRepository careerRoleRepository;
    
    /**
     * Get all career roles
     */
    public List<CareerRole> getAllRoles() {
        return careerRoleRepository.findAll();
    }
    
    /**
     * Get career role by ID
     */
    public CareerRole getRoleById(Long id) {
        return careerRoleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Career role not found with id: " + id));
    }
}
