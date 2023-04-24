package com.gdu.app08.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.gdu.app08.domain.BoardDTO;

public interface BoardService {
	public List<BoardDTO> getBoardList();
	public BoardDTO getBoardByNo(HttpServletRequest request);
	public int addBoard(HttpServletRequest request);
	public int modifyBoard(HttpServletRequest request);
	public int removeBoard(HttpServletRequest request);
}
