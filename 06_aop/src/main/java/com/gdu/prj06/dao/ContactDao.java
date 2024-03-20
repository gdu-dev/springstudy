package com.gdu.prj06.dao;

import java.util.List;

import com.gdu.prj06.dto.ContactDto;

public interface ContactDao {
  int registerContact(ContactDto contact);
  int modifyContact(ContactDto contact);
  int removeContact(int contactNo);
  List<ContactDto> getContactList();
  ContactDto getContactByNo(int contactNo);
}
