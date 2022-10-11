package com.project.recipeapp.dto;

import java.time.LocalDate;

import com.project.recipeapp.model.FridgeEntity;
import com.project.recipeapp.model.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FridgeDTO {
	private String key;
	private String name;
	private String category;
	private boolean checked;
	private LocalDate exdate;
	private String mkey;
	
	public FridgeDTO(final FridgeEntity entity) {
		this.key = entity.getKey();
		this.name = entity.getName();
		this.category = entity.getCategory();
		this.checked = entity.isChecked();
		this.exdate = entity.getExdate();
		this.mkey = entity.getMkey();
	}
	
	public static FridgeEntity toEntity(final FridgeDTO dto) {
		return FridgeEntity.builder()
				.key(dto.getKey())
				.name(dto.getName())
				.category(dto.getCategory())
				.checked(dto.isChecked())
				.exdate(dto.getExdate())
				.mkey(dto.getMkey())
				.build();
	}
}