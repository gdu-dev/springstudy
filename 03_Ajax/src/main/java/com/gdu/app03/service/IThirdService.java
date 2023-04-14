package com.gdu.app03.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.gdu.app03.domain.Contact;

public interface IThirdService {
	public ResponseEntity<Contact> execute1(Contact contact);
	public ResponseEntity<Map<String, String>> execute2(Map<String, String> map);
}
