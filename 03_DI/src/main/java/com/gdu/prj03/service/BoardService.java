package com.gdu.prj03.service;

import java.util.List;

import com.gdu.prj03.dto.BoardDto;

public interface BoardService {
  List<BoardDto> getBoardList();
  BoardDto getBoardByNo(int boardNo);
}
