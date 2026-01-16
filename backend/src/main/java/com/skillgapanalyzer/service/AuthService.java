package com.skillgapanalyzer.service;

import com.skillgapanalyzer.dto.JwtResponse;
import com.skillgapanalyzer.dto.LoginRequest;
import com.skillgapanalyzer.dto.RegisterRequest;
import com.skillgapanalyzer.model.ERole;
import com.skillgapanalyzer.model.Role;
import com.skillgapanalyzer.model.User;
import com.skillgapanalyzer.repository.RoleRepository;
import com.skillgapanalyzer.repository.UserRepository;
import com.skillgapanalyzer.security.JwtUtils;
import com.skillgapanalyzer.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service for authentication operations
 */
@Service
public class AuthService {
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    RoleRepository roleRepository;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    JwtUtils jwtUtils;
    
    /**
     * Register a new user
     */
    @Transactional
    public JwtResponse register(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new RuntimeException("Error: Email is already taken!");
        }
        
        if (userRepository.existsByName(registerRequest.getName())) {
            throw new RuntimeException("Error: Username is already taken!");
        }
        
        // Create new user
        User user = new User(registerRequest.getName(), 
                           registerRequest.getEmail(),
                           passwordEncoder.encode(registerRequest.getPassword()));
        
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
        
        user.setRoles(roles);
        user = userRepository.save(user);
        
        // Authenticate and generate token
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(registerRequest.getEmail(), 
                                                  registerRequest.getPassword()));
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        List<String> userRoles = userPrincipal.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        
        return new JwtResponse(jwt, userPrincipal.getId(), userPrincipal.getName(), 
                             userPrincipal.getUsername(), userRoles);
    }
    
    /**
     * Authenticate user and generate JWT token
     */
    public JwtResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), 
                                                  loginRequest.getPassword()));
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        List<String> userRoles = userPrincipal.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        
        return new JwtResponse(jwt, userPrincipal.getId(), userPrincipal.getName(), 
                             userPrincipal.getUsername(), userRoles);
    }
}
