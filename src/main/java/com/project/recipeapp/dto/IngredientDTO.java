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
	private String ikey;
	private String ingredient;
	private RecipeEntity rkey;
	
	public IngredientDTO(final IngredientEntity entity) {
		this.ikey = entity.getIkey();
		this.ingredient = entity.getIngredient();
		this.rkey = entity.getRkey();
	}
	
	public static IngredientEntity toEntity(final IngredientDTO dto) {
		return IngredientEntity.builder()
				.ikey(dto.getIkey())
				.ingredient(dto.getIngredient())
				.rkey(dto.getRkey())
				.build();
	}
}
