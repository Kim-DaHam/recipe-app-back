package com.project.recipeapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@Builder 
@NoArgsConstructor 
@AllArgsConstructor 
public class UserDTO { 
    private String token;
    private String memail;
    private String mname;
    private String mpw;
    private String mkey;
}