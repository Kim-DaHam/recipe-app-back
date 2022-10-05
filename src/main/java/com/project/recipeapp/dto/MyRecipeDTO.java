package com.project.recipeapp.dto;

import com.project.recipeapp.model.MyRecipeEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MyRecipeDTO {
	private String mrkey;
	private String mrname;
	private String mrcontent;
	private String mrcategory;
	private String mrimage;
	
	public MyRecipeDTO(final MyRecipeEntity entity) {
		this.mrkey = entity.getMrkey();
		this.mrname = entity.getMrname();
		this.mrcontent = entity.getMrcontent();
		this.mrcategory = entity.getMrcategory();
		this.mrimage = entity.getMrimage();
	}
	
	public static MyRecipeEntity toEntity(final MyRecipeDTO dto) {
		return MyRecipeEntity.builder()
				.mrkey(dto.getMrkey())
				.mrname(dto.getMrname())
				.mrcontent(dto.getMrcontent())
				.mrcategory(dto.getMrcategory())
				.mrimage(dto.getMrimage())
				.build();
	}
}
