package com.project.recipeapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.recipeapp.model.FridgeEntity;
import com.project.recipeapp.model.RecipeEntity;
import com.project.recipeapp.persistence.IngredientRepository;
import com.project.recipeapp.persistence.RecipeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecipeService {
	
	@Autowired
	private RecipeRepository Rrepository;
	@Autowired
	private IngredientRepository Irepository;
	
	public String create(final RecipeEntity entity){
		String msg = "my recipe is updated.";
		validate(entity);
		Rrepository.save(entity);
		return msg;
	}
	
	public List<RecipeEntity> retrieve(final List<RecipeEntity> entities, final String member){
		List<RecipeEntity> Rlist = new ArrayList();
		entities.forEach((entity)->{
			if(entity.getMember() == member)
				Rlist.add(entity);
			if(entity.getMember() == "admin")
				Rlist.add(entity);
		});
		return Rlist;
	}
	
	public void validate(final RecipeEntity entity) {
		if(entity==null) {
			log.warn("Entity cannot be null");
			throw new RuntimeException("Entity cannot be null.");
		}
		if(entity.getMember()==null) {
			log.warn("Unknown user.");
			throw new RuntimeException("Unknown user.");
		}
	}
}
