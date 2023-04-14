package com.gdu.app03.service;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.gdu.app03.domain.Contact;

public class ThirdServiceImpl implements IThirdService {

	@Override
	public ResponseEntity<Contact> execute1(Contact contact) {
		
		// 이름 또는 전화번호가 공백인 경우 $.ajax의 error 처리
		if(contact.getName().isEmpty() || contact.getTel().isEmpty()) {
			return new ResponseEntity<Contact>(HttpStatus.BAD_REQUEST);
		}
		
		// 이름과 전화번호가 공백이 아닌 경우 입력된 값 그대로 다시 돌려보내기 ($.ajax의 success로 전달)
		return new ResponseEntity<Contact>(contact, HttpStatus.OK);
		
	}

	@Override
	public ResponseEntity<Map<String, String>> execute2(Map<String, String> map) {
		// TODO Auto-generated method stub
		return null;
	}

}
