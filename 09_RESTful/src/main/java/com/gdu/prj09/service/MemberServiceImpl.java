package com.gdu.prj09.service;

import java.io.PrintWriter;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.gdu.prj09.dao.MemberDao;
import com.gdu.prj09.dto.AddressDto;
import com.gdu.prj09.dto.MemberDto;
import com.gdu.prj09.utils.MyPageUtils;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

  private final MemberDao memberDao;
  private final MyPageUtils myPageUtils;
  
  @Override
  public ResponseEntity<Map<String, Object>> getMembers(int page, int display) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ResponseEntity<MemberDto> getMemberByNo(int memberNo) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ResponseEntity<Map<String, Object>> registerMember(Map<String, Object> map
                                                          , HttpServletResponse response) {
    
    ResponseEntity<Map<String, Object>> result = null;
    
    try {
      
      MemberDto member = MemberDto.builder()
                          .email((String)map.get("email"))
                          .name((String)map.get("name"))
                          .gender((String)map.get("gender"))
                         .build();
      
      int insertCount = memberDao.insertMember(member);
      
      AddressDto address = AddressDto.builder()
                            .zonecode((String)map.get("zonecode"))
                            .address((String)map.get("address"))
                            .detailAddress((String)map.get("detailAddress"))
                            .extraAddress((String)map.get("extraAddress"))
                            .member(member)
                           .build();
                                
      insertCount += memberDao.insertAddress(address);
      
      result = new ResponseEntity<Map<String,Object>>(
                      Map.of("insertCount", insertCount),
                      HttpStatus.OK);
      
    } catch (DuplicateKeyException e) {  // catch(Exception e) { 이름 확인 : e.getClass().getName() }
            
      try {
        
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.println("이미 가입된 이메일입니다.");  // jqXHR 객체의 responseText 속성으로 확인 가능
        out.flush();
        out.close();
        
      } catch (Exception e2) {
        e.printStackTrace();
      }
      
    }
    
    return result;
    
  }

  @Override
  public ResponseEntity<Map<String, Object>> modifyMember(MemberDto member) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ResponseEntity<Map<String, Object>> removeMember(int memberNo) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ResponseEntity<Map<String, Object>> removeMembers(String memberNoList) {
    // TODO Auto-generated method stub
    return null;
  }

}
