package com.korea.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {

	private Long id;
	private String title;
	private String author;
	private String writingTime;
	private String content;
}
