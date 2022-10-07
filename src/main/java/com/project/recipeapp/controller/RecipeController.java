package com.project.recipeapp.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.recipeapp.dto.FridgeDTO;
import com.project.recipeapp.dto.FridgeResponseDTO;
import com.project.recipeapp.dto.IngredientDTO;
import com.project.recipeapp.dto.IngredientResponseDTO;
import com.project.recipeapp.dto.RecipeDTO;
import com.project.recipeapp.dto.RecipeResponseDTO;
import com.project.recipeapp.model.IngredientEntity;
import com.project.recipeapp.model.RecipeEntity;
import com.project.recipeapp.service.RecipeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins="*")
@RestController
@RequestMapping("recipe")
public class RecipeController {
	
	@Autowired
	private RecipeService service;
	
	@PostMapping
	public ResponseEntity<?> createRecipe(@AuthenticationPrincipal String userId, @RequestBody RecipeDTO dto){
		try {
			RecipeEntity entity = RecipeDTO.toEntity(dto);
			entity.setRmember(userId);
			entity.setRdate(LocalDate.now());
			
			List<RecipeEntity> entities = service.Rcreate(entity);
			List<RecipeDTO> dtos = entities.stream().map(RecipeDTO::new)
					.collect(Collectors.toList());
			
			RecipeResponseDTO<RecipeDTO> response = RecipeResponseDTO
					.<RecipeDTO>builder().data(dtos).build();
			
			return ResponseEntity.ok().body(response);
		}catch(Exception e) {
			String error = e.getMessage();
			RecipeResponseDTO<RecipeDTO> response = RecipeResponseDTO
					.<RecipeDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@PostMapping("/ingredient")
	public ResponseEntity<?> createIngredient(@AuthenticationPrincipal String userId, @RequestBody IngredientDTO dto){
		try {
			IngredientEntity entity = IngredientDTO.toEntity(dto);
			entity.setRkey(dto.getRkey());
			
			List<IngredientEntity> entities = service.Icreate(entity);
			
			List<IngredientDTO> dtos = entities.stream().map(IngredientDTO::new)
					.collect(Collectors.toList());
			
			IngredientResponseDTO<IngredientDTO> response = IngredientResponseDTO
					.<IngredientDTO>builder().data(dtos).build();
			
			return ResponseEntity.ok().body(response);
		}catch(Exception e) {
			String error = e.getMessage();
			IngredientResponseDTO<IngredientDTO> response = IngredientResponseDTO
					.<IngredientDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@GetMapping
	public ResponseEntity<?> retrieveRecipe(@AuthenticationPrincipal String userId, @RequestBody Map<String, String> cate){
		String category = cate.get("category");
		List<RecipeEntity> entities = service.Rretrieve(userId, category);
		List<RecipeDTO> dtos = entities.stream().map(RecipeDTO::new)
				.collect(Collectors.toList());
		RecipeResponseDTO<RecipeDTO> response = RecipeResponseDTO
				.<RecipeDTO>builder().data(dtos).build();
		
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping("/ingredient")
	public ResponseEntity<?> retrieveIngredient(@AuthenticationPrincipal String userId, @RequestBody Map<String, String> recipeKey){
		String rkey = recipeKey.get("rkey");
		List<IngredientEntity> entities = service.Iretrieve(rkey);
		List<IngredientDTO> dtos = entities.stream().map(IngredientDTO::new)
				.collect(Collectors.toList());
		IngredientResponseDTO<IngredientDTO> response = IngredientResponseDTO
				.<IngredientDTO>builder().data(dtos).build();
		
		return ResponseEntity.ok().body(response);
	}
	
	@DeleteMapping("/ingredient")
	public ResponseEntity<?> deleteIngredient(@AuthenticationPrincipal String userId, @RequestBody IngredientDTO dto){
		try {
			IngredientEntity entity = IngredientDTO.toEntity(dto);
			List<IngredientEntity> entities = service.Idelete(entity);
			List<IngredientDTO> dtos = entities.stream().map(IngredientDTO::new)
					.collect(Collectors.toList());
			
			IngredientResponseDTO<IngredientDTO> response = IngredientResponseDTO
					.<IngredientDTO>builder().data(dtos).build();
			
			return ResponseEntity.ok().body(response);
		}catch(Exception e) {
			String error = e.getMessage();
			IngredientResponseDTO<IngredientDTO> response = IngredientResponseDTO
					.<IngredientDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}
}
