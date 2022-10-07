package com.project.recipeapp.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@RestController
@RequestMapping("recipe")
public class RecipeController {
	
	@Autowired
	private RecipeService service;
	
	@PostMapping
	public ResponseEntity<?> createRecipe(@RequestBody RecipeDTO dto){
		try {
			RecipeEntity entity = RecipeDTO.toEntity(dto);
			entity.setRmember("temporary-userid");
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
	public ResponseEntity<?> createIngredient(@RequestBody IngredientDTO dto){
		try {
			IngredientEntity entity = IngredientDTO.toEntity(dto);
			entity.setRkey("temporary-recipeid");
			
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
	public ResponseEntity<?> retrieveRecipe(){
		String tempUserId = "temporary-userid";
		List<RecipeEntity> entities = service.Rretrieve(tempUserId);
		List<RecipeDTO> dtos = entities.stream().map(RecipeDTO::new)
				.collect(Collectors.toList());
		RecipeResponseDTO<RecipeDTO> response = RecipeResponseDTO
				.<RecipeDTO>builder().data(dtos).build();
		
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping("/ingredient")
	public ResponseEntity<?> retrieveIngredient(@RequestBody String rkey){
		String tempRecipeId = "temporary-recipeid";
		List<IngredientEntity> entities = service.Iretrieve(tempRecipeId);
		List<IngredientDTO> dtos = entities.stream().map(IngredientDTO::new)
				.collect(Collectors.toList());
		IngredientResponseDTO<IngredientDTO> response = IngredientResponseDTO
				.<IngredientDTO>builder().data(dtos).build();
		
		return ResponseEntity.ok().body(response);
	}
	
	@DeleteMapping("/ingredient")
	public ResponseEntity<?> deleteIngredient(@RequestBody IngredientDTO dto){
		try {
			System.out.println(dto.getIkey());
			System.out.println(dto.getRkey());
			List<IngredientEntity> entities = service.Idelete(dto.getIkey(), dto.getRkey());
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
