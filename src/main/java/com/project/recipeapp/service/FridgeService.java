package com.project.recipeapp.service;

import java.util.List;
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
	
	public List<FridgeEntity> create(final FridgeEntity entity){
		validate(entity);
		repository.save(entity);
		return repository.findByMember(entity.getMember());
	}
	
	public List<FridgeEntity> retrieve(final String member){
		return repository.findByMember(member);
	}
	
	public void validate(final FridgeEntity entity) {
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