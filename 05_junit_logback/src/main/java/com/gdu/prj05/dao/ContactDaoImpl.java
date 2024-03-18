package com.gdu.prj05.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.gdu.prj05.dto.ContactDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ContactDaoImpl implements ContactDao {

  private final SqlSessionTemplate sqlSessionTemplate;
  
  @Override
  public int registerContact(ContactDto contact) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int modifyContact(ContactDto contact) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int removeContact(int contactNo) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public List<ContactDto> getContactList() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ContactDto getContactByNo(int contactNo) {
    // TODO Auto-generated method stub
    return null;
  }

}
