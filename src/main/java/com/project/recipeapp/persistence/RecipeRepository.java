package com.project.recipeapp.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.recipeapp.model.IngredientEntity;
import com.project.recipeapp.model.RecipeEntity;
import com.project.recipeapp.model.UserEntity;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, String>{
	List<RecipeEntity> findByMkey(String mkey);
	List<RecipeEntity> findByRmemberAndRcategory(String mkey, String categorty);
}