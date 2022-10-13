package com.project.recipeapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.recipeapp.model.UserEntity;
import com.project.recipeapp.persistence.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j 
@Service 
public class UserService { 
    @Autowired 
    private UserRepository userRepository;
    
    public UserEntity create(final UserEntity userEntity) { 
        if(userEntity == null || userEntity.getMemail() == null) { 
            throw new RuntimeException("Invalid arguments");
        } 
        final String email = userEntity.getMemail();
        if(userRepository.existsByMemail(email)) { 
            log.warn("Email already exists {}",email);
            throw new RuntimeException("Email already exists");
        }
        
        return userRepository.save(userEntity);
    } 
    
    public UserEntity getByCredentials(final String email, final String password, final PasswordEncoder encoder) { 
        final UserEntity originalUser = userRepository.findByMemail(email);
        
        if(originalUser != null && encoder.matches(password, originalUser.getMpw())) {
        	return originalUser;
        }
        return null;
    }
    
    public Boolean emailRetrieve(final String email) {
    	final Boolean isEmailExits = userRepository.existsByMemail(email);
    	return isEmailExits;
    }
}