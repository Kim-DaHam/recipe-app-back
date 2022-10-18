package com.project.recipeapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.recipeapp.dto.FridgeDTO;
import com.project.recipeapp.dto.FridgeResponseDTO;
import com.project.recipeapp.dto.ResponseDTO;
import com.project.recipeapp.dto.UserDTO;
import com.project.recipeapp.model.FridgeEntity;
import com.project.recipeapp.model.UserEntity;
import com.project.recipeapp.security.TokenProvider;
import com.project.recipeapp.service.UserService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*")
@Slf4j 
@RestController 
@RequestMapping("/auth") 
public class UserController { 
    @Autowired 
    private UserService userService;
    
    @Autowired 
    private TokenProvider tokenProvider;
    
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    @GetMapping
    public ResponseEntity<?> getUserName(@AuthenticationPrincipal String mkey) {
    	List<String> msg = new ArrayList<>();
    	String uname = userService.getuname(mkey);
		msg.add(uname);
		ResponseDTO<String> response = 
				ResponseDTO.<String>builder().data(msg).build();
				return ResponseEntity.ok().body(response);
    }
    
    @PostMapping("/signup") 
    public ResponseEntity<?>registerUser(@RequestBody UserDTO userDTO){
        try {
            UserEntity user = UserEntity.builder()
                .memail(userDTO.getMemail()) 
                .mname(userDTO.getMname())
                .mpw(passwordEncoder.encode(userDTO.getMpw()))
                .build();

            UserEntity registeredUser = userService.create(user);
            UserDTO responseUserDTO = userDTO.builder()
                .memail(registeredUser.getMemail())
                .mkey(registeredUser.getMkey())
                .mname(registeredUser.getMname())
                .build();
            return ResponseEntity.ok().body(responseUserDTO);
        }catch(Exception e){ 
            ResponseDTO responseDTO = ResponseDTO.builder()
            		.error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDTO);
        } 
    }

    @PostMapping("/signin") 
    public ResponseEntity<?>authenticate(@RequestBody UserDTO userDTO){
    	System.out.println(userDTO);
    	System.out.println(userDTO.getMpw());
        UserEntity user = userService.getByCredentials(
        		userDTO.getMemail(),
        		userDTO.getMpw(),
        		passwordEncoder);
        
        if(user !=null){
        	final String token = tokenProvider.create(user);   
        	final UserDTO responseUserDTO = UserDTO.builder()
                            .memail(user.getMemail()) 
                            .mkey(user.getMkey())
                            .token(token) 
                            .build();
        	return ResponseEntity.ok().body(responseUserDTO);
            } else { 
        	   ResponseDTO responseDTO = ResponseDTO.builder() 
        			   .error("Login failed") 
        			   .build();
        	   return ResponseEntity.badRequest().body(responseDTO);
            }	
    	}
    
    @PostMapping("/emailcheck")
    public boolean checkEmailDuplicate(@RequestBody String email){
    	boolean isAlreadyExist = userService.emailCheck(email);
		return isAlreadyExist;
    }
}