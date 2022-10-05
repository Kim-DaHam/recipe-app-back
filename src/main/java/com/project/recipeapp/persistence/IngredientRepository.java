package com.project.recipeapp.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.recipeapp.model.RecipeEntity;

@Repository
public interface IngredientRepository extends JpaRepository<RecipeEntity, String>{
	
}
