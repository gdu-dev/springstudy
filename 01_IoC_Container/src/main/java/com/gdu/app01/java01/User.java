package com.gdu.app01.java01;

public class User {

	// field
	private String id;
	private Contact contact;
	
	// default constructor
	public User() {
		
	}
	// constructor
	public User(String id, Contact contact) {
		super();
		this.id = id;
		this.contact = contact;
	}
	
	// method(getter, setter)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
}
