package com.gdu.myapp.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdu.myapp.domain.BoardDTO;
import com.gdu.myapp.repository.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {

	
	// 서비스는 DAO를 사용한다.
	@Autowired
	private BoardDAO boardDAO;
	
	
	@Override
	public List<BoardDTO> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoardDTO detail1(int boardNo) {
		return boardDAO.detail1(boardNo);
	}

	@Override
	public BoardDTO detail2(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
