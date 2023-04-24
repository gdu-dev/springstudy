package com.gdu.app08.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.gdu.app08.service.BoardService;

public class BoardCountAlertScheduler {

	@Autowired
	private BoardService boardService;
	
	@Scheduled(cron="0 0/1 * * * *")
	public void execute() {
		boardService.getBoardCount();
	}
	
}