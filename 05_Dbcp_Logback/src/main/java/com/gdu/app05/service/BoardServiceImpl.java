package com.gdu.app05.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gdu.app05.domain.BoardDTO;
import com.gdu.app05.repository.BoardDAO;

// @Service 대신 AppConfig에 @Bean이 등록되어 있다.
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDAO;
	
	@Override
	public List<BoardDTO> getBoardList() {
		return boardDAO.selectBoardList();
	}

	@Override
	public BoardDTO getBoardByNo(int board_no) {
		return boardDAO.selectBoardByNo(board_no);
	}

	@Override
	public int addBoard(BoardDTO board) {
		return boardDAO.insertBoard(board);
	}

	@Override
	public int modifyBoard(BoardDTO board) {
		return boardDAO.updateBoard(board);
	}

	@Override
	public int removeBoard(int board_no) {
		return boardDAO.deleteBoard(board_no);
	}

}
