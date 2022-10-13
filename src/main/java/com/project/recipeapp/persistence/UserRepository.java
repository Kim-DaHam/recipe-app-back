package com.project.recipeapp.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.recipeapp.model.UserEntity;

@Repository 
public interface UserRepository extends JpaRepository<UserEntity, String>{ 
    UserEntity findByMemail(String email);
    UserEntity findByMemailAndMpw(String email, String password);
    boolean existsByMemail(String email);
}