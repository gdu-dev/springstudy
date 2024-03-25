package com.gdu.prj09.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface MemberService {
  public ResponseEntity<Map<String, Object>> getMembers(int page, int display);
}
