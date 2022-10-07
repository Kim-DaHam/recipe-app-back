package com.project.recipeapp.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name="T_RECIPE")
public class RecipeEntity {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid")
	@Column(name="R_KEY", nullable=false)
	private String rkey;
	
	@Column(name="R_NAME", nullable=false, length=50)
	private String rname;
	
	@Column(name="R_CONTENT", nullable=false, length=5000)
	private String rcontent;
	
	@Column(name="R_CATEGORY", length=10)
	private String rcategory;
	
	@Column(name="R_IMAGE", length=50)
	private String rimage;
	
	@Column(name="R_DATE", nullable=false)
	private LocalDate rdate;

	//@JoinColumn(name="R_MEMBER", nullable=false)
	//private MemberEntity mkey;
	@Column(name="R_MEMBER", nullable=false)
	private String rmember;
}