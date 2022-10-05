package com.project.recipeapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.recipeapp.persistence.RecipeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecipeService {
	
	@Autowired
	private RecipeRepository repository;
	
	//public List<RecipeRepository> create 
}
