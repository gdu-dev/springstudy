package com.gdu.myapp.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gdu.myapp.domain.BoardDTO;
import com.gdu.myapp.repository.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {

	
	// 서비스는 DAO를 사용한다.
	@Autowired
	private BoardDAO boardDAO;
	
	
	@Override
	public List<BoardDTO> list() {
		return boardDAO.list();
	}

	@Override
	public BoardDTO detail1(int boardNo) {
		return boardDAO.detail1(boardNo);
	}

	@Override
	public BoardDTO detail2(HttpServletRequest request) {
		Optional<String> opt = Optional.ofNullable(request.getParameter("boardNo"));
		int boardNo = Integer.parseInt(opt.orElse("0"));
		return boardDAO.detail2(boardNo);
	}
	
	@Override
	public void detail3(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		Optional<String> opt = Optional.ofNullable(request.getParameter("boardNo"));
		int boardNo = Integer.parseInt(opt.orElse("0"));
		model.addAttribute("board", boardDAO.detail3(boardNo));
	}

}