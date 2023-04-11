package com.gdu.app01.xml04;

import java.sql.Connection;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MyDAO {

	// field
	private Connection con;
	
	// singleton pattern
	private static MyDAO dao = new MyDAO();
	private MyDAO() { }
	public static MyDAO getInstance() {
		return dao;
	}
	
	// method
	public void list() {
		
		// Spring Container에 만들어 둔 myConn Bean 가져오기
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("xml04/app-context.xml");
		con = ctx.getBean("myConn", MyConnection.class).getConnection();
		
		ctx.close();
		
	}
	
}