package com.gdu.app01.xml04;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("xml04/app-context.xml");
		MyDAO dao = ctx.getBean("dao", MyDAO.class);
		dao.list();
		
		ctx.close();

	}

}
