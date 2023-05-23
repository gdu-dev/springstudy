package com.gdu.app13.mapper;

import java.util.Map;

import com.gdu.app13.domain.MemberDTO;

public interface MemberMapper {
  public MemberDTO selectMemberByMap(Map<String, Object> map);
}
