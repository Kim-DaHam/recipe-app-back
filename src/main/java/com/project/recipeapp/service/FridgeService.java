package com.project.recipeapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.recipeapp.model.FridgeEntity;
import com.project.recipeapp.persistence.FridgeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FridgeService {
	
	@Autowired
	private FridgeRepository repository;
	
	public Optional<FridgeEntity> create(final FridgeEntity entity){
		validate(entity);
		repository.save(entity);
		return repository.findByG_KEY(entity.getG_KEY());
	}
	
	public void validate(final FridgeEntity entity) {
		if(entity==null) {
			log.warn("Entity cannot be null");
			throw new RuntimeException("Entity cannot be null.");
		}
		if(entity.getG_MEMBER()==null) {
			log.warn("Unknown user.");
			throw new RuntimeException("Unknown user.");
		}
	}
}