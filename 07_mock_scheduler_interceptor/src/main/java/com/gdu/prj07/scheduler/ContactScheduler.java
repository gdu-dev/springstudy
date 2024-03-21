package com.gdu.prj07.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gdu.prj07.service.ContactService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ContactScheduler {

  private final ContactService contactService;
  
  @Scheduled(cron="0 40 11 * * *")
  public void execute() {
    log.info("{}개", contactService.getContactList().size());
  }
  
  /*
   * 크론식
   * 
   * 1. 형식
   *    초 분 시 일 월 요일
   * 
   * 2. 상세 형식
   *   1) 초   : 0 ~ 59
   *   2) 분   : 0 ~ 59
   *   3) 시   : 0 ~ 23
   *   4) 일   : 1 ~ 31
   *   5) 월   : 0 ~ 11 (JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC)
   *   6) 요일 : 0 ~ 7  (SUN, MON, TUE, WED, THR, FRI, SAT, SUN)
   * 
   * 3. 키워드
   *   1) ? : 설정 없음 (요일)
   *   2) * : 모든 (초 분 시 일 월)
   *   3) - : 범위 (ex 0-30)
   *   4) / : 주기 (ex 0/10)
   * 
   * 4. 예시
   *   1) 0 0 * * * *      : 매일 모든 시간마다
   *   2) 0 0 3 * * *      : 매일 3시마다
   *   3) 0 0 3-5 * * *    : 매일 3,4,5시마다
   *   4) 0 0 3,5 * * *    : 매일 3,5시마다
   *   5) 0 0/30 3-5 * * * : 매일 3:00/3:30/4:00/4:30/5:00/5:30마다
   *   6) 0 1/30 3-5 * * * : 매일 3:01/3:31/4:01/4:31/5:01/5:31마다
   */
  
}
