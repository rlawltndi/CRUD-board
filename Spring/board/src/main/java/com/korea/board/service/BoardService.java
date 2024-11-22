package com.korea.board.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.korea.board.dto.BoardDTO;
import com.korea.board.entity.BoardEntity;
import com.korea.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardRepository repository;
	
	//1. 조회
	public List<BoardDTO> getAllPosts(){
		//repository.findAll()의 반환값 List<BoardEntity>
		return repository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}
	
	//1-1. 한건 조회
	public BoardDTO getPostById(Long id){
		Optional<BoardEntity> board = repository.findById(id);
		return board.map(this::convertToDTO).orElseThrow(()-> new RuntimeException("게시글을 찾을 수 없습니다."));
	}
	
	//2. 추가
	public BoardDTO createPost(BoardDTO boardDTO) {
		boardDTO.setWritingTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
		BoardEntity board = convertToEntity(boardDTO);
		
		return convertToDTO(repository.save(board)); 
	}
	
	//3. 수정
	public BoardDTO updatePost(Long id, BoardDTO boardDTO) {
		BoardEntity existingBoard = repository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
		existingBoard.setTitle(boardDTO.getTitle());
		existingBoard.setAuthor(boardDTO.getAuthor());
		existingBoard.setContent(boardDTO.getContent());
		return convertToDTO(repository.save(existingBoard));
	}
	
	//4. 삭제
	public boolean deletePost(Long id) {
		if(repository.existsById(id)) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}
	
	//Entity -> DTO 변환함수
	private BoardDTO convertToDTO(BoardEntity board) {
		return BoardDTO.builder()
				.id(board.getId())
				.title(board.getTitle())
				.author(board.getAuthor())
				.writingTime(board.getWritingTime())
				.content(board.getContent())
				.build();
	}
	
	//DTO -> Entity 변환함수
	private BoardEntity convertToEntity(BoardDTO boardDTO) {
		return BoardEntity.builder()
				.id(boardDTO.getId())
				.title(boardDTO.getTitle())
				.author(boardDTO.getAuthor())
				.writingTime(boardDTO.getWritingTime())
				.content(boardDTO.getContent())
				.build();
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
