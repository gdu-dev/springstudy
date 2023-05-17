package com.gdu.app12.service;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gdu.app12.domain.LeaveUserDTO;
import com.gdu.app12.domain.UserDTO;
import com.gdu.app12.mapper.UserMapper;
import com.gdu.app12.util.JavaMailUtil;
import com.gdu.app12.util.SecurityUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor  // field에 @Autowired 처리를 위해서
@Service
public class UserServiceImpl implements UserService {

  // field
  private UserMapper userMapper;
  private JavaMailUtil javaMailUtil;
  private SecurityUtil securityUtil;
  
  @Override
  public Map<String, Object> verifyId(String id) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("enableId", userMapper.selectUserById(id) == null && userMapper.selectSleepUserById(id) == null && userMapper.selectLeaveUserById(id) == null);
    return map;
  }
  
  @Override
  public Map<String, Object> verifyEmail(String email) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("enableEmail", userMapper.selectUserByEmail(email) == null && userMapper.selectSleepUserByEmail(email) == null && userMapper.selectLeaveUserByEmail(email) == null);
    return map;
  }
  
  @Override
  public Map<String, Object> sendAuthCode(String email) {
    
    // 사용자에게 전송할 인증코드 6자리
    String authCode = securityUtil.getRandomString(6, true, true);  // 6자리, 문자사용, 숫자사용
    
    // 사용자에게 메일 보내기
    javaMailUtil.sendJavaMail(email, "[앱이름] 인증요청", "인증번호는 <strong>" + authCode + "</strong>입니다.");
    
    // 사용자에게 전송한 인증코드를 join.jsp로 응답
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("authCode", authCode);
    return map;
    
  }
  
  @Override
  public void join(HttpServletRequest request, HttpServletResponse response) {
    
    // 요청 파라미터
    String id = request.getParameter("id");
    String pw = request.getParameter("pw");
    String name = request.getParameter("name");
    String gender = request.getParameter("gender");
    String email = request.getParameter("email");
    String mobile = request.getParameter("mobile");
    String birthyear = request.getParameter("birthyear");
    String birthmonth = request.getParameter("birthmonth");
    String birthdate = request.getParameter("birthdate");
    String postcode = request.getParameter("postcode");
    String roadAddress = request.getParameter("roadAddress");
    String jibunAddress = request.getParameter("jibunAddress");
    String detailAddress = request.getParameter("detailAddress");
    String extraAddress = request.getParameter("extraAddress");
    String location = request.getParameter("location");
    String event = request.getParameter("event");
    
    // 비밀번호 SHA-256 암호화
    pw = securityUtil.getSha256(pw);
    
    // 이름 XSS 처리
    name = securityUtil.preventXSS(name);
    
    // 출생월일
    birthdate = birthmonth + birthdate;
    
    // 상세주소 XSS 처리
    detailAddress = securityUtil.preventXSS(detailAddress);
    
    // 참고항목 XSS 처리
    extraAddress = securityUtil.preventXSS(extraAddress);
    
    // agreecode
    int agreecode = 0;
    if(location.isEmpty() == false && event.isEmpty() == false) {
      agreecode = 3;
    } else if(location.isEmpty() && event.isEmpty() == false) {
      agreecode = 2;
    } else if(location.isEmpty() == false && event.isEmpty()) {
      agreecode = 1;
    }
    
    // UserDTO 만들기
    UserDTO userDTO = new UserDTO();
    userDTO.setId(id);
    userDTO.setPw(pw);
    userDTO.setName(name);
    userDTO.setGender(gender);
    userDTO.setEmail(email);
    userDTO.setMobile(mobile);
    userDTO.setBirthyear(birthyear);
    userDTO.setBirthdate(birthdate);
    userDTO.setPostcode(postcode);
    userDTO.setRoadAddress(roadAddress);
    userDTO.setJibunAddress(jibunAddress);
    userDTO.setDetailAddress(detailAddress);
    userDTO.setExtraAddress(extraAddress);
    userDTO.setAgreecode(agreecode);
    
    // 회원가입(UserDTO를 DB로 보내기)
    int joinResult = userMapper.insertUser(userDTO);
    
    // 응답
    try {
      
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("<script>");
      if(joinResult == 1) {
        out.println("alert('회원 가입되었습니다.');");
        out.println("location.href='" + request.getContextPath() + "/index.do';");
      } else {
        out.println("alert('회원 가입에 실패했습니다.');");
        out.println("history.go(-2);");
      }
      out.println("</script>");
      out.flush();
      out.close();
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }
  
  @Override
  public void login(HttpServletRequest request, HttpServletResponse response) {
    
    // 요청 파라미터
    String url = request.getParameter("url");  // 로그인 화면의 이전 주소(로그인 후 되돌아갈 주소)
    String id = request.getParameter("id");
    String pw = request.getParameter("pw");
    
    // 비밀번호 SHA-256 암호화
    pw = securityUtil.getSha256(pw);
    
    // UserDTO 만들기
    UserDTO userDTO = new UserDTO();
    userDTO.setId(id);
    userDTO.setPw(pw);
    
    // DB에서 UserDTO 조회하기
    UserDTO loginUserDTO = userMapper.selectUserByUserDTO(userDTO);
    
    // ID, PW가 일치하는 회원이 있으면 로그인 성공
    // 1. session에 ID 저장하기
    // 2. 회원 접속 기록 남기기
    // 3. 이전 페이지로 이동하기
    if(loginUserDTO != null) {
      
      HttpSession session = request.getSession();
      session.setAttribute("loginId", id);
      
      int updateResult = userMapper.updateUserAccess(id);
      if(updateResult == 0) {
        userMapper.insertUserAccess(id);
      }
      
      try {
        response.sendRedirect(url);
      } catch (Exception e) {
        e.printStackTrace();
      }
      
    }
    // ID, PW가 일치하는 회원이 없으면 로그인 실패
    else {
      
      // 응답
      try {
        
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>");
        out.println("alert('일치하는 회원 정보가 없습니다.');");
        out.println("location.href='" + request.getContextPath() + "/index.do';");
        out.println("</script>");
        out.flush();
        out.close();
        
      } catch (Exception e) {
        e.printStackTrace();
      }
      
    }
    
  }
  
  @Override
  public void logout(HttpServletRequest request, HttpServletResponse response) {
    
    // session에 저장된 모든 정보를 지운다.
    HttpSession session = request.getSession();
    if(session.getAttribute("loginId") != null) {
      session.invalidate();
    }
    
  }
  
  @Transactional(readOnly=true)
  @Override
  public void leave(HttpServletRequest request, HttpServletResponse response) {
    
    // 탈퇴할 회원의 ID는 session에 loginId 속성으로 저장되어 있다.
    HttpSession session = request.getSession();
    String id = (String) session.getAttribute("loginId");
    
    // 탈퇴할 회원의 정보(ID, EMAIL, JOINED_AT) 가져오기
    UserDTO userDTO = userMapper.selectUserById(id);
    
    // LeaveUserDTO 만들기
    LeaveUserDTO leaveUserDTO = new LeaveUserDTO();
    leaveUserDTO.setId(id);
    leaveUserDTO.setEmail(userDTO.getEmail());
    leaveUserDTO.setJoinedAt(userDTO.getJoinedAt());
    
    // 회원 탈퇴하기
    int insertResult = userMapper.insertLeaveUser(leaveUserDTO);
    int deleteResult = userMapper.deleteUser(id);
    
    // 응답
    try {
      
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("<script>");
      if(insertResult == 1 && deleteResult == 1) {
        
        // session 초기화
        session.invalidate();
        
        out.println("alert('회원 탈퇴되었습니다.');");
        out.println("location.href='" + request.getContextPath() + "/index.do';");
        
      } else {
        out.println("alert('회원 탈퇴에 실패했습니다.');");
        out.println("history.back();");
      }
      out.println("</script>");
      out.flush();
      out.close();
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }

}











