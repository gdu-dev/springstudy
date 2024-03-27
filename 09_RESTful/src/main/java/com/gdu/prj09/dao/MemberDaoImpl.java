package com.gdu.prj09.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.gdu.prj09.dto.AddressDto;
import com.gdu.prj09.dto.MemberDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberDaoImpl implements MemberDao {

  private final SqlSessionTemplate sqlSessionTemplate;
  
  public final static String NS = "com.gdu.prj09.mybatis.mapper.member_t.";
  
  @Override
  public int insertMember(MemberDto member) {
    return sqlSessionTemplate.insert(NS + "insertMember", member);
  }

  @Override
  public int insertAddress(AddressDto address) {
    return sqlSessionTemplate.insert(NS + "insertAddress", address);
  }
  
  @Override
  public int updateMember(Map<String, Object> map) {
    return sqlSessionTemplate.update(NS + "updateMember", map);
  }
  
  @Override
  public int updateAddress(Map<String, Object> map) {
    return sqlSessionTemplate.update(NS + "updateAddress", map);
  }

  @Override
  public int deleteMember(int memberNo) {
    return sqlSessionTemplate.delete(NS + "deleteMember", memberNo);
  }

  @Override
  public int deleteMembers(List<String> memberNoList) {
    return sqlSessionTemplate.delete(NS + "deleteMembers", memberNoList);
  }

  @Override
  public int getTotalMemberCount() {
    return sqlSessionTemplate.selectOne(NS + "getTotalMemberCount");
  }

  @Override
  public List<AddressDto> getMemberList(Map<String, Object> map) {
    return sqlSessionTemplate.selectList(NS + "getMemberList", map);
  }

  @Override
  public MemberDto getMemberByNo(int memberNo) {
    return sqlSessionTemplate.selectOne(NS + "getMemberByNo", memberNo);
  }

  @Override
  public int getTotalAddressCountByNo(int memberNo) {
    return sqlSessionTemplate.selectOne(NS + "getTotalAddressCountByNo", memberNo);
  }
  
  @Override
  public List<AddressDto> getAddressListByNo(Map<String, Object> map) {
    return sqlSessionTemplate.selectList(NS + "getAddressListByNo", map);
  }
  
}
