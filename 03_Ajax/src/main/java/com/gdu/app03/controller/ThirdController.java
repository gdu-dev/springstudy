package com.gdu.app03.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.gdu.app03.domain.Contact;
import com.gdu.app03.service.IThirdService;

@Controller
public class ThirdController {

	// field
	private IThirdService thirdService;
	
	// setter method
	@Autowired  // setter method 형식의 자동 주입의 경우 @Autowired를 생략할 수 없다.
	public void method(IThirdService thirdService) {  // Spring Container에서 IThirdService 타입의 Bean을 찾아서 매개변수에 주입한다.
		this.thirdService = thirdService;
	}
	
	@PostMapping(value="/third/ajax1", produces="application/json")
	public ResponseEntity<Contact> ajax1(@RequestBody Contact contact) {  // 요청 본문(request body)에 포함된 JSON 데이터를 Contact contact 객체에 저장해 주세요.
		return thirdService.execute1(contact);
	}
	
	@PostMapping(value="/third/ajax2", produces="application/json")
	public ResponseEntity<Map<String, String>> ajax2(@RequestBody Map<String, String> map) {  // 요청 본문(request body)에 포함된 JSON 데이터를 Map에 저장해 주세요.
		return thirdService.execute2(map);
	}
	
}
