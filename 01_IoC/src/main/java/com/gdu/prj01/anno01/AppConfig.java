package com.gdu.prj01.anno01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  // IoC Container 에 bean 을 등록하는 클래스
public class AppConfig {

  /*
   * 반환타입 : bean 의 타입, <bean class="">
   * 메소드명 : bean 의 이름, <bean id="">
   */
  @Bean
  public Calculator calculator() {
    return new Calculator();
  }
  
  @Bean
  public Computer computer1() {
    Computer computer1 = new Computer();
    computer1.setModel("gram");
    computer1.setPrice(200);
    computer1.setCalculator(calculator());
    return computer1;
  }

  @Bean
  public Computer computer2() {
    return new Computer("macbook", 300, calculator());
  }
  
}
