package com.gdu.prj07.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.prj07.dto.ContactDto;

public interface ContactService {
  void registerContact(HttpServletRequest request, HttpServletResponse response);
  void modifyContact(HttpServletRequest request, HttpServletResponse response);
  void removeContact(HttpServletRequest request, HttpServletResponse response);
  List<ContactDto> getContactList();
  ContactDto getContactByNo(int contactNo);
  void txTest();
}
