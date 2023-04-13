package com.gdu.app03.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.gdu.app03.domain.Person;

public interface IFirstService {
	public Person execute1(HttpServletRequest request);
	public Map<String, Object> execute2(String name, int age);
}
