package com.gdu.prj09.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.gdu.prj09.service.MemberService;

import lombok.RequiredArgsConstructor;

/*
 * RESTful
 * 1. REpresentation State Transfer
 * 2. 요청 주소를 작성하는 한 방식이다.
 * 3. 요청 파라미터를 ? 뒤에 추가하는 Query String 방식을 사용하지 않는다.
 * 4. 요청 파라미터를 주소에 포함하는 Path Variable 방식을 사용하거나, 요청 본문에 포함하는 방식을 사용한다.
 * 5. 요청의 구분을 "주소 + 메소드" 조합으로 구성한다.
 * 6. CRUD 요청 예시
 *            | URL                         | Method
 *       -----|-----------------------------|----------
 *    1) 목록 | /members                    | GET
 *            | /members/page/1             |
 *            | /members/page/1/display/20  |
 *    2) 상세 | /members/1                  | GET
 *    3) 삽입 | /members                    | POST
 *    4) 수정 | /members                    | PUT
 *    5) 삭제 | /members/1                  | DELETE 
 *            | /members/1,2,3              |         
 */

@RequiredArgsConstructor
@Controller
public class MemberController {

  private final MemberService memberService;
  
  @GetMapping("/admin/member.do")
  public void adminMember() {
    // 반환타입이 void 인 경우 주소를 JSP 경로로 인식한다.
    // /admin/member.do =====> /WEB-INF/views/admin/member.jsp
  }
  
  @PostMapping(value="/members", produces="application/json")
  public ResponseEntity<Map<String, Object>> registerMember(@RequestBody Map<String, Object> map) {
    System.out.println(map);
    return new ResponseEntity<Map<String,Object>>(HttpStatus.OK);
  }
  
  
  
  
  
  
  
  
  
}
