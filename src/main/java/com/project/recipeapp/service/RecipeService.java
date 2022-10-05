package com.project.recipeapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.recipeapp.model.FridgeEntity;
import com.project.recipeapp.model.MyRecipeEntity;
import com.project.recipeapp.persistence.IngredientRepository;
import com.project.recipeapp.persistence.MyIngredientRepository;
import com.project.recipeapp.persistence.MyRecipeRepository;
import com.project.recipeapp.persistence.RecipeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecipeService {
	
	@Autowired
	private RecipeRepository Rrepository;
	@Autowired
	private MyRecipeRepository MRrepository;
	@Autowired
	private IngredientRepository Irepository;
	@Autowired
	private MyIngredientRepository MIrepository;
	
	public String create(final MyRecipeEntity entity){
		String msg = "my recipe is updated.";
		validate(entity);
		MRrepository.save(entity);
		return msg;
	}
}
