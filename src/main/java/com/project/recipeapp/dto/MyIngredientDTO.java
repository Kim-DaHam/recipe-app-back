package com.project.recipeapp.dto;

import com.project.recipeapp.model.MyIngredientEntity;
import com.project.recipeapp.model.MyRecipeEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MyIngredientDTO {
	private MyRecipeEntity mrkey;
	private String ingredient;
	
	public MyIngredientDTO(final MyIngredientEntity entity) {
		this.mrkey = entity.getMrkey();
		this.ingredient = entity.getIngredient();
	}
	
	public static MyIngredientEntity toEntity(final MyIngredientDTO dto) {
		return MyIngredientEntity.builder()
				.mrkey(dto.getMrkey())
				.ingredient(dto.getIngredient())
				.build();
	}
}
