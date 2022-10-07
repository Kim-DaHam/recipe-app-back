package com.project.recipeapp.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@RestController
@RequestMapping("fridge")
public class FridgeController {
	
	@Autowired
	private FridgeService service;
	
	@PostMapping
	public ResponseEntity<?> createGrocery(@RequestBody FridgeDTO dto){
		try {	
			FridgeEntity entity = FridgeDTO.toEntity(dto);
			entity.setMember("temporary-userid");
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
	public ResponseEntity<?> retrieveGroceryList(){
		String temporaryUserId = "temporary-userid";
		List<FridgeEntity> entities = service.retrieve(temporaryUserId);
		List<FridgeDTO> dtos = entities.stream().map(FridgeDTO::new)
				.collect(Collectors.toList());
		FridgeResponseDTO<FridgeDTO> response = FridgeResponseDTO
				.<FridgeDTO>builder().data(dtos).build();
		
		return ResponseEntity.ok().body(response);
	}
	
	@PutMapping
	public ResponseEntity<?> updateGrocery(@RequestBody FridgeDTO dto){
		try {
			FridgeEntity entity = FridgeDTO.toEntity(dto);
			entity.setMember("temporary-userid");
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
	public ResponseEntity<?> deleteGrocery(@RequestBody List<FridgeDTO> dto){
		try {
			String temporaryUserId = "temporary-userid";
			List<FridgeEntity> deleteList = new ArrayList();
			dto.forEach((d)->{
				deleteList.add(FridgeDTO.toEntity(d));
			});
			List<FridgeEntity> entities = service.delete(deleteList, temporaryUserId);
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