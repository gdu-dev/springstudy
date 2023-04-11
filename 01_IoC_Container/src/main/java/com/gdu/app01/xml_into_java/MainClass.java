package com.gdu.app01.xml_into_java;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		
		// AppContext.java에 정의된 Bean을 주세요!
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
		
		// 이름이 upload인 Bean을 주세요!
		Upload upload = ctx.getBean("upload", Upload.class);
		
		System.out.println("첨부제목: " + upload.getTitle());
		System.out.println("첨부파일: " + upload.getAttach().getFilename());
		System.out.println("첨부경로: " + upload.getAttach().getPath());
		
		ctx.close();

	}

}
