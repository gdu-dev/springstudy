package com.gdu.app03.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.gdu.app03.domain.BmiVO;

public interface ISecondService {
	public ResponseEntity<BmiVO> execute1(HttpServletRequest request);
	
	// public BmiVO execute1(HttpServletRequest request, HttpServletResponse response);
	// public Map<String, Object> execute2(BmiVO bmiVO);
}
