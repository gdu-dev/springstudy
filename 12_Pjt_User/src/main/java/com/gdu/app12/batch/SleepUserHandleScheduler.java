package com.gdu.app12.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gdu.app12.service.UserService;

@Component
@EnableScheduling
public class SleepUserHandleScheduler {

  @Autowired
  private UserService userService;
  
  // 매일 새벽 1시
  @Scheduled(cron="0 0 1 1/1 * ?")
  public void execute() {
    userService.sleepUserHandle();
  }
  
}
