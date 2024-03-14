package com.gdu.prj03.dao;

import java.util.List;

import com.gdu.prj03.dto.BoardDto;

public interface BoardDao {
  List<BoardDto> getBoardList();
  BoardDto getBoardByNo(int boardNo);
}
