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
	@Column(name="G_KEY", nullable=false)
	private String key;
	
	@Column(name="G_NAME", nullable=false, length=50)
	private String name;
	
	@Column(name="G_CATEGORY", length=20)
	private String category;
	
	@Column(name="G_CHECK", nullable=false)
	private boolean checked;
	
	@Column(name="G_EXDATE")
	private LocalDate exdate;
	
	@Column(name="G_DATE", nullable=false)
	private LocalDate date;
	
	//@JoinColumn(name="R_MEMBER", nullable=false)
	//private MemberEntity mkey;
	@Column(name="G_MEMBER", nullable=false)
	private String member;
}