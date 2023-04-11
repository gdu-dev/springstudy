package com.gdu.app01.java02;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Student {

	// field
	private List<Integer> scores;         // 0~100 난수 5개
	private Set<String> awards;           // 상 3개
	private Map<String, String> contact;  // address, tel
	
	// default constructor
	
	// method(getter, setter)
	public List<Integer> getScores() {
		return scores;
	}
	public void setScores(List<Integer> scores) {
		this.scores = scores;
	}
	public Set<String> getAwards() {
		return awards;
	}
	public void setAwards(Set<String> awards) {
		this.awards = awards;
	}
	public Map<String, String> getContact() {
		return contact;
	}
	public void setContact(Map<String, String> contact) {
		this.contact = contact;
	}
	
}
