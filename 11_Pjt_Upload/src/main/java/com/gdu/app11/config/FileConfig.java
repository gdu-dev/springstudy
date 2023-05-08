package com.gdu.app11.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class FileConfig {

	@Bean
	public CommonsMultipartResolver commonsMultipartResolver() {
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
		commonsMultipartResolver.setDefaultEncoding("UTF-8");
		commonsMultipartResolver.setMaxUploadSize(1024 * 1024 * 100);       // 전체 첨부 파일의 크기 100MB
		commonsMultipartResolver.setMaxUploadSizePerFile(1024 * 1024 * 10); // 첨부 파일 하나의 크기 10MB
		return commonsMultipartResolver;
	}
	
}