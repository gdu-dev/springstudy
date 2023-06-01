package com.gdu.app13.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdu.app13.mapper.GoodMapper;

@Service
public class GoodServiceImpl implements GoodService {

	@Autowired
	private GoodMapper goodMapper;
	
	@Override
	public Map<String, Object> getGoodCheckState(HttpServletRequest request) {
		int blogNo = Integer.parseInt(request.getParameter("blogNo"));
		int memberNo = 0;
		HttpSession session = request.getSession();
		if(session != null && session.getAttribute("loginNo") != null) {
		  memberNo = (int)session.getAttribute("loginNo");
		}
		Map<String, Object> map = new HashMap<>();
		map.put("blogNo", blogNo);
		map.put("memberNo", memberNo);
		Map<String, Object> result = new HashMap<>();
		result.put("userGoodCount", goodMapper.getUserGoodCount(map));
		return result;
	}
	
	@Override
	public Map<String, Object> getGoodCount(int blogNo) {
		Map<String, Object> result = new HashMap<>();
		result.put("blogGoodCount", goodMapper.getBlogGoodCount(blogNo));
		return result;
	}
	
	@Override
	public Map<String, Object> mark(HttpServletRequest request) {
		int blogNo = Integer.parseInt(request.getParameter("blogNo"));
		int memberNo = 0;
    HttpSession session = request.getSession();
    if(session != null && session.getAttribute("loginNo") != null) {
      memberNo = (int)session.getAttribute("loginNo");
    }
		Map<String, Object> map = new HashMap<>();
		map.put("blogNo", blogNo);
		map.put("memberNo", memberNo);
		Map<String, Object> result = new HashMap<>();
		if (goodMapper.getUserGoodCount(map) == 0) {                 // 예전에 "좋아요"를 누른 적이 없다면
			result.put("isSuccess",goodMapper.addGood(map) == 1);      // "좋아요" 정보 등록
		} else {
			result.put("isSuccess", goodMapper.deleteGood(map) == 1);  // "좋아요" 정보 삭제
		}
		return result;
	}
	
}
