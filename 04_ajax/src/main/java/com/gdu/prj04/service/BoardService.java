package com.gdu.prj04.service;

import java.util.List;

import com.gdu.prj04.dto.BoardDto;

public interface BoardService {
  List<BoardDto> getBoardList();
  BoardDto getBoardByNo(int boardNo);
}
