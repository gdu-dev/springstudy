package com.gdu.prj04.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gdu.prj04.dto.BoardDto;
import com.gdu.prj04.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/ajax3")
@RequiredArgsConstructor
@Controller  // @ResponseBody annotation 은 ResponseEntity 클래스가 제공한다.
public class BoardController3 {

  
  private final BoardService boardService;
  
  
  @GetMapping("/list.do")
  public ResponseEntity<List<BoardDto>> list() {
    
    // 응답 헤더
    HttpHeaders header = new HttpHeaders();
    header.add("Content-Type", "application/json");
    
    // 반환
    return new ResponseEntity<List<BoardDto>>(boardService.getBoardList()
                                            , header
                                            , HttpStatus.OK);
    
  }
  
  
  @GetMapping("/detail.do")
  public ResponseEntity<BoardDto> detail(int boardNo) {
    
    HttpHeaders header = new HttpHeaders();
    header.add("Content-Type", "application/json");
    
    return new ResponseEntity<BoardDto>(boardService.getBoardByNo(boardNo)
                                      , header
                                      , HttpStatus.OK);

  }

  
}
