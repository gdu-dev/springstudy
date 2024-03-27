package com.gdu.prj09.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

public interface MemberService {
  ResponseEntity<Map<String, Object>> getMembers(int page, int display);
  ResponseEntity<Map<String, Object>> getMemberByNo(int memberNo);
  ResponseEntity<Map<String, Object>> registerMember(Map<String, Object> map, HttpServletResponse response);
  ResponseEntity<Map<String, Object>> modifyMember(Map<String, Object> map);
  ResponseEntity<Map<String, Object>> removeMember(int memberNo);
  ResponseEntity<Map<String, Object>> removeMembers(String memberNoList);
}