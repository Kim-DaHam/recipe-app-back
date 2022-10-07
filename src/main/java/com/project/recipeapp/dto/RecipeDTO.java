package com.project.recipeapp.dto;

import com.project.recipeapp.model.RecipeEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RecipeDTO {
	private String rkey;
	private String rname;
	private String rcontent;
	private String rcategory;
	private String rimage;
	
	public RecipeDTO(final RecipeEntity entity) {
		this.rkey = entity.getRkey();
		this.rname = entity.getRname();
		this.rcontent = entity.getRcontent();
		this.rcategory = entity.getRcategory();
		this.rimage = entity.getRimage();
	}
	
	public static RecipeEntity toEntity(final RecipeDTO dto) {
		return RecipeEntity.builder()
				.rkey(dto.getRkey())
				.rname(dto.getRname())
				.rcontent(dto.getRcontent())
				.rcategory(dto.getRcategory())
				.rimage(dto.getRimage())
				.build();
	}
}