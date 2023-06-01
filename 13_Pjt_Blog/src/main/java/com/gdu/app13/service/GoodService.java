package com.gdu.app13.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface GoodService {
	public Map<String, Object> getGoodCheckState(HttpServletRequest request);
	public Map<String, Object> getGoodCount(int blogNo);
	public Map<String, Object> mark(HttpServletRequest request);
}
