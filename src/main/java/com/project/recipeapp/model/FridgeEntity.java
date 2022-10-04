package com.project.recipeapp.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="T_GROCERY")
public class FridgeEntity {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid")
	@Column(nullable=false)
	private String G_KEY;
	
	@Column(nullable=false)
	private String G_NAME;
	
	private String G_CATEGORY;
	
	private boolean G_CHECK;
	
	private LocalDate G_EXDATE;
	
	@Column(nullable=false)
	private LocalDate G_DATE;
	
	@Column(nullable=false)
	private String G_MEMBER;
}