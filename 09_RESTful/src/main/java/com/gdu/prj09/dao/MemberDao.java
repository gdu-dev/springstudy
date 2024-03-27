package com.gdu.prj09.dao;

import java.util.List;
import java.util.Map;

import com.gdu.prj09.dto.AddressDto;
import com.gdu.prj09.dto.MemberDto;

public interface MemberDao {
  int insertMember(MemberDto member);
  int insertAddress(AddressDto address);
  int updateMember(Map<String, Object> map);
  int updateAddress(Map<String, Object> map);
  int deleteMember(int memberNo);
  int deleteMembers(List<String> memberNoList);
  int getTotalMemberCount();
  List<AddressDto> getMemberList(Map<String, Object> map);
  MemberDto getMemberByNo(int memberNo);
  int getTotalAddressCountByNo(int memberNo);
  List<AddressDto> getAddressListByNo(Map<String, Object> map);
}
