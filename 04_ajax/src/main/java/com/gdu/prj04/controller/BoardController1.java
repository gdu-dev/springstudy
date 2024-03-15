package com.gdu.prj04.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.prj04.dto.BoardDto;
import com.gdu.prj04.service.BoardService;

import lombok.RequiredArgsConstructor;


@RequestMapping("/ajax1")  // /ajax1 로 시작하는 모든 요청을 담당하는 컨트롤러
@Controller
@RequiredArgsConstructor
public class BoardController1 {

  
  private final BoardService boardService;
  
  
  @ResponseBody  // 반환 값은 jsp 의 이름이 아니고 어떤 데이터이다. (비동기 작업에서 꼭 필요한 annotation)
  @GetMapping(value="/list.do", produces="application/json")  // produces : 응답 데이터 타입 (Content-Type)
  public List<BoardDto> list() {  // jackson 라이브러리가 List<BoardDto> 를 JSON 데이터로 변환한다.
    return boardService.getBoardList();
  }
  
  
  @ResponseBody
  @GetMapping(value="/detail.do", produces="application/json")
  public BoardDto detail(int boardNo) {
    return boardService.getBoardByNo(boardNo);
  }
  
  
}
