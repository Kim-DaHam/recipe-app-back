package com.project.recipeapp.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.recipeapp.dto.FridgeDTO;
import com.project.recipeapp.dto.FridgeResponseDTO;
import com.project.recipeapp.model.FridgeEntity;
import com.project.recipeapp.service.FridgeService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@CrossOrigin(origins="*")
@RestController
@RequestMapping("fridge")
public class FridgeController {
	
	@Autowired
	private FridgeService service;
	
	@PostMapping
	public ResponseEntity<?> createGrocery(@AuthenticationPrincipal String userId, @RequestBody FridgeDTO dto){
		try {	
			FridgeEntity entity = FridgeDTO.toEntity(dto);
			entity.setMember(userId);
			entity.setDate(LocalDate.now());
			
			List<FridgeEntity> entities = service.create(entity);
			
			List<FridgeDTO> dtos = entities.stream().map(FridgeDTO::new)
					.collect(Collectors.toList());
			
			FridgeResponseDTO<FridgeDTO> response = FridgeResponseDTO
					.<FridgeDTO>builder().data(dtos).build();
			
			return ResponseEntity.ok().body(response);
		} catch(Exception e) {
			String error = e.getMessage();
			FridgeResponseDTO<FridgeDTO> response = FridgeResponseDTO
					.<FridgeDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@GetMapping
	public ResponseEntity<?> retrieveGroceryList(@AuthenticationPrincipal String userId){
		List<FridgeEntity> entities = service.retrieve(userId);
		List<FridgeDTO> dtos = entities.stream().map(FridgeDTO::new)
				.collect(Collectors.toList());
		FridgeResponseDTO<FridgeDTO> response = FridgeResponseDTO
				.<FridgeDTO>builder().data(dtos).build();
		
		return ResponseEntity.ok().body(response);
	}
	
	@PutMapping
	public ResponseEntity<?> updateGrocery(@AuthenticationPrincipal String userId, @RequestBody FridgeDTO dto){
		try {
			FridgeEntity entity = FridgeDTO.toEntity(dto);
			entity.setMember(userId);
			List<FridgeEntity> entities = service.update(entity);
			
			List<FridgeDTO> dtos = entities.stream().map(FridgeDTO::new)
					.collect(Collectors.toList());
			
			FridgeResponseDTO<FridgeDTO> response = FridgeResponseDTO
					.<FridgeDTO>builder().data(dtos).build();
			
			return ResponseEntity.ok().body(response);
		}catch(Exception e) {
			String error = e.getMessage();
			FridgeResponseDTO<FridgeDTO> response = FridgeResponseDTO
					.<FridgeDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteGrocery(@AuthenticationPrincipal String userId, @RequestBody List<FridgeDTO> dto){
		try {
			List<FridgeEntity> deleteList = new ArrayList();
			dto.forEach((d)->{
				if(dto.size()==1 || d.isChecked())
					deleteList.add(FridgeDTO.toEntity(d));
			});
			List<FridgeEntity> entities = service.delete(deleteList, userId);
			List<FridgeDTO> dtos = entities.stream().map(FridgeDTO::new)
					.collect(Collectors.toList());
			
			FridgeResponseDTO<FridgeDTO> response = FridgeResponseDTO
					.<FridgeDTO>builder().data(dtos).build();
			
			return ResponseEntity.ok().body(response);
		}catch(Exception e) {
			String error = e.getMessage();
			FridgeResponseDTO<FridgeDTO> response = FridgeResponseDTO
					.<FridgeDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}
}