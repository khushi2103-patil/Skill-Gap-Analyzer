package com.skillgapanalyzer.repository;

import com.skillgapanalyzer.model.CareerRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for CareerRole entity
 */
@Repository
public interface CareerRoleRepository extends JpaRepository<CareerRole, Long> {
    
    Optional<CareerRole> findByName(String name);
}
