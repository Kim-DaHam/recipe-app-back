package com.project.recipeapp.dto;

import com.project.recipeapp.model.IngredientEntity;
import com.project.recipeapp.model.RecipeEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class IngredientDTO {
	private RecipeEntity rkey;
	private String ingredient;
	
	public IngredientDTO(final IngredientEntity entity) {
		this.rkey = entity.getRkey();
		this.ingredient = entity.getIngredient();
	}
	
	public static IngredientEntity toEntity(final IngredientDTO dto) {
		return IngredientEntity.builder()
				.rkey(dto.getRkey())
				.ingredient(dto.getIngredient())
				.build();
	}
}
