package com.gdu.app08.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.gdu.app08.service.BoardService;

/*
	cron
	
	1. 특정 시간에 특정 프로그램을 주기적으로 수행하는 유닉스(Unix) 기반 프로그램이다.
	2. 리눅스에서는 crontab 명령으로 설정할 수 있다.
	3. 크론식
		1) 형식
			초  분  시  일  월  요일  [년도]
		2) 상세
			(1) 초 : 0 ~ 59
			(2) 분 : 0 ~ 59
			(3) 시 : 0 ~ 23
			(4) 일 : 1 ~ 31
			(5) 월 : 0 ~ 11 (또는 JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC)
			(6) 요일 : 1 ~ 7 (또는 MON, TUE, WED, THR, FRI, SAT, SUN)
			(7) 년도 : 생략
		3) 작성 규칙
			(1) *   : 매번
			(2) ?   : 설정 없음(일, 요일에서 사용)
			(3) A/B : 주기 (A부터 B마다 동작)
		4) 작성 예시
			(1) 매일 10초마다       : 0/10 * * * * ?
			(2) 매일 1분마다        : 0 0/1 * * * ?
			(3) 매일 20분, 50분마다 : 0 20,50 * * * ?
			(4) 매일 3시마다        : 0 0 0/3 * * ?
			(5) 월요일 12시마다     : 0 0 12 ? * 1   또는 0 0 12 ? * MON
			(6) 주말 12마다         : 0 0 12 ? * 6,7 또는 0 0 12 ? * SAT,SUN
	4. 크론식 작성 사이트
		http://www.cronmaker.com/
*/

public class BoardCountAlertScheduler {

	@Autowired
	private BoardService boardService;
	
	@Scheduled(cron="0 0/1 * * * ?")  // 크론식(1분마다)
	public void execute() {
		boardService.getBoardCount();
	}
	
}