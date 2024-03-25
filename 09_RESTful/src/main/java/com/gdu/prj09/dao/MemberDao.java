package com.gdu.prj09.dao;

import java.util.List;

import com.gdu.prj09.dto.MemberDto;

public interface MemberDao {
  int insertMember(MemberDto member);
  int updateMember(MemberDto member);
  int deleteMember(int memberNo);
  int deleteMembers(List<String> memberNoList);
  int getTotalMemberCount();
  List<MemberDto> getMemberList();
  MemberDto getMemberByNo(int memberNo);
}
