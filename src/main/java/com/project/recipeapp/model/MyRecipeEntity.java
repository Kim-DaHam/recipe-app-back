package com.project.recipeapp.model;

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
@Table(name="T_MEMBER_RECIPE")
public class MyRecipeEntity {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid")
	@Column(name="MR_KEY", nullable=false)
	private String mrkey;
	
	@Column(name="MR_NAME", nullable=false, length=50)
	private String mrname;
	
	@Column(name="MR_CONTENT", nullable=false, length=5000)
	private String mrcontent;
	
	@Column(name="MR_CATEGORY", length=10)
	private String mrcategory;
	
	@Column(name="MR_IMAGE", length=50)
	private String mrimage;
	
	@Column(name="MR_MEMBER", nullable=false)
	private String mrmember;
}