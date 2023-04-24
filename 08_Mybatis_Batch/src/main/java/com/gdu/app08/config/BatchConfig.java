package com.gdu.app08.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.gdu.app08.batch.BoardCountAlertScheduler;

@EnableScheduling  // @Scheduled를 허용한다.
@Configuration
public class BatchConfig {

	@Bean
	public BoardCountAlertScheduler boardCountAlertScheduler() {
		return new BoardCountAlertScheduler();
	}
	
}
