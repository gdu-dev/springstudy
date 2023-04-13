package com.gdu.app03.service;

import javax.servlet.http.HttpServletRequest;

import com.gdu.app03.domain.Person;

public class FirstServiceImpl implements IFirstService {

	@Override
	public Person execute1(HttpServletRequest request) {
		
		String name = request.getParameter("name");
		name = name.isEmpty() ? "홍길동" : name;   // 사용자가 입력한 name이 없으면 빈 문자열이 전달된다.
		
		String strAge = request.getParameter("age");
		strAge = strAge.isEmpty() ? "0" : strAge;  // 사용자가 입력한 age가 없으면 빈 문자열이 전달된다.
		int age = Integer.parseInt(strAge);
		
		return new Person(name, age);

	}

}
