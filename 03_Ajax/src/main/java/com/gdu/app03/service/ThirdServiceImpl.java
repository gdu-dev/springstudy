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
		
		// 이름이 공백이거나, 전화번호의 글자 수가 3글자 이하인 경우 $.ajax의 error 처리
		String name = map.get("name");
		String tel = map.get("tel");
		if(name.isEmpty() || tel.length() <= 3) {
			return new ResponseEntity<Map<String,String>>(HttpStatus.BAD_REQUEST);
		}
		
		// 이름은 그대로, 전화번호에 하이픈(-)이 포함되어 있으면 모두 제거 후 돌려보내기 ($.ajax의 success로 전달)
		map.put("tel", tel.replace("-", ""));  // Map의 key는 중복될 수 없다. put() 메소드로 Map에 값을 추가할 때 이미 Map에 있는 key를 사용하면 추가되지 않고, 기존 key의 value가 수정된다.  
		
		return new ResponseEntity<Map<String,String>>(map, HttpStatus.OK);
		
	}

}
