package com.gdu.app08.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.app08.domain.BoardDTO;

/*
	@Mapper
	
	1. mapper의 쿼리문을 직접 호출할수 있는 인터페이스이다.
	2. 쿼리문의 id와 메소드명을 동일하게 처리한다.
	3. DBconfig.java(SqlSessionTemplate Bean이 정의된 파일)에 @MapperScan을 추가해야한다.
*/
@Mapper
public interface BoardMapper {
	public List<BoardDTO> selectBoardList();
	public BoardDTO selectBoardByNo(int boardNo);
	public int insertBoard(BoardDTO board);
	public int updateBoard(BoardDTO board);
	public int deleteBoard(int boardNo);
	public int deleteBoardList(List<String> boardNoList);
	public int selectBoardCount();
}
