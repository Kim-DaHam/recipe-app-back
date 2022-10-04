package com.project.recipeapp.dto;

import java.time.LocalDate;

import com.project.recipeapp.model.FridgeEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FridgeDTO {
	private String G_KEY;
	private String G_NAME;
	private String G_CATEGORY;
	private boolean G_CHECK;
	private LocalDate G_EXDATE;
	private LocalDate G_DATE;
	//private String G_MEMBER;
	
	public FridgeDTO(final FridgeEntity entity) {
		this.G_KEY = entity.getG_KEY();
		this.G_NAME = entity.getG_NAME();
		this.G_CATEGORY = entity.getG_CATEGORY();
		this.G_CHECK = entity.isG_CHECK();
		this.G_EXDATE = entity.getG_EXDATE();
		this.G_DATE = entity.getG_DATE();
	}
	
	public static FridgeEntity toEntity(final FridgeDTO dto) {
		return FridgeEntity.builder()
				.G_KEY(dto.getG_KEY())
				.G_NAME(dto.getG_NAME())
				.G_CATEGORY(dto.getG_CATEGORY())
				.G_CHECK(dto.isG_CHECK())
				.G_EXDATE(dto.getG_EXDATE())
				.G_DATE(dto.getG_DATE())
				.build();
	}
}