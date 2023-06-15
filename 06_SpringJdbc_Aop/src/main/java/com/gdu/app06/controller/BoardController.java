package com.gdu.app06.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdu.app06.domain.BoardDTO;
import com.gdu.app06.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/board")
@Controller
public class BoardController {
	
  // Spring이 생성한 boardService 빈(Bean)의 수정을 방지하기 위해서 final 처리한 뒤 @RequiredArgsConstructor 생성자를 이용해서 주입한다.
	private final BoardService boardService;

	@GetMapping("/list.do")
	public String list(Model model) {
		model.addAttribute("boardList", boardService.getBoardList());
		return "board/list";
	}
	
	@GetMapping("/write.do")
	public String write() {
		return "board/write";
	}
	
	@PostMapping("/add.do")
	public String add(BoardDTO board) {
		boardService.addBoard(board);
		return "redirect:/board/list.do";
	}
	
	@GetMapping("/detail.do")
	public String detail(@RequestParam(value="board_no", required=false, defaultValue="0") int board_no, Model model) {
		model.addAttribute("board", boardService.getBoardByNo(board_no));
		return "board/detail";
	}
	
	@GetMapping("/remove.do")
	public String remove(@RequestParam(value="board_no", required=false, defaultValue="0") int board_no) {
		boardService.removeBoard(board_no);
		return "redirect:/board/list.do";
	}
	
	@PostMapping("/modify.do")
	public String modify(BoardDTO board) {
		boardService.modifyBoard(board);
		return "redirect:/board/detail.do?board_no=" + board.getBoard_no();
	}
	
	// 트랜잭션 처리 확인을 위한 testTx() 메소드 호출하기
	@GetMapping("/tx.do")  // http://localhost:9090/app06/board/tx.do
	public String tx() {
	  try {
	    boardService.testTx();
	  } catch (Exception e) {
  	    return "redirect:/board/list.do";
    }
	  return null;
	}
	
}