package com.gdu.prj02.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdu.prj02.dto.ArticleDto;

@Controller
public class MyController3 {

  /* 1. HttpServletRequest 를 이용한 요청 파라미터 처리 */
  
  @RequestMapping(value="/article/detail1.do", method=RequestMethod.GET)
  public String detail1(HttpServletRequest request) {
    int article_no = Integer.parseInt(request.getParameter("article_no"));
    System.out.println("detail1:" + article_no);
    return "index";
  }
  
  /*
   * 2. @RequestParam annotation 을 이용한 요청 파라미터 처리
   *  1) 변수를 선언하고 요청 파라미터를 받는다.
   *  2) @RequestParam annotation 은 생략할 수 있다.
   *  3) 주요 메소드
   *    (1) value        : 요청 파라미터 이름
   *    (2) required     : 요청 파라미터 필수 여부(디폴트 true)
   *    (3) defaultValue : 요청 파라미터가 없을 때 사용할 값
   */
  
  @RequestMapping(value="/article/detail2.do", method=RequestMethod.GET)
  public String detail2(@RequestParam(value="article_no"
                                    , required=false
                                    , defaultValue="1") int article_no) {
    System.out.println("detail2:" + article_no);
    return "index";
  }
  
  /*
   * 3. 커맨드 객체를 이용한 요청 파라미터 처리
   *  1) 요청 파라미터를 필드로 가진 객체를 커맨드 객체라고 한다.
   *  2) 요청 파라미터를 setter 를 이용하여 필드에 저장한다.
   *  3) 자동으로 Model 에 저장된다.
   */
  
  @RequestMapping(value="/article/detail3.do", method=RequestMethod.GET)
  public String detail3(ArticleDto articleDto) {
    System.out.println("detail3:" + articleDto.getArticle_no());
    return "index";
  }
  
}
