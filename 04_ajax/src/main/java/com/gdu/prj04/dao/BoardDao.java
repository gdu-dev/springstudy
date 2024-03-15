package com.gdu.prj04.dao;

import java.util.List;

import com.gdu.prj04.dto.BoardDto;

public interface BoardDao {
  List<BoardDto> getBoardList();
  BoardDto getBoardByNo(int boardNo);
}
