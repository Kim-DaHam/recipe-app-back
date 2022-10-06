package com.project.recipeapp.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.recipeapp.model.IngredientEntity;

@Repository
public interface IngredientRepository extends JpaRepository<IngredientEntity, String>{
	Optional<IngredientEntity> findByIkey(String key);
	Optional<IngredientEntity> findByRkey(String key);
	List<IngredientEntity> deleteByIkey(String key);
	boolean existsByIkey(String key);
}