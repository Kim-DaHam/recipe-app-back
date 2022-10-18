package com.project.recipeapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.recipeapp.dto.FridgeDTO;
import com.project.recipeapp.model.FridgeEntity;
import com.project.recipeapp.model.IngredientEntity;
import com.project.recipeapp.model.RecipeEntity;
import com.project.recipeapp.model.UserEntity;
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
	
	public List<RecipeEntity> Rcreate(final RecipeEntity entity){
		List<RecipeEntity> Rlist = new ArrayList();
		validate(entity);
		Rrepository.save(entity);
		Rlist.addAll(Rrepository.findByMkey("admin"));
		Rlist.addAll(Rrepository.findByMkey(entity.getMkey()));
		return Rlist;
	}
	
	public List<IngredientEntity> Icreate(final IngredientEntity entity){
		Irepository.save(entity);
		return Irepository.findByRkey(entity.getRkey());
	} 
	
	
	public List<RecipeEntity> Rretrieve(final String mkey, String category){
		List<RecipeEntity> Rlist = new ArrayList();

		if(category=="null") {
			Rlist.addAll(Rrepository.findByMkey("admin"));
			Rlist.addAll(Rrepository.findByMkey(mkey));
		}
		else {
			switch(category) {
			case "아침":
				Rlist.addAll(Rrepository.findByMkeyAndRcategory("admin", "아침"));
				Rlist.addAll(Rrepository.findByMkeyAndRcategory(mkey, "아침"));
				break;
			case "점심":
				Rlist.addAll(Rrepository.findByMkeyAndRcategory("admin", "점심"));
				Rlist.addAll(Rrepository.findByMkeyAndRcategory(mkey, "점심"));
				break;
			case "저녁":
				Rlist.addAll(Rrepository.findByMkeyAndRcategory("admin", "저녁"));
				Rlist.addAll(Rrepository.findByMkeyAndRcategory(mkey, "저녁"));
				break;
			}
		}
		
		return Rlist;
	}
	
	public List<IngredientEntity> Iretrieve(final String rkey){
		List<IngredientEntity> Ilist = new ArrayList();
		Ilist.addAll(Irepository.findByRkey(rkey));
		return Ilist;
	}
	
	@Transactional
	public List<IngredientEntity> Idelete(final IngredientEntity entity){
		if(Irepository.existsByIkey(entity.getIkey()))
			Irepository.deleteByIkey(entity.getIkey());
		else
			throw new RuntimeException("key does not exist");
		
		return Irepository.findByRkey(entity.getRkey());
	}
	
	public void validate(final RecipeEntity entity) {
		if(entity==null) {
			log.warn("Entity cannot be null");
			throw new RuntimeException("Entity cannot be null.");
		}
		if(entity.getMkey()==null) {
			log.warn("Unknown user.");
			throw new RuntimeException("Unknown user.");
		}
	}
}