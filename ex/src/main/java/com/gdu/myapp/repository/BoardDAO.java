package com.gdu.myapp.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.gdu.myapp.domain.BoardDTO;

@Repository  // Spring Container에 BoardDAO 타입의 Bean을 만든다.
public class BoardDAO {

	public List<BoardDTO> list() {
		List<BoardDTO> boardList = new ArrayList<BoardDTO>();
		boardList.add(new BoardDTO(1, "공지"));
		boardList.add(new BoardDTO(2, "협조"));
		return boardList;
	}
	
	public BoardDTO detail1(int boardNo) {
		return new BoardDTO(boardNo, "제목");
	}
	
	public BoardDTO detail2(int boardNo) {
		return new BoardDTO(boardNo, "스프링");
	}
	
	public BoardDTO detail3(int boardNo) {
		return new BoardDTO(boardNo, "이게뭐야");
	}
	
	
	
	
	
	
	
	
	
}
