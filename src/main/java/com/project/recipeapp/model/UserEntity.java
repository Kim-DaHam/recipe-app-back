package com.project.recipeapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@Entity 
@Builder
@NoArgsConstructor 
@AllArgsConstructor 
@Table(name="T_MEMBER", uniqueConstraints = {@UniqueConstraint(columnNames = "M_EMAIL")}) 
public class UserEntity { 
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy="uuid")
    @Column(name="M_KEY", nullable=false)
    private String mkey;
    
    @Column(name="M_NAME", nullable=false) 
    private String mname;
    
    @Column(name="M_EMAIL", nullable=false) 
    private String memail;

    @Column(name="M_PW", nullable=false) 
    private String mpw;
}