package com.gdu.prj04.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gdu.prj04.dto.BoardDto;

@Configuration
public class AppConfig {

  @Bean
  public BoardDto board1() {
    return new BoardDto(1, "제목1", "내용1");
  }
  
  @Bean
  public BoardDto board2() {
    return new BoardDto(2, "제목2", "내용2");
  }
  
  @Bean
  public BoardDto board3() {
    return new BoardDto(3, "제목3", "내용3");
  }
  
}
