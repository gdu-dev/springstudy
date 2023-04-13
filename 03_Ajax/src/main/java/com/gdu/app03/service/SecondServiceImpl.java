package com.gdu.app03.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.app03.domain.BmiVO;

public class SecondServiceImpl implements ISecondService {

	@Override
	public BmiVO execute1(HttpServletRequest request, HttpServletResponse response) {
		
		// bmi = 몸무게(kg) / 키(m)*키(m)
		double weight = Double.parseDouble(request.getParameter("weight"));
		double height = Double.parseDouble(request.getParameter("height")) / 100;
		
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
		
		return new BmiVO(weight, height, bmi, obesity);
		
	}

	@Override
	public Map<String, Object> execute2(BmiVO bmiVO) {
		// TODO Auto-generated method stub
		return null;
	}

}
