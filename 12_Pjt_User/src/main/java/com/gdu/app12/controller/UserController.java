package com.gdu.app12.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.app12.domain.UserDTO;
import com.gdu.app12.service.UserService;

@RequestMapping("/user")
@Controller
public class UserController {

  // field
  @Autowired
  private UserService userService;
  
  @GetMapping("/agree.form")
  public String agreeForm() {
    return "user/agree";
  }
  
  @GetMapping("/join.form")
  public String joinForm(@RequestParam(value="location", required=false) String location  // 파라미터 location이 전달되지 않으면 빈 문자열("")이 String location에 저장된다.
                       , @RequestParam(value="event", required=false) String event        // 파라미터 event가 전달되지 않으면 빈 문자열("")이 String event에 저장된다.
                       , Model model) {
    model.addAttribute("location", location);
    model.addAttribute("event", event);
    return "user/join";
  }
  
  @ResponseBody
  @GetMapping(value="/verifyId.do", produces="application/json")
  public Map<String, Object> verifyId(@RequestParam("id") String id) {
    return userService.verifyId(id);
  }
  
  @ResponseBody
  @GetMapping(value="/verifyEmail.do", produces="application/json")
  public Map<String, Object> verifyEmail(@RequestParam("email") String email) {
    return userService.verifyEmail(email);
  }
  
  @ResponseBody
  @GetMapping(value="/sendAuthCode.do", produces="application/json")
  public Map<String, Object> sendAuthCode(@RequestParam("email") String email) {
    return userService.sendAuthCode(email);
  }
  
  @PostMapping("/join.do")
  public void join(HttpServletRequest request, HttpServletResponse response) {
    userService.join(request, response);
  }
  
  @GetMapping("/login.form")
  public String loginForm(HttpServletRequest request, Model model) {
    // 요청 헤더 referer : 로그인 화면으로 이동하기 직전의 주소를 저장하는 헤더 값
    String url = request.getHeader("referer");
    model.addAttribute("url", url == null ? request.getContextPath() : url);
    return "user/login";
  }
  
  @PostMapping("/login.do")
  public void login(HttpServletRequest request, HttpServletResponse response) {
    userService.login(request, response);
  }
  
  @GetMapping("/logout.do")
  public String logout(HttpServletRequest request, HttpServletResponse response) {
    userService.logout(request, response);
    return "redirect:/";
  }
  
  @GetMapping("/leave.do")
  public void leave(HttpServletRequest request, HttpServletResponse response) {
    userService.leave(request, response);
  }
  
  @GetMapping("/wakeup.form")  // 휴면 복원 화면으로 이동
  public String wakeup() {
    return "user/wakeup";
  }
  
  @GetMapping("/restore.do")  // 휴면 복원
  public void restore(HttpServletRequest request, HttpServletResponse response) {
    userService.restore(request, response);
  }
  
  @GetMapping("/checkPw.form")  // 마이페이지 직전 비밀번호 확인 화면으로 이동
  public String checkPwForm() {
    return "user/checkPw";
  }
  
  @ResponseBody
  @PostMapping(value="/checkPw.do", produces="application/json")  // 사용자가 입력한 비밀번호가 맞는지 확인
  public Map<String, Object> checkPw(HttpServletRequest request) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("isCorrect", userService.checkPw(request));
    return map;
  }
  
  @GetMapping("/mypage.do")  // 마이페이지로 이동
  public String mypage(HttpSession session, Model model) {
    String id = (String) session.getAttribute("loginId");
    model.addAttribute("loginUser", userService.getUserById(id));
    return "user/mypage";
  }
  
  @ResponseBody
  @PostMapping(value="/modifyPw.do", produces="application/json")  // 비밀번호 변경
  public Map<String, Object> modifyPw(HttpServletRequest request) {
    return userService.modifyPw(request);
  }
  
  @ResponseBody
  @PostMapping(value="/modifyEmail.do", produces="application/json")  // 이메일 변경
  public Map<String, Object> modifyEmail(HttpServletRequest request) {
    return userService.modifyEmail(request);
  }
  
  @GetMapping("/findId.form")  // 아이디 찾기 화면으로 이동
  public String findIdForm() {
    return "user/findId";
  }
  
  @ResponseBody
  @PostMapping(value="/findId.do", produces="application/json")  // 아이디 찾기
  public Map<String, Object> findId(@RequestBody UserDTO userDTO) {
    return userService.findId(userDTO);
  }
  
  @GetMapping("/findPw.form")  // 비밀번호 찾기 화면으로 이동
  public String findPwForm() {
    return "user/findPw";
  }
  
  @ResponseBody
  @PostMapping(value="/findPw.do", produces="application/json")  // 비밀번호 찾기
  public Map<String, Object> findPw(@RequestBody UserDTO userDTO) {
    return userService.findPw(userDTO);
  }
  
  @ResponseBody
  @PostMapping(value="/sendTempPw.do", produces="application/json")  // 임시비밀번호 발급 및 전송
  public Map<String, Object> sendTempPw(@RequestBody UserDTO userDTO) {
    return userService.sendTempPw(userDTO);
  }
  
  
  
  
  
  
  
  
}
