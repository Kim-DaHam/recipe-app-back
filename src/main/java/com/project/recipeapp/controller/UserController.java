package com.project.recipeapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.recipeapp.dto.ResponseDTO;
import com.project.recipeapp.dto.UserDTO;
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
    
    @PostMapping("/signup") 
    public ResponseEntity<?>registerUser(@RequestBody UserDTO userDTO){ 
        try {
            UserEntity user = UserEntity.builder()
                .memail(userDTO.getMemail()) 
                .mname(userDTO.getMname())
                .mpw(userDTO.getMpw()) 
                .build();

            UserEntity registeredUser = userService.create(user);
            UserDTO responseUserDTO = userDTO.builder()
                .memail(registeredUser.getMemail())
                .mkey(registeredUser.getMkey())
                .mname(registeredUser.getMname())
                .build();
            return ResponseEntity.ok().body(responseUserDTO);
        }catch(Exception e){ 
            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDTO);
        } 
    }

    @PostMapping("/signin") 
    public ResponseEntity<?>authenticate(@RequestBody UserDTO userDTO){
        UserEntity user = userService.getByCredentials(userDTO.getMemail(),userDTO.getMpw());
        
        if(user !=null){ final String token = tokenProvider.create(user);
                        final UserDTO responseUserDTO = UserDTO.builder()
                            .memail(user.getMemail()) 
                            .mkey(user.getMkey())
                            .token(token) 
                            .build();

                        return ResponseEntity.ok().body(responseUserDTO);
                       }else { 
            ResponseDTO responseDTO = ResponseDTO.builder() 
                .error("Login failed") 
                .build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }
}