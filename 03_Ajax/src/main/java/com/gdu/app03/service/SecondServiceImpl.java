package com.gdu.app03.service;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.app03.domain.BmiVO;

public class SecondServiceImpl implements ISecondService {

	@Override
	public BmiVO execute1(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			
			double weight = Double.parseDouble(request.getParameter("weight"));
			double height = Double.parseDouble(request.getParameter("height")) / 100;
			
			double bmi = weight / (height * height);  // bmi = 몸무게(kg) / 키(m)*키(m)
			String obesity = null;
			if(bmi < 18.5) {
				obesity = "저체중";
			} else if(bmi < 24.9) {
				obesity = "정상";
			} else if(bmi < 29.9) {
				obesity = "과체중";
			} else {
				obesity = "비만";
			}
			
			return new BmiVO(weight, height, bmi, obesity);     // $.ajax의 success로 넘기는 값
			
		} catch(Exception e) {
			
			try {
				response.setContentType("text/plain; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("몸무게와 키 입력을 확인하세요.");  // $.ajax의 error로 넘기는 예외 메시지
				out.flush();
				out.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
			return null;
			
		}
		
	}

	@Override
	public Map<String, Object> execute2(BmiVO bmiVO) {
		
		double weight = bmiVO.getWeight();
		double height = bmiVO.getHeight() / 100;
		
		double bmi = weight / (height * height);
		String obesity = null;
		if(bmi < 18.5) {
			obesity = "저체중";
		} else if(bmi < 24.9) {
			obesity = "정상";
		} else if(bmi < 29.9) {
			obesity = "과체중";
		} else {
			obesity = "비만";
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bmi", bmi);
		map.put("obesity", obesity);
		return map;
		
	}

}
