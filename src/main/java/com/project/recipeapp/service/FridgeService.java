package com.project.recipeapp.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.recipeapp.model.FridgeEntity;
import com.project.recipeapp.model.UserEntity;
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
		return repository.findByMkey(entity.getMkey());
	}
	
	public List<FridgeEntity> retrieve(final String mkey){
		return repository.findByMkey(mkey);
	}
	
	public List<FridgeEntity> update(final FridgeEntity entity) {
		 validate(entity);
		 final Optional<FridgeEntity> original = repository.findByKey(entity.getKey());
		 original.ifPresent(grocery -> {
			 grocery.setName(entity.getName());
			 grocery.setCategory(entity.getCategory());
			 grocery.setChecked(entity.isChecked());
			 repository.save(grocery);
		 });
		 
		 return repository.findByMkey(entity.getMkey());
	}
	
	@Transactional
	public List<FridgeEntity> delete(final List<FridgeEntity> entities, final String mkey){
		entities.forEach((entity)->{
			if(repository.existsByKey(entity.getKey()))
				repository.deleteByKey(entity.getKey());
			else
				throw new RuntimeException("key does not exist");
		});
		
		return repository.findByMkey(mkey);
	}
	
	public void validate(final FridgeEntity entity) {
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