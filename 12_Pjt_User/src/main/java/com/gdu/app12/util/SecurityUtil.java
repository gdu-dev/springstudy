package com.gdu.app12.util;

import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {

  // 크로스 사이트 스크립팅(Cross Site Scripting) 방지하기
  public String preventXSS(String str) {
    str = str.replace("<", "&lt;");
    str = str.replace(">", "&gt;");
    return str;
  }
  
}
