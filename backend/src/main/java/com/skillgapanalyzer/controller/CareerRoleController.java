package com.skillgapanalyzer.controller;

import com.skillgapanalyzer.model.CareerRole;
import com.skillgapanalyzer.service.CareerRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for career role endpoints
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/roles")
public class CareerRoleController {
    
    @Autowired
    CareerRoleService careerRoleService;
    
    /**
     * Get all career roles
     */
    @GetMapping
    public ResponseEntity<List<CareerRole>> getAllRoles() {
        List<CareerRole> roles = careerRoleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }
    
    /**
     * Get career role by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<CareerRole> getRoleById(@PathVariable Long id) {
        try {
            CareerRole role = careerRoleService.getRoleById(id);
            return ResponseEntity.ok(role);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
