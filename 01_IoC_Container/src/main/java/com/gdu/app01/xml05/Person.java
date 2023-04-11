package com.gdu.app01.xml05;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Person {

	// field
	private List<String> hobbies;
	private Set<String> contacts;
	private Map<String, String> friends;

	// default constructor
	
	// method(getter, setter)
	public List<String> getHobbies() {
		return hobbies;
	}
	public void setHobbies(List<String> hobbies) {
		this.hobbies = hobbies;
	}
	public Set<String> getContacts() {
		return contacts;
	}
	public void setContacts(Set<String> contacts) {
		this.contacts = contacts;
	}
	public Map<String, String> getFriends() {
		return friends;
	}
	public void setFriends(Map<String, String> friends) {
		this.friends = friends;
	}
	
}
