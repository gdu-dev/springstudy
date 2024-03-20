package com.gdu.prj06.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.gdu.prj06.aspect.MyAroundAspect;

@EnableAspectJAutoProxy
@Configuration
public class AppConfig {

  @Bean
  public MyAroundAspect myAroundAspect() {
    return new MyAroundAspect();
  }
  
}
