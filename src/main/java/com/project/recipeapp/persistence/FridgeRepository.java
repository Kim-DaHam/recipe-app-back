package com.project.recipeapp.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.recipeapp.model.FridgeEntity;
import com.project.recipeapp.model.UserEntity;

@Repository
public interface FridgeRepository extends JpaRepository<FridgeEntity, String>{
	Optional<FridgeEntity> findByKey(String key);
	List<FridgeEntity> findByMkey(String mkey);
	boolean existsByKey(String key);
	Optional<FridgeEntity> deleteByKey(String key);
}