package com.gdu.app12.service;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

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
  

}











