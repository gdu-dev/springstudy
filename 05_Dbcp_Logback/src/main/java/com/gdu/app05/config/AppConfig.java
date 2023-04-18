package com.gdu.app05.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gdu.app05.repository.BoardDAO;
import com.gdu.app05.service.BoardService;
import com.gdu.app05.service.BoardServiceImpl;

@Configuration
public class AppConfig {

	@Bean
	public BoardService boardService() {
		return new BoardServiceImpl();
	}
	
	@Bean
	public BoardDAO boardDAO() {
		return new BoardDAO();
	}
	
}
