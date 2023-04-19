package com.gdu.myapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gdu.myapp.service.BoardService;
import com.gdu.myapp.service.BoardServiceImpl;

@Configuration
public class AppConfig {

	// @Bean
	public BoardService boardService() {
		return new BoardServiceImpl();
	}
	
}
