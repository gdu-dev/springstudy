package com.gdu.app01.java01;

public class Contact {

	// field
	private String tel;
	private String fax;
	
	// default constructor
	public Contact() {
		
	}
	// constructor
	public Contact(String tel, String fax) {
		super();
		this.tel = tel;
		this.fax = fax;
	}
	
	// method(getter, setter)
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	
}
