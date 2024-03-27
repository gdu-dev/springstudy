package com.gdu.prj09.service;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.gdu.prj09.dao.MemberDao;
import com.gdu.prj09.dto.AddressDto;
import com.gdu.prj09.dto.MemberDto;
import com.gdu.prj09.utils.MyPageUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

  private final MemberDao memberDao;
  private final MyPageUtils myPageUtils;
  
  @Override
  public ResponseEntity<Map<String, Object>> getMembers(int page, int display) {
    
    int total = memberDao.getTotalMemberCount();
    
    myPageUtils.setPaging(total, display, page);
    
    Map<String, Object> params = Map.of("begin", myPageUtils.getBegin()
                                      , "end", myPageUtils.getEnd());
    
    List<AddressDto> members = memberDao.getMemberList(params);
    
    return new ResponseEntity<Map<String,Object>>(Map.of("members", members
                                                       , "total", total
                                                       , "paging", myPageUtils.getAsyncPaging())
                                                , HttpStatus.OK);
    
  }

  @Override
  public ResponseEntity<Map<String, Object>> getMemberByNo(int memberNo) {
    
    int total = memberDao.getTotalAddressCountByNo(memberNo);
    int page = 1;
    int display = 20;
    
    myPageUtils.setPaging(total, display, page);
    
    Map<String, Object> params = Map.of("memberNo", memberNo
                                      , "begin", myPageUtils.getBegin()
                                      , "end", myPageUtils.getEnd());
    
    List<AddressDto> addressList = memberDao.getAddressListByNo(params);
    MemberDto member = memberDao.getMemberByNo(memberNo);
    
    return new ResponseEntity<Map<String,Object>>(Map.of("addressList", addressList
                                                       , "member", member)
                                                , HttpStatus.OK);
    
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
  public ResponseEntity<Map<String, Object>> modifyMember(Map<String, Object> map) {
    
    int updateMemberCount = memberDao.updateMember(map);
    int updateAddressCount = memberDao.updateAddress(map);
    
    if(updateAddressCount == 0) {
      AddressDto address = AddressDto.builder()
                              .zonecode((String)map.get("zonecode"))
                              .address((String)map.get("address"))
                              .detailAddress((String)map.get("detailAddress"))
                              .extraAddress((String)map.get("extraAddress"))
                              .member(MemberDto.builder()
                                        .memberNo(Integer.parseInt((String)map.get("memberNo")))
                                      .build())
                            .build();
      updateAddressCount = memberDao.insertAddress(address);
    }
    
    return new ResponseEntity<Map<String,Object>>(Map.of("updateCount", updateMemberCount + updateAddressCount)
                                                , HttpStatus.OK);
    
  }

  @Override
  public ResponseEntity<Map<String, Object>> removeMember(int memberNo) {
    return new ResponseEntity<Map<String,Object>>(Map.of("deleteCount", memberDao.deleteMember(memberNo))
                                                , HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Map<String, Object>> removeMembers(String memberNoList) {
    return new ResponseEntity<Map<String,Object>>(Map.of("deleteCount", memberDao.deleteMembers(Arrays.asList(memberNoList.split(","))))
                                                , HttpStatus.OK);
  }

}
