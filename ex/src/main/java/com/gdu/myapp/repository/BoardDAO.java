package com.gdu.myapp.repository;

import org.springframework.stereotype.Repository;

import com.gdu.myapp.domain.BoardDTO;

@Repository  // Spring Container에 BoardDAO 타입의 Bean을 만든다.
public class BoardDAO {

	public BoardDTO detail1(int boardNo) {
		return new BoardDTO(boardNo, "제목");
	}
	
}
