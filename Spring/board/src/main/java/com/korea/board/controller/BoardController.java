package com.korea.board.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korea.board.dto.BoardDTO;
import com.korea.board.dto.ResponseDTO;
import com.korea.board.service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService service;

	//1. 조회 "/all"
	@GetMapping("/all")
	public ResponseEntity<?> getAllPosts(){
		List<BoardDTO> dtos = service.getAllPosts();
		ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(dtos).build();
		return ResponseEntity.ok(response);
	}
	
	
	//1-1. 한건 조회 "/get/{id}"
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getPostById(@PathVariable Long id){
		System.out.println(id);
		List<BoardDTO> dtos = Arrays.asList(service.getPostById(id));
		System.out.println(dtos);
		ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder()
				.data(dtos).build();
		return ResponseEntity.ok(response);
	}
	
	
	//2. 추가 "/write"
	@PostMapping("/write")
	public ResponseEntity<?> createPost(@RequestBody BoardDTO boardDTO){
		List<BoardDTO> dtos = Arrays.asList(service.createPost(boardDTO));
		ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder()
				.data(dtos).build();
		
		return ResponseEntity.ok(response);
	}
	
	
	
	
	
	//3. 수정 "/modify/{id}"
	@PutMapping("/modify/{id}")
	public boolean updatePost(@PathVariable Long id, @RequestBody BoardDTO boardDTO) {
		BoardDTO dto = service.updatePost(id,boardDTO);
		if(dto != null) {
			return true;
		}
		return false;
	}
	
	
	
	//4. 삭제 "/delete/{id}"
	@DeleteMapping("/delete/{id}")
	public boolean deletePost(@PathVariable Long id){
		return service.deletePost(id);
	}
	
	
	
	
	
}
