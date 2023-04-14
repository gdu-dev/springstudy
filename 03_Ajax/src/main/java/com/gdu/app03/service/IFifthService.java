package com.gdu.app03.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

public interface IFifthService {
	public ResponseEntity<String> papago(HttpServletRequest request);
}
